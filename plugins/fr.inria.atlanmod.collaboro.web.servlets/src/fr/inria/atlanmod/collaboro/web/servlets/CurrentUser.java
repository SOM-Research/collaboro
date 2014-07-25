package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import fr.inria.atlanmod.collaboro.history.User;

@WebServlet(description = "Exposes the get current user in the session (if exists)", urlPatterns = { "/currentUser" })
public class CurrentUser extends AbstractSecurityServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

				JsonObject jsonResponse = buildJsonUserResponse(historyUser, dsl);
				out.print(jsonResponse.toString());
			}
		}
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
		super.doOptions(request, response);
	}

}
