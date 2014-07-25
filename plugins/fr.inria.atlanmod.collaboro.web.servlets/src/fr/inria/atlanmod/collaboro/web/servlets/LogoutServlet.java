/*******************************************************************************
 * Copyright (c) 2014 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Juan David Villa Calle - (juan-david.villa_calle@inria.fr)
 * Javier Canovas (me@jlcanovas.es)
 *******************************************************************************/

package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "Exposes the logout service", urlPatterns = { "/logout" })
public class LogoutServlet extends AbstractSecurityServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);
				 
		// Invalidate the session if exists
        HttpSession session = request.getSession(false);
        if(session != null) 
            session.invalidate();
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
		super.doOptions(request, response);
	}
}
