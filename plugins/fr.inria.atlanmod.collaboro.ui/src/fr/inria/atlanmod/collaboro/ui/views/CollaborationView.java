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


import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Vote;

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


	/**
	 * 
	 */
	public CollaborationView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
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
		Group group = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		group.setLayout(gridLayout);
		gridLayout.numColumns = 2; 
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		group.setText("Collaboration");
		
		Label proposedByLabel = new Label(group, SWT.NONE);
		proposedByLabel.setText("Proposed by");
		proposedByText = new Text(group, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		proposedByText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		proposedByText.setText("");

		Label rationaleLabel = new Label(group, SWT.NONE);
		rationaleLabel.setText("Rationale");
		rationaleText = new Text(group, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
		rationaleText.setText("");
		rationaleText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Label votesAgreeLabel = new Label(group, SWT.NONE);
		votesAgreeLabel.setText("Votes Agree:");
		votesAgreeText = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = SWT.FILL;
		votesAgreeText.setLayoutData(gridData1);
		
		Label votesDisagreeLabel = new Label(group, SWT.NONE);
		votesDisagreeLabel.setText("Votes Disagree");
		votesDisagreeText = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		votesDisagreeText.setLayoutData(gridData1);

	}

	private Composite createDefaultComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(data);

		return composite;
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
			Collaboration collaboration = (Collaboration) objectSelected;
			if(proposedByText != null) {
				proposedByText.setText(collaboration.getProposedBy().getId());
			}

			if(rationaleText != null && collaboration.getRationale() != null) {
				rationaleText.setText(collaboration.getRationale());
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
		}

	}
	
	public void resetFields() {
		proposedByText.setText("");
		rationaleText.setText("");
		votesAgreeText.setText("");
		votesDisagreeText.setText("");
	}

}
