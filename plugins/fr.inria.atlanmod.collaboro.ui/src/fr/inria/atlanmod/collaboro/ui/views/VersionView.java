/*******************************************************************************
 * Copyright (c) 2008, 2013
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Javier Canovas (javier.canovas@inria.fr) 
 *******************************************************************************/
package fr.inria.atlanmod.collaboro.ui.views;


import java.util.List;

import org.eclipse.core.internal.resources.File;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.internal.EditorReference;
import org.eclipse.ui.part.ViewPart;

import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Version;
import fr.inria.atlanmod.collaboro.ui.CollaboroPlugin;
import fr.inria.atlanmod.collaboro.ui.Controller;

/**
 * @author Javier Canovas (javier.canovas@inria.fr)
 *
 */
@SuppressWarnings("restriction")
public class VersionView extends ViewPart implements ISelectionListener, IPartListener2 {
	public static final String ID = "atlanmod.collaboro.ui.versionView";

	public static final String LOGIN_ACTION_ICON  = "icons/loginAction.png";
	public static final String FILTER_ACTION_ICON = "icons/filterAction.png";
	public static final String CREATE_ACTION_ICON = "icons/createAction.png";
	public static final String VERSIONS_ICON = "icons/versions.png";
	public static final String DELETE_ICON = "icons/delete.png";

	public static final String ECORE_PLUGIN_ID = "org.eclipse.emf.ecore.presentation.EcoreEditorID";
	public static final String PACKAGE_EXPLORER_PLUGIN_ID = "org.eclipse.jdt.ui.PackageExplorer";
	public static final String PROJECT_EXPLORER_PLUGIN_ID = "org.eclipse.ui.navigator.ProjectExplorer";
	public static final String CDO_SESSIONS_VIEW = "org.eclipse.emf.cdo.ui.CDOSessionsView";
	public static final String CDO_EDITOR = "org.eclipse.emf.cdo.ui.CDOEditor";

	private TreeViewer collaborationsViewer;

	/**
	 * The constructor
	 */
	public VersionView() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		collaborationsViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		collaborationsViewer.setContentProvider(new VersionViewContentProvider());
		collaborationsViewer.setLabelProvider(new VersionViewLabelProvider());
		collaborationsViewer.setInput(getViewSite());

		// Contributing ActionBars

		Action loginAction = new Action() {
			public void run() {
				InputDialog loginDialog = null;
				boolean logged = false;
				do {
					loginDialog = new InputDialog(collaborationsViewer.getControl().getShell(), "Collaboro login", "Username", "Developer 1", null);
					if (loginDialog.open() == Window.OK) {
						boolean userExists = Controller.INSTANCE.loginUser(loginDialog.getValue());
						if(!userExists) {
							MessageDialog.openError(collaborationsViewer.getControl().getShell(), "Login error", "The user does not exist");
						} else {
							logged = true;
						}
					} else {
						logged = true;
					}
				} while (!logged);
			}
		};
		loginAction.setText("Login");
		loginAction.setToolTipText("Login");
		loginAction.setImageDescriptor(CollaboroPlugin.getImage(LOGIN_ACTION_ICON));

		Action changeVersionAction = new Action() {
			public void run() {

				if(Controller.INSTANCE.isLogged()) {
					ElementListSelectionDialog dialog = new ElementListSelectionDialog(collaborationsViewer.getControl().getShell(), new LabelProvider() {
						@Override
						public String getText(Object element) {
							if (element instanceof Version) {
								Version version = (Version) element;
								return version.getId();
							}
							return super.getText(element);
						}
					});
					List<Version> versions = Controller.INSTANCE.getHistory().getHistories().get(Controller.INSTANCE.getHistoryTracked()).getVersions();
					dialog.setElements(versions.toArray());
					dialog.setTitle("Select the version to change:");
					if(dialog.open() == Window.OK) {
						Object[] result = dialog.getResult();
						if(result.length > 0) {
							Object selectedResult = result[0];
							if (selectedResult instanceof Version) {
								Version selectedVersion = (Version) selectedResult;
								Controller.INSTANCE.changeVersion(selectedVersion);							
							}
						}
					}
				} else {
					MessageDialog.openInformation(collaborationsViewer.getControl().getShell(), "Not looged in", "You are not logged in");

				}

			}
		};
		changeVersionAction.setText("Change Version");
		changeVersionAction.setToolTipText("Change Version");
		changeVersionAction.setImageDescriptor(CollaboroPlugin.getImage(VERSIONS_ICON));

		Action filterAction = new Action() {
			public void run() {
				MessageDialog.openInformation(collaborationsViewer.getControl().getShell(), "Under development", "This action will allow filter the view");

			}
		};
		filterAction.setText("Filters");
		filterAction.setToolTipText("Filters");
		filterAction.setImageDescriptor(CollaboroPlugin.getImage(FILTER_ACTION_ICON));

