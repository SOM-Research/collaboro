package fr.inria.atlanmod.collaboro.metrics.symbol;

import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class Symbol {
	
	private String name;
	private NotationElement concreteSyntaxElement;
	
	public Symbol(String name, NotationElement concreteSyntaxElement) {
		this.name = name;
		this.concreteSyntaxElement = concreteSyntaxElement;
	}
	
	public String getName() {
		return this.name;
	}
	
	public NotationElement getConcreteSyntaxElement() {
		return this.concreteSyntaxElement;
	}
	
	public String toString() {
		return "Symbol " + name;
	}
	
}
