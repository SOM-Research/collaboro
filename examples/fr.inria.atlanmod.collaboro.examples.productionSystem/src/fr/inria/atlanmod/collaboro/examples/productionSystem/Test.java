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

package fr.inria.atlanmod.collaboro.examples.productionSystem;



import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import fr.inria.atlanmod.collaboro.history.Add;
import fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement;
import fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.notation.NotationPackage;
import fr.inria.atlanmod.collaboro.notation.Rectangle;

public class Test {
	private static void performChanges() {
ResourceSet rset = new ResourceSetImpl();
		
		rset.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		rset.getPackageRegistry().put(HistoryPackage.eNS_URI, HistoryPackage.eINSTANCE);
		rset.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new EcoreResourceFactoryImpl());
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
	
		EcorePlugin.getPlatformResourceMap().put("fr.inria.atlanmod.collaboro.examples.productionSystem", URI.createFileURI("/home/jcanovas/eclipses/eclipse-3.7.1-xtext/workspace/fr.inria.atlanmod.collaboro.examples.productionSystem/"));
		
		Resource r1 = rset.getResource(URI.createPlatformResourceURI("/fr.inria.atlanmod.collaboro.examples.productionSystem/model/productionSystem-history1.ecore.xmi", true), true);
		
		try {
			r1.load(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		History history = (History) r1.getContents().get(0);
		
		Resource r2 = rset.getResource(URI.createPlatformResourceURI("/fr.inria.atlanmod.collaboro.examples.productionSystem/model/productionSystem-notation.xmi", true), true);
		try {
			r2.load(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Rectangle rectangle = (Rectangle) r2.getContents().get(0);
		
		Resource r3 = rset.getResource(URI.createPlatformResourceURI("/fr.inria.atlanmod.collaboro.examples.productionSystem/model/productionSystem.ecore", true), true);
		try {
			r3.load(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EPackage ePackage = (EPackage) r3.getContents().get(0);

		List<ModelChange> changes = history.getHistories().get(0).getVersions().get(0).getProposals().get(0).getSols().get(0).getChanges();
		for(ModelChange mc : changes) {
			Add addChange = (Add) mc;
			
			ExistingAbstractSyntaxElement ease = HistoryFactory.eINSTANCE.createExistingAbstractSyntaxElement();
			EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
			ease.setElement(eClass);
			addChange.setReferredElement(ease);
			
			ConcreteSyntaxElement cse = HistoryFactory.eINSTANCE.createConcreteSyntaxElement();
			cse.setElement(rectangle);
			
			addChange.setTarget(cse);
		}

		try {
			r1.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void checkChanges() {
ResourceSet rset = new ResourceSetImpl();
		
		rset.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		rset.getPackageRegistry().put(HistoryPackage.eNS_URI, HistoryPackage.eINSTANCE);
		rset.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new EcoreResourceFactoryImpl());
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
	
		EcorePlugin.getPlatformResourceMap().put("fr.inria.atlanmod.collaboro.examples.productionSystem", URI.createFileURI("/home/jcanovas/eclipses/eclipse-3.7.1-xtext/workspace/fr.inria.atlanmod.collaboro.examples.productionSystem/"));
		
		Resource r1 = rset.getResource(URI.createPlatformResourceURI("/fr.inria.atlanmod.collaboro.examples.productionSystem/model/productionSystem-history1.ecore.xmi", true), true);
		
		try {
			r1.load(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		History history = (History) r1.getContents().get(0);
		
		Resource r2 = rset.getResource(URI.createPlatformResourceURI("/fr.inria.atlanmod.collaboro.examples.productionSystem/model/productionSystem-notation.xmi", true), true);
		try {
			r2.load(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Rectangle rectangle = (Rectangle) r2.getContents().get(0);
		
		Resource r3 = rset.getResource(URI.createPlatformResourceURI("/fr.inria.atlanmod.collaboro.examples.productionSystem/model/productionSystem.ecore", true), true);
		try {
			r3.load(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EPackage ePackage = (EPackage) r3.getContents().get(0);

		List<ModelChange> changes = history.getHistories().get(0).getVersions().get(0).getProposals().get(0).getSols().get(0).getChanges();
		for(ModelChange mc : changes) {
			Add addChange = (Add) mc;
			
			ExistingAbstractSyntaxElement ease = (ExistingAbstractSyntaxElement) addChange.getReferredElement();
			System.out.println(ease.getElement().getClass().getName());
			
			ConcreteSyntaxElement cse = (ConcreteSyntaxElement) addChange.getTarget();
			System.out.println(cse.getElement().getClass().getName());
		}

	}
	
	public static void main(String[] args) {
		Test.performChanges();
		Test.checkChanges();
	}
}
