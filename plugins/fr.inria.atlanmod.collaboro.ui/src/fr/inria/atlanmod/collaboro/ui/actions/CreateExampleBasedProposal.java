package fr.inria.atlanmod.collaboro.ui.actions;

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.ui.Controller;

public class CreateExampleBasedProposal extends CreateCollaborationAction implements IViewActionDelegate {

	@Override
	public void run(IAction action) {
		if(Controller.INSTANCE.isLogged()) {
			String rationale = ((Proposal) this.collaboration).getRationale();
			if(rationale != null && rationale.startsWith("[EXAMPLE]")) {
				MessageDialog.openInformation(this.shell, "Example already given", "The proposal already includes an example.");
				return;
			}
			
			if (shell == null) 
			    shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			FileDialog dialog = new FileDialog(shell);
			dialog.setText("Select file containing the example");
			dialog.setFilterExtensions(new String[] { "*.dia" });
			dialog.setFilterNames(new String[] {"Dia files(*.dia)"});
			String fileSelected = dialog.open();
			System.out.println("selected: " + fileSelected);
			Controller.INSTANCE.createExampleBasedProposal((Proposal) this.collaboration, new File(fileSelected));
			Controller.INSTANCE.saveHistory();
		} else {
			MessageDialog.openInformation(this.shell, "Not looged in", "You are not logged in");
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			Object element = treeSelection.getFirstElement();
			if (element instanceof Proposal) {
				Proposal collaboration = (Proposal) element;
				this.collaboration = collaboration;
			}			
		}

	}
	
	@Override
	public void init(IViewPart view) {
		System.out.println("initializing exampleBased command");
		shell = view.getViewSite().getShell();
	}

}
