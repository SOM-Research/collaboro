/*******************************************************************************
 * Copyright (c) 2014 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Juan David Villa Calle - (juan-david.villa_calle@inria.fr)
 *******************************************************************************/
package fr.inria.atlanmod.collaboro.web.servlets;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.processor.core.StandaloneProcessor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import sun.misc.BASE64Encoder;
import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.history.User;

/**
 * Service to render an EMF model as as jpg in base64
 * 
 * @author Juan David Villa Calle (juan-david.villa_calle@inria.fr)
 *
 */
@WebServlet("/renderMetamodel")
public class MetamodelRendererServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 1L;

	// The main path to the working dir (needed for generating the pictures)
	public static File workingDir = null;

	// The path to the Graphviz DOT execitable (needed for generating the pictures)
	public static String dotExePath = null;


	@Override
	public void init() throws ServletException {
		super.init();
		String workingDirString = properties.getProperty("workingDir");
		dotExePath = properties.getProperty("dotExePath");

		// We need a File (not a String)
		workingDir = new File(workingDirString);
		if(!workingDir.isDirectory()) throw new ServletException("The working dir does not exist");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		if(session == null) 
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		else
		{
			User historyUser = (User) session.getAttribute("user");
			String dsl = (String) session.getAttribute("dsl");
			if(historyUser == null || dsl == null)
			{
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			else
			{
				int numOfAbsModelImages=CollaboroBackendFactory.getBackend(dsl).getNumOfAbsModelImages();
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);

		HttpSession session = request.getSession(false);
		if(session == null) 
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		else {
			User historyUser = (User) session.getAttribute("user");
			String dsl = (String) session.getAttribute("dsl");
			if(historyUser == null || dsl == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			} else {
				// Getting the parameters from the request
				StringBuffer jb = new StringBuffer();
				String line = null;
				try {
					BufferedReader reader = request.getReader();
					while ((line = reader.readLine()) != null)
						jb.append(line);
				} catch (Exception e) {
					throw new ServletException("There was an error reading the parameters");
				}
				
				JsonParser parser = new JsonParser();
				JsonObject actionJsonObject = (JsonObject) parser.parse(jb.toString());
				EPackage metamodelPackage = CollaboroBackendFactory.getBackend(dsl).getEcoreModel(actionJsonObject.get("numImage").getAsInt());

				// Drawing the metamodel
				List<EObject> toDraw= new ArrayList<EObject>();
				toDraw.add(metamodelPackage);

				File resultPath = drawModel(toDraw);
				String resultImage;
				try{
					resultImage = encodeToString(resultPath);
				}
				catch (IOException e) {
					throw new ServletException("Not possible to encode");
				}

				PrintWriter out = response.getWriter();
				out.print(resultImage);
			}
		}

	}

	private File drawModel(List<EObject> elements) throws ServletException {
		EcorePackage.eINSTANCE.eClass();
		GraphdescPackage.eINSTANCE.eClass();
		File uniqueWorkingDir = new File(workingDir.getAbsolutePath());

		File resultPath;
		try {
			resultPath = File.createTempFile("temp", ".jpg", uniqueWorkingDir);
		} catch (IOException e1) {
			throw new ServletException("Not possible to access to temp dir");
		}

		try {
			StandaloneProcessor.process(elements, null, uniqueWorkingDir, resultPath.getAbsolutePath(), null, null, dotExePath, true, false, "UTF-8", null, null, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return resultPath;
	}


	/**
	 * Encodes a JPG picture into the BASE64 format
	 * 
	 * @param imagePath
	 * @return
	 * @throws IOException
	 */
	private String encodeToString(File imagePath) throws IOException {
		BufferedImage image = ImageIO.read(imagePath);

		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, "JPG", bos);
			byte[] imageBytes = bos.toByteArray();

			BASE64Encoder encoder = new BASE64Encoder();
			imageString = encoder.encode(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageString;
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
	}

}
