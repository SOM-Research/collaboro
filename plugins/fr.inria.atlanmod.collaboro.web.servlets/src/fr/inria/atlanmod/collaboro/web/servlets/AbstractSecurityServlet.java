package fr.inria.atlanmod.collaboro.web.servlets;

import com.google.gson.JsonObject;

import fr.inria.atlanmod.collaboro.history.User;

public abstract class AbstractSecurityServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 3L;

	protected JsonObject buildJsonUserResponse(User historyUser, String dsl) {
		JsonObject jsonUserResponse = new JsonObject();
		jsonUserResponse.addProperty("firstName", historyUser.getFirstName());
		jsonUserResponse.addProperty("lastName", historyUser.getLastName());
		jsonUserResponse.addProperty("dsl", dsl);
		
		JsonObject jsonResponse = new JsonObject();
		jsonResponse.add("user", jsonUserResponse);

		return jsonResponse;
	}
}
