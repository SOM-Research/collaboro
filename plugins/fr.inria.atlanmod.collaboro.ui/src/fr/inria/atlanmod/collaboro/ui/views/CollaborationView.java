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


import java.io.File;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.part.ViewPart;

import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.Vote;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.ui.CollaboroPlugin;
import fr.inria.atlanmod.collaboro.ui.Controller;

/**
 * This is the view for the details of the collaborations
 * 
 * @author Javier Canovas (javier.canovas@inria.fr)
 *
 */
public class CollaborationView extends ViewPart implements ISelectionListener {
	public static final String ID = "atlanmod.collaboro.ui.collaborationView";

	private Text proposedByText = null;
	private Text rationaleText = null;
	private Text votesAgreeText = null;
	private Text votesDisagreeText = null;

	private Composite parent = null;
	private Group collaborationGroup = null;
	private Group solutionGroup = null;
	private Composite mainGroup = null;
	private TableViewer tableCreation = null;
	private TreeViewer solutionViewer = null;
	private VoteUpdater voteUpdater = null;
	private Button saveButton = null;


	public class VoteUpdater {
		Text agree, disagree;
		Collaboration collaboration;

		public VoteUpdater(Collaboration collaboration, Text agree, Text disagree) {
			this.collaboration = collaboration;
			this.agree = agree;
			this.disagree = disagree;
		}


		public void update() {
			String votesAgreeString = "";
			String votesDisagreeString = "";			
			for(Vote vote : collaboration.getVotes()) {
				if(vote.isAgreement()) {
					votesAgreeString += vote.getUser().getId() + ", ";
				} else {
					votesDisagreeString += vote.getUser().getId() + ", ";
				}
			}
			agree.setText((votesAgreeString.length() == 0) ? "" : votesAgreeString.substring(0, votesAgreeString.length()-2));
			disagree.setText((votesDisagreeString.length() == 0) ? "" : votesDisagreeString.substring(0, votesDisagreeString.length()-2));
		}
	}

	/**
	 * Implements the double click listener for syntax elements. When the user double clicks
	 * on the referred, target or source elements, a new editor is shown
	 * 
	 * @author Javier Canovas (javier.canovas@inria.fr)
	 *
	 */
	public class ChangesDoubleClickListener implements IDoubleClickListener {
		TreeViewer viewer = null;

		public ChangesDoubleClickListener(TreeViewer viewer) {
			this.viewer = viewer;
		}

		@Override
		public void doubleClick(DoubleClickEvent event) {
			IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
			Object selectedElement = selection.getFirstElement();
			if (selectedElement instanceof SyntaxElementPropertySource) {
				SyntaxElementPropertySource propertySource = (SyntaxElementPropertySource) selectedElement;
				Object object = propertySource.getObject();

				if (object instanceof NotationElement) {
					NotationElement notationElement = (NotationElement) object;
					Controller.INSTANCE.openNotationEditor(notationElement);
				} else if (object instanceof EClass) {
					EClass eClass = (EClass) object;
					Controller.INSTANCE.openAbstractSyntaxEditor(eClass);
				}

			}
		}

	}
	
	public class SaveButtonListener implements SelectionListener {
		private Collaboration collaboration;

		public SaveButtonListener(Collaboration collaboration) {
			this.collaboration = collaboration;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			collaboration.setRationale(rationaleText.getText());
			Controller.INSTANCE.saveHistory();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) { }

		public void setCollaboration(Collaboration collaboration) {
			this.collaboration = collaboration;
		}
		
	}
	
	public class RationaleModifyListener implements ModifyListener {
		private Collaboration collaboration;

		public RationaleModifyListener(Collaboration collaboration) {
			this.collaboration = collaboration;
		}
		@Override
		public void modifyText(ModifyEvent e) {
			if(collaboration == null) return;
			Text text = (Text) e.getSource();
			collaboration.setRationale(text.getText());
		}
		public void setCollaboration(Collaboration collaboration) {
			this.collaboration = collaboration;
		}
	}

	/**
	 * Creates a new ADD model change
	 * 
	 * @author Javier Canovas (javier.canovas@inria.fr)
	 *
	 */
	class AddListener implements SelectionListener {
		private Solution solution;

