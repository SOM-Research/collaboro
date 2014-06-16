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

package fr.inria.atlanmod.collaboro.backend;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.NotationFactory;
import fr.inria.atlanmod.collaboro.notation.NotationPackage;

public class LocalModelManager extends ModelManager {

	// Track of history model
	private File historyModelFile;
	private History history;
	
	// Track of abstract syntax
	private File ecoreModelFile;
	private EPackage ePackage;

	// Track of concrete syntax
	private File notationModelFile;
	private Definition notationDefinition;
	
	// Resource unified
	ResourceSet rset = null;
	
	public File getHistoryFile() {
		return historyModelFile;
	}
	public History getHistory() {
		return history;
	}

	public File getEcoreModelFile() {
		return ecoreModelFile;
	}
	public EPackage getEcoreModel() {
		return ePackage;
	}

	public File getNotationFile() {
		return notationModelFile;
	}
	public Definition getNotation() {
		return notationDefinition;
	}
	
	public boolean initialize(File modelBeingTracked) {
		
		System.out.println("El archivo que esta llegando del modelo ecore: "+modelBeingTracked.getAbsolutePath());
		if(!modelBeingTracked.getName().endsWith(".ecore")) 
			return false;

		if(ecoreModelFile != null && modelBeingTracked.getAbsoluteFile().equals(ecoreModelFile.getAbsoluteFile())) {
			return false;
		} 

		// Inferring nameFiles
		historyModelFile = inferHistoryModel(modelBeingTracked);		
		if(! historyModelFile.exists()) {
			System.out.println("No History found");
			ecoreModelFile = null;
			return false;
		} else {
			ecoreModelFile = modelBeingTracked;
			notationModelFile = inferNotationModel(historyModelFile);

			// Preparing the resource
			rset = new ResourceSetImpl();
			rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(HISTORY_EXTENSION, new EcoreResourceFactoryImpl());
			rset.getPackageRegistry().put(HistoryPackage.eNS_URI, HistoryPackage.eINSTANCE);
			rset.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);

			rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(MODEL_EXTENSION, new EcoreResourceFactoryImpl());
			rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(NOTATION_EXTENSION, new EcoreResourceFactoryImpl());
			rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(ECORE_EXTENSION, new EcoreResourceFactoryImpl());

			// Loading the history model
			loadHistory();
			// Loading the ecore model (abstract syntax)
			loadEcoreModel();
			// Loading the notation model (concrete syntax)
			loadNotation();
			
			return true;
		}
	}
	
	@Override
	public History loadHistory() {
		if(rset == null) return null;

		History result = null;
		
		if(historyModelFile != null && historyModelFile.exists()) {
			Resource res = rset.getResource(URI.createFileURI(historyModelFile.getAbsolutePath()), true);
			try {
				res.load(null);
			} catch (IOException e) {
				e.printStackTrace();
			}

			result = (History) res.getContents().get(0);
		}
		
		history = result;
		
		return result;
	}

	@Override
	public Definition loadNotation() {
		if(rset == null) return null;

		Definition result = null;
		
		if(notationModelFile != null && notationModelFile.exists()) { 
			Resource res3 = rset.getResource(URI.createFileURI(notationModelFile.getAbsolutePath()), true);
			try {
				res3.load(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = (Definition) res3.getContents().get(0);
		} else {
			result = NotationFactory.eINSTANCE.createDefinition();
		}
		
		notationDefinition = result;
		
		return result;
	}
	

	public EPackage loadEcoreModel() {
		if(rset == null) return null;
		
		EPackage result = null;
		
		if(ecoreModelFile != null && ecoreModelFile.exists()) {
			Resource res2 = rset.getResource(URI.createFileURI(ecoreModelFile.getAbsolutePath()), true);
			try {
				res2.load(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = (EPackage) res2.getContents().get(0);
		}
		
		ePackage = result;
		
		return result;
	}
	

	public EObject loadModel(File modelPath) {
		if(rset != null && ePackage != null) {
			rset.getPackageRegistry().put(ePackage.getNsURI(), ePackage);

			rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(MODEL_EXTENSION, new EcoreResourceFactoryImpl());

			Resource res = rset.getResource(URI.createFileURI(modelPath.getAbsolutePath()), true);
			try {
				res.load(null);
			} catch (IOException e) {
				e.printStackTrace();
			}

			EObject eObject = res.getContents().get(0);
			return eObject;
		} 
		return null;

	}
	

	/**
	 * Infers the path to the history model tracking the changes in modelBeingTracked
	 * @param modelBeingTracked
	 * @return
	 */
	private File inferHistoryModel(File modelBeingTracked) {
		String parent =  modelBeingTracked.getParentFile().toString();
		String name = modelBeingTracked.getName();
		String historyName = name.substring(0, name.lastIndexOf(".")) + "." + HISTORY_EXTENSION;
		return new File(parent + File.separator + historyName);
	}

	/**
	 * Infers the path to the notatoin model
	 * @param modelBeingTracked
	 * @return
	 */
	private File inferNotationModel(File modelBeingTracked) {
		String parent =  modelBeingTracked.getParentFile().toString();
		String name = modelBeingTracked.getName();
		String notationName = name.substring(0, name.lastIndexOf(".")) + "." + NOTATION_EXTENSION;
		return new File(parent + File.separator + notationName);
	}
	
	public void saveHistory() {
		// Saving history model
		if(historyModelFile != null && rset != null) {
			Resource res = rset.getResource(URI.createFileURI(historyModelFile.getAbsolutePath()), true);

			try {
				res.getContents().clear();  // TODO fix this!
				res.getContents().add(history);
				res.save(null);
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}
	}
	
	public void saveNotation() {
		if(notationModelFile != null && rset != null) {
			Resource res = null;

			if(notationModelFile.exists()) 
				res = rset.getResource(URI.createFileURI(notationModelFile.getAbsolutePath()), true);
			else
				res = rset.createResource(URI.createFileURI(notationModelFile.getAbsolutePath()));

			try {
				res.getContents().clear();  // TODO fix this!
				res.getContents().add(notationDefinition);
				res.save(null);
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}
	}
	

	public void saveEcoreModel() {
		if(ecoreModelFile != null && rset != null) {
			Resource res = rset.getResource(URI.createFileURI(ecoreModelFile.getAbsolutePath()), true);

			try {
				res.getContents().clear(); // TODO fix this!
				res.getContents().add(ePackage);
				res.save(null);
			} catch (IOException e) {
//				e.printStackTrace(); 
			}
		}
	}
	
	@Override
	public void closeConnection() {
		this.saveEcoreModel();
		this.saveHistory();
		this.saveNotation();
	}

}
