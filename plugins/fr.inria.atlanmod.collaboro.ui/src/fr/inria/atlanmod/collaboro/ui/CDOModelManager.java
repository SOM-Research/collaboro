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

//import org.eclipse.emf.cdo.eresource.CDOResource;
//import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
//import org.eclipse.emf.cdo.session.CDOSession;
//import org.eclipse.emf.cdo.session.CDOSessionConfiguration;
//import org.eclipse.emf.cdo.transaction.CDOTransaction;
//import org.eclipse.emf.cdo.util.CDOUtil;
//import org.eclipse.emf.cdo.util.CommitException;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.ecore.EPackage;
//import org.eclipse.net4j.Net4jUtil;
//import org.eclipse.net4j.connector.IConnector;
//import org.eclipse.net4j.tcp.TCPUtil;
//import org.eclipse.net4j.util.container.ContainerUtil;
//import org.eclipse.net4j.util.container.IManagedContainer;
//import org.eclipse.net4j.util.om.OMPlatform;
//import org.eclipse.net4j.util.om.log.PrintLogHandler;
//import org.eclipse.net4j.util.om.trace.PrintTraceHandler;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.notation.Definition;

public class CDOModelManager { //extends ModelManager {
//	private CDOHelper helper;
//	
//	private CDOSession session;
//	private CDOTransaction transaction;
//	private CDOResource historyResource;
//	
//	/**
//	 * This class helps to manage the CDO conections. It is in charge of creating 
//	 * CDOSessions from a conection. It is very important to call to deactivate method
//	 * when it is not going to be used. 
//	 * @author jlcanovas
//	 *
//	 */
//	class CDOHelper {
//		private String CDOServer;
//		private String CDORepository;
//		private CDOSessionConfiguration configuration;
//		private IManagedContainer container;
//		private IConnector connector;
//		private EPackage ePackage;
//	
//		/**
//		 * The main constructor
//		 * @param CDOServer Path to the server
//		 * @param CDORepository Path to the repository
//		 * @param ePackage EPackage to which conforms the models
//		 */
//		CDOHelper(String CDOServer, String CDORepository, EPackage ePackage) {
//			this.CDOServer = CDOServer;
//			this.CDORepository = CDORepository;
//			this.ePackage = ePackage;
//		}
//		
//		/**
//		 * Deactivate the CDO conection
//		 */
//		public void deactivate() {
//			if(connector.isConnected()) {
//				connector.close(); 
//			}
//			if(container.isActive()) {
//				container.deactivate();
//			}
//		}
//		
//		/**
//		 * Configures the CDO connection to be used
//		 */
//		private void configure() {
//		    OMPlatform.INSTANCE.setDebugging(false);
//		    OMPlatform.INSTANCE.addLogHandler(PrintLogHandler.CONSOLE);
//		    OMPlatform.INSTANCE.addTraceHandler(PrintTraceHandler.CONSOLE);
//		    
//		    // Prepare container
//		    container = ContainerUtil.createContainer();
//		    Net4jUtil.prepareContainer(container); // Register Net4j factories
//		    TCPUtil.prepareContainer(container); // Register TCP factories
//		    CDONet4jUtil.prepareContainer(container); // Register CDO factories
//		    container.activate(); 		    
//
//		    // Create connector
//		    connector = TCPUtil.getConnector(container, CDOServer); //$NON-NLS-1$		    
//
//		    // Create configuration
//		    configuration = CDONet4jUtil.createSessionConfiguration();
//		    
////		    configuration.setConnector(connector);
////		    configuration.setRepositoryName(CDORepository); 
//		}
//				
//		/**
//		 * Opens a new session from the CDO connection
//		 * @return
//		 */
//		public CDOSession openSession() {
//		    // Open session
//		    CDOSession session = configuration.openSession();
//		    session.getPackageRegistry().putEPackage(ePackage);
//		    return session;
//		}		
//	}
//	
//	@Override
//	public boolean initialize(File modelBeingTracked) {
//		helper = new CDOHelper("localhost:2036", "repo1", HistoryPackage.eINSTANCE);
//		helper.configure();
//		return true;
//	}
//
//	@Override
//	public File getNotation() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public File getEcoreModel() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public History loadHistory() {
//		session = helper.openSession();
//		transaction = session.openTransaction();
//		historyResource = transaction.getOrCreateResource("default.history");
//		
//		EObject eObject = historyResource.getContents().get(0);
//		if (eObject instanceof History) {
//			History history = (History) eObject;
//			return history;
//		}
//		
//		return null;
//	}
//
//	@Override
//	public Definition loadNotation() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public EPackage loadEcoreModel() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void saveHistory(History history) {
//		historyResource.getContents().clear();
//		historyResource.getContents().add(history);
//		
//		try {
//			transaction.commit();
//			session.close();
//			
//			session = helper.openSession();
//			transaction = session.openTransaction();
//			historyResource = transaction.getOrCreateResource("default.history");
//		} catch (CommitException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	@Override
//	public void saveNotationModel(Definition notationDefinition) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void saveEcoreModel(EPackage ePackage) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public EObject loadModel(File modelPath) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
