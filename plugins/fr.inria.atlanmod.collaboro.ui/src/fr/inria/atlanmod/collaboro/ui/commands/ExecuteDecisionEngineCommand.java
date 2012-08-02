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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.dialogs.MessageDialog;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Version;
import fr.inria.atlanmod.collaboro.ui.Controller;
import fr.inria.atlanmod.collaboro.ui.DecisionEngine;

public class ExecuteDecisionEngineCommand extends AbstractHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {

	}

	private String getStringState(Proposal proposal) {
		return "Proposal " + proposal.getId() + ((proposal.isAccepted()) ? " accepted " : " not accepted " ) + "\n  " + proposal.getSols().size() + " solutions proposed\n  " + ((proposal.getSelected() == null) ? "No solution selected": "Solution " + proposal.getSelected().getId() + " selected");
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		History history = Controller.INSTANCE.getHistory();
		DecisionEngine engine = DecisionEngine.INSTANCE;

		Version version = history.getHistories().get(Controller.INSTANCE.getHistoryTracked()).getVersions().get(0);
		for (Proposal proposal : version.getProposals()) {
			engine.resolveProposal(history, proposal);
			engine.resolveSolution(history, proposal);
		}

		String result = "Decision results: \n";
		for (Proposal proposal : version.getProposals()) {
			result += "- " + getStringState(proposal) + "\n";
		}

		MessageDialog.openInformation(null, "Decision state", result);

		Controller.INSTANCE.refreshView();
		Controller.INSTANCE.saveHistory();
		return null;
	}



	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {

	}

}
