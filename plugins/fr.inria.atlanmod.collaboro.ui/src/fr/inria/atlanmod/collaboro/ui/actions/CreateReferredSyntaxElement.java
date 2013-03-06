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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import fr.inria.atlanmod.collaboro.ui.wizards.ReferredWizard;

public class CreateReferredSyntaxElement implements IViewActionDelegate {
	private ISelection selection = null;
	private Shell shell = null;

	@Override
	public void run(IAction action) { 
		ReferredWizard wizard = new ReferredWizard();
		wizard.init(shell, (IStructuredSelection) selection);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.setPageSize(600, 500);
		dialog.create();
		dialog.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	@Override
	public void init(IViewPart view) {
		System.out.println(view.getTitle() +" - " + view.getClass().getName());
		shell = view.getViewSite().getShell();
	}

}
