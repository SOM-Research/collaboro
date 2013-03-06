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

package fr.inria.atlanmod.collaboro.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.ui.Controller;

public class CreateVoteNegativeAction extends CreateCollaborationAction implements IViewActionDelegate {
	
	@Override
	public void run(IAction action) {
		if(Controller.INSTANCE.isLogged()) {
			Controller.INSTANCE.createVoteNegative(this.collaboration);
		} else {
			MessageDialog.openInformation(this.shell, "Not looged in", "You are not logged in");
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			Object element = treeSelection.getFirstElement();
			if (element instanceof Collaboration) {
				Collaboration collaboration = (Collaboration) element;
				this.collaboration = collaboration;
			}
		}
		
	}

	@Override
	public void init(IViewPart view) {
		shell = view.getViewSite().getShell();
	}
}
