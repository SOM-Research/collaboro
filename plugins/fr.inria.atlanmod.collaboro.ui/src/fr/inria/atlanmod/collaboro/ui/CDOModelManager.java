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
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.CDOState;
import org.eclipse.emf.cdo.common.revision.delta.CDOFeatureDelta;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.internal.ui.editor.CDOEditor;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.net4j.CDOSessionConfiguration;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOCommitContext;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.transaction.CDOTransactionFinishedEvent;
import org.eclipse.emf.cdo.transaction.CDOTransactionHandler;
import org.eclipse.emf.cdo.transaction.CDOTransactionHandlerBase;
import org.eclipse.emf.cdo.ui.CDOEditorUtil;
import org.eclipse.emf.cdo.util.CDOUtil;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.cdo.view.CDOAdapterPolicy;
import org.eclipse.emf.cdo.view.CDOObjectHandler;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.cdo.view.CDOViewAdaptersNotifiedEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.internal.cdo.object.CDOLegacyWrapper;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench;

import fr.inria.atlanmod.collaboro.Synchronization.Event;
import fr.inria.atlanmod.collaboro.Synchronization.EventList;
import fr.inria.atlanmod.collaboro.Synchronization.SynchronizationFactory;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.notation.Definition;

public class CDOModelManager extends ModelManager implements IPropertyListener, IPartListener2{

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

	private EventList synchronization;

	private String synchronizationPath;

	private CDOTransaction synchronizationTransaction;

	private Event oldEvent = null;

	public CDOModelManager() {
		CDOUtil.setLegacyModeDefault(true);
		eventFromThisUser = new LinkedList<Event>();
		final IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		win.getPartService().addPartListener(this);

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
		synchronizationPath = path.substring(0, path.lastIndexOf(".")) + ".sync";
		System.out.println(abstractSyntaxPath + " " + historyPath + " " + concreteSyntaxPath);

		generalTransaction = session.openTransaction();
		synchronizationTransaction = session.openTransaction();

		loadHistory();
		loadEcoreModel();
		loadNotation();
		loadSynchronization();

		synchronizationTransaction.addListener(new IListener() {

			@Override
			public void notifyEvent(IEvent arg0) {
				System.out.println("synchronization listener "+Controller.INSTANCE.getLoggedUser().getId());
				System.out.println(arg0.toString());
				if (arg0 instanceof CDOViewAdaptersNotifiedEvent) {
					System.err.println(arg0.toString());
					Event e = synchronization.getEvents().get(synchronization.getEvents().size()-1);
					if(e != null) {
						String loggedUser = Controller.INSTANCE.getLoggedUser().getId();
						if (! e.getUser().equals(loggedUser)) {
							if (oldEvent != e) {
								oldEvent = e;
								System.out.println(e.getModifiedModel() +"|"+e.getModifiedElement()+"|"+e.getTimeStamp()+"|"+e.getUser());

								IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
								final Display disp = PlatformUI.getWorkbench().getDisplay();
								disp.asyncExec(new Runnable() {

									@Override
									public void run() {
										Shell activeShell = disp.getShells()[0];
										MessageBox message = new MessageBox(activeShell);
										Event ev = synchronization.getEvents().get(synchronization.getEvents().size()-1);
										String status;
										if (ev.getStatus().equals("DIRTY")){
											status = " is modifying ";
										} else {
											status = " has finish to modify ";
										}

										message.setMessage("User "+ev.getUser()+status+ev.getModifiedModel());
										message.open();
									}
								});

							}
						}
					}
				}
			}
		});


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


	public EventList loadSynchronization() {
		// Get or create resource
		CDOResource resource = synchronizationTransaction.getOrCreateResource(synchronizationPath); //$NON-NLS-1$

		if (resource.getContents().get(0) != null) {
			Object object = resource.getContents().get(0);
			if (object instanceof EventList) {
				synchronization = (EventList) object;
			}
		}

		return synchronization;
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

	private List<Event> eventFromThisUser;

	private String oldValue = "";

	@Override
	public void propertyChanged(Object source, int propId) {
		//		synchronized (this) {
		if (((CDOEditor)(source)).getSite().getId().equals(CDOEditorUtil.EDITOR_ID)) {
			if (propId == IEditorPart.PROP_DIRTY) {
				String newValue = Controller.INSTANCE.getLoggedUser().getId()+((CDOEditor)(source)).getEditorInput().getName()+((CDOEditor) source).isDirty();
				if (!oldValue.equals(newValue)) {
					oldValue = newValue;
					Event ev = SynchronizationFactory.eINSTANCE.createEvent();
					ev.setTimeStamp(System.currentTimeMillis());
					ev.setModifiedElement(((CDOEditor)(source)).getSelection().toString());
					ev.setModifiedModel( ((CDOEditor)(source)).getEditorInput().getName());
					ev.setUser(Controller.INSTANCE.getLoggedUser().getId());

					CDOEditor ed = (CDOEditor) source;
					if (ed.isDirty()) {
						ev.setStatus("DIRTY");
					} else {
						ev.setStatus("CLEAN");
					}
					eventFromThisUser.add(ev);
					synchronization.getEvents().add(ev);
					try {
						synchronizationTransaction.commit();
					} catch (CommitException e) {
						e.printStackTrace();
					}
				}
			}
			//		}
		}




	}

	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		if (partRef.getPart(true).getSite().getId().equals(CDOEditorUtil.EDITOR_ID)) {
			IEditorPart ed = (IEditorPart) (partRef.getPart(true));
			ed.addPropertyListener(this);
		}
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeConnection() {
		this.saveEcoreModel();
		this.saveHistory();
		this.saveNotation();
		this.closeSync();
		generalTransaction.close();
		if(session != null) {
			session.close();
		}
		if(connector != null) {
			connector.close();
		}
		if(container != null) {
			container.deactivate();
		}
	}




	//TODO a close operation to close the CDO session in the end and remove the event created by this user
	protected void closeSync() {
		synchronization.getEvents().removeAll(eventFromThisUser);
		try {
			synchronizationTransaction.commit();
		} catch (CommitException e) {
			e.printStackTrace();
		}
		eventFromThisUser.clear();
		synchronizationTransaction.close();
	}


}
