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

package fr.inria.atlanmod.collaboro.ui.wizards;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.LabelProvider;

import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class SyntaxElementLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof EPackage) {
			EPackage ePackage = (EPackage) element;
			return "EPackage " + ePackage.getName();
		} else if (element instanceof NotationElement) {
			NotationElement notationElement = (NotationElement) element;
			return notationElement.eClass().getName() + " " + notationElement.getId();
		} else if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			return "EClass " + eClass.getName();
		} else if (element instanceof EEnum) {
			EEnum eEnum = (EEnum) element;
			return "EEnum " + eEnum.getName();
		} else if (element instanceof EAttribute) {
			EAttribute eAttribute = (EAttribute) element;
			return "EAttribute " + eAttribute.getName() + " from " + eAttribute.eContainer().eClass().getName() + " " + ((eAttribute.eContainer() instanceof EClassifier) ? (((EClassifier) eAttribute.eContainer()).getName()) : " ");
		} else if (element instanceof EReference) {
			EReference eReference = (EReference) element;
			return "EReference " + eReference.getName() + " from " + eReference.eContainer().eClass().getName() + " " + ((eReference.eContainer() instanceof EClassifier) ? (((EClassifier) eReference.eContainer()).getName()) : " ");
		} 
		return "Element unknown";
	}		
}
