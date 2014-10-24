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
public class CurrentUserServlet extends AbstractSecurityServlet {
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

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		JsonObject jsonResponse = buildJsonUserResponse(historyUser, dsl);
		out.print(jsonResponse.toString());
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
		super.doOptions(request, response);
	}

}
