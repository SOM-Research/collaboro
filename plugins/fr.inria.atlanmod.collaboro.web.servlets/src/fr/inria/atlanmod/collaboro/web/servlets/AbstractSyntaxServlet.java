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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.emftools.emf2gv.graphdesc.GraphdescPackage;
import org.emftools.emf2gv.processor.core.StandaloneProcessor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.history.User;

/**
 * Service to render an EMF model as as jpg in base64
 * 
 */
@WebServlet(description = "Provides access to the abstract syntax", urlPatterns = { "/abstractSyntax" } )
public class AbstractSyntaxServlet extends AbstractRendererServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);

		// Checking the user is logged
		if(!isLogged(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		HttpSession session = request.getSession(false);
		User historyUser = (User) session.getAttribute("user");
		String dsl = (String) session.getAttribute("dsl");

		int numOfAbsModelImages=CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).getNumOfAbsModelImages();
		JsonObject responseObject = new JsonObject();
		responseObject.addProperty("numImages", numOfAbsModelImages);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(responseObject.toString()); 
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);

		// Checking the user is logged
		if(!isLogged(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		HttpSession session = request.getSession(false);
		User historyUser = (User) session.getAttribute("user");
		String dsl = (String) session.getAttribute("dsl");

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
		int num = actionJsonObject.get("numImage").getAsInt();
		File pictureFile = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).getEcoreModel(num);

		if(pictureFile == null) 
			pictureFile = new File(workingDir.getAbsolutePath() + "/error.jpg");

		String resultImage;
		try {
			resultImage = encodeToString(pictureFile);
		} catch (IOException e) {
			throw new ServletException("Not possible to encode");
		}

		PrintWriter out = response.getWriter();
		out.print(resultImage);
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
	}

}
