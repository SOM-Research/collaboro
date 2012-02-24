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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jface.viewers.TreeViewer;

import fr.inria.atlanmod.collaboro.history.Add;
import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.Delete;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.Update;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Version;
import fr.inria.atlanmod.collaboro.history.Vote;

public class Controller {

	public static Controller INSTANCE = new Controller();

	public static final String HISTORY_EXTENSION = ".history.xmi";

	private History history;

	private List<Proposal> proposals = new ArrayList<Proposal>();

	private User loggedUser = null;

	private int lastIndexProposal = 0;

	private File historyModel;

	/**
	 * The controller knows the view, this is temporal, I have to discover 
	 * how to use better the event control in Eclipse
	 */
	private TreeViewer view;

	private Controller() {

	}

	public void initialize() {		

	}


	public List<Proposal> getProposals() {
		return proposals;
	}


	public History getHistory() {
		return history;
	}

	public void loadHistory(File modelBeingTracked) {
		proposals.clear();

		if(!modelBeingTracked.getName().endsWith(".ecore")) {
			return;
		}

		historyModel = inferHistoryModel(modelBeingTracked);		
		ResourceSet rset = new ResourceSetImpl();
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new EcoreResourceFactoryImpl());
		rset.getPackageRegistry().put(HistoryPackage.eNS_URI, HistoryPackage.eINSTANCE);

		Resource res = rset.getResource(URI.createFileURI(historyModel.getAbsolutePath()), true);

		try {
			res.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		history = (History) res.getContents().get(0);
		Version version = history.getVersions().get(0);
		for(Proposal proposal : version.getProposals()) {
			proposals.add(proposal);			
		}		
	}

	/**
	 * Infers the path to the history model tracking the changes in modelBeingTracked
	 * @param modelBeingTracked
	 * @return
	 */
	private File inferHistoryModel(File modelBeingTracked) {
		String parent =  modelBeingTracked.getParentFile().toString();
		String name = modelBeingTracked.getName();
		String historyName = name.substring(0, name.lastIndexOf(".")) + HISTORY_EXTENSION;
		return new File(parent + File.separator + historyName);
	}

	/**
	 * Sets the user logged in the application. 
	 * 
	 * @param userName
	 * @return True if the user was found, false if not
	 */
	public boolean loginUser(String userName) {
		boolean found = false;

		if(history != null) {
			for (User user : history.getUsers()) {
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
			newComment.setId("n" + lastIndexProposal++);
			collaboration.getComments().add(newComment);
			newVote.setComment(newComment);
			collaboration.getVotes().add(newVote);
		}

		newVote.setAgreement(false);
		newVote.setUser(loggedUser); 

		refreshView();
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

		refreshView();
	}

	public void createSolution(Proposal proposal) {
		Solution newSolution = HistoryFactory.eINSTANCE.createSolution();
		newSolution.setProposedBy(loggedUser);
		newSolution.setId("n" + lastIndexProposal++);
		proposal.getSols().add(newSolution);	
		refreshView();
	}


	public void createProposal() {
		Proposal newProposal = HistoryFactory.eINSTANCE.createProposal();
		newProposal.setId("n" + lastIndexProposal++);
		newProposal.setProposedBy(loggedUser);
		proposals.add(newProposal);
		Version version = history.getVersions().get(0);
		version.getProposals().add(newProposal);
		refreshView();
	}

	public void createComment(Collaboration collaboration) {
		Comment newComment = HistoryFactory.eINSTANCE.createComment();
		newComment.setProposedBy(loggedUser);
		newComment.setId("n" + lastIndexProposal++);
		collaboration.getComments().add(newComment);
		refreshView();
	}

	public void createAdd(Solution solution) {
		Add newAdd = HistoryFactory.eINSTANCE.createAdd();
		solution.getChanges().add(newAdd);
	}

	public void createUpdate(Solution solution) {
		Update newUpdate = HistoryFactory.eINSTANCE.createUpdate();
		solution.getChanges().add(newUpdate);
	}

	public void createDelete(Solution solution) {
		Delete newDelete = HistoryFactory.eINSTANCE.createDelete();
		solution.getChanges().add(newDelete);
	}

	public void setView(TreeViewer viewer) {
		this.view = viewer;		
	}

	public void refreshView() {
		this.view.refresh();
	}

	public void saveHistory() {
		if(historyModel != null) {
			ResourceSet rset = new ResourceSetImpl();
			rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new EcoreResourceFactoryImpl());
			rset.getPackageRegistry().put(HistoryPackage.eNS_URI, HistoryPackage.eINSTANCE);

			Resource res = rset.getResource(URI.createFileURI(historyModel.getAbsolutePath()), true);

			try {
				res.getContents().clear();
				res.getContents().add(history);
				res.save(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
