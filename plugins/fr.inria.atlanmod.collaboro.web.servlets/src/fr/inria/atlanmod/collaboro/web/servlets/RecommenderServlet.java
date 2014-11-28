package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.backend.CollaboroRecommenderBackend;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.metrics.Metric;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;

@WebServlet(description = "Provides access to the recommender engine", urlPatterns = { "/recommender" })
public class RecommenderServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 381L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);

		// Checking the user is logged
		if(!isLogged(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		HttpSession session = request.getSession(false);
		String dsl = (String) session.getAttribute("dsl");
		
		CollaboroRecommenderBackend recommenderBackend = CollaboroBackendFactory.getRecommenderBackend(dsl);
		recommenderBackend.launchRecommender();
		
		HashMap<Metric, List<MetricResult>> results = recommenderBackend.getResults();
		MetricResultStatus finalStatus = MetricResultStatus.GOOD;
		int problemCounter = 0;
		for(Metric metric : results.keySet()) {
			System.out.println("--> " + metric.getName());
			List<MetricResult> metricResults = results.get(metric);
			for(MetricResult metricResult : metricResults) {
				if(metricResult.getStatus() == MetricResultStatus.MIDDLE && finalStatus == MetricResultStatus.GOOD)
					finalStatus = MetricResultStatus.MIDDLE;
				else if(metricResult.getStatus() == MetricResultStatus.BAD && finalStatus == MetricResultStatus.GOOD)
					finalStatus = MetricResultStatus.BAD;
				else if(metricResult.getStatus() == MetricResultStatus.BAD && finalStatus == MetricResultStatus.MIDDLE)
					finalStatus = MetricResultStatus.BAD;
				
				if(metricResult.getStatus() == MetricResultStatus.MIDDLE || metricResult.getStatus() == MetricResultStatus.BAD)
					problemCounter++;
			}
		}
		
		JsonObject responseObject = new JsonObject();
		
		if(finalStatus == MetricResultStatus.GOOD)
			responseObject.addProperty("status", "success");
		else if(finalStatus == MetricResultStatus.MIDDLE)
			responseObject.addProperty("status", "warning");
		else if(finalStatus == MetricResultStatus.BAD)
			responseObject.addProperty("status", "danger");
		

		if(finalStatus == MetricResultStatus.GOOD)
			responseObject.addProperty("message", "Everything looks fine!");
		else if(finalStatus == MetricResultStatus.MIDDLE || finalStatus == MetricResultStatus.BAD)
			responseObject.addProperty("message", problemCounter + " issue" + ((problemCounter > 1) ? "s were" : " was") +" detected");
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(responseObject.toString()); 
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);

		// Checking the user is logged
		if(!isLogged(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		HttpSession session = request.getSession(false);
		User historyUser = (User) session.getAttribute("user");
		String dsl = (String) session.getAttribute("dsl");
		CollaboroRecommenderBackend recommender = CollaboroBackendFactory.getRecommenderBackend(dsl);

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
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(jb.toString()).getAsJsonObject();

		String action = jsonObject.get("action").getAsString();
		JsonObject responseObject = new JsonObject();
		if(action.equals("launch")) {
			recommender.applyRecommendations();
			responseObject.addProperty("status", "ok");
			response.setContentType("application/json");
		} else if(action.equals("list")) {
			
		}

		PrintWriter out = response.getWriter();
		out.print(responseObject.toString()); 
	}
	

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
		super.doOptions(request, response);
	}

}
