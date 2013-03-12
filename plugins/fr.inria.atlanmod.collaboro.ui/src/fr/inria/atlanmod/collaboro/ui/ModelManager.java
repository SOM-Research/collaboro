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

package fr.inria.atlanmod.collaboro.ui;

import java.io.File;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.notation.Definition;

public abstract class ModelManager {	
	// Extensions for the models supported
	public static final String HISTORY_EXTENSION = "history";
	public static final String NOTATION_EXTENSION = "notation";
	public static final String ECORE_EXTENSION = "ecore";
	public static final String MODEL_EXTENSION = "xmi";
	
	// getters
	public abstract Definition getNotation();
	public abstract EPackage getEcoreModel();
	public abstract History getHistory();
	
	// Loading methods 
	public abstract History loadHistory();
	public abstract Definition loadNotation();
	public abstract EPackage loadEcoreModel();
	
	// Saving methods
	/**
	 * Saves the current history model. It also saves the notation model.
	 */
	public abstract void saveHistory();
	
	/**
	 * Saves the current notation model
	 */
	public abstract void saveNotation();
	
	/**
	 * Saves the current ecore model (abstract syntax metamodel)
	 */
	public abstract void saveEcoreModel();
	
	/**
	 * Loads an ecore model. Normally the model conforms to the metamodel being tracked.
	 * 
	 * @param modelPath
	 * @return
	 */
	public abstract EObject loadModel(File modelPath);
	
	/**
	 * Save at logout and close the connection if needed
	 */
	public abstract void closeConnection();
	
	
}
