package fr.inria.atlanmod.collaboro.metrics.tools;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;

public class Relationship {
	
	private EObject relationshipFrom;
	private Symbol relationshopTo;
	
	public Relationship(EObject from, Symbol to) {
		this.relationshipFrom = from;
		this.relationshopTo = to;
	}

	public EObject getRelationshipFrom() {
		return relationshipFrom;
	}

	public Symbol getRelationshopTo() {
		return relationshopTo;
	}
	
	public String toString() {
		return relationshipFrom + " -> " + relationshopTo;
	}
	

}
