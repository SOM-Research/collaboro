/*******************************************************************************
 * Copyright (c) 2008, 2013
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Javier Canovas (javier.canovas@inria.fr) 
 *******************************************************************************/
package fr.inria.atlanmod.collaboro.ui;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import fr.inria.atlanmod.collaboro.history.AbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.Add;
import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement;
import fr.inria.atlanmod.collaboro.history.Delete;
import fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.SyntaxElement;
import fr.inria.atlanmod.collaboro.history.Update;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Version;
import fr.inria.atlanmod.collaboro.history.VersionHistory;
import fr.inria.atlanmod.collaboro.history.Vote;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.notation.NotationFactory;
import fr.inria.atlanmod.collaboro.notation.presentation.NotationEditor;
import fr.inria.atlanmod.collaboro.ui.views.CollaborationView;

/**
 * Main class controlling the Collaborto Plugin
 * 
 * @author Javier Canovas (javier.canovas@inria.fr)
 *
 */
/**
 * @author Javier Canovas (javier.canovas@inria.fr)
 *
 */
public class Controller {
	// Default plugin IDs
	public static final String ECORE_PLUGIN_ID = "org.eclipse.emf.ecore.presentation.EcoreEditorID";
	public static final String PACKAGE_EXPLORER_PLUGIN_ID = "org.eclipse.jdt.ui.PackageExplorer";
	public static final String REFLECTIVE_EDITOR_PLUGIN_ID = "org.eclipse.emf.ecore.presentation.ReflectiveEditorID";
	public static final String NOTATION_EDITOR_PLUGIN_ID = "fr.inria.atlanmod.collaboro.notation.presentation.NotationEditorID";

	// Singleton instance
	public static Controller INSTANCE = new Controller();

	// Extensions for the models supported
	public static final String HISTORY_EXTENSION = "history";
	public static final String NOTATION_EXTENSION = "notation";
	public static final String ECORE_EXTENSION = "ecore";
	public static final String MODEL_EXTENSION = "xmi";

	// Variables to control the state of the plugin
	private User loggedUser = null;
	private int lastIndex = 0;
	private int historyTracked = 0;
	private int versionTracked = 0;

	/**
	 * The controller knows some views, this is temporal, I have to discover 
	 * how to use better the event control in Eclipse
	 */
	private TreeViewer view;
	private TreeViewer changes;
	private CollaborationView.VoteUpdater voteUpdater;

	LocalModelManager modelManager = new LocalModelManager();
	
	private Controller () {
	}
	
