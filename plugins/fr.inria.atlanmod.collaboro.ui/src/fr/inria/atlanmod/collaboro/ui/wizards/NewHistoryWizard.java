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
	protected HistoryNewUsers newUsersPage;

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

	public class HistoryNewUsers extends WizardPage {
		protected HistoryNewUsers(String pageName) {
			super(pageName);
		}

		private ListViewer userList;
		private List<User> users = new ArrayList<User>();
		Text userNameText;

		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			GridLayout layout = new GridLayout();
			composite.setLayout(layout);
			layout.numColumns = 1;
			composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

			Group newUser = new Group(composite, SWT.NONE);
			newUser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			GridLayout gridLayout = new GridLayout();
			gridLayout.numColumns = 3;
			newUser.setLayout(gridLayout);
			newUser.setText("Add new user");	

			Label label = new Label(newUser, SWT.NONE);
			label.setText("User name:");
			label.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, false, true));

			userNameText = new Text(newUser, SWT.BORDER);
			userNameText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));

			Button addButton = new Button(newUser, SWT.NONE);
			addButton.setLayoutData(new GridData(GridData.END, GridData.FILL, false, true));
			addButton.setText("Add");
			addButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					User user = HistoryFactory.eINSTANCE.createUser();
					user.setId(userNameText.getText());					
					users.add(user);
					userList.refresh();
					userNameText.setText("");
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) { }
			});

			Group listGroup = new Group(composite, SWT.NONE);
			listGroup.setBounds(new Rectangle(0, 0, 500, 500));
			listGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.numColumns = 1;
			listGroup.setLayout(gridLayout2);
			listGroup.setText("User list");	

			userList = new ListViewer(listGroup, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			userList.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			userList.setContentProvider(new IStructuredContentProvider() {
				@Override
				public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {	}

				@Override
				public void dispose() {	}

				@Override
				public Object[] getElements(Object inputElement) {
					if (inputElement instanceof List) {
						List list = (List) inputElement;
						return list.toArray();						
					}
					return null;
				}
			});
			userList.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					if (element instanceof User) {
						User user = (User) element;
						return user.getId();
					} 
					return "User unknown";
				}
			});
			userList.setInput(users);

			setControl(composite);

		}

		public List<User> getUsers() {
			return users;
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

		newUsersPage = new HistoryNewUsers("Adding users");
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
