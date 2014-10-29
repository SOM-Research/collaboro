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

		// Checking the user is logged
		if(!isLogged(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		HttpSession session = request.getSession(false);
		User historyUser = (User) session.getAttribute("user");
		String dsl = (String) session.getAttribute("dsl");

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		CollaboroBackend backend = CollaboroBackendFactory.getBackend(dsl, historyUser.getId());
		List<Proposal> proposals = backend.getProposals();
		JsonArray proposalsJson = toJson(proposals);
		out.print(proposalsJson.toString());  
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
		addResponseOptions(response);
		PrintWriter out = response.getWriter();

		// Checking the user is logged
		if(!isLogged(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		HttpSession session = request.getSession(false);
		User historyUser = (User) session.getAttribute("user");
		String dsl = (String) session.getAttribute("dsl");

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
		if(action.equals("save")) {
			JsonObject data = jsonObject.get("collaboration").getAsJsonObject();

			String type = data.get("type").getAsString();
			String rationale = "";
			if(data.has("rationale"))
				rationale = data.get("rationale").getAsString();
			String referredElements = "";
			if(data.has("referredElements")) {
				JsonArray referredElementsArray = data.get("referredElements").getAsJsonArray();
				for(JsonElement referredElement : referredElementsArray) {
					if (referredElement instanceof JsonPrimitive) {
						JsonPrimitive jsonPrimitive = (JsonPrimitive) referredElement;
						referredElements += jsonPrimitive.getAsString() + ",";
					}
				}
				if(referredElementsArray.size() > 0)
					referredElements = referredElements.substring(0, referredElements.length()-1);
			}
			String parentId = "";
			if(data.has("parent_id"))
				parentId = data.get("parent_id").getAsString();

			String collaborationId = null;
			if(type.equals("Proposal")) {
				collaborationId = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).createProposalPlain(historyUser.getId(), rationale, referredElements);
			} else if(type.equals("Comment")) {
				collaborationId = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).createCommentPlain(parentId, historyUser.getId(), rationale, referredElements);
			} else if(type.equals("Solution")) {
				collaborationId = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).createSolutionPlain(parentId, historyUser.getId(), rationale, "", referredElements);
			}

			if(collaborationId != null) {
				response.setContentType("application/json");
				Collaboration collaboration = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).locateCollaborationById(null, collaborationId);
				JsonObject collaborationJSON = toJson(collaboration);
				out.print(collaborationJSON);
			} else {
				throw new ServletException("Problem saving the collaboration");
			}
		} else if (action.equals("edit")) { 
			JsonObject data = jsonObject.get("collaboration").getAsJsonObject();

			String id = data.get("id").getAsString();
			String rationale = "";
			if(data.has("rationale"))
				rationale = data.get("rationale").getAsString();
			String referredElements = "";
			if(data.has("referredElements")){
				JsonArray referredElementsArray = data.get("referredElements").getAsJsonArray();
				for(JsonElement referredElement : referredElementsArray) {
					if (referredElement instanceof JsonPrimitive) {
						JsonPrimitive jsonPrimitive = (JsonPrimitive) referredElement;
						referredElements += jsonPrimitive.getAsString() + ",";
					}
				}
				if(referredElementsArray.size() > 0)
					referredElements = referredElements.substring(0, referredElements.length()-1);
			}
			String parentId = "";
			if(data.has("parent_id") && !data.get("parent_id").equals(""))
				parentId = data.get("parent_id").getAsString();

			String collaborationId = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).editCollaborationPlain(parentId, id, historyUser.getId(), rationale, referredElements);

			if(collaborationId != null) {
				response.setContentType("application/json");
				Collaboration collaboration = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).locateCollaborationById(null, collaborationId);
				JsonObject collaborationJSON = toJson(collaboration);
				out.print(collaborationJSON);
			} else {
				throw new ServletException("Problem editing the collaboration");
			}
		} else if (action.equals("delete")) {
			JsonObject data = jsonObject.get("collaboration").getAsJsonObject().get("data").getAsJsonObject();
			String collaborationId = data.get("id").getAsString();
			CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).deleteCollaborationPlain(collaborationId);
			response.setContentType("application/json");
			out.print("{\"result\": \"success\" }");
		} else if (action.equals("vote")) {
			JsonObject data = jsonObject.get("collaboration").getAsJsonObject().get("data").getAsJsonObject();
			String collaborationId = data.get("id").getAsString();

			String vote = jsonObject.get("data").getAsJsonObject().get("vote").getAsString();
			if(vote != null) {
				if(vote.equals("yes")) {
					CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).createVotePlain(collaborationId, historyUser.getId(), true);
				} else if (vote.equals("no")) {
					CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).createVotePlain(collaborationId, historyUser.getId(), false);
				}

				response.setContentType("application/json");
				Collaboration collaboration = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).locateCollaborationById(null, collaborationId);
				JsonObject collaborationJSON = toJson(collaboration);
				out.print(collaborationJSON);
			}
		} else if (action.equals("disagreementComment")) {
			JsonObject data = jsonObject.get("collaboration").getAsJsonObject().get("data").getAsJsonObject();
			String collaborationId = data.get("id").getAsString();

			String comment = jsonObject.get("data").getAsJsonObject().get("comment").getAsString();

			if(comment != null) {
				String commentId = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).createDisagreementVote(collaborationId, historyUser.getId(), comment);

				response.setContentType("application/json");
				Collaboration collaboration = CollaboroBackendFactory.getBackend(dsl, historyUser.getId()).locateCollaborationById(null, commentId);
				JsonObject collaborationJSON = toJson(collaboration);
				out.print(collaborationJSON);
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

	private JsonObject toJson(Collaboration collaboration) {
		if(collaboration == null)
			throw new IllegalArgumentException("The collaboration cannot be null");

		if (collaboration instanceof Proposal) {
			Proposal proposal = (Proposal) collaboration;
			return toJson(proposal);
		} else if (collaboration instanceof Solution) {
			Solution solution = (Solution) collaboration;
			return toJson(solution);
		} else if (collaboration instanceof Comment) {
			Comment comment = (Comment) collaboration;
			return toJson(comment);
		}
		return new JsonObject();
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
		result.add("children", children);

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
			String builtName = vote.getUser().getFirstName() + " " + vote.getUser().getLastName();
			if(vote.isAgreement())
				usersAgree.add(new JsonPrimitive(builtName));
			else
				usersDisagree.add(new JsonPrimitive(builtName));
		}

		// Building response JSON object
		JsonObject result = new JsonObject();
		result.addProperty("id", collaboration.getId());
		result.addProperty("username", collaboration.getProposedBy().getId());
		result.addProperty("rationale", cleanRationale);
		result.add("referredElements", referredElementsArray);
		result.addProperty("type", collaboration.eClass().getName());
		result.add("agree", usersAgree);
		result.add("disagree", usersDisagree);

		return result;
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		addResponseOptions(response);
	}
}
