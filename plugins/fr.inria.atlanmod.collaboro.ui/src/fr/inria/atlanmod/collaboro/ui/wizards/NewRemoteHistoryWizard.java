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

import java.util.List;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.net4j.CDOSession;
import org.eclipse.emf.cdo.net4j.CDOSessionConfiguration;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CDOUtil;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
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
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

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

public class NewRemoteHistoryWizard extends Wizard implements INewWizard {

	protected RemoteHistoryPage remoteHistoryPage;
	protected NewUsersWizardPage newUsersPage;

	public class RemoteHistoryPage extends WizardPage {
		Text serverText;
		Text repositoryText;
		Text languageNameText;

		protected RemoteHistoryPage(String pageName) {
			super(pageName);
		}

		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			GridLayout layout = new GridLayout();
			composite.setLayout(layout);
			layout.numColumns = 1;
			composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

			Group configuration = new Group(composite, SWT.NONE);
			configuration.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			GridLayout gridLayout = new GridLayout();
			gridLayout.numColumns = 2;
			configuration.setLayout(gridLayout);
			configuration.setText("CDO Configuration");	

			Label serverLabel = new Label(configuration, SWT.NONE);
			serverLabel.setText("Server:");
			serverLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, false, true));

			serverText = new Text(configuration, SWT.BORDER);
			serverText.setText("localhost:2036");
			serverText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));

			Label repositoryLabel = new Label(configuration, SWT.NONE);
			repositoryLabel.setText("Repository:");
			repositoryLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, false, true));

			repositoryText = new Text(configuration, SWT.BORDER);
			repositoryText.setText("repo1");
			repositoryText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));

			Group language = new Group(composite, SWT.NONE);
			language.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.numColumns = 2;
			language.setLayout(gridLayout2);
			language.setText("Language Configuration");	

			Label languageNameLabel = new Label(language, SWT.NONE);
			languageNameLabel.setText("Name:");
			languageNameLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, false, true));

			languageNameText = new Text(language, SWT.BORDER);
			languageNameText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));


			setControl(composite);
		}

		public String getServer() {
			return serverText.getText();
		}

		public String getRepository() {
			return repositoryText.getText();
		}

		public String getLanguageName() {
			return languageNameText.getText();
		}
	}

	@Override
	public void addPages() {
		remoteHistoryPage = new RemoteHistoryPage("Configuration");
		remoteHistoryPage.setTitle("Collaboro model");
		remoteHistoryPage.setDescription("Create a new remote Collaboro Model");
		addPage(remoteHistoryPage);

		newUsersPage = new NewUsersWizardPage("Adding users");
		newUsersPage.setTitle("Community Users");
		newUsersPage.setDescription("Add new users to the community");
		addPage(newUsersPage);
	}

	@Override
	public boolean performFinish() {
		String server = remoteHistoryPage.getServer();
		String repository = remoteHistoryPage.getRepository();
		String languageName = remoteHistoryPage.getLanguageName();

		// Enable logging and tracing
		OMPlatform.INSTANCE.setDebugging(true);
		OMPlatform.INSTANCE.addLogHandler(PrintLogHandler.CONSOLE);
		OMPlatform.INSTANCE.addTraceHandler(PrintTraceHandler.CONSOLE);

		// Set legacy
		CDOUtil.setLegacyModeDefault(true);
		
		// Prepare container
		IManagedContainer container = ContainerUtil.createContainer();
		Net4jUtil.prepareContainer(container); // Register Net4j factories
		TCPUtil.prepareContainer(container); // Register TCP factories
		CDONet4jUtil.prepareContainer(container); // Register CDO factories
		container.activate();

		// Create connector
		IConnector connector = TCPUtil.getConnector(container, server); //$NON-NLS-1$

		// Create configuration
		CDOSessionConfiguration configuration = CDONet4jUtil.createSessionConfiguration();
		configuration.setConnector(connector);
		configuration.setRepositoryName(repository); //$NON-NLS-1$

		// Open session
		CDOSession session = configuration.openSession();
		
		session.getPackageRegistry().putEPackage(NotationPackage.eINSTANCE);
		session.getPackageRegistry().putEPackage(HistoryPackage.eINSTANCE);

		// Open transaction
		CDOTransaction transaction = session.openTransaction();

		// Get or create resource
		CDOResource resource = transaction.getOrCreateResource("/" + languageName + ".history"); //$NON-NLS-1$
		History initialHistory = createInitialHistoryModel();
		resource.getContents().add(initialHistory);

		resource = transaction.getOrCreateResource("/" + languageName + ".ecore"); //$NON-NLS-1$
		EPackage initialEPackage = EcoreFactory.eINSTANCE.createEPackage();
		initialEPackage.setName(languageName);
		resource.getContents().add(initialEPackage);

		resource = transaction.getOrCreateResource("/" + languageName + ".notation"); //$NON-NLS-1$
		Definition initialNotationDefinition = NotationFactory.eINSTANCE.createDefinition();
		resource.getContents().add(initialNotationDefinition);

		try {
			transaction.commit();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session.close();
		connector.close();
		container.deactivate();

		return true;
	}


	protected History createInitialHistoryModel() {
		History history = HistoryFactory.eINSTANCE.createHistory();

		VersionHistory versionHistory = HistoryFactory.eINSTANCE.createVersionHistory();
		versionHistory.setType(VersionHistoryType.TRUNK);
		history.getHistories().add(versionHistory);

		Version version = HistoryFactory.eINSTANCE.createVersion();
		version.setId("0.1");
		versionHistory.getVersions().add(version);
		
		List<User> users = newUsersPage.getUsers();
		if(users != null) 
			history.getUsers().addAll(users);

		return history;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}

}
