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

package fr.inria.atlanmod.collaboro.ui.views;

import java.util.ArrayList;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EModelElementItemProvider;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.internal.keys.model.ModelElement;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

import fr.inria.atlanmod.collaboro.history.AbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement;
import fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.SyntaxElement;
import fr.inria.atlanmod.collaboro.history.Update;
import fr.inria.atlanmod.collaboro.history.impl.ModelChangeImpl;
import fr.inria.atlanmod.collaboro.history.provider.HistoryItemProviderAdapterFactory;
import fr.inria.atlanmod.collaboro.history.provider.ModelChangeItemProvider;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.notation.provider.NotationItemProviderAdapterFactory;
import fr.inria.atlanmod.collaboro.notation.util.NotationAdapterFactory;

public class ChangesContentProvider implements IStructuredContentProvider, ITreeContentProvider, IPropertySourceProvider {
	private Solution solution;
	
	public ChangesContentProvider(Solution solution) {
		super();
		this.solution = solution;
	}
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ModelChange) {
			ModelChange modelChange = (ModelChange) parentElement;
			
			SyntaxElement referred = modelChange.getReferredElement();
			SyntaxElement target = modelChange.getTarget();
			
			ArrayList<Object> result = new ArrayList<Object>();
			if(referred != null) result.add(referred);
			if(target != null) result.add(target);
			
			if (modelChange instanceof Update) {
				Update update = (Update) modelChange;
				if(update.getSource() != null) result.add(update.getSource());				
			}
			
			return result.toArray();
		} else if (parentElement instanceof SyntaxElement) {
			SyntaxElement syntaxElement = (SyntaxElement) parentElement;
			Object result = null;
			if (syntaxElement instanceof NewAbstractSyntaxElement) {
				NewAbstractSyntaxElement abstractSyntaxElement = (NewAbstractSyntaxElement) syntaxElement;
				EModelElement modelElement = abstractSyntaxElement.getElement();
				EcoreItemProviderAdapterFactory factory = new EcoreItemProviderAdapterFactory();
				result = new SyntaxElementPropertySource(modelElement, syntaxElement, (IItemPropertySource) factory.adapt(modelElement, IItemPropertySource.class));
			} else if (syntaxElement instanceof ExistingAbstractSyntaxElement) {
				ExistingAbstractSyntaxElement abstractSyntaxElement = (ExistingAbstractSyntaxElement) syntaxElement;
				EModelElement modelElement = abstractSyntaxElement.getElement();
				EcoreItemProviderAdapterFactory factory = new EcoreItemProviderAdapterFactory();
				result = new SyntaxElementPropertySource(modelElement, syntaxElement, (IItemPropertySource) factory.adapt(modelElement, IItemPropertySource.class));
			} else if (syntaxElement instanceof ConcreteSyntaxElement) {
				ConcreteSyntaxElement concreteSyntaxElement = (ConcreteSyntaxElement) syntaxElement;
				NotationElement notationElement = concreteSyntaxElement.getElement();
				NotationItemProviderAdapterFactory factory = new NotationItemProviderAdapterFactory();
				result = new SyntaxElementPropertySource(notationElement, syntaxElement, (IItemPropertySource) factory.adapt(notationElement, IItemPropertySource.class));
			}
			return new Object[] { result };
			
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof ModelChange) {
			return null;
		} else if (element instanceof SyntaxElement) {
			SyntaxElement syntaxElement = (SyntaxElement) element;
			return syntaxElement.eContainer();
		} else if (element instanceof SyntaxElementPropertySource) {
			SyntaxElementPropertySource elementPropertySource = (SyntaxElementPropertySource) element;
			return elementPropertySource.getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof ModelChange) {
			return true;
		} else if (element instanceof SyntaxElement) {
			return true;
		}
		return false;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return solution.getChanges().toArray();
	}


	@Override
	public IPropertySource getPropertySource(Object object) {
		System.out.println("llamando para " + object);
		return null;
	}

}
