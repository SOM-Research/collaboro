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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.history.User;

/**
 * Service to render the notation images
 * 
 */
@WebServlet(description = "Provides access to the concrete syntax", urlPatterns = { "/concreteSyntax" })
public class ConcreteSyntaxServlet extends AbstractRendererServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);

		// Checking the user is logged
		if(!isLogged(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		HttpSession session = request.getSession(false);
		User historyUser = (User) session.getAttribute("user");
		String dsl = (String) session.getAttribute("dsl");

		int numOfNotModelImages = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).getNumOfNotModelImages();
		JsonObject responseObject = new JsonObject();
		responseObject.addProperty("numImages", numOfNotModelImages);

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
		File pictureFile = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).getModel(num);

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
		super.doOptions(request, response);
	}
}
