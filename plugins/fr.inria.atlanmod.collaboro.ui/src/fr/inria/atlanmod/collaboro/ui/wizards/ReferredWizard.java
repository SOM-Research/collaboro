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

import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
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
import org.eclipse.swt.widgets.Shell;

import fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement;
import fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.ui.Controller;

public class ReferredWizard extends Wizard {
	private static final String EXISTING_ABSTRACT_ELEMENT = "Existing Abstract Syntax Element";
	private static final String CONCRETE_ELEMENT = "Concrete Syntax Element";

	private Shell shell = null;
	private ModelChange modelChange = null;

	private ListViewer elementList = null;
	private Button[] radios = null;

	class ExistingListener implements SelectionListener {
		@Override
		public void widgetSelected(SelectionEvent e) {
			elementList.setInput(Controller.INSTANCE.getEcoreModel());
			elementList.refresh();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}

	class NotationListener implements SelectionListener {
		@Override
		public void widgetSelected(SelectionEvent e) {
			elementList.setInput(Controller.INSTANCE.getAllNotationElements());
			elementList.refresh();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}

	class SelectionPage extends WizardPage {
		protected SelectionPage(String pageName) {
			super(pageName);

		}

		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			GridLayout layout = new GridLayout();
			composite.setLayout(layout);
			layout.numColumns = 1;
			composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

			Group elementType = new Group(composite, SWT.NONE);
			elementType.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			GridLayout gridLayout = new GridLayout();
			gridLayout.numColumns = 2;
			elementType.setLayout(gridLayout);
			elementType.setText("Select referred type");	

			radios = new Button[2];

			radios[0] = new Button(elementType, SWT.RADIO);
			radios[0].setSelection(false);
			radios[0].setText(EXISTING_ABSTRACT_ELEMENT);
			radios[0].setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
			radios[0].addSelectionListener(new ExistingListener());

			radios[1] = new Button(elementType, SWT.RADIO);
			radios[1].setSelection(false);
			radios[1].setText(CONCRETE_ELEMENT);
			radios[1].setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
			radios[1].addSelectionListener(new NotationListener());

			Group element = new Group(composite, SWT.NONE);
			element.setBounds(new Rectangle(0, 0, 500, 500));
			element.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.numColumns = 1;
			element.setLayout(gridLayout2);
			element.setText("Select Element");	

			elementList = new ListViewer(element, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			elementList.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			elementList.setContentProvider(new SyntaxElementContentProvider());
			elementList.setLabelProvider(new SyntaxElementLabelProvider());

			setControl(composite);
		}

	}

	public void init(Shell shell, IStructuredSelection selection) {
		this.shell = shell;
		this.modelChange = (ModelChange) selection.getFirstElement();
		if (selection != null && !selection.isEmpty()) {
			Object obj = selection.getFirstElement();
			if (obj  instanceof IFolder) {
				
			}
		}
	}

	@Override
	public boolean performFinish() {
		Button selected = null;
		for(int i = 0; i < radios.length; i++) {
			Button button = radios[i];
			if(button.getSelection()) { 
				selected = button; 
				break; 
			}
		}

		IStructuredSelection selection = (IStructuredSelection) elementList.getSelection();
		Object selectedElement = selection.getFirstElement();
		
		if (selected.getText().equals(EXISTING_ABSTRACT_ELEMENT)) {
			ExistingAbstractSyntaxElement syntaxElement = HistoryFactory.eINSTANCE.createExistingAbstractSyntaxElement();
			syntaxElement.setElement((EModelElement) selectedElement);
			modelChange.setReferredElement(syntaxElement);
		} else if (selected.getText().equals(CONCRETE_ELEMENT)) {
			ConcreteSyntaxElement syntaxElement = HistoryFactory.eINSTANCE.createConcreteSyntaxElement();
			syntaxElement.setElement((NotationElement) selectedElement);
			modelChange.setReferredElement(syntaxElement);
		}
		Controller.INSTANCE.saveHistory();
		Controller.INSTANCE.refreshChanges();

		return true;
	}

	@Override
	public boolean performCancel() {
		return super.performCancel();
	}

	@Override
	public void addPages() {
		WizardPage page = new SelectionPage("first");
		page.setTitle("Referred Element");
		page.setDescription("Select the element being referred by this model change");
		addPage(page);
	}

}