	/**
	 * Testing method to reset the information
	 */
	public void reset() {
		loggedUser = null;
		lastIndex = 0;
		historyTracked = 0;
		versionTracked = 0;
		
		modelManager = new LocalModelManager();
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
	
	/**
	 * Loads a new History model
	 * 
	 * @param modelBeingTracked
	 */
	public void loadHistory(File modelBeingTracked) {
		reset();
		modelManager.initialize(modelBeingTracked);	
		calculateLastIndexProposal();
		versionTracked = getHistory().getHistories().get(historyTracked).getVersions().size() - 1;
	}
	
	private void calculateLastIndexProposal() {
		lastIndex = 0;
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

	public void createVoteNegative(Collaboration collaboration) {
		if(collaboration.getProposedBy().getId().equals(loggedUser.getId()))
			return;

		Vote newVote = null;

		for(Vote vote : collaboration.getVotes()) {
			if(vote.getUser().getId().equals(loggedUser.getId())) {
				newVote = vote;
				break;
			}
		}

		if(newVote == null) {
			newVote = HistoryFactory.eINSTANCE.createVote();

			Comment newComment = HistoryFactory.eINSTANCE.createComment();
			newComment.setProposedBy(loggedUser);
			newComment.setId("n" + ++lastIndex);
			collaboration.getComments().add(newComment);
			newVote.setComment(newComment);
			collaboration.getVotes().add(newVote);
		}

		newVote.setAgreement(false);
		newVote.setUser(loggedUser); 
		modelManager.saveHistory();
		modelManager.saveNotationModel();

		refreshView();
		refreshVoteUpdater();
	}

	public void createVotePositive(Collaboration collaboration) {
		if(collaboration.getProposedBy().getId().equals(loggedUser.getId()))
			return;

		Vote newVote = null;

		for(Vote vote : collaboration.getVotes()) {
			if(vote.getUser().getId().equals(loggedUser.getId())) {
				newVote = vote;
				break;
			}
		}

		if(newVote == null) {
			newVote = HistoryFactory.eINSTANCE.createVote();
			collaboration.getVotes().add(newVote);
		}

		newVote.setAgreement(true);
		newVote.setUser(loggedUser);
		modelManager.saveHistory();
		modelManager.saveNotationModel();

		refreshView();
		refreshVoteUpdater();
	}

	public void createSolution(Proposal proposal) {
		Solution newSolution = HistoryFactory.eINSTANCE.createSolution();
		newSolution.setProposedBy(loggedUser);
		newSolution.setId("n" + ++lastIndex);
		proposal.getSols().add(newSolution);	
		modelManager.saveHistory();
		modelManager.saveNotationModel();
		refreshView();
	}


	public void createProposal() {
		Proposal newProposal = HistoryFactory.eINSTANCE.createProposal();
		newProposal.setId("n" + ++lastIndex);
		newProposal.setProposedBy(loggedUser);
		getProposals().add(newProposal);
		Version version = getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked());
		version.getProposals().add(newProposal);
		modelManager.saveHistory();
		modelManager.saveNotationModel();
		refreshView();
	}

	public void createComment(Collaboration collaboration) {
		Comment newComment = HistoryFactory.eINSTANCE.createComment();
		newComment.setProposedBy(loggedUser);
		newComment.setId("n" + ++lastIndex);
		collaboration.getComments().add(newComment);
		modelManager.saveHistory();
		modelManager.saveNotationModel();
		refreshView();
	}

	public void createAdd(Solution solution) {
		Add newAdd = HistoryFactory.eINSTANCE.createAdd();
		solution.getChanges().add(newAdd);
		modelManager.saveHistory();
		modelManager.saveNotationModel();
		refreshChanges();
	}

	public void createUpdate(Solution solution) {
		Update newUpdate = HistoryFactory.eINSTANCE.createUpdate();
		solution.getChanges().add(newUpdate);
		modelManager.saveHistory();
		modelManager.saveNotationModel();
		refreshChanges();
	}

	public void createDelete(Solution solution) {
		Delete newDelete = HistoryFactory.eINSTANCE.createDelete();
		solution.getChanges().add(newDelete);
		modelManager.saveHistory();
		modelManager.saveNotationModel();
		refreshChanges();
	}

	public void setView(TreeViewer viewer) {
		this.view = viewer;		
	}

	public void refreshView() {
		if(this.view != null)
			this.view.refresh();
	}

	public void setChanges(TreeViewer changes) {
		this.changes = changes;		
	}

	public void refreshChanges() {
		if(this.changes != null)
			this.changes.refresh();
	}


	public void setVoteUpdater(CollaborationView.VoteUpdater voteUpdater) {
		this.voteUpdater = voteUpdater;		
	}

	public void refreshVoteUpdater() {
		if(this.voteUpdater != null)
			this.voteUpdater.update();
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
	


	public List<NotationElement> getAllNotationElements() {
		List<NotationElement> result = new ArrayList<NotationElement>();

		List<Proposal> proposals = getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked()).getProposals();
		for(Proposal proposal : proposals) {
			List<Solution> solutions = proposal.getSols();
			for(Solution solution : solutions) {
				List<ModelChange> changes = solution.getChanges();
				for(ModelChange change : changes) {
					if(change.getReferredElement() instanceof ConcreteSyntaxElement) {
						if(!result.contains(change.getReferredElement())) 
							result.add(((ConcreteSyntaxElement) change.getReferredElement()).getElement());
					}

					if(change.getTarget() instanceof ConcreteSyntaxElement) { 
						if(!result.contains(change.getTarget())) 
							result.add(((ConcreteSyntaxElement) change.getTarget()).getElement());

					}

				}
			}
		}
		return result;
	}

	public List<EObject> getAllNotationMetaElements() {
		List<EObject> result = new ArrayList<EObject>();

		result.add(NotationFactory.eINSTANCE.createComposite());
		result.add(NotationFactory.eINSTANCE.createKeyword());
		result.add(NotationFactory.eINSTANCE.createToken());
		result.add(NotationFactory.eINSTANCE.createReferenceValue());
		result.add(NotationFactory.eINSTANCE.createAttributeValue());
		result.add(NotationFactory.eINSTANCE.createSyntaxOf());

		return result;
	}

	public List<EObject> getAllEcoreElements() {
		List<EObject> result = new ArrayList<EObject>();

		result.add(EcoreFactory.eINSTANCE.createEClass());
		result.add(EcoreFactory.eINSTANCE.createEEnum());
		result.add(EcoreFactory.eINSTANCE.createEAttribute());

		return result;

	}

	public void openNotationEditor(NotationElement notationElement) {
		if (modelManager.getNotation() != null && modelManager.getNotationFile().exists() && modelManager.getNotationFile().isFile()) {
			String path = modelManager.getNotationFile().getAbsolutePath();
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				java.net.URI fromString = org.eclipse.core.runtime.URIUtil.fromString("file://" + path);
				NotationEditor openEditor = (NotationEditor) IDE.openEditor(page, fromString, NOTATION_EDITOR_PLUGIN_ID, true);
				IEditorInput editorInput = openEditor.getEditorInput();

				EditingDomain editingDomain = openEditor.getEditingDomain();
				URI uri = EcoreUtil.getURI(notationElement);
				EObject editObject = editingDomain.getResourceSet().getEObject(uri, true);
				if(editObject != null) {
					openEditor.setSelectionToViewer(Collections.singleton(editObject));
				}

			} catch ( PartInitException e ) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	public void openAbstractSyntaxEditor(EClass eClass) {

		if (modelManager.getEcoreModel() != null && modelManager.getEcoreModelFile().exists() && modelManager.getEcoreModelFile().isFile()) {
			String path = modelManager.getEcoreModelFile().getAbsolutePath();
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				java.net.URI fromString = org.eclipse.core.runtime.URIUtil.fromString("file://" + path);
				IEditorPart openEditor = IDE.openEditor(page, fromString, ECORE_PLUGIN_ID, true);
				IEditorInput editorInput = openEditor.getEditorInput();
			} catch ( PartInitException e ) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		//		String abstractModelFileName = ecoreModel.getName();
		//		IEditorDescriptor descriptor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(abstractModelFileName);
		//
		//		if (ecoreModel.exists() && ecoreModel.isFile()) {
		//			String path = ecoreModel.getAbsolutePath();
		//			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		//			try {
		//				java.net.URI fromString = org.eclipse.core.runtime.URIUtil.fromString("file://" + path);
		//				EcoreEditor ecoreEditor = (EcoreEditor) IDE.openEditor(page, fromString, ECORE_PLUGIN_ID, true);
		//				
		//				EditingDomain editingDomain = ecoreEditor.getEditingDomain();
		//				URI uri = EcoreUtil.getURI(eClass);
		//				EObject editObject = editingDomain.getResourceSet().getEObject(uri, true);
		//				if(editObject != null) {
		//					ecoreEditor.setSelectionToViewer(Collections.singleton(editObject));
		//				}
		//				
		//			} catch ( PartInitException e ) {
		//				e.printStackTrace();
		//			} catch (URISyntaxException e) {
		//				e.printStackTrace();
		//			}
		//		}
	}

	public void addNotationElement(NotationElement selectedElement) {
		if(getNotation() != null & selectedElement != null) {
			getNotation().getElements().add(selectedElement);
			modelManager.saveNotationModel();
		}

	}

	private static boolean inNotation = false;

	public void inNotation() {
		inNotation = true;
	}

	public void outNotation() {
		if(inNotation) {
			inNotation = false;
			modelManager.loadHistory();
			modelManager.loadNotation();
			if(changes != null) changes.refresh();
		}
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
		modelManager.saveNotationModel();
		calculateLastIndexProposal();
	}

	private void deleteProposal(Proposal proposalToDelete) {
		getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked()).getProposals().remove(proposalToDelete);
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

	public void deleteModelChange(ModelChange modelChange) {
		EObject eObject = modelChange.eContainer();
		if (eObject instanceof Solution) {
			Solution solution = (Solution) eObject;
			solution.getChanges().remove(modelChange);
		}
		modelManager.saveHistory();
		modelManager.saveNotationModel();
	}

	public List<ModelChange> applyChanges() {
		List<ModelChange> appliedChanges = new ArrayList<ModelChange>();

		if(getHistory() == null) return appliedChanges;
		
		for(Proposal proposal : getHistory().getHistories().get(getHistoryTracked()).getVersions().get(getVersionTracked()).getProposals()) {
			if(proposal.isAccepted()) {
				Solution solution = proposal.getSelected();
				if(solution != null) {
					for(ModelChange change : solution.getChanges()) {
						if (change instanceof Add) {
							Add add = (Add) change;
							SyntaxElement referred = add.getReferredElement();
							SyntaxElement target = add.getTarget();

							if (referred instanceof ExistingAbstractSyntaxElement) {
								ExistingAbstractSyntaxElement refExistingElement = (ExistingAbstractSyntaxElement) referred;
								EObject refEObject = refExistingElement.getElement();

								if (target instanceof NewAbstractSyntaxElement) {
									NewAbstractSyntaxElement tgtNewElement = (NewAbstractSyntaxElement) target;
									EObject tgtEObject = tgtNewElement.getElement();

									if (tgtEObject instanceof EAttribute) {
										EAttribute newAttribute = (EAttribute) tgtEObject;

										if (refEObject instanceof EClass) {
											EClass eClass = (EClass) refEObject;
											eClass.getEStructuralFeatures().add(newAttribute);
											appliedChanges.add(change);
										}

									}

									if(tgtEObject instanceof EClass) {
										EClass eClass = (EClass) tgtEObject;

										if (refEObject instanceof EPackage) {
											EPackage ePackage = (EPackage) refEObject;
											ePackage.getEClassifiers().add(eClass);
											appliedChanges.add(change);
										}
									}

									if(tgtEObject instanceof EEnum) {
										EEnum eEnum = (EEnum) tgtEObject;

										if (refEObject instanceof EPackage) {
											EPackage ePackage = (EPackage) refEObject;
											ePackage.getEClassifiers().add(eEnum);
											appliedChanges.add(change);
										}
									}

								}
							}
						} else if (change instanceof Delete) {
							Delete delete = (Delete) change;
							SyntaxElement referred = delete.getReferredElement();
							SyntaxElement target = delete.getTarget();

							if (referred instanceof ExistingAbstractSyntaxElement) {
								ExistingAbstractSyntaxElement refExistingElement = (ExistingAbstractSyntaxElement) referred;
								EObject refEObject = refExistingElement.getElement();

								if (target instanceof ExistingAbstractSyntaxElement) {
									ExistingAbstractSyntaxElement toDelete = (ExistingAbstractSyntaxElement) target;
									EObject tgtEObject = toDelete.getElement();

									if(tgtEObject instanceof EAttribute) {
										EAttribute eAttribute = (EAttribute) tgtEObject;

										if (refEObject instanceof EClass) {
											EClass eClass = (EClass) refEObject;
											eClass.getEStructuralFeatures().remove(eAttribute);
											appliedChanges.add(change);
										}
									}

									if(tgtEObject instanceof EClass) {
										EClass eClass = (EClass) tgtEObject;

										if (refEObject instanceof EPackage) {
											EPackage ePackage = (EPackage) refEObject;
											ePackage.getEClassifiers().remove(eClass);
											appliedChanges.add(change);
										}
									}

									if(tgtEObject instanceof EEnum) {
										EEnum eEnum = (EEnum) tgtEObject;

										if (refEObject instanceof EPackage) {
											EPackage ePackage = (EPackage) refEObject;
											ePackage.getEClassifiers().remove(eEnum);
											appliedChanges.add(change);
										}
									}

								}
							}
						}
					}
				}
			}
		}
		modelManager.saveEcoreModel();
		return appliedChanges;
	}

	public void saveHistory() {
		modelManager.saveHistory();
	}

	public void changeVersion(Version version) {
		if(getHistory() == null) return;
		
		List<Version> versions = getHistory().getHistories().get(getHistoryTracked()).getVersions();
		int indexOfVersion = versions.indexOf(version);
		versionTracked = indexOfVersion;
		view.refresh();		
	}
}
