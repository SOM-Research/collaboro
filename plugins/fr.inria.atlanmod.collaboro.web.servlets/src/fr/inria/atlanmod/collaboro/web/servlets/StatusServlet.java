package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;

@WebServlet(description = "Provides some status info", urlPatterns = { "/status" })
public class StatusServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 6699L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		addResponseOptions(resp);
		
		PrintWriter out = resp.getWriter();
		out.println("CORS allow-origin: " + serverURL);
		
		String[] activeLanguages = CollaboroBackendFactory.getActiveLanguages();
		String languages = "";
		for(String language : activeLanguages)
			languages = languages + " " + language;
		
		out.println("Active languages: " + languages);
	}

}
