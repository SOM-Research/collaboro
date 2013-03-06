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

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.dialogs.MessageDialog;

import fr.inria.atlanmod.collaboro.history.Add;
import fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement;
import fr.inria.atlanmod.collaboro.history.Delete;
import fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.SyntaxElement;
import fr.inria.atlanmod.collaboro.history.Update;
import fr.inria.atlanmod.collaboro.ui.Controller;

public class ApplyChangesCommand implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {
	}
	
	private String digestSyntaxElement(SyntaxElement element) {
		String result = "";
		
		if (element instanceof NewAbstractSyntaxElement) {
			NewAbstractSyntaxElement newAbstract = (NewAbstractSyntaxElement) element;
			return newAbstract.getElement().eClass().getName();
		} else if (element instanceof ExistingAbstractSyntaxElement) {
			ExistingAbstractSyntaxElement existingAbstract = (ExistingAbstractSyntaxElement) element;
			return existingAbstract.getElement().eClass().getName();
		} else if (element instanceof ConcreteSyntaxElement) {
			ConcreteSyntaxElement concrete = (ConcreteSyntaxElement) element;
			return concrete.getElement().getClass().getName();
		}
		
		return result;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		List<ModelChange> changes = Controller.INSTANCE.applyChanges();
		
		int addNumber = 0;
		int updateNumber = 0;
		int deleteNumber = 0;
		
		String result = "Changes applied: \n";
		
		for(ModelChange change : changes) {
			if (change instanceof Add) {
				Add add = (Add) change;
				addNumber++;
			} else if (change instanceof Delete) {
				Delete delete = (Delete) change;
				deleteNumber++;
			} else if (change instanceof Update) {
				Update delete = (Update) change;
				updateNumber++;
			}
		}
		
		result += "  - " + addNumber + " ADDs applied\n";
		result += "  - " + updateNumber + " UPDATEs applied\n";
		result += "  - " + deleteNumber + " DELETEs applied\n";
		
		MessageDialog.openInformation(null, "Changes applied", result);
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
