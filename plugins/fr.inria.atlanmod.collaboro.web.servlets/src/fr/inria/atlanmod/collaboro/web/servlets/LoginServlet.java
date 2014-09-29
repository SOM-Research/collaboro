package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackend;
import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.history.User;

@WebServlet(description = "Exposes the login service", urlPatterns = { "/login" })
public class LoginServlet extends AbstractSecurityServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);

		// Getting the parameter (in JSON)
		StringBuffer jb = new StringBuffer();
		String line = null;
		try	{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e)	{
			throw new ServletException("There are no parameters in the login");
		}

		// Getting the JSON object 
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(jb.toString()).getAsJsonObject();
		String email = jsonObject.get("email").getAsString();
		String password = jsonObject.get("password").getAsString();
		String dsl = jsonObject.get("dsl").getAsString();
		
		// Accesing the backend to validate the user
		CollaboroBackend backend = CollaboroBackendFactory.getBackend(dsl);
		User historyUser = backend.loginUser(email, password, dsl);  
		if(historyUser != null) {
			String userId = historyUser.getId();

			// Setting session and cookies
			HttpSession session = request.getSession();
			session.setAttribute("user", historyUser);
			session.setAttribute("dsl", dsl);
			session.setMaxInactiveInterval(30*60);

			Cookie userName = new Cookie("collaboro_user", userId);
			userName.setMaxAge(30*60); // setting cookie to expiry in 30 mins

			response.addCookie(userName);
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			
			JsonObject jsonResponse = buildJsonUserResponse(historyUser, dsl);
			out.print(jsonResponse.toString());
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);
		super.doOptions(request, response);
	}
}
