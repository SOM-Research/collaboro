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

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple login service
 * 
 * @author Juan David Villa Calle (juan-david.villa_calle@inria.fr)
 *
 */
@WebServlet(description = "Exposes the login service", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	
	public LoginServlet()
	{
		super();
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		System.out.println("Llega");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		super.doOptions(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 response.setHeader("Access-Control-Allow-Origin", "http://localhost:8001");
	     response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
	     //TODO Authenticate the user using the email and password parameters from the request
	     response.setContentType("application/json");
	     PrintWriter out = response.getWriter();
	     out.print("{\"user\": { \"firstName\" : \"Juan\", \"lastName\" : \"Villa\", \"admin\" : false}}");
	     
	}
	
	

}
