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
