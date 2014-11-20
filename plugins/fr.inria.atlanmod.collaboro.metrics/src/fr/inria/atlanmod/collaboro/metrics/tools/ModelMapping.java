package fr.inria.atlanmod.collaboro.metrics.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;

public class ModelMapping {
	
	private Map<String,EObject> abstractConcepts;
	private List<Symbol> concreteSymbols;
	private List<Relationship> mapping;
	
	public ModelMapping(Map<String,EObject> abstractConcepts, List<Symbol> concreteSymbols) {
		this.abstractConcepts = abstractConcepts;
		this.concreteSymbols = concreteSymbols;
		this.mapping = mapModel();
	}
	
	private List<Relationship> mapModel() {
		List<Relationship> mapping = new ArrayList<Relationship>();
		for(Symbol concreteSymbol : concreteSymbols) {
			String concreteSymbolName = concreteSymbol.getName();
			if(abstractConcepts.containsKey(concreteSymbolName)) {
				EObject abstractConcept = abstractConcepts.get(concreteSymbolName);
				Relationship relationship = new Relationship(abstractConcept, concreteSymbol);
				mapping.add(relationship);
			}
		}
		return mapping;
	}
	
	
	
	public Map<String, EObject> getAbstractConcepts() {
		return abstractConcepts;
	}

	public List<Symbol> getConcreteSymbols() {
		return concreteSymbols;
	}

	public List<Relationship> getMapping() {
		return mapping;
	}

	public String toString() {
		return mapping.toString();
	}
	
	
	

}
