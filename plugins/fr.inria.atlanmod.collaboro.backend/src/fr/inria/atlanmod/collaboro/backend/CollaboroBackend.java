package fr.inria.atlanmod.collaboro.backend;

import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
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

/**
 * Main backedn for collaboro
 */
public class CollaboroBackend {
	// Variables to control the state of the collaboration
	private int historyTracked = 0;
	private int versionTracked = 0;
	private int maxVersionTracked = 0;

	ModelManager modelManager = null;

	/**
	 * Keeps a list of files pointing at previous versions of the Ecore files
	 */
	private HashMap<String, List<File>> previousEcores;

	/**
	 * Keeps a list of files pointing at previous model examples   
	 */
	private HashMap<String, List<File>> previousModels;

	public CollaboroBackend(ModelManager modelManager) {
		this.modelManager = modelManager;

		VersionHistory versionHistory = getHistory().getHistories().get(getHistoryTracked());
		maxVersionTracked = versionHistory.getVersions().size() - 1;
	}

	public void setPreviousEcores(HashMap<String, List<File>> previousEcores) {
		this.previousEcores = previousEcores;
	}

	public void setPreviousModels(HashMap<String, List<File>> previousModels) {
		this.previousModels = previousModels;
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
				if(user.getEmail() != null && user.getEmail().equals(email) && user.getPassword() != null && user.getPassword().equals(password))
					return user;

		return found;
	}

	/**
	 * Testing method to reset the information
	 */
	public void reset() {
		historyTracked = 0;
		versionTracked = 0;
	}

	public History getHistory() {
		return modelManager.getHistory();
	}

	public EPackage getEcoreModel() {
		return modelManager.getEcoreModel();
	}

	public File getEcoreModel(int index) {
		if(this.previousEcores == null)
			return null;
		if(this.previousEcores.get(String.valueOf(getVersionTracked())) == null) 
			throw new IllegalArgumentException("There are no models for such version");	
		if(index < 0 || index > this.previousEcores.get(String.valueOf(getVersionTracked())).size())
			throw new IllegalArgumentException("The index is out of bounds");
		File previousEcoreModelsList = this.previousEcores.get(String.valueOf(getVersionTracked())).get(index);
		return previousEcoreModelsList;
	}

	public File getModel(int index) {
		if(this.previousModels == null)
			return null;
		if(this.previousModels.get(String.valueOf(getVersionTracked())) == null) 
			throw new IllegalArgumentException("There are no example models for such version");	
		if(index < 0 || index > this.previousModels.get(String.valueOf(getVersionTracked())).size())
			throw new IllegalArgumentException("The index is out of bounds");
		File previousModels = this.previousModels.get(String.valueOf(getVersionTracked())).get(index);
		return previousModels;
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
	
	public void moveToLastVersion() {
		VersionHistory versionHistory = getHistory().getHistories().get(getHistoryTracked());
		Version version = versionHistory.getVersions().get(versionHistory.getVersions().size() - 1);
		versionTracked = Integer.valueOf(version.getId());		
	}

	public void createVersion() {
		Version version = HistoryFactory.eINSTANCE.createVersion();
		versionTracked = ++maxVersionTracked;
		version.setId(String.valueOf(versionTracked));

		getHistory().getHistories().get(getHistoryTracked()).getVersions().add(version);
		modelManager.saveHistory();
		modelManager.saveNotation();
	}

	public String createProposalPlain(String userId, String rationale, String referredElements) {
		Proposal newProposal = HistoryFactory.eINSTANCE.createProposal();
		initCollaborationPlain(newProposal, userId, rationale, referredElements);
		createProposal(newProposal);

		return newProposal.getId();
	}

	public String editCollaborationPlain(String parentId, String collabId, String userId, String rationale, String referredElements) {
		Collaboration parent = locateCollaborationById(null, parentId);
		Collaboration collaboration = locateCollaborationById(parent, collabId);
		if(collaboration != null) {
			User user = locateUser(userId);
			if(user == null) throw new IllegalArgumentException("The user does not exists");
			
			collaboration.setProposedBy(user);
			collaboration.setRationale(rationale);
			collaboration.setReferredElements(referredElements);
			return collaboration.getId();
		} else {
			return null;
		}
	}
	
	public void createProposal(Proposal newProposal) {
		newProposal.setId(generateId());
		Version version = getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked());
		version.getProposals().add(newProposal);
		modelManager.saveHistory();
		modelManager.saveNotation();
	}

