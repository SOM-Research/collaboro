package fr.inria.atlanmod.collaboro.metrics.symbol;

import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class ClassSymbol extends Symbol{

	private String className;
	
	public ClassSymbol(String id, String className, NotationElement concreteSyntaxElement) {
		super(id,concreteSyntaxElement);
		this.className = className;
	}

	public String getClassName() {
		return className;
	}	
	
	
}
