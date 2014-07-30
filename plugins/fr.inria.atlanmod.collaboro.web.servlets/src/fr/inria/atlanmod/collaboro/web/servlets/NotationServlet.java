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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
 * @author Juan David Villa Calle (juan-david.villa_calle@inria.fr)
 *
 */
@WebServlet(description = "Exposes the notations", urlPatterns = { "/notations" })
public class NotationServlet extends AbstractCollaboroServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		addResponseOptions(response);
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
				int numOfNotModelImages=CollaboroBackendFactory.getBackend(dsl).getNumOfNotModelImages();
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.print("{\"numImages\": "+numOfNotModelImages +"}"); 
			}

		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		addResponseOptions(response);
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
				int numImage = actionJsonObject.get("numImage").getAsInt();
				File notationModelImage=null;
				
			}
		}
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		addResponseOptions(response);
		super.doOptions(request, response);
	
		
	}
	

}
