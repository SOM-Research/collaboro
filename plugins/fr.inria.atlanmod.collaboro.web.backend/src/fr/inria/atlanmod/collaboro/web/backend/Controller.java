package fr.inria.atlanmod.collaboro.web.backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.history.AbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement;
import fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Version;
import fr.inria.atlanmod.collaboro.history.VersionHistory;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
//import fr.inria.atlanmod.collaboro.ui.LocalModelManager;
//import fr.inria.atlanmod.collaboro.ui.ModelManager;
//import fr.inria.atlanmod.collaboro.ui.ModelManagerFactory;

/**
 * 
 * @author Juan David Villa Calle
 *
 */
public class Controller
{

	// Variables to control the state of the plugin
	private User loggedUser = null;
	private int historyTracked = 0;
	private int versionTracked = 0;
	private int lastIndex = 0;
	
	ModelManagerFactory modelManagerFactory = new ModelManagerFactory();
	ModelManager modelManager = modelManagerFactory.createEmptyModelManager();

	public Controller(URI modelBeingTracked)
	{
		if(modelManager instanceof LocalModelManager)
		{
			boolean initialized=((LocalModelManager) modelManager).initialize(new File(modelBeingTracked.toString()));
			System.out.println("Inicializo al modelManager? "+initialized);
		}
	}
	
	/**
	 * Testing method to reset the information
	 */
	public void reset() {
		loggedUser = null;
		lastIndex = 0;
		historyTracked = 0;
		versionTracked = 0;
	}
	
	//public Controller(URI uriHistoryModel)
	//{
		//modelManager = new LocalModelManager();
		//modelManager.initialize(uriHistoryModel);
	//}
	
	public User getLoggedUser()
	{
			return loggedUser;
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
	
	public ModelManager getModelManager() 
	{
		return modelManager;
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
	
	/**
	 * Loads an ecore model. Normally the model conforms to the metamodel being tracked.
	 * 
	 * @param modelPath
	 * @return
	 */
	public EObject loadModel(File modelPath) {
		if(modelManager == null) return null;
		return modelManager.loadModel(modelPath);
	}
	
	/**
	 * Sets the user logged in the application. 
	 * 
	 * @param userName
	 * @return True if the user was found, false if not
	 */
	public boolean loginUser(String userName) {
		boolean found = false;

		if(getHistory() != null) {
			for (User user : getHistory().getUsers()) {
				if(user.getId().equals(userName)) {
					loggedUser = user;
					found = true;
					break;
				}
			}
		}

		return found;
	}
	
	/**
	 * Indicates if the user is logged
	 * @return
	 */
	public boolean isLogged() {
		return !(loggedUser == null);
	}
	
	public void createSolution(Proposal proposal) {
		Solution newSolution = HistoryFactory.eINSTANCE.createSolution();
		newSolution.setProposedBy(loggedUser);
		newSolution.setId("n" + ++lastIndex);
		proposal.getSols().add(newSolution);	
		modelManager.saveHistory();
		modelManager.saveNotation();
		//refreshVersionView();
	}


	public void createProposal() {
		Proposal newProposal = HistoryFactory.eINSTANCE.createProposal();
		newProposal.setId("n" + ++lastIndex);
		//newProposal.setProposedBy(loggedUser);
		getProposals().add(newProposal);
		Version version = getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked());
		version.getProposals().add(newProposal);
		modelManager.saveHistory();
		modelManager.saveNotation();
		//refreshVersionView();
	}

	public void createComment(Collaboration collaboration) {
		Comment newComment = HistoryFactory.eINSTANCE.createComment();
		//newComment.setProposedBy(loggedUser);
		newComment.setId("n" + ++lastIndex);
		collaboration.getComments().add(newComment);
		modelManager.saveHistory();
		modelManager.saveNotation();
		//refreshVersionView();
	}
	
	
	
	public int getHistoryTracked() {
		return historyTracked;
	}
	
	public int getVersionTracked() {
		return versionTracked;
	}
	
	/**
	 * Looks for a ConcreteSyntaxElement linked with the Eclassifier received
	 * 
	 * @param modelElement
	 * @return
	 */
	public NotationElement getNotation(EClassifier modelElement) {
		if(getHistory() == null) return null;

		List<Proposal> proposals = getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked()).getProposals();
		for(Proposal proposal : proposals) {
			List<Solution> solutions = proposal.getSols();
			for(Solution solution : solutions) {
				List<ModelChange> changes = solution.getChanges();
				for(ModelChange change : changes) {
					if(change.getReferredElement() instanceof AbstractSyntaxElement && change.getTarget() instanceof ConcreteSyntaxElement) {
						EModelElement refModelElement = null;
						if (change.getReferredElement() instanceof ExistingAbstractSyntaxElement) {
							refModelElement = ((ExistingAbstractSyntaxElement) change.getReferredElement()).getElement();
						}
						if (change.getReferredElement() instanceof NewAbstractSyntaxElement) {
							refModelElement = ((NewAbstractSyntaxElement) change.getReferredElement()).getElement();
						}
						if(refModelElement != null) {
							if (refModelElement instanceof EClassifier) {
								EClassifier classifier = (EClassifier) refModelElement;
								if(classifier.getName().equals(modelElement.getName())) {
									ConcreteSyntaxElement concreteElement = (ConcreteSyntaxElement) change.getTarget();
									NotationElement result = concreteElement.getElement();
									return result;
								}
							}
						} 
					} 
				}
			}
		}

		return null;
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
	
	public void saveHistory() {
		modelManager.saveHistory();
	}
	
	
	
}