	public String createSolutionPlain(String parentCollaboration, String userId, String rationale, String actions, String referredElements) {
		Solution newSolution = HistoryFactory.eINSTANCE.createSolution();
		initCollaborationPlain(newSolution, userId, rationale,referredElements);
		newSolution.setChangesText(actions);

		Collaboration parent = locateCollaborationById(null, parentCollaboration);
		if (parent != null && parent instanceof Proposal) {
			Proposal parentProposal = (Proposal) parent;
			createSolution(parentProposal, newSolution);
			return newSolution.getId();
		} else {
			throw new IllegalArgumentException("The parent does not exists");
		}
	}

	public void createSolution(Proposal parent, Solution newSolution) {
		newSolution.setId(generateId());
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

	Collaboration initCollaborationPlain(Collaboration collaboration, String userId, String rationale, String referredElements) {
		// Locating the user
		User userProposing = locateUser(userId);
		if(userProposing != null) {
			collaboration.setProposedBy(userProposing);
			collaboration.setRationale(rationale);
			collaboration.setReferredElements(referredElements);
		} else {
			throw new IllegalArgumentException("The user does not exists");
		}
		return collaboration;
	}

	/**
	 * Locates a collaboration
	 *  
	 * @param collaboration The root proposal to traverse (null if general root)
	 * @param id The id of the collaboration to look for
	 * @return
	 */
	public Collaboration locateCollaborationById(Collaboration parentCollaboration, String collaborationId) {
		Collaboration found = null;
		if(parentCollaboration == null ) {
			for(Proposal proposal : getProposals()) {
				found = locateCollaborationById(proposal, collaborationId);
				if(found != null) break;
			}
		} else {
			if(parentCollaboration.getId().equals(collaborationId)) 
				return parentCollaboration;
			else {
				if(parentCollaboration instanceof Proposal) { 
					for(Solution solution : ((Proposal) parentCollaboration).getSols()) {
						found = locateCollaborationById(solution, collaborationId);
						if(found != null) break;
					}
				}
				if(found == null) {
					for(Comment comment : parentCollaboration.getComments()) { 
						found = locateCollaborationById(comment, collaborationId);
						if(found != null) break;
					}
				}
				return found;
			}
		}
		return found;
	}

	public String createCommentPlain(String parentCollaboration, String userId, String rationale, String referredElements) {
		Comment newComment = HistoryFactory.eINSTANCE.createComment();
		initCollaborationPlain(newComment, userId, rationale,referredElements);

		Collaboration parent = locateCollaborationById(null, parentCollaboration);
		if(parent != null) {
			createComment(parent, newComment);
		} else {
			throw new IllegalArgumentException("The parent does not exists");
		}
		
		return newComment.getId();
	}
	
	public String createDisagreementVote(String collaborationId, String userId, String comment) {
		Collaboration collaboration = locateCollaborationById(null, collaborationId);
		User user = locateUser(userId);
		if(collaboration != null) {
			for(Vote vote : collaboration.getVotes()) {
				boolean alreadyVoted = false;
				if(vote.getUser().getId().equals(user.getId())) {
					alreadyVoted = true;
				}
				
				if(!alreadyVoted) 
					createVote(collaboration, user, false);
				
				return createCommentPlain(collaborationId, userId, comment, "");
			}
		} else {
			throw new IllegalArgumentException("The collaboration does not exists");
		}
		return null;
	}

	public void createComment(Collaboration collaboration, Comment newComment) {
		newComment.setId(generateId());
		collaboration.getComments().add(newComment);
		modelManager.saveHistory();
		modelManager.saveNotation();
	}

	public void createVotePlain(String parentCollaboration, String userId, boolean agreement) {
		User userVoting = locateUser(userId);
		Collaboration collaboration = locateCollaborationById(null, parentCollaboration);
		if(collaboration != null)
			createVote(collaboration, userVoting, agreement);
		else 
			throw new IllegalArgumentException("The collaboration does not exist");
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
		modelManager.saveHistory();
		modelManager.saveNotation();
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

	public int getNumOfAbsModelImages() {
		if(previousEcores == null) return 0;
		List<File> previousEcoresList = previousEcores.get(String.valueOf(getVersionTracked()));
		if(previousEcoresList == null) return 0;
		return previousEcoresList.size();
	}

	public int getNumOfNotModelImages()	{
		if(previousModels == null) return 0;
		List<File> previousModelsList = previousModels.get(String.valueOf(getVersionTracked()));
		if(previousModelsList == null) return 0;
		return previousModelsList.size();
	}

	public String generateId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
}
