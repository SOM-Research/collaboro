package fr.inria.atlanmod.collaboro.web.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 * This class contains common features for all the servlets
 */
public class AbstractCollaboroServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	
	/**
	 * Builds the response options to deal with CORS
	 * 
	 * @param response
	 */
	protected void addResponseOptions(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8001");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Allow-Credentials", "true");
	}
}
