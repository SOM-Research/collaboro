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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class SyntaxElementContentProvider implements IStructuredContentProvider {
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {	}

	@Override
	public void dispose() { }

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof EPackage) {
			EPackage ePackage = (EPackage) inputElement;

			List<Object> result = new ArrayList<Object>();

			result.add(ePackage);
			for(EClassifier eClassifier : ePackage.getEClassifiers()){
				result.add(eClassifier);
			}
			for(EClassifier eClassifier : ePackage.getEClassifiers()){
				if (eClassifier instanceof EClass) {
					EClass eClass = (EClass) eClassifier;
					for(EStructuralFeature eStructuralFeature : eClass.getEStructuralFeatures()) {
						result.add(eStructuralFeature);
					}
				}
			}
			return result.toArray();
		} else if (inputElement instanceof List) {
			List list = (List) inputElement;
			return list.toArray();
		} 
		return null;
	}
}
