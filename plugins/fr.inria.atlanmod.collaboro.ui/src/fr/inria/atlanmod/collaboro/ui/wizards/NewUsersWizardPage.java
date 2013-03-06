package fr.inria.atlanmod.collaboro.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
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

import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.User;

public class NewUsersWizardPage extends WizardPage {
	protected NewUsersWizardPage(String pageName) {
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
