package fr.inria.atlanmod.collaboro.ui.views;

import java.io.File;

import fr.inria.atlanmod.collaboro.history.Proposal;

/**
 * Extra class to represent example elements in the version view. This avoids poluting the 
 * History metamodel but it is a bit dirty :(
 * 
 * @author Javier Canovas (javier.canovas@inria.fr)
 *
 */
public class ExampleElement {
	private File file;
	private Proposal parent;
	
	public ExampleElement(Proposal proposal) {
		this.parent = proposal;
		int indexA = proposal.getRationale().indexOf("-")+1;
		int indexB = proposal.getRationale().indexOf("]");
		String filePath = proposal.getRationale().substring(indexA, indexB);
		this.file = new File(filePath);
	}
	
	public String getLabel() {
		return "Example " + file.getName();
	}
	public Proposal getParent() {
		return parent;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
}
