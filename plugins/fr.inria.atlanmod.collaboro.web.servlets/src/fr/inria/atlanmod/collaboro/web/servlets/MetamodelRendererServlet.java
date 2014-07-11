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
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.processor.core.StandaloneProcessor;

import sun.misc.BASE64Encoder;

/**
 * Service to render an EMF model as as jpg in base64
 * 
 * @author Juan David Villa Calle (juan-david.villa_calle@inria.fr)
 *
 */
@WebServlet("/renderMetamodel")
public class MetamodelRendererServlet extends HttpServlet
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// The main path to the working dir (needed for generating the pictures)
	public static File workingDir = null;
	
	// The path to the Graphviz DOT execitable (needed for generating the pictures)
	public static String dotExePath = null;
	
	Properties properties = null;
	
	@Override
	public void init() throws ServletException
	{
		super.init();
		
		String workingDirString = null;
		properties = new Properties();
		
		try
		{
			properties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));
			workingDirString = properties.getProperty("workingDir");
			dotExePath = properties.getProperty("dotExePath");
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// We need a File (not a String)
		workingDir = new File(workingDirString);
		if(!workingDir.isDirectory()) throw new ServletException("The working dir does not exist");
		
	}


	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8001");
	    response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
	    response.addHeader("Access-Control-Allow-Credentials", "true");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e)
		  {
			  
		  }
		
		//TODO	 Obtain from the request the name of the metamodel to render
		//String resultImage = discoverMetamodelBase64(metamodelName);
		String resultImage = discoverMetamodelBase64("ModiscoWorkflow.ecore");
		PrintWriter out = response.getWriter();
        out.print(resultImage);
	}
	
	
	private String discoverMetamodelBase64(String metamodelName) throws ServletException 
	{
		EPackage metamodelPackage=getMetamodel(metamodelName);
		
		
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
		
		return resultImage;
		
		
	}
	
	private EPackage getMetamodel(String metamodelName)
	{
		
		//Load a metamodel
		URI uriHistoryModel=URI.createFileURI(getServletContext().getRealPath("/WEB-INF/model/"+metamodelName));
				
				
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
				
		ResourceSet rs = new ResourceSetImpl();
				
		Resource r = rs.getResource(uriHistoryModel, true);
		EObject eObject = r.getContents().get(0);
		EPackage p=null;
		if (eObject instanceof EPackage)
		{
			p = (EPackage)eObject;	   
		}
		
		return p;
	}
	
	private File drawModel(List<EObject> elements) throws ServletException
	{
		
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
			// TODO Auto-generated catch block
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
	private String encodeToString(File imagePath) throws IOException
	{
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
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8001");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		super.doOptions(request, response);
		
	}

}