		Action createProposalAction = new Action() {
			public void run() {
				if(Controller.INSTANCE.isLogged()) {
					Controller.INSTANCE.createProposal();
					collaborationsViewer.refresh();
				} else {
					MessageDialog.openInformation(collaborationsViewer.getControl().getShell(), "Not looged in", "You are not logged in");

				}
			}
		};
		createProposalAction.setText("Create proposal");
		createProposalAction.setToolTipText("Create proposal");
		createProposalAction.setImageDescriptor(CollaboroPlugin.getImage(CREATE_ACTION_ICON));

		Action deleteProposalAction = new Action() {
			public void run() {
				if(Controller.INSTANCE.isLogged()) {
					ITreeSelection selection = (ITreeSelection) collaborationsViewer.getSelection();
					Object object = selection.getFirstElement();
					if(object != null && object instanceof Collaboration) {
						Controller.INSTANCE.deleteCollaboration((Collaboration) object);
						collaborationsViewer.refresh();
					}
				} else {
					MessageDialog.openInformation(collaborationsViewer.getControl().getShell(), "Not looged in", "You are not logged in");

				}
			}
		};
		deleteProposalAction.setText("Delete proposal");
		deleteProposalAction.setToolTipText("Delete proposal");
		deleteProposalAction.setImageDescriptor(CollaboroPlugin.getImage(DELETE_ICON));

		IActionBars bars = getViewSite().getActionBars();

		bars.getMenuManager().add(loginAction);
		bars.getToolBarManager().add(loginAction);

		bars.getMenuManager().add(changeVersionAction);
		bars.getToolBarManager().add(changeVersionAction);

		bars.getMenuManager().add(filterAction);
		bars.getToolBarManager().add(filterAction);

		bars.getMenuManager().add(createProposalAction);
		bars.getToolBarManager().add(createProposalAction);

		bars.getMenuManager().add(deleteProposalAction);
		bars.getToolBarManager().add(deleteProposalAction);

		// Contributing Contex menu
		MenuManager contextMenu = new MenuManager("#ViewerPopupMenu");
		//		contextMenu.setRemoveAllWhenShown(true);
		//		contextMenu.addMenuListener(new IMenuListener() {
		//			public void menuAboutToShow(IMenuManager manager) {
		//				manager.add(loginAction);
		//				manager.add(new Separator());
		//				manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		//			}
		//		});
		Menu menu = contextMenu.createContextMenu(collaborationsViewer.getControl());

		collaborationsViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, collaborationsViewer);

		// Providing events		
		getSite().setSelectionProvider(collaborationsViewer);

		// Listening events
		getSite().getPage().addSelectionListener(this); 
		//		getSite().getPage().addSelectionListener("org.eclipse.jdt.ui.PackageExplorer", this); 
		//		getSite().getPage().addSelectionListener("org.eclipse.emf.ecore.presentation.EcoreEditorID", this); 

		getSite().getPage().addPartListener(this);

		Controller.INSTANCE.setVersionView(this); 
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		//		System.out.println("\nSe detecto un cambio en la vista principal desde " + this.ID + "\n" + part.getSite().getId() + " - " + selection);

		if(part.getSite().getId().equals(ECORE_PLUGIN_ID)) {
			//			System.out.println(selection.getClass().getCanonicalName());
			TreeSelection treeSelection = (TreeSelection) selection;
			for(TreePath treePath : treeSelection.getPaths()) {
				//				System.out.println("treepath: " + treePath.getLastSegment());
			}

			Object element = treeSelection.getFirstElement();
			//			System.out.println("object: " + element);
			collaborationsViewer.refresh();

		}

		if(part.getSite().getId().equals(PACKAGE_EXPLORER_PLUGIN_ID) || part.getSite().getId().equals(PROJECT_EXPLORER_PLUGIN_ID)) {
			TreeSelection treeSelection = (TreeSelection) selection;
			Object element = treeSelection.getFirstElement();
			if (element instanceof File) {
				File file = (File) element;
				java.io.File ioFile = new java.io.File(file.getLocation().toOSString());
				Controller.INSTANCE.loadHistory(ioFile);
			}
			collaborationsViewer.refresh();
		}		

		if(part.getSite().getId().equals(CDO_SESSIONS_VIEW)) {
			if(selection != null) {
				Object element = ((TreeSelection) selection).getFirstElement(); 

				if(element != null) {
					System.out.println(element.getClass().getName());
					Controller.INSTANCE.loadHistory(element);
				}
			}
			collaborationsViewer.refresh();
		}
	}

	public void refresh() {
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				collaborationsViewer.refresh();
			}
		});
	}
	
	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		//		System.out.println("partActivated " + partRef);

	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		//		System.out.println("partBroughtToTop " + partRef);

	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		Controller.INSTANCE.saveHistory();		
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		//		System.out.println("partDeactivated " + partRef);

	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		//		System.out.println("partOpened " + partRef);

	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		//		System.out.println("partHidden " + partRef);

	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		//		System.out.println("partVisible " + partRef);

	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		//		System.out.println("partInputChanged " + partRef);

	}

}
