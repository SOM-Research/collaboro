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
package fr.inria.atlanmod.collaboro.ui.views;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.ui.Controller;

/**
 * @author Javier Canovas (javier.canovas@inria.fr)
 *
 */
public class VersionViewContentProvider implements
IStructuredContentProvider, ITreeContentProvider {


	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
//		System.out.println("Se detectaron cambios " + ((oldInput == null) ? "null" : oldInput.getClass().getCanonicalName()) + " - " +	((newInput == null) ? "null" : newInput.getClass().getCanonicalName())); 
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Proposal) {
			Proposal proposal = (Proposal) parentElement;
			
			ArrayList<Object> result = new ArrayList<Object>();
			result.addAll(proposal.getSols());
			result.addAll(proposal.getComments());
			
			return result.toArray();
		} else if (parentElement instanceof Solution) {
			Solution solution = (Solution) parentElement;
			return solution.getComments().toArray();
		}
		return null;
	}


	@Override
	public Object getParent(Object element) {
		if (element instanceof Proposal) {
			return null;
		}
		return ((EObject) element).eContainer();
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Proposal) {
			Proposal proposal = (Proposal) element;
			return !proposal.getSols().isEmpty() || !proposal.getComments().isEmpty();
		} else if (element instanceof Solution) {
			Solution solution = (Solution) element;
			return !solution.getComments().isEmpty();
		}
		return false;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return Controller.INSTANCE.getProposals().toArray();
	}

}
