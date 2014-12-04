package fr.inria.atlanmod.collaboro.metrics.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.collaboro.metrics.symbol.AttributeSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.ClassSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.Concept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ReferenceSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;

public class ModelMapping {
	
	/**
	 * Map<conceptName, conceptObject>
	 */
	private List<Concept> abstractConcepts;
	private List<Symbol> concreteSymbols;
	private List<Relationship> mapping;
	
	public ModelMapping(List<Concept> abstractConcepts, List<Symbol> concreteSymbols) {
		this.abstractConcepts = abstractConcepts;
		this.concreteSymbols = concreteSymbols;
		this.mapping = mapModel();
	}
	
	private List<Relationship> mapModel() {
		List<Relationship> mapping = new ArrayList<Relationship>();
		for(Symbol concreteSymbol : concreteSymbols) {
			String symbolName = "";
			if(concreteSymbol instanceof ClassSymbol) {
				ClassSymbol classSymbol = (ClassSymbol) concreteSymbol;
				symbolName = classSymbol.getClassName();
			} else if(concreteSymbol instanceof AttributeSymbol) {
				AttributeSymbol attributeSymbol = (AttributeSymbol) concreteSymbol;
				symbolName = attributeSymbol.getClassName() + "." + attributeSymbol.getAttributeName();
			} else if(concreteSymbol instanceof ReferenceSymbol) {
				ReferenceSymbol referenceSymbol = (ReferenceSymbol) concreteSymbol;
				symbolName = referenceSymbol.getClassName() + "." + referenceSymbol.getReferenceName();
			}
			
			for(Concept abstractConcept : abstractConcepts) {
				if(abstractConcept.getName().equals(symbolName)) {
					Relationship relationship = new Relationship(abstractConcept, concreteSymbol);
					mapping.add(relationship);
				}
			}
		}
		return mapping;
	}
	
	
	
	public List<Concept> getAbstractConcepts() {
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
	
	public Concept getConceptByName(String conceptName) {
		List<Concept> conceptList = this.abstractConcepts;
		for(Concept concept : conceptList) {
			if(concept.getName().equals(conceptName)) {
				return concept;
			}
		}
		return null;
	}
	
	public Concept getConceptByAbstractSyntaxElement(EObject abstractSyntaxElement) {
		List<Concept> conceptList = this.abstractConcepts;
		for(Concept concept : conceptList) {
			if(concept.getAbstractModelElement().equals(abstractSyntaxElement)) {
				return concept;
			}
		}
		return null;
	}
	
	

}
