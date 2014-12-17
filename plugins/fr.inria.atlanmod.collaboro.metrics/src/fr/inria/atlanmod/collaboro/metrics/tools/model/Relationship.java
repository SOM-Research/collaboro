package fr.inria.atlanmod.collaboro.metrics.tools;

import fr.inria.atlanmod.collaboro.metrics.symbol.Concept;
import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;

public class Relationship {
	
	private Concept relationshipFrom;
	private Symbol relationshipTo;
	
	public Relationship(Concept from, Symbol to) {
		this.relationshipFrom = from;
		this.relationshipTo = to;
	}

	public Concept getRelationshipFrom() {
		return relationshipFrom;
	}

	public Symbol getRelationshipTo() {
		return relationshipTo;
	}
	
	public String toString() {
		return relationshipFrom + " -> " + relationshipTo;
	}
	

}
