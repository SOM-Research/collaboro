package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.history.User;

@WebServlet(description = "Gives access to the decision engine in collaboro", urlPatterns = { "/decision" })
public class DecisionEngineServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 99L;
	
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
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				
				CollaboroBackendFactory.getBackend(dsl).launchDecision();
				
				out.print("{\"result\": \"success\" }");
			}
		}
	}
	
	@Override
	protected void doOptions(HttpServletRequest arg0, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);
	}

}
