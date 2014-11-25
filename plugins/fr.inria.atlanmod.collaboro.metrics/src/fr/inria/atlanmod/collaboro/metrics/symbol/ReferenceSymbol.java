package fr.inria.atlanmod.collaboro.metrics.symbol;

import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class ReferenceSymbol extends Symbol{
	
	private String className;
	private String referenceName;
	private String attributeName;
	
	public ReferenceSymbol(String id, String className, String referenceName, String attributeName, NotationElement concreteSyntaxElement) {
		super(id, concreteSyntaxElement);
		this.className = className;
		this.referenceName = referenceName;
		this.attributeName = attributeName;
	}

	public String getClassName() {
		return className;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public String getAttributeName() {
		return attributeName;
	}

}
