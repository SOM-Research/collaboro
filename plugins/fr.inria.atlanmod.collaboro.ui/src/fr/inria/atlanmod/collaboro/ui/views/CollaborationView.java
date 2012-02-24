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


import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import fr.inria.atlanmod.collaboro.history.Add;
import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Delete;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.Update;
import fr.inria.atlanmod.collaboro.history.Vote;
import fr.inria.atlanmod.collaboro.ui.Controller;

/**
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
	private TableViewer tableCreation = null;


	class ReferredEditingSupport extends EditingSupport {

		private final TableViewer viewer;

		public ReferredEditingSupport(TableViewer viewer) {
			super(viewer);
			this.viewer = viewer;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return new TextCellEditor(viewer.getTable());
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			return ((ModelChange) element).getReferredElement();
		}

		@Override
		protected void setValue(Object element, Object value) {
			((ModelChange) element).setReferredElement(String.valueOf(value));
			viewer.refresh();
		}
	}
	
	class TargetEditingSupport extends EditingSupport {

		private final TableViewer viewer;

		public TargetEditingSupport(TableViewer viewer) {
			super(viewer);
			this.viewer = viewer;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return new TextCellEditor(viewer.getTable());
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			return ((ModelChange) element).getTarget();
		}

		@Override
		protected void setValue(Object element, Object value) {
			((ModelChange) element).setTarget(String.valueOf(value));
			viewer.refresh();
		}
	}
	
	class SourceEditingSupport extends EditingSupport {

		private final TableViewer viewer;

		public SourceEditingSupport(TableViewer viewer) {
			super(viewer);
			this.viewer = viewer;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return new TextCellEditor(viewer.getTable());
		}

		@Override
		protected boolean canEdit(Object element) {
			if (element instanceof Update) {
				return true;
			}
			return false;
		}

		@Override
		protected Object getValue(Object element) {
			return ((Update) element).getSource();
		}

		@Override
		protected void setValue(Object element, Object value) {
			((Update) element).setSource(String.valueOf(value));
			viewer.refresh();
		}
	}

	/**
	 * 
	 */
	public CollaborationView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 1;
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		addCollaborationInfo(composite);

		// Listening events
		getSite().getPage().addSelectionListener(VersionView.ID, this);
	}

	private void addCollaborationInfo(Composite parent) {
		FillLayout parentLayout = new FillLayout(SWT.VERTICAL);
		parent.setLayout(parentLayout);
		parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		collaborationGroup = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		collaborationGroup.setLayout(gridLayout);
		gridLayout.numColumns = 2; 
		collaborationGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		collaborationGroup.setText("Collaboration");

		Label proposedByLabel = new Label(collaborationGroup, SWT.NONE);
		proposedByLabel.setText("Proposed by");
		proposedByText = new Text(collaborationGroup, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		proposedByText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		proposedByText.setText("");

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
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		//		System.out.println("Se detecto un cambio en la vista principal  desde " + this.ID + "\n" + part.getSite().getId() + " - " + selection);

		resetFields();
		Object objectSelected = null;
		if (selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			objectSelected = treeSelection.getFirstElement();
		}

		if(objectSelected != null && objectSelected instanceof Collaboration) {
			final Collaboration collaboration = (Collaboration) objectSelected;
			if(proposedByText != null) {
				proposedByText.setText(collaboration.getProposedBy().getId());
			}

			if(rationaleText != null && collaboration.getRationale() != null) {
				rationaleText.setText(collaboration.getRationale());
				rationaleText.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent e) {
						Text text = (Text) e.getSource();
						collaboration.setRationale(text.getText());
					}
				});
			}

			if(votesAgreeText != null && votesDisagreeText != null) {
				String votesAgreeString = "";
				String votesDisagreeString = "";
				for(Vote vote : collaboration.getVotes()) {
					if(vote.isAgreement()) {
						votesAgreeString += vote.getUser().getId() + ", ";
					} else {
						votesDisagreeString += vote.getUser().getId() + ", ";
					}
				}
				votesAgreeText.setText((votesAgreeString.length() == 0) ? "" : votesAgreeString.substring(0, votesAgreeString.length()-2));
				votesDisagreeText.setText((votesDisagreeString.length() == 0) ? "" : votesDisagreeString.substring(0, votesDisagreeString.length()-2));
			}

			if (collaboration instanceof Solution) {
				final Solution solution = (Solution) collaboration;

				solutionGroup = new Group(parent, SWT.NONE);
				GridLayout gridLayout = new GridLayout();
				solutionGroup.setLayout(gridLayout);
				gridLayout.numColumns = 1; 
				solutionGroup.setText("Solution");			

				Combo comboCreation = new Combo(solutionGroup, SWT.BORDER);
				comboCreation.setText("Testinggg");
				comboCreation.setItems(new String[] { "Create ADD", "Create UDPATE", "Create DELETE"});
				comboCreation.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						Combo combo = (Combo) e.getSource();
						if(combo.getSelectionIndex() == 0) {
							Controller.INSTANCE.createAdd(solution);
						} else if(combo.getSelectionIndex() == 1) {
							Controller.INSTANCE.createUpdate(solution);
						} else if(combo.getSelectionIndex() == 2) {
							Controller.INSTANCE.createDelete(solution);
						}
						tableCreation.setInput(solution.getChanges());
						tableCreation.refresh();						
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});

				tableCreation = new TableViewer(solutionGroup, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

				TableViewerColumn tableColumn1 = new TableViewerColumn(tableCreation, SWT.NONE);
				tableColumn1.getColumn().setWidth(50);
				tableColumn1.getColumn().setText("Type");
				tableColumn1.setLabelProvider(new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						if (element instanceof Add) {
							return "ADD";
						} else if (element instanceof Update) {
							return "UPDATE";							
						} else if (element instanceof Delete) {
							return "DELETE";
						} else {
							return "NAN";
						}
					}
				});

				TableViewerColumn tableColumn2 = new TableViewerColumn(tableCreation, SWT.NONE);
				tableColumn2.getColumn().setWidth(80);
				tableColumn2.getColumn().setText("Referred");
				tableColumn2.setEditingSupport(new ReferredEditingSupport(tableCreation));
				tableColumn2.setLabelProvider(new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						ModelChange p = (ModelChange) element; 
						return p.getReferredElement();
					}
				});

				TableViewerColumn tableColumn3 = new TableViewerColumn(tableCreation, SWT.NONE);
				tableColumn3.getColumn().setWidth(80);
				tableColumn3.getColumn().setText("Target");
				tableColumn3.setEditingSupport(new TargetEditingSupport(tableCreation));
				tableColumn3.setLabelProvider(new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						ModelChange p = (ModelChange) element; 
						return p.getTarget();
					}
				});

				TableViewerColumn tableColumn4 = new TableViewerColumn(tableCreation, SWT.NONE);
				tableColumn4.getColumn().setWidth(80);
				tableColumn4.getColumn().setText("Source");
				tableColumn4.setEditingSupport(new SourceEditingSupport(tableCreation));
				tableColumn4.setLabelProvider(new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						if (element instanceof Update) {
							Update update = (Update) element;
							return update.getSource();
						}
						return "";
					}
				});

				tableCreation.setContentProvider(ArrayContentProvider.getInstance());
				tableCreation.setInput(solution.getChanges());
				tableCreation.refresh();

				final Table table = tableCreation.getTable();
				table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				table.setHeaderVisible(true);
				table.setLinesVisible(true);

				parent.layout(true);		
			} 
		}

	}

	public void resetFields() {
		if(solutionGroup != null) {
			solutionGroup.dispose();
			solutionGroup = null;
			parent.layout(true);			
		}

	}

}


