/*******************************************************************************
 * Copyright (c) 2008, 2012
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Javier Canovas (javier.canovas@inria.fr) 
 *******************************************************************************/

package fr.inria.atlanmod.collaboro.ui.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.dialogs.MessageDialog;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Version;
import fr.inria.atlanmod.collaboro.history.Vote;
import fr.inria.atlanmod.collaboro.ui.Controller;
import fr.inria.atlanmod.collaboro.ui.DecisionEngine;

public class ExecuteDecisionEngineCommand extends AbstractHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		History history = Controller.INSTANCE.getHistory();
		DecisionEngine engine = DecisionEngine.INSTANCE;
		
		Version version = history.getVersions().get(0);
		for (Proposal proposal : version.getProposals()) {
			engine.resolveProposal(history, proposal);
			engine.resolveSolution(history,proposal);
		}
		
		String result = "Decision results: \n";
		for (Proposal proposal : version.getProposals()) {
			result += "- " + proposal.getStringState() + "\n";
		}
		
		MessageDialog.openInformation(null, "Decision state", result);
		
		Controller.INSTANCE.refreshView();
		return null;
	}
	
	
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
