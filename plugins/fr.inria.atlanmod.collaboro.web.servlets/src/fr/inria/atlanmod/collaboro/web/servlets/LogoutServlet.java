package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "Exposes the logout service", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8001");
	    response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
	    response.addHeader("Access-Control-Allow-Credentials", "true");
		System.out.println("Llega al post del servlet de logout");
		Cookie loginCookie=null;
		Cookie [] cookies = request.getCookies();
		
		
		if(cookies!=null)
		{
			System.out.println("El numero de cookies: "+cookies.length);
			for(Cookie cookie: cookies)
			{
				System.out.println("Hay al menos una cookie");
				if(cookie.getName().equals("JSESSIONID"))
				{
					System.out.println("JSESSIONID="+cookie.getValue());
	                break;
				}
			}
		}
		 //invalidate the session if exists
        HttpSession session = request.getSession(false);
        //System.out.println("User="+session.getAttribute("user"));
        if(session != null){
        	System.out.println("There is a session when trying to logout");
            session.invalidate();
        }
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		System.out.println("Llega al options del servlet de logout");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		super.doOptions(request, response);
	}
	

}
