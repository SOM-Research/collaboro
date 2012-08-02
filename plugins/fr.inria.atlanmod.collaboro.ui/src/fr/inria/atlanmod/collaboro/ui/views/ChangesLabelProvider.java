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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.provider.EModelElementItemProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import fr.inria.atlanmod.collaboro.history.Add;
import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.Delete;
import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.SyntaxElement;
import fr.inria.atlanmod.collaboro.history.Update;
import fr.inria.atlanmod.collaboro.history.Vote;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.ui.CollaboroPlugin;

public class ChangesLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof Add) {
			Add add = (Add) element;
			return "Add";
		} else if (element instanceof Update) {
			Update update = (Update) element;
			return "Update";
		} else if (element instanceof Delete) {
			Delete delete = (Delete) element;
			return "Delete";
		} else if (element instanceof SyntaxElement) {
			SyntaxElement syntaxElement = (SyntaxElement) element;
			
			EReference reference = syntaxElement.eContainmentFeature();
			if(reference.getName().equals("referredElement")) {
				return "Referred";
			} else if (reference.getName().equals("target")) {
				return "Target";
			} else if (reference.getName().equals("source")) {
				return "Source";
			} else {
				return "Unknown";
			}
		} else if (element instanceof SyntaxElementPropertySource) {
			SyntaxElementPropertySource syntaxElementPropertySource = (SyntaxElementPropertySource) element;
			Object syntaxElement = syntaxElementPropertySource.getObject();
			if (syntaxElement instanceof EModelElement) {
				EModelElement modelElement = (EModelElement) syntaxElement;
				return modelElement.eClass().getName() + " " + ((modelElement instanceof EClassifier) ? (((EClassifier) modelElement).getName()) : " ");
			} else if (syntaxElement instanceof NotationElement) {
				NotationElement notationElement = (NotationElement) syntaxElement;
				return notationElement.eClass().getName() + " " + notationElement.getId();
			}
		} 
		return super.getText(element);
	}
	

	protected String imagePath(Object element) {
		if (element instanceof Add) {
			return "icons/add.png";
		} else if (element instanceof Update) {
			return "icons/update.png";
		} else if (element instanceof Delete) {
			return "icons/delete.png";
		} else if (element instanceof SyntaxElement) {
			SyntaxElement syntaxElement = (SyntaxElement) element;
			
			EReference reference = syntaxElement.eContainmentFeature();
			if(reference.getName().equals("referredElement")) {
				return "icons/referred.png";
			} else if (reference.getName().equals("target")) {
				return "icons/target.png";
			} else if (reference.getName().equals("source")) {
				return "icons/source.png";
			} else {
				return null;
			}
		}  else if (element instanceof SyntaxElementPropertySource) {
			SyntaxElementPropertySource syntaxElementPropertySource = (SyntaxElementPropertySource) element;
			Object syntaxElement = syntaxElementPropertySource.getObject();
			if (syntaxElement instanceof EModelElement) {
				return "icons/abstract.png";
			} else if (syntaxElement instanceof NotationElement) {
				return "icons/concrete.png";
			}
		} 
		
		return null;

	}
	

	public Image getImage(Object element) {
		return CollaboroPlugin.getImage(imagePath(element)).createImage();
	}
}
