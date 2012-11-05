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
import java.io.IOException;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.net4j.CDOSessionConfiguration;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CDOUtil;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.cdo.view.CDOAdapterPolicy;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.event.IEvent;
import org.eclipse.net4j.util.event.IListener;
import org.eclipse.net4j.util.om.OMPlatform;
import org.eclipse.net4j.util.om.log.PrintLogHandler;
import org.eclipse.net4j.util.om.trace.PrintTraceHandler;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.notation.Definition;

public class CDOModelManager extends ModelManager {

	private static final String REPOSITORY = "repo1";

	private static final String SERVER = "localhost:2036";

	// Track of history model
	private String historyPath;
	private History history;
	//	private CDOTransaction historyTransaction = null;

	// Track of abstract syntax
	private String abstractSyntaxPath;
	private EPackage ePackage;
	//	private CDOTransaction abstractSyntaxTransaction = null;

	// Track of concrete syntax
	private String concreteSyntaxPath;
	private Definition concreteSyntaxDefinition;
	//	private CDOTransaction concreteSyntaxTransaction  = null;

	// Resource unified
	ResourceSet rset = null;
	
	//	private CDOHelper helper;
	private CDOTransaction generalTransaction;
	private CDOSession session = null;
	private IManagedContainer container = null;
	private IConnector connector = null;
	
	public CDOModelManager() {
		CDOUtil.setLegacyModeDefault(true);
	}

	public boolean initialize(CDOResource cdoResource) {
		System.out.println("----> " + cdoResource.getPath());
		if(!cdoResource.getPath().endsWith(ECORE_EXTENSION)) {
			return false;
		}

		if(session != null) {
			session.close();
		}
		if(connector != null) {
			connector.close();
		}
		if(container != null) {
			container.deactivate();
		}

		// Enable logging and tracing
		OMPlatform.INSTANCE.setDebugging(true);
		OMPlatform.INSTANCE.addLogHandler(PrintLogHandler.CONSOLE);
		OMPlatform.INSTANCE.addTraceHandler(PrintTraceHandler.CONSOLE);

		// Prepare container
		container = ContainerUtil.createContainer();
		Net4jUtil.prepareContainer(container); // Register Net4j factories
		TCPUtil.prepareContainer(container); // Register TCP factories
		CDONet4jUtil.prepareContainer(container); // Register CDO factories
		container.activate();

		// Create connector
		connector = TCPUtil.getConnector(container, SERVER); //$NON-NLS-1$

		// Create configuration
		CDOSessionConfiguration configuration = CDONet4jUtil.createSessionConfiguration();
		configuration.setConnector(connector);
		configuration.setRepositoryName(REPOSITORY); //$NON-NLS-1$

		// Open session
		session = configuration.openSession();


		String path = cdoResource.getPath();
		abstractSyntaxPath = path;
		historyPath = path.substring(0, path.lastIndexOf(".")) + "." + HISTORY_EXTENSION;
		concreteSyntaxPath = path.substring(0, path.lastIndexOf(".")) + "." + NOTATION_EXTENSION;
		System.out.println(abstractSyntaxPath + " " + historyPath + " " + concreteSyntaxPath);

		generalTransaction = session.openTransaction();

		loadHistory();
		loadEcoreModel();
		loadNotation();

		generalTransaction.addListener(new IListener() {
			@Override
			public void notifyEvent(IEvent arg0) {
				System.out.println("EVENT " + arg0.toString());
				Controller.INSTANCE.refreshVersionView();
			}
		});
		generalTransaction.options().addChangeSubscriptionPolicy(CDOAdapterPolicy.CDO);
		

		return true;
	}

	@Override
	public History loadHistory() {
		// Get or create resource
		CDOResource resource = generalTransaction.getOrCreateResource(historyPath); //$NON-NLS-1$
		
		if (resource.getContents().get(0) != null) {
			Object object = resource.getContents().get(0);
			if (object instanceof History) {
				history = (History) object;
			}
		}

		return history;
	}

	@Override
	public Definition loadNotation() {
		// Get or create resource
		CDOResource resource = generalTransaction.getOrCreateResource(concreteSyntaxPath); //$NON-NLS-1$
		
		if (resource.getContents().get(0) != null) {
			Object object = resource.getContents().get(0);
			if (object instanceof Definition) {
				concreteSyntaxDefinition = (Definition) object;
			}
		}

		return concreteSyntaxDefinition;
	}

	@Override
	public EPackage loadEcoreModel() {
		// Get or create resource
		CDOResource resource = generalTransaction.getOrCreateResource(abstractSyntaxPath); //$NON-NLS-1$

		if (resource.getContents().get(0) != null) {
			Object object = resource.getContents().get(0);
			if (object instanceof EPackage) {
				ePackage = (EPackage) object;
			}
		}

		return ePackage;
	}



	@Override
	public EObject loadModel(File modelPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNotation() {
		System.out.println("Saving notation");

		CDOResource resource = generalTransaction.getOrCreateResource(concreteSyntaxPath); //$NON-NLS-1$
		
//		try {
//			resource.getContents().clear();  
//			resource.getContents().add(concreteSyntaxDefinition);
//			resource.save(null);
//		} catch (IOException e) {
//		}

		try {
			generalTransaction.commit();
		} catch (CommitException e) {
			e.printStackTrace();
		}

		loadNotation();
	}

	@Override
	public void saveEcoreModel() {
		System.out.println("Saving ecore model");

		CDOResource resource = generalTransaction.getOrCreateResource(abstractSyntaxPath); //$NON-NLS-1$

//		try {
//			resource.getContents().clear();  
//			resource.getContents().add(ePackage);
//			resource.save(null);
//		} catch (IOException e) {
//		}

		try {
			generalTransaction.commit();
		} catch (CommitException e) {
			e.printStackTrace();
		}

		loadEcoreModel();
	}

	@Override
	public void saveHistory() {
		System.out.println("Saving history");

		CDOResource resource = generalTransaction.getOrCreateResource(historyPath); //$NON-NLS-1$

//		try {
//			resource.getContents().clear();  
//			resource.getContents().add(history);
//			resource.save(null);
//		} catch (IOException e) {
//		}

		try {
			generalTransaction.commit();
		} catch (CommitException e) {
			e.printStackTrace();
		}

		loadHistory();
	}

	@Override
	public History getHistory() {
		return history;
	}

	@Override
	public Definition getNotation() {
		return concreteSyntaxDefinition;
	}

	@Override
	public EPackage getEcoreModel() {
		return ePackage;
	}

}
