package fr.inria.atlanmod.collaboro.backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Version;
import fr.inria.atlanmod.collaboro.history.VersionHistory;
import fr.inria.atlanmod.collaboro.notation.Definition;

public class CollaboroBackend {
	// Variables to control the state of the collaboration
	private int historyTracked = 0;
	private int versionTracked = 0;
	private int lastIndex = 0;

	ModelManager modelManager = null;

	public CollaboroBackend(File historyFile, File ecoreFile) {
		 modelManager = ModelManagerFactory.createModelManager(ecoreFile);
	}

	/**
	 * Sets the user logged in the application. 
	 * 
	 * @param userName
	 * @return The user found, null if not
	 */
	public User loginUser(String email, String password, String dsl) {
		if(email == null || password == null || dsl == null)
			throw new IllegalArgumentException("Parameters cannot be null");

		User found = null;
		if(getHistory() != null) 
			for (User user : getHistory().getUsers()) 
				if(user.getEmail() != null && user.getEmail().equals(email) && user.getPasword() != null && user.getPasword().equals(password))
					return user;

		return found;
	}

	/**
	 * Testing method to reset the information
	 */
	public void reset() {
		lastIndex = 0;
		historyTracked = 0;
		versionTracked = 0;
	}

	private void calculateLastIndexProposal() {
		lastIndex = 0;

		if(getHistory() != null) {
			for(Proposal proposal : getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked()).getProposals()) {
				String idProposal = proposal.getId();
				int valueProposal = Integer.valueOf(idProposal.substring(1, idProposal.length()));
				if(valueProposal > lastIndex) 
					lastIndex = valueProposal;
				for(Comment comment : proposal.getComments()) { 
					String idComment = comment.getId();
					int valueComment = Integer.valueOf(idComment.substring(1, idComment.length()));
					if(valueComment > lastIndex) 
						lastIndex = valueComment;
				}
				for(Solution solution : proposal.getSols()) {
					String idSolution = solution.getId();
					int valueSolution = Integer.valueOf(idSolution.substring(1, idSolution.length()));
					if(valueSolution > lastIndex) 
						lastIndex = valueSolution;
					for(Comment comment : solution.getComments()) {
						String idCommentSol = comment.getId();
						int valueCommentSol = Integer.valueOf(idCommentSol.substring(1, idCommentSol.length()));
						if(valueCommentSol > lastIndex) 
							lastIndex = valueCommentSol;
					}
				}
			}
		}
	}


	public History getHistory() {
		return modelManager.getHistory();
	}

	public EPackage getEcoreModel() {
		return modelManager.getEcoreModel();
	}

	public Definition getNotation() {
		return modelManager.getNotation();
	}

	public ModelManager getModelManager() {
		return modelManager;
	}

	public int getHistoryTracked() {
		return historyTracked;
	}

	public int getVersionTracked() {
		return versionTracked;
	}

	public void createProposalPlain(String userId, String rationale) {
		Proposal newProposal = HistoryFactory.eINSTANCE.createProposal();

		// Locating the user
		User userProposing = null;
		if(getHistory() != null) 
			for (User user : getHistory().getUsers()) 
				if(user.getId() != null && user.getId().equals(userId))
					userProposing = user;

		if(userProposing != null) {
			newProposal.setProposedBy(userProposing);
			newProposal.setRationale(rationale);
			createProposal(newProposal);
		}
	}

	public void createProposal(Proposal newProposal) {
		newProposal.setId("n" + ++lastIndex);
		getProposals().add(newProposal);
		Version version = getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked());
		version.getProposals().add(newProposal);
		modelManager.saveHistory();
		modelManager.saveNotation();
		System.out.println("Se guardo!");
	}

	public List<Proposal> getProposals() {
		List<Proposal> result = new ArrayList<Proposal>();

		if(getHistory() != null && getHistory().getHistories() != null) {
			VersionHistory versionHistory = getHistory().getHistories().get(getHistoryTracked());
			if(versionHistory != null && getHistory().getHistories().get(getHistoryTracked()).getVersions() != null) {
				Version version = getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked());
				if(version != null) {
					result = getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked()).getProposals();
				}
			}
		}
		return result;
	}

	private Collaboration initCollaborationPlain(Collaboration collaboration, String userId, String rationale) {
		// Locating the user
		User userProposing = null;
		if(getHistory() != null) 
			for (User user : getHistory().getUsers()) 
				if(user.getId() != null && user.getId().equals(userId))
					userProposing = user;

		if(userProposing != null) {
			collaboration.setProposedBy(userProposing);
			collaboration.setRationale(rationale);
		}
		return collaboration;
	}

	private Collaboration locateCollaborationById(Collaboration collaboration, String id) {
		if(collaboration == null ) {
			for(Proposal proposal : getProposals()) 
				return locateCollaborationById(proposal, id);
		} else {
			if(collaboration.getId().equals(id)) 
				return collaboration;
			else {
				if(collaboration instanceof Proposal) 
					for(Solution solution : ((Proposal) collaboration).getSols()) 
						return locateCollaborationById(solution, id);
				for(Comment comment : collaboration.getComments()) 
					return locateCollaborationById(comment, id);
			}
		}
		return null;
	}

	public void createCommentPlain(String parentCollaboration, String userId, String rationale) {
		Comment newComment = HistoryFactory.eINSTANCE.createComment();
		initCollaborationPlain(newComment, userId, rationale);

		Collaboration parent = locateCollaborationById(null, parentCollaboration);
		if(parent != null) 
			createComment(parent, newComment);
	}

	public void createComment(Collaboration collaboration, Comment comment) {
		Comment newComment = HistoryFactory.eINSTANCE.createComment();
		newComment.setId("n" + ++lastIndex);
		collaboration.getComments().add(newComment);
		modelManager.saveHistory();
		modelManager.saveNotation();
	}
	

	public void saveHistory() {
		modelManager.saveHistory();
	}

}
