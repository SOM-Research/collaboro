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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

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
	     
	     StringBuffer jb = new StringBuffer();
		 String line = null;
		 try
		 {
		    BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			      jb.append(line);
		 } 
		 catch (Exception e)
		 {
				  
		 }
			
		 System.out.println(jb);
		 Gson gson = new Gson();
		
		JsonUser user=gson.fromJson(jb.toString(), JsonUser.class);   
	    //System.out.println(user.getEmail());
	    //System.out.println(user.getPassword());
	    //TODO Authenticate the user using the email and password parameters from the request
	     
		HttpSession session = request.getSession();
		session.setAttribute("user", "Juan");
		session.setMaxInactiveInterval(30*60);
		Cookie userName = new Cookie("user","admin");
		
		//setting cookie to expiry in 30 mins
        userName.setMaxAge(30*60);
        response.addCookie(userName);
        System.out.println("Se crea una cookie llamada: "+userName.getName());
		response.setContentType("application/json");
	     PrintWriter out = response.getWriter();
	     out.print("{\"user\": { \"firstName\" : \"Juan\", \"lastName\" : \"Villa\", \"admin\" : false}}");
	     
	}
	
	

}