		public AddListener(Solution solution) {
			this.solution = solution;
		}

		public void widgetSelected(SelectionEvent e) {
			Controller.INSTANCE.createAdd(solution);
			solutionViewer.refresh();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {

		}
	}

	/**
	 * Creates a new update model change
	 * 
	 * @author Javier Canovas (javier.canovas@inria.fr)
	 *
	 */
	class UpdateListener implements SelectionListener {
		private Solution solution;

		public UpdateListener(Solution solution) {
			this.solution = solution;
		}

		public void widgetSelected(SelectionEvent e) {
			Controller.INSTANCE.createUpdate(solution);
			solutionViewer.refresh();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {

		}
	}

	/**
	 * Creates a new delete model change
	 * 
	 * @author Javier Canovas (javier.canovas@inria.fr)
	 *
	 */
	class DeleteListener implements SelectionListener {
		private Solution solution;

		public DeleteListener(Solution solution) {
			this.solution = solution;
		}

		public void widgetSelected(SelectionEvent e) {
			Controller.INSTANCE.createDelete(solution);
			solutionViewer.refresh();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {

		}
	}

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 1;
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		FillLayout parentLayout = new FillLayout(SWT.VERTICAL);
		composite.setLayout(parentLayout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createCollaborationPart(composite);

		// Listening events
		getSite().getPage().addSelectionListener(this);
	}


	public void createCollaborationPart(Composite parent) {
		collaborationGroup = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		collaborationGroup.setLayout(gridLayout);
		gridLayout.numColumns = 2; 
		collaborationGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		collaborationGroup.setText("Collaboration");

		Label proposedByLabel = new Label(collaborationGroup, SWT.NONE);
		proposedByLabel.setText("Proposed by");

		Composite proposedAndButtons = new Composite(collaborationGroup, SWT.NONE);
		GridLayout gridLayout2 = new GridLayout();
		proposedAndButtons.setLayout(gridLayout2);
		gridLayout2.numColumns = 3;
		proposedAndButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		proposedByText = new Text(proposedAndButtons, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		proposedByText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		proposedByText.setText("");
				
		saveButton = new Button(proposedAndButtons, SWT.PUSH);
		saveButton.setText("");
		saveButton.setImage(WorkbenchImages.getImage(ISharedImages.IMG_ETOOL_SAVE_EDIT));
		
		Label rationaleLabel = new Label(collaborationGroup, SWT.NONE);
		rationaleLabel.setText("Rationale");				
		rationaleText = new Text(collaborationGroup, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
		rationaleText.setText("");
		rationaleText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));		

		Label votesAgreeLabel = new Label(collaborationGroup, SWT.NONE);
		votesAgreeLabel.setText("Votes Agree:");
		votesAgreeText = new Text(collaborationGroup, SWT.BORDER | SWT.READ_ONLY);
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = SWT.FILL;
		votesAgreeText.setLayoutData(gridData1);

		Label votesDisagreeLabel = new Label(collaborationGroup, SWT.NONE);
		votesDisagreeLabel.setText("Votes Disagree");
		votesDisagreeText = new Text(collaborationGroup, SWT.BORDER | SWT.READ_ONLY);
		votesDisagreeText.setLayoutData(gridData1);

		parent.layout(true);
	}

