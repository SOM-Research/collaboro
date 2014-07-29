package fr.inria.atlanmod.collaboro.backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
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
import fr.inria.atlanmod.collaboro.history.Vote;
import fr.inria.atlanmod.collaboro.notation.Definition;

public class CollaboroBackend {
	// Variables to control the state of the collaboration
	private int historyTracked = 0;
	private int versionTracked = 0;
	private int maxVersionTracked = 0;
	private int lastIndex = 0;

	ModelManager modelManager = null;

	public CollaboroBackend(File historyFile, File ecoreFile) {
		modelManager = ModelManagerFactory.createModelManager(ecoreFile);

		VersionHistory versionHistory = getHistory().getHistories().get(getHistoryTracked());
		maxVersionTracked = versionHistory.getVersions().size() - 1;
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
	
	public EPackage getEcoreModel(int index) {
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
	
	public void nextVersion() {
		VersionHistory versionHistory = getHistory().getHistories().get(getHistoryTracked());
		
		if(versionTracked + 1 < versionHistory.getVersions().size()) {
			versionTracked++;
		}
	} 
	
	public void previousVersion() {
		if(versionTracked > 0) {
			versionTracked--;
		}
	}
	
	public void createVersion() {
		Version version = HistoryFactory.eINSTANCE.createVersion();
		versionTracked = ++maxVersionTracked;
		version.setId(String.valueOf(versionTracked));
		
		getHistory().getHistories().get(getHistoryTracked()).getVersions().add(version);
		modelManager.saveHistory();
		modelManager.saveNotation();
	}

	public void createProposalPlain(String userId, String rationale) {
		Proposal newProposal = HistoryFactory.eINSTANCE.createProposal();
		initCollaborationPlain(newProposal, userId, rationale);
		createProposal(newProposal);
	}

	public void createProposal(Proposal newProposal) {
		newProposal.setId("n" + ++lastIndex);
		Version version = getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked());
		version.getProposals().add(newProposal);
		modelManager.saveHistory();
		modelManager.saveNotation();
	}
	
	public void createSolutionPlain(String parentCollaboration, String userId, String rationale, String actions) {
		Solution newSolution = HistoryFactory.eINSTANCE.createSolution();
		initCollaborationPlain(newSolution, userId, rationale);
		newSolution.setChangesText(actions);
		
		Collaboration parent = locateCollaborationById(null, parentCollaboration);
		if (parent != null && parent instanceof Proposal) {
			Proposal parentProposal = (Proposal) parent;
			createSolution(parentProposal, newSolution);
		} else {
			throw new IllegalArgumentException("The parent does not exists");
		}
	}

	public void createSolution(Proposal parent, Solution newSolution) {
		newSolution.setId("n" + ++lastIndex);
		parent.getSols().add(newSolution);	
		modelManager.saveHistory();
		modelManager.saveNotation();
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

	private User locateUser(String userId) {
		User userProposing = null; 
		if(getHistory() != null) 
			for (User user : getHistory().getUsers()) 
				if(user.getId() != null && user.getId().equals(userId)) {
					userProposing = user;
					break;
				}
		return userProposing;
	}
	
	private Collaboration initCollaborationPlain(Collaboration collaboration, String userId, String rationale) {
		// Locating the user
		User userProposing = locateUser(userId);

		if(userProposing != null) {
			collaboration.setProposedBy(userProposing);
			collaboration.setRationale(rationale);
		} else {
			throw new IllegalArgumentException("The user does not exists");
		}
		return collaboration;
	}

	public Collaboration locateCollaborationById(Collaboration collaboration, String id) {
		Collaboration found = null;
		if(collaboration == null ) {
			for(Proposal proposal : getProposals()) {
				found = locateCollaborationById(proposal, id);
				if(found != null) break;
			}
		} else {
			if(collaboration.getId().equals(id)) 
				return collaboration;
			else {
				if(collaboration instanceof Proposal) { 
					for(Solution solution : ((Proposal) collaboration).getSols()) {
						found = locateCollaborationById(solution, id);
						if(found != null) break;
					}
				}
				if(found == null) {
					for(Comment comment : collaboration.getComments()) { 
						found = locateCollaborationById(comment, id);
						if(found != null) break;
					}
				}
				return found;
			}
		}
		return found;
	}

	public void createCommentPlain(String parentCollaboration, String userId, String rationale) {
		Comment newComment = HistoryFactory.eINSTANCE.createComment();
		initCollaborationPlain(newComment, userId, rationale);

		Collaboration parent = locateCollaborationById(null, parentCollaboration);
		if(parent != null) {
			createComment(parent, newComment);
		} else {
			throw new IllegalArgumentException("The parent does not exists");
		}
	}

	public void createComment(Collaboration collaboration, Comment newComment) {
		newComment.setId("n" + ++lastIndex);
		collaboration.getComments().add(newComment);
		modelManager.saveHistory();
		modelManager.saveNotation();
	}

	public void createVotePlain(String parentCollaboration, String userId, boolean agreement) {
		User userVoting = locateUser(userId);
		Collaboration collaboration = locateCollaborationById(null, parentCollaboration);
		createVote(collaboration, userVoting, agreement);
	}
	
	public void createVote(Collaboration collaboration, User userVoting, boolean agreement) {
		Vote newVote = null;
		
		// If the user has already voted, we take the vote
		for(Vote vote : collaboration.getVotes()) {
			if(vote.getUser().getId().equals(userVoting.getId())) {
				newVote = vote;
				break;
			}
		}

		if(newVote == null) {
			newVote = HistoryFactory.eINSTANCE.createVote();
			newVote.setUser(userVoting);
			collaboration.getVotes().add(newVote);
		}

		newVote.setAgreement(agreement);
		modelManager.saveHistory();
		modelManager.saveNotation();
	}

	public void saveHistory() {
		modelManager.saveHistory();
	}
	
	public void launchDecision() {
		DecisionEngine engine = DecisionEngine.INSTANCE;

		Version version = getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked());
		for (Proposal proposal : version.getProposals()) {
			engine.resolveProposal(getHistory(), proposal);
			engine.resolveSolution(getHistory(), proposal);
		}
	}
	
	public void deleteCollaborationPlain(String collaborationId) {
		Collaboration collaboration = locateCollaborationById(null, collaborationId);
		if(collaboration != null)
			deleteCollaboration(collaboration);
		else 
			throw new IllegalArgumentException("The collaboration does not exist");
	}
	
	public void deleteCollaboration(Collaboration collaboration) {
		if (collaboration instanceof Proposal) {
			Proposal proposal = (Proposal) collaboration;
			deleteProposal(proposal);
		} else if (collaboration instanceof Solution) {
			Solution solution = (Solution) collaboration;
			deleteSolution(solution);
		} else if (collaboration instanceof Comment) {
			Comment comment = (Comment) collaboration;
			deleteComment(comment);
		}
		modelManager.saveHistory();
		modelManager.saveNotation();
		calculateLastIndexProposal();
	}

	private void deleteProposal(Proposal proposalToDelete) {
		getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked()).getProposals().remove(proposalToDelete);
		proposalToDelete.setVersion(null);
		proposalToDelete.setProposedBy(null);
	}

	private void deleteSolution(Solution solutionToDelete) {
		EObject eObject = solutionToDelete.eContainer();
		if (eObject instanceof Proposal) {
			Proposal proposal = (Proposal) eObject;
			proposal.getSols().remove(solutionToDelete);
		}
	}

	private void deleteComment(Comment commentToDelete) {
		EObject eObject = commentToDelete.eContainer();
		if (eObject instanceof Collaboration) {
			Collaboration collaboration = (Collaboration) eObject;
			collaboration.getComments().remove(commentToDelete);
		}
	}

	
	public void removeCollaboration(Collaboration collaboration) {
		
	}
	
	public int getNumOfAbsModelImages()
	{
		return 2;
	}

}
