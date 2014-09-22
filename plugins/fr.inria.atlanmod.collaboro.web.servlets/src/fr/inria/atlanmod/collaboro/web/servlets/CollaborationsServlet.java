/*******************************************************************************
 * Copyright (c) 2014 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Juan David Villa Calle - (juan-david.villa_calle@inria.fr)
 *******************************************************************************/
package fr.inria.atlanmod.collaboro.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.emf.common.util.EList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackend;
import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Vote;


/**
 * Service to access to main collaboration activities
 */
@WebServlet(description = "Gives access to the collaboration", urlPatterns = { "/collaboration" })
public class CollaborationsServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addResponseOptions(response);

		HttpSession session = request.getSession();
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

				CollaboroBackend backend = CollaboroBackendFactory.getBackend(dsl);
				List<Proposal> proposals = backend.getProposals();
				JsonArray proposalsJson = toJson(proposals);
				System.out.println("--> " + proposalsJson.toString());
				out.print(proposalsJson.toString());  
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
		addResponseOptions(response);
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
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
				System.out.println(jb);

				// Parsing the parameters
				Gson gson = new Gson();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = (JsonObject) parser.parse(jb.toString()).getAsJsonObject();
				String action = jsonObject.get("action").getAsString();
				if(action.equals("save")) {
					JsonArray array = (JsonArray) jsonObject.get("collaborations");
					for (JsonElement jsonElement : array) {
						JsonCollaborationSimplified jsonCollaboration = gson.fromJson(jsonElement, JsonCollaborationSimplified.class);

						String collaborationType = jsonCollaboration.getType();
						if(collaborationType.compareTo("Proposal") == 0) {
							CollaboroBackendFactory.getBackend(dsl).createProposalPlain(historyUser.getId(), jsonCollaboration.getRationale(),jsonCollaboration.getReferredElements());
						} else if(collaborationType.compareTo("Comment") == 0) {
							CollaboroBackendFactory.getBackend(dsl).createCommentPlain(jsonCollaboration.getParent_id(), historyUser.getId(), jsonCollaboration.getRationale(),jsonCollaboration.getReferredElements());
						} else if(collaborationType.compareTo("Solution") == 0) {
							CollaboroBackendFactory.getBackend(dsl).createSolutionPlain(jsonCollaboration.getParent_id(), historyUser.getId(), jsonCollaboration.getRationale(), jsonCollaboration.getActions(),jsonCollaboration.getReferredElements());
						}
					}
					response.setContentType("application/json");
					out.print("{\"result\": \"success\" }");
				} else if (action.equals("delete")) {
					JsonElement jsonElementCollaboration = jsonObject.get("collaboration").getAsJsonObject().get("data");
					JsonCollaborationSimplified jsonCollaboration = gson.fromJson(jsonElementCollaboration, JsonCollaborationSimplified.class);
					CollaboroBackendFactory.getBackend(dsl).deleteCollaborationPlain(jsonCollaboration.getId());
					response.setContentType("application/json");
					out.print("{\"result\": \"success\" }");
				} else if (action.equals("vote")) {

					JsonElement jsonElementCollaboration = jsonObject.get("collaboration").getAsJsonObject().get("data");
					JsonCollaborationSimplified jsonCollaboration = gson.fromJson(jsonElementCollaboration, JsonCollaborationSimplified.class);

					String vote = jsonObject.get("data").getAsJsonObject().get("vote").getAsString();
					if(vote != null) {
						if(vote.equals("yes")) {
							CollaboroBackendFactory.getBackend(dsl).createVotePlain(jsonCollaboration.getId(), historyUser.getId(), true);
						} else if (vote.equals("no")) {
							CollaboroBackendFactory.getBackend(dsl).createVotePlain(jsonCollaboration.getId(), historyUser.getId(), false);
						}
						response.setContentType("application/json");
						Collaboration collaboration = CollaboroBackendFactory.getBackend(dsl).locateCollaborationById(null, jsonCollaboration.getId());
					}
				}
			}
		}
	}

	private JsonArray toJson(List<Proposal> proposals) {
		JsonArray result = new JsonArray();

		for(Proposal proposal : proposals) {
			JsonObject proposalJSON = toJson(proposal);
			result.add(proposalJSON);
		}

		return result;
	}

	private JsonObject toJson(Proposal proposal) {
		JsonObject result = new JsonObject();

		String label = generateLabel(proposal);
		result.addProperty("label", label);

		JsonObject data = generateData(proposal);
		result.add("data", data);

		JsonArray children = new JsonArray();
		for(Comment comment : proposal.getComments()) {
			JsonObject commentJSON = toJson(comment);
			children.add(commentJSON);
		}

		for(Solution solution : proposal.getSols()) {
			JsonObject solutionJSON = toJson(solution);
			children.add(solutionJSON);
		}

		result.add("children", children);

		return result;
	}

	private JsonObject toJson(Comment comment) {
		JsonObject result = new JsonObject();

		String label = generateLabel(comment);
		result.addProperty("label", label);

		JsonObject data = generateData(comment);
		result.add("data", data);

		JsonArray children = new JsonArray();
		for(Comment comment2 : comment.getComments()) {
			JsonObject commentJSON = toJson(comment2);
			children.add(commentJSON);
		} 

		return result;
	}

	private JsonObject toJson(Solution solution) {
		JsonObject result = new JsonObject();

		String label = generateLabel(solution);
		result.addProperty("label", label);

		JsonObject data = generateData(solution);
		result.add("data", data);

		JsonArray children = new JsonArray(); 
		for(Comment comment2 : solution.getComments()) {
			JsonObject commentJSON = toJson(comment2);
			children.add(commentJSON);
		} 
		result.add("children", children);

		return result;
	}

	private String generateLabel(Collaboration collaboration) {
		String typeOfCollaboration = collaboration.eClass().getName();
		String author = (collaboration.getProposedBy().getFirstName() == null || collaboration.getProposedBy().getLastName() == null) ? collaboration.getProposedBy().getId() : collaboration.getProposedBy().getFirstName() + " " + collaboration.getProposedBy().getLastName();
		String status = (collaboration instanceof Proposal) ? ((((Proposal) collaboration).isAccepted()) ? "[Accepted]" : "[Not accepted]") : "";
		String collaborationLabel = typeOfCollaboration + " from " + author + " " + status;
		return collaborationLabel;
	}

	private JsonObject generateData(Collaboration collaboration) {
		// Cleaning rationale
		String cleanRationale = collaboration.getRationale().replaceAll("(\\r|\\t|\\n|\")", " ");

		// Digesting the referredElements (covnerting to String[])
		JsonArray referredElementsArray = new JsonArray();
		if(collaboration.getReferredElements().length() > 0) {
			String[] referredElements = collaboration.getReferredElements().split(",");
			for(String referredElement : referredElements) {
				referredElementsArray.add(new JsonPrimitive(referredElement));
			} 
		} 
		
		// Digesting votes
		JsonArray usersAgree = new JsonArray();
		JsonArray usersDisagree = new JsonArray();
		for (Vote vote : collaboration.getVotes())	{
			if(vote.isAgreement())
				usersAgree.add(new JsonPrimitive(vote.getUser().getId()));
			else
				usersDisagree.add(new JsonPrimitive(vote.getUser().getId()));
		}

		// Building response JSON object
		JsonObject result = new JsonObject();
		result.addProperty("id", collaboration.getId());
		result.addProperty("username", collaboration.getProposedBy().getId());
		result.addProperty("description", cleanRationale);
		result.add("referredElements", referredElementsArray);
		result.addProperty("type", collaboration.eClass().getName());
		result.addProperty("collaboration_id", collaboration.getId());
		result.add("agree", usersAgree);
		result.add("disagree", usersDisagree);

		return result;
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
	}
}