	@Override
	public void setFocus() {

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// System.out.println("Se detecto un cambio en la vista principal  desde " + this.ID + "\n" + part.getSite().getId() + " - " + selection);
		if(part.getSite().getId().equals(VersionView.ID)) {	
			TreeSelection treeSelection = (TreeSelection) selection;
			Object objectSelected = treeSelection.getFirstElement();

			if(objectSelected != null && objectSelected instanceof Collaboration) { 
				resetFields();

				Collaboration collaboration = (Collaboration) objectSelected;
				if(proposedByText != null) {
					proposedByText.setText((collaboration.getProposedBy() != null) ? collaboration.getProposedBy().getId() : "?");
				}

				if(rationaleText != null) {
					rationaleText.setText((collaboration.getRationale() == null) ? "" : collaboration.getRationale());
					rationaleText.addModifyListener(new RationaleModifyListener(collaboration));
					saveButton.addSelectionListener(new SaveButtonListener(collaboration));
				}

				if(votesAgreeText != null && votesDisagreeText != null) {
					voteUpdater = new VoteUpdater(collaboration, votesAgreeText, votesDisagreeText);
					Controller.INSTANCE.setVoteUpdater(voteUpdater);
					voteUpdater.update();
				}

				if (collaboration instanceof Solution) {
					Solution solution = (Solution) collaboration;

					solutionGroup = new Group(parent, SWT.NONE);
					GridLayout gridLayout = new GridLayout();
					gridLayout.numColumns = 3;
					solutionGroup.setLayout(gridLayout);
					solutionGroup.setText("Solution");			

					Button buttonAdd = new Button(solutionGroup, SWT.PUSH);
					GridData gridDataAddButton = new GridData(GridData.FILL, GridData.CENTER, true, false);
					buttonAdd.setLayoutData(gridDataAddButton);
					buttonAdd.setText("Add");
					buttonAdd.addSelectionListener(new AddListener(solution));

					Button buttonUpdate = new Button(solutionGroup, SWT.PUSH);
					GridData gridDataUpdateButton = new GridData(GridData.FILL, GridData.CENTER, true, false);
					buttonUpdate.setLayoutData(gridDataUpdateButton);
					buttonUpdate.setText("Update");
					buttonUpdate.addSelectionListener(new UpdateListener(solution));

					Button buttonDelete = new Button(solutionGroup, SWT.PUSH);
					GridData gridDataDeleteButton = new GridData(GridData.FILL, GridData.CENTER, true, false);
					buttonDelete.setLayoutData(gridDataDeleteButton);
					buttonDelete.setText("Delete");
					buttonDelete.addSelectionListener(new DeleteListener(solution));

					solutionViewer = new TreeViewer(solutionGroup, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FILL);
					Controller.INSTANCE.setChanges(solutionViewer);
					GridData gridDataViewer = new GridData(GridData.FILL_BOTH);
					gridDataViewer.horizontalSpan = 3;
					solutionViewer.getTree().setLayoutData(gridDataViewer);

					solutionViewer.setContentProvider(new ChangesContentProvider(solution));
					solutionViewer.setLabelProvider(new ChangesLabelProvider());
					solutionViewer.setInput("caca");

					solutionViewer.addDoubleClickListener(new ChangesDoubleClickListener(solutionViewer));

					MenuManager contextMenu = new MenuManager("#ViewerPopupMenu");
					Menu menu = contextMenu.createContextMenu(solutionViewer.getControl());

					solutionViewer.getControl().setMenu(menu);
					getSite().registerContextMenu(contextMenu, solutionViewer); 

					parent.layout(true);		

					getSite().setSelectionProvider(solutionViewer);
				} 
			}
		} else {
//			resetFields();
		}
	}

	public void resetFields() {
		if(solutionGroup != null) {
			Controller.INSTANCE.setChanges(null);
			solutionGroup.dispose();
			solutionGroup = null;
			parent.layout(true);			
			solutionViewer = null;
			getSite().setSelectionProvider(null);
		}
		proposedByText.setText("");
		votesAgreeText.setText("");
		votesDisagreeText.setText("");

		Listener[] listeners = rationaleText.getListeners(SWT.Modify);
		for(Listener listener : listeners) {
			if (listener instanceof TypedListener) {
				TypedListener typedListener = (TypedListener) listener;
				if (typedListener.getEventListener() instanceof CollaborationView.RationaleModifyListener) {
					CollaborationView.RationaleModifyListener modifyListener = (CollaborationView.RationaleModifyListener) typedListener.getEventListener();
					modifyListener.setCollaboration(null);
					rationaleText.removeListener(SWT.Modify, typedListener);
				}
			}
		}
		

		listeners = saveButton.getListeners(SWT.Selection);
		for(Listener listener : listeners) {
			if (listener instanceof TypedListener) {
				TypedListener typedListener = (TypedListener) listener;
				if (typedListener.getEventListener() instanceof CollaborationView.SaveButtonListener) {
					CollaborationView.SaveButtonListener selectionListener = (CollaborationView.SaveButtonListener) typedListener.getEventListener();
					selectionListener.setCollaboration(null);
					saveButton.removeListener(SWT.Selection, typedListener);
				}
			}
		}
	}
}




