package fr.inria.atlanmod.collaboro.ui.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.net4j.CDOSessionConfiguration;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CDOUtil;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.cdo.view.CDOAdapterPolicy;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.om.OMPlatform;
import org.eclipse.net4j.util.om.log.PrintLogHandler;
import org.eclipse.net4j.util.om.trace.PrintTraceHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import fr.inria.atlanmod.collaboro.Synchronization.EventList;
import fr.inria.atlanmod.collaboro.Synchronization.SynchronizationFactory;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Version;
import fr.inria.atlanmod.collaboro.history.VersionHistory;
import fr.inria.atlanmod.collaboro.history.VersionHistoryType;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.NotationFactory;
import fr.inria.atlanmod.collaboro.notation.NotationPackage;
import fr.inria.atlanmod.collaboro.ui.Controller;
import fr.inria.atlanmod.collaboro.ui.ModelManager;

public class NewRemoteCollaboroProjectWizard extends
NewLocalCollaboroProjectWizard {

	protected CDOConfiguration newCDOConfigurationPage;


	private CDOTransaction generalTransaction;
	private CDOSession session = null;
	private IManagedContainer container = null;
	private IConnector connector = null;


	private String abstractSyntaxPath;


	private String historyPath;


	private String concreteSyntaxPath;


	private EPackage ePackage;


	private Definition concreteSyntaxDefinition;


	private History history;


	private String synchronizationPath;


	private EventList synchronization;

	public NewRemoteCollaboroProjectWizard() {
	}



	public class CDOConfiguration extends WizardPage {
		protected CDOConfiguration(String pageName) {
			super(pageName);
		}


		Text serverDescriptionText;
		Text repositoryNameText;

		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			GridLayout layout = new GridLayout();
			composite.setLayout(layout);
			layout.numColumns = 1;
			composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

			Group serverDescription = new Group(composite, SWT.NONE);
			serverDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			GridLayout gridLayout = new GridLayout();
			gridLayout.numColumns = 2;
			serverDescription.setLayout(gridLayout);
			serverDescription.setText("Server Description");	

			Label label = new Label(serverDescription, SWT.NONE);
			label.setText("Server description:");
			label.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, false, true));

			serverDescriptionText = new Text(serverDescription, SWT.BORDER);
			serverDescriptionText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
			serverDescriptionText.setText("localhost:2036");	

			Label label2 = new Label(serverDescription, SWT.NONE);
			label2.setText("Repository:");
			label2.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, false, true));

			repositoryNameText = new Text(serverDescription, SWT.BORDER);
			repositoryNameText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
			repositoryNameText.setText("repo1");


			setControl(composite);

		}

		public String getRepositoryName() {
			return repositoryNameText.getText();
		}

		public String getServerDescription() {
			return serverDescriptionText.getText();
		}

	}

	@Override
	public void addPages() {
		newProjectPage = new WizardNewProjectCreationPage("Creating Collaboro Project");
		newProjectPage.setTitle("Creating Collaboro Project");
		newProjectPage.setDescription("A new Collaboro project to develop a DSL collaboratively project will be created");
		addPage(newProjectPage);

		newUsersPage = new HistoryNewUsers("Adding users");
		newUsersPage.setTitle("Community Users");
		newUsersPage.setDescription("Add new users to the community");
		addPage(newUsersPage);

		newCDOConfigurationPage = new CDOConfiguration("CDO Configuration");
		newCDOConfigurationPage.setTitle("CDO configuration");
		newCDOConfigurationPage.setDescription("Prepare CDO connection");
		addPage(newCDOConfigurationPage);
	}





	private boolean initializeCDO() {

		CDOUtil.setLegacyModeDefault(true);

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
		connector = TCPUtil.getConnector(container, this.newCDOConfigurationPage.getServerDescription()); //$NON-NLS-1$

		// Create configuration
		CDOSessionConfiguration configuration = CDONet4jUtil.createSessionConfiguration();
		configuration.setConnector(connector);
		configuration.setRepositoryName(this.newCDOConfigurationPage.getRepositoryName()); //$NON-NLS-1$

		// Open session
		session = configuration.openSession();


		String path = "/"+this.newProjectPage.getProjectName()+"/"+this.newProjectPage.getProjectName()+ModelManager.ECORE_EXTENSION;
		abstractSyntaxPath = "/"+this.newProjectPage.getProjectName()+"/"+this.newProjectPage.getProjectName()+"."+ModelManager.ECORE_EXTENSION;
		historyPath = "/"+this.newProjectPage.getProjectName()+"/"+this.newProjectPage.getProjectName() +"."+ ModelManager.HISTORY_EXTENSION;
		concreteSyntaxPath = "/"+this.newProjectPage.getProjectName()+"/"+this.newProjectPage.getProjectName() + "." + ModelManager.NOTATION_EXTENSION;
		synchronizationPath = "/"+this.newProjectPage.getProjectName()+"/"+this.newProjectPage.getProjectName() + ".sync";
		System.out.println(abstractSyntaxPath + " " + historyPath + " " + concreteSyntaxPath);

		generalTransaction = session.openTransaction();

		createHistory();
		createEcoreModel();
		createNotation();
		createSynchronization();
		
		List<User> users = newUsersPage.getUsers();
		if(users != null) 
			history.getUsers().addAll(users);

		//		generalTransaction.addObjectHandler(new CDOObjectHandler() {
		//			@Override
		//			public void objectStateChanged(CDOView view, CDOObject object,
		//					CDOState oldState, CDOState newState) {
		//				System.out.println("Object: " + object.toString()
		//						+ " transitioned to " + newState);
		//				
		//			}
		//		});
		//		generalTransaction.addListener(new IListener() {
		//			@Override
		//			public void notifyEvent(IEvent arg0) {
		//				System.out.println("EVENT " + arg0.toString());
		//				Controller.INSTANCE.refreshVersionView();
		////				Workbench.getInstance().getDisplay().syncExec(new Runnable() {
		////					@Override
		////					public void run() {
		////						MessageBox message = new MessageBox(Workbench.getInstance().getDisplay().getActiveShell());
		////						message.setMessage("change");
		////						message.open();
		////					}
		////				});
		//			}
		//		});
		generalTransaction.options().addChangeSubscriptionPolicy(CDOAdapterPolicy.CDO);
		try {
			generalTransaction.commit();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return true;
	}


	public History createHistory() {
		// Get or create resource
		CDOResource resource = generalTransaction.getOrCreateResource(historyPath); //$NON-NLS-1$

		history = createInitialHistoryModel();
		resource.getContents().add(history);
		
		return history;
	}


	public Definition createNotation() {
		// Get or create resource
		CDOResource resource = generalTransaction.getOrCreateResource(concreteSyntaxPath); //$NON-NLS-1$

		concreteSyntaxDefinition = createInitiaNotationModel();
		resource.getContents().add(concreteSyntaxDefinition);

		return concreteSyntaxDefinition;
	}


	public EPackage createEcoreModel() {
		// Get or create resource
		CDOResource resource = generalTransaction.getOrCreateResource(abstractSyntaxPath); //$NON-NLS-1$

		ePackage = createInitiaEcorePackage(this.newProjectPage.getProjectName());

		resource.getContents().add(ePackage);

		return ePackage;
	}

	public EventList createSynchronization() {
		// Get or create resource
		CDOResource resource = generalTransaction.getOrCreateResource(synchronizationPath); //$NON-NLS-1$

		synchronization = SynchronizationFactory.eINSTANCE.createEventList();
		resource.getContents().add(synchronization);
		
		return synchronization;
	}
	

	@Override
	public boolean performFinish() {
		final String projectName = newProjectPage.getProjectName();

		initializeCDO();

		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor progressMonitor) {
				progressMonitor.beginTask("Creating Collaboro Project", 10);

				progressMonitor.subTask("Registering packages");
				ResourceSet rset = new ResourceSetImpl();
				rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Controller.HISTORY_EXTENSION, new EcoreResourceFactoryImpl());
				rset.getPackageRegistry().put(HistoryPackage.eNS_URI, HistoryPackage.eINSTANCE);
				rset.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);

				rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Controller.MODEL_EXTENSION, new EcoreResourceFactoryImpl());
				rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Controller.NOTATION_EXTENSION, new EcoreResourceFactoryImpl());
				rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Controller.ECORE_EXTENSION, new EcoreResourceFactoryImpl());

				progressMonitor.worked(1);
				progressMonitor.subTask("Creating the project");
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				IProject project = root.getProject(projectName);
				try {
					project.create(progressMonitor);
					project.open(progressMonitor);
				} catch(Exception e) {
					e.printStackTrace();
					MessageDialog dialog = new MessageDialog(getShell(), "Error", null, "Error when creating the project in the workspace", MessageDialog.ERROR, new String[] {"OK"}, 0);
					dialog.open();
					progressMonitor.done();
					return;
				}

				progressMonitor.worked(6);
				progressMonitor.done();
			}
		};

		try {
			getContainer().run(false, false, operation);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;
	}

	protected Definition createInitiaNotationModel() {
		Definition definition = NotationFactory.eINSTANCE.createDefinition();
		return definition;
	}

	protected EPackage createInitiaEcorePackage(String mainName) {
		EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ePackage.setName(mainName);
		ePackage.setNsURI("http://" + mainName);
		ePackage.setNsPrefix(mainName);
		return ePackage;
	}

	protected History createInitialHistoryModel() {
		History history = HistoryFactory.eINSTANCE.createHistory();

		VersionHistory versionHistory = HistoryFactory.eINSTANCE.createVersionHistory();
		versionHistory.setType(VersionHistoryType.TRUNK);
		history.getHistories().add(versionHistory);

		Version version = HistoryFactory.eINSTANCE.createVersion();
		version.setId("0.1");
		versionHistory.getVersions().add(version);

		return history;
	}

}
