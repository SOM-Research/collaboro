package fr.inria.atlanmod.collaboro.backend;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.notation.Definition;

/**
 * This class is the main entry point to play with Collaboro. 
 * For the moment, we are moving thing from Controller class here.
 * 
 * @author Javier Canovas (javier.canovas@inria.fr)
 *
 */
public class CollaboroBackend {
	private static CollaboroBackend instance;
	public static String PATH_TO_HISTORY = "C:\\Users\\useradm\\git\\collaboro\\plugins\\fr.inria.atlanmod.collaboro.web.servlets\\WebContent\\WEB-INF\\model\\ModiscoWorkflow.ecore";
	
	// Variables to control the state of the collaboration
	private int historyTracked = 0;
	private int versionTracked = 0;
	private int lastIndex = 0;
	
	ModelManagerFactory modelManagerFactory = new ModelManagerFactory();
	ModelManager modelManager = modelManagerFactory.createEmptyModelManager();
	
	private CollaboroBackend() {
		File historyFile = new File(PATH_TO_HISTORY);
		loadHistory(historyFile);
	}
	
	public static CollaboroBackend getInstance() {
		if(instance == null) {
			instance = new CollaboroBackend();
		}
		return instance;
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
				if(user.getEmail() != null && user.getEmail().equals(email))
					return user;

		return found;
	}
	
	/**
	 * Loads a new History model
	 * 
	 * @param resource to be tracked
	 */
	public void loadHistory(Object resource) { 
		reset();
		modelManager = modelManagerFactory.createModelManager(resource);
		calculateLastIndexProposal();
		if(getHistory() != null) 
			versionTracked = getHistory().getHistories().get(historyTracked).getVersions().size() - 1;
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
	
	
}
