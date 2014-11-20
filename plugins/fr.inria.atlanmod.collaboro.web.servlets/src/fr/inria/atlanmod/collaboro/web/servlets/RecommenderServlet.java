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
import fr.inria.atlanmod.collaboro.backend.CollaboroRecommenderBackend;

@WebServlet(description = "Provides access to the recommender engine", urlPatterns = { "/recommender" })
public class RecommenderServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 381L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);

		// Checking the user is logged
		if(!isLogged(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		HttpSession session = request.getSession(false);
		String dsl = (String) session.getAttribute("dsl");

		CollaboroRecommenderBackend recommender = CollaboroBackendFactory.getRecommenderBackend(dsl);
		recommender.launchRecommender();
		recommender.applyRecommendations();
		
		JsonObject responseObject = new JsonObject();
		responseObject.addProperty("status", "ok");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(responseObject.toString()); 
	}

}
