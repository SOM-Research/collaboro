package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CurrentUser
 */
@WebServlet(description = "Exposes the get current user service", urlPatterns = { "/currentUser" })
public class CurrentUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrentUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8001");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		System.out.println("Accesed the doGet of the currentuserServlet");
		if(request.getSession(false)==null)
		{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		else
		{
			//TODO Send the correct answer
			response.setContentType("application/json");
		    PrintWriter out = response.getWriter();
		    out.print("{\"user\": { \"firstName\" : \"Juan\", \"lastName\" : \"Villa\", \"admin\" : false}}");
		    System.out.println("Does it try to request the user again?");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		//System.out.println("Llega al options del servlet de logout");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8001");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		super.doOptions(request, response);
	}

}
