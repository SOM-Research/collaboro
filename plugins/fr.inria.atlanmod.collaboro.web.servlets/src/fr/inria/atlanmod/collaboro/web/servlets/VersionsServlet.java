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
import com.google.gson.JsonParser;

import fr.inria.atlanmod.collaboro.backend.CollaboroBackend;
import fr.inria.atlanmod.collaboro.backend.CollaboroBackendFactory;
import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Vote;


/**
 * Service to deserialize a .history model into json format
 * 
 */
@WebServlet(description = "Exposes the versions", urlPatterns = { "/version" })
public class VersionsServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 1L;

	private String createProposalsResponseJson(List<Proposal> proposals) {
		String proposalsJson="[";

		if(proposals.size() > 0) {
			for (Proposal proposal : proposals) {
				String proposalJson = createCollaborationJson(proposal);
				proposalsJson = proposalsJson.concat(proposalJson).concat(",");
			}
			proposalsJson=proposalsJson.substring(0,proposalsJson.length()-1);
		} else {	
			proposalsJson=proposalsJson.concat("{\"label\": \"No proposals yet\"}");
		}
		proposalsJson=proposalsJson.concat("]");

		return proposalsJson;
	}

	private String createCollaborationJson(Collaboration collaboration) {
		String collaborationJson = "";
		if(collaboration instanceof Comment) {
			collaborationJson = createCommentJson((Comment)collaboration);
		} else {
			collaborationJson = "{" + getCollaborationLabel(collaboration);

			//Proposals and Solutions have comments
			EList<Comment> collaborationComments = collaboration.getComments();
			boolean collaborationHasComments = collaborationComments != null && collaborationComments.size() > 0;
			if(collaborationHasComments) {
				collaborationJson = collaborationJson.concat(", \"children\": [");

				for (Comment collaborationComment : collaborationComments) {
					collaborationJson = collaborationJson.concat(createCollaborationJson(collaborationComment).concat(","));
				}

				collaborationJson = collaborationJson.substring(0, collaborationJson.length() - 1);

				if(collaboration instanceof Proposal) {
					Proposal proposal = (Proposal)collaboration;
					EList<Solution> proposalSols = proposal.getSols();

					//Continue the children of the proposal with the solutions
					collaborationJson = collaborationJson.concat(",");
					for (Solution collaborationSolution : proposalSols)	{
						collaborationJson=collaborationJson.concat(createCollaborationJson(collaborationSolution).concat(","));
					}
					collaborationJson = collaborationJson.substring(0,collaborationJson.length()-1);

				}
				collaborationJson = collaborationJson.concat("]");
			}
			//Test if is a proposal with no comments but with solutions
			else if(collaboration instanceof Proposal) {
				Proposal proposal = (Proposal)collaboration;
				EList<Solution> proposalSols = proposal.getSols();
				if(proposalSols != null && proposalSols.size() > 0)	{
					collaborationJson = collaborationJson.concat(", \"children\": [");
					for (Solution proposalSol : proposalSols) {
						collaborationJson=collaborationJson.concat(createCollaborationJson(proposalSol).concat(","));
					}
					collaborationJson = collaborationJson.substring(0,collaborationJson.length() - 1);
					collaborationJson = collaborationJson.concat("]");
				}			

			}
			//If the collaboration had no comments it was only created with it's label
			collaborationJson = collaborationJson.concat("}");
		}

		return collaborationJson;
	}


	private String getCollaborationLabel(Collaboration collaboration) {
		String collaborationJson = "";
		String typeOfCollaboration = collaboration.eClass().getName();
		String collaborationLabel = "\"label\": \"" + typeOfCollaboration + " " + collaboration.getId() + " from " + collaboration.getProposedBy().getFirstName() + " " + collaboration.getProposedBy().getLastName() + "\"";
		collaborationJson = collaborationJson.concat(collaborationLabel);

		if(collaboration.getRationale() != null && collaboration.getRationale().length() > 0) {
			collaborationJson = collaborationJson.concat(",");
			String cleanRationale = collaboration.getRationale().replaceAll("(\\r|\\t|\\n|\")", " ");
			//String collaborationData="\"data\": { \"username\":\""+ collaboration.getProposedBy().getId() +"\",\"description\": \""+cleanRationale+"\"";

			String collaborationType = "";
			String parentId = "";
			String actions = "";
			if(collaboration instanceof Solution) {
				collaborationType = "Solution";
				parentId = ((Solution)collaboration).getProposal().getId();
				actions = (((Solution) collaboration).getChangesText() != null) ? ((Solution) collaboration).getChangesText() : "";
			} else if(collaboration instanceof Comment) {
				collaborationType = "Comment";
				parentId = ((Comment)collaboration).getCommentedElement().getId();
			} else if(collaboration instanceof Proposal)
				collaborationType="Proposal";

			String collaborationData = "\"data\": { "
					+ "\"username\":\""	+ collaboration.getProposedBy().getId() + "\"," 
					+ "\"description\": \"" + cleanRationale + "\"," 
					+ ((collaboration instanceof Solution) ? "\"actions\" : \"" + actions.replaceAll("(\\r|\\t|\\n|\")", " ") + "\", ": "\"actions\" : \"\",") 
					+ "\"type\": \"" + collaborationType + "\"," 
					+ "\"collaboration_id\": \"" + collaboration.getId() + "\"";

			if(parentId != "")
				collaborationData = collaborationData + ",\"parent_id\": \"" + parentId + "\"";

			collaborationJson = collaborationJson.concat(collaborationData);

			EList<Vote> votes = collaboration.getVotes();
			String usersAgree = "";
			String usersDisagree = "";

			for (Vote vote : votes)	{
				User user = vote.getUser();

				String userName = "Vote with no user name";
				if(user!=null)
					userName = user.getId();

				if(vote.isAgreement())
					usersAgree = usersAgree.concat(userName).concat(", ");
				else
					usersDisagree = usersDisagree.concat(userName).concat(", ");	
			}

			if(usersAgree.length() > 0)
				usersAgree = usersAgree.substring(0, usersAgree.length() - 2);
			else
				usersAgree = "No users agree";

			if(usersDisagree.length() > 0)
				usersDisagree = usersDisagree.substring(0, usersDisagree.length() - 2);
			else
				usersDisagree = "No users disagree";

			collaborationJson = collaborationJson.concat(",\"agree\":\"" + usersAgree + "\"");
			collaborationJson = collaborationJson.concat(",\"disagree\":\"" + usersDisagree + "\"}");
		}
		return collaborationJson;
	}

	private String createCommentJson(Comment comment) {
		//String commentJson="{\"label\": \"Comment "+comment.getId()+" from "+comment.getProposedBy().getId()+"\"}";
		String commentJson = "{" + getCollaborationLabel(comment) + "}";
		return commentJson;
	}

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
				String proposalsJson = createProposalsResponseJson(proposals);

				System.out.println(proposalsJson);
				out.print(proposalsJson);  
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
				JsonArray array = (JsonArray)parser.parse(jb.toString()).getAsJsonObject().get("collaborations");
				for (JsonElement jsonElement : array) {
					JsonCollaborationSimplified jsonCollaboration = gson.fromJson(jsonElement, JsonCollaborationSimplified.class);

					String collaborationType = jsonCollaboration.getType();
					System.out.println("El tipo de la colaboracion: "+collaborationType);

					if(collaborationType.compareTo("Proposal") == 0) {
						CollaboroBackendFactory.getBackend(dsl).createProposalPlain(historyUser.getId(), jsonCollaboration.getRationale());
					} else if(collaborationType.compareTo("Comment") == 0) {
						CollaboroBackendFactory.getBackend(dsl).createCommentPlain(jsonCollaboration.getParent_id(), historyUser.getId(), jsonCollaboration.getRationale());
					} else if(collaborationType.compareTo("Solution") == 0) {
						CollaboroBackendFactory.getBackend(dsl).createSolutionPlain(jsonCollaboration.getParent_id(), historyUser.getId(), jsonCollaboration.getRationale(), jsonCollaboration.getActions());
					}
				}
				response.setContentType("application/json");
				
				//TODO Change the response to a success or failure alert
				out.print("{\"user\": { \"firstName\" : \"Juan\", \"lastName\" : \"Villa\", \"admin\" : false}}");
			}
		}
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
	}
}
