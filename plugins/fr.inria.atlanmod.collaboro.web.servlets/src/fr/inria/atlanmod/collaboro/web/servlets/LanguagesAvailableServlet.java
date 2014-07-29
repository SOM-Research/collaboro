package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;

@WebServlet(description = "Exposes the available langues to collaborate", urlPatterns = { "/languages" })
public class LanguagesAvailableServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 82L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		JsonArray languages = new JsonArray();
		for(String languageName : CollaboroBackendFactory.getActiveLanguages()) {
			JsonElement languageElement = new JsonPrimitive(languageName);
			languages.add(languageElement);
		}
		
		JsonObject jsonResponse = new JsonObject();
		jsonResponse.add("languages", languages);
		
		out.print(jsonResponse.toString());
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		addResponseOptions(response);
	}

}
