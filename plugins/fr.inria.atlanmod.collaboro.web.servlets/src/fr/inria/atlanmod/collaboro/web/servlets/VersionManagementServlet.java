package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.history.User;

@WebServlet(description = "Exposes the current version being used", urlPatterns = { "/versionManagement" })
public class VersionManagementServlet extends AbstractCollaboroServlet {

	private static final long serialVersionUID = 52L;

	@Override
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

				JsonObject jsonResponse = new JsonObject();
				String version = String.valueOf(CollaboroBackendFactory.getBackend(dsl).getVersionTracked());
				jsonResponse.addProperty("version", version);
				
				out.print(jsonResponse.toString());
			}
		}
	}
	
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

				// Parsing the parameters
				Gson gson = new Gson();
				JsonParser parser = new JsonParser();
				JsonObject actionJsonObject = (JsonObject) parser.parse(jb.toString());
				String action = actionJsonObject.get("action").getAsString();
				
				if(action.equals("next")) {
					CollaboroBackendFactory.getBackend(dsl).nextVersion();
				} else if (action.equals("previous")) {
					CollaboroBackendFactory.getBackend(dsl).previousVersion();
				} else if (action.equals("create")) {
					CollaboroBackendFactory.getBackend(dsl).createVersion();
				}
				
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();

				JsonObject jsonResponse = new JsonObject();
				String version = String.valueOf(CollaboroBackendFactory.getBackend(dsl).getVersionTracked());
				jsonResponse.addProperty("version", version);
				
				out.print(jsonResponse.toString());
			}
		}
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
	}

}
