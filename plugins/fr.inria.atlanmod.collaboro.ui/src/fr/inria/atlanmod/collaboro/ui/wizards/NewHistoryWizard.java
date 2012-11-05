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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Version;
import fr.inria.atlanmod.collaboro.history.VersionHistory;
import fr.inria.atlanmod.collaboro.history.VersionHistoryType;
import fr.inria.atlanmod.collaboro.notation.NotationPackage;
import fr.inria.atlanmod.collaboro.ui.Controller;

public class NewHistoryWizard extends Wizard implements INewWizard {
	private static final String DEFAULT_FILENAME = "defaultHistoryFileName";
	protected IStructuredSelection selection;
	protected IWorkbench workbench;

	protected HistoryNewFileCreationPage newFileCreationPage;
	protected NewUsersWizardPage newUsersPage;

	public class HistoryNewFileCreationPage extends WizardNewFileCreationPage {
		public HistoryNewFileCreationPage(String pageId, IStructuredSelection selection) {
			super(pageId, selection);
		}

		@Override
		protected boolean validatePage() {
			if (super.validatePage()) {
				String extension = new Path(getFileName()).getFileExtension();
				if (extension == null || !Controller.HISTORY_EXTENSION.equals(extension)) {
					setErrorMessage("Extension must be '" + Controller.HISTORY_EXTENSION + "'");
					return false;
				}
				return true;
			}
			return false;
		}

		public IFile getModelFile() {
			return ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName()));
		}
	}

	@Override
	public void addPages() {
		newFileCreationPage = new HistoryNewFileCreationPage("Creating history model", selection);
		newFileCreationPage.setTitle("Collaboro Model");
		newFileCreationPage.setDescription("Create a new Collaboro Model");
		newFileCreationPage.setFileName("history" + "." + Controller.HISTORY_EXTENSION);
		addPage(newFileCreationPage);

		if (selection != null && !selection.isEmpty()) {
			Object selectedElement = selection.iterator().next();
			if (selectedElement instanceof IResource) {
				String defaultFileName = DEFAULT_FILENAME;

				// Get the resource parent, if its a file.
				IResource selectedResource = (IResource) selectedElement;
				if (selectedResource.getType() == IResource.FILE) {
					defaultFileName = selectedResource.getName().substring(0, selectedResource.getName().lastIndexOf("."));
					selectedResource = selectedResource.getParent();
				}

				if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
					// Set this for the container.
					newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

					// Make up a unique new name here.
					String defaultModelBaseFilename = defaultFileName;
					String defaultModelFilenameExtension = Controller.HISTORY_EXTENSION;
					String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;

					System.out.println("-----> " + modelFilename);
					newFileCreationPage.setFileName(modelFilename);
				}
			}
		}

		newUsersPage = new NewUsersWizardPage("Adding users");
		newUsersPage.setTitle("Community Users");
		newUsersPage.setDescription("Add new users to the community");
		addPage(newUsersPage);
	}

	public NewHistoryWizard() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;

	}

	@Override
	public boolean performFinish() {
		final IFile file = newFileCreationPage.getModelFile();

		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor progressMonitor) {
				ResourceSet rset = new ResourceSetImpl();
				rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Controller.HISTORY_EXTENSION, new EcoreResourceFactoryImpl());
				rset.getPackageRegistry().put(HistoryPackage.eNS_URI, HistoryPackage.eINSTANCE);
				rset.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);

				rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Controller.MODEL_EXTENSION, new EcoreResourceFactoryImpl());
				rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Controller.NOTATION_EXTENSION, new EcoreResourceFactoryImpl());
				rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Controller.ECORE_EXTENSION, new EcoreResourceFactoryImpl());

				URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
				Resource res = rset.createResource(fileURI);

				History historyModel = createInitialHistoryModel();
				res.getContents().add(historyModel);

				List<User> users = newUsersPage.getUsers();
				if(users != null) 
					historyModel.getUsers().addAll(users);

				try {
					res.save(null);
				} catch (Exception exception) {
					exception.printStackTrace();
				} finally {
					progressMonitor.done();
				}
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
