package fr.inria.atlanmod.collaboro.metrics.symbol;

import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class AttributeSymbol extends Symbol {
	
	private String className;
	private String attributeName;
	
	public AttributeSymbol(String id, String className, String attributeName, NotationElement concreteSyntaxElement) {
		super(id,concreteSyntaxElement);
		this.className = className;
		this.attributeName = attributeName;
	}

	public String getClassName() {
		return className;
	}

	public String getAttributeName() {
		return attributeName;
	}

}
