package fr.inria.atlanmod.collaboro.metrics.tools;

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.model.AttributeConcept;
import fr.inria.atlanmod.collaboro.metrics.model.ClassConcept;
import fr.inria.atlanmod.collaboro.metrics.model.Concept;
import fr.inria.atlanmod.collaboro.metrics.model.ReferenceConcept;
import fr.inria.atlanmod.collaboro.metrics.model.Symbol;
import fr.inria.atlanmod.collaboro.metrics.model.SymbolType;
import fr.inria.atlanmod.collaboro.metrics.tools.model.Relationship;

public class ModelMapping {

	private List<Relationship> mapping;
	
	private List<ClassConcept> abstractClassConcepts;
	private List<AttributeConcept> abstractAttributeConcepts;
	private List<ReferenceConcept> abstractReferenceConcepts;
	
	private List<Symbol> concreteSymbols;
	
	public ModelMapping() {
		this.mapping = new ArrayList<Relationship>();
		this.abstractClassConcepts = new ArrayList<ClassConcept>();
		this.abstractAttributeConcepts = new ArrayList<AttributeConcept>();
		this.abstractReferenceConcepts = new ArrayList<ReferenceConcept>();
		this.concreteSymbols = new ArrayList<Symbol>();
	}
	
	public ModelMapping(List<ClassConcept> classConcepts, List<AttributeConcept> attributeConcepts, List<ReferenceConcept> referenceConcepts, List<Symbol> symbols) {
		this.abstractClassConcepts = classConcepts;
		this.abstractAttributeConcepts = attributeConcepts;
		this.abstractReferenceConcepts = referenceConcepts;
		this.concreteSymbols = symbols;
		this.mapping = new ArrayList<Relationship>();
		initialize();
	}
	
	private void initialize() {
		mapConceptsToSymbols();
		System.out.println(mapping);
	}
	
	private void mapConceptsToSymbols() {
		for(Symbol symbol : this.concreteSymbols) {
			if(symbol.getType() == SymbolType.CLASS) {
				for(ClassConcept classConcept : abstractClassConcepts) {
					if(symbol.getMappingName().equals(classConcept.getName())) {
						Relationship relationship = new Relationship(classConcept, symbol);
						mapping.add(relationship);
					}
				}
			} else if(symbol.getType() == SymbolType.ATTRIBUTE){
				for(AttributeConcept attributeConcept : abstractAttributeConcepts) {
					if(symbol.getMappingName().equals(attributeConcept.getName())) {
						Relationship relationship = new Relationship(attributeConcept, symbol);
						mapping.add(relationship);
					}
				}
			} else if(symbol.getType() == SymbolType.REFERENCE) {
				for(ReferenceConcept referenceConcept : abstractReferenceConcepts) {
					if(symbol.getMappingName().equals(referenceConcept.getName())) {
						Relationship relationship = new Relationship(referenceConcept, symbol);
						mapping.add(relationship);
					}
				}
			}
		}
	}	

	public List<Relationship> getMapping() {
		return mapping;
	}	

	public List<ClassConcept> getAbstractClassConcepts() {
		return abstractClassConcepts;
	}

	public List<AttributeConcept> getAbstractAttributeConcepts() {
		return abstractAttributeConcepts;
	}

	public List<ReferenceConcept> getAbstractReferenceConcepts() {
		return abstractReferenceConcepts;
	}

	public List<Symbol> getConcreteSymbols() {
		return concreteSymbols;
	}

	public String toString() {
		return mapping.toString();
	}
	
	public boolean isSymbolMapped(Symbol symbol) {
		for(Relationship relationship : this.mapping) {
			Symbol concreteSymbol = relationship.getRelationshipTo();
			if(concreteSymbol.equals(symbol)) {
				return true;
			}
		}
		return false;
	}
	
	public int countSymbolOccurence(Symbol symbol) {
		int count = 0;
		for(Relationship relationship : this.mapping) {
			if(relationship.getRelationshipTo().equals(symbol)) {
				count++;
			}
		}
		return count;
	}
	
	public boolean isConceptMapped(Concept concept) {
		for(Relationship relationship : this.mapping) {
			Concept abstractConcept = relationship.getRelationshipFrom();
			if(abstractConcept.equals(concept)) {
				return true;
			}
		}
		return false;
	}
	
	public int countConceptOccurence(Concept concept) {
		int count = 0;
		for(Relationship relationship : this.mapping) {
			if(relationship.getRelationshipFrom().equals(concept)) {
				count++;
			}
		}
		return count;
	}
	
	/*public Concept getConceptByName(String conceptName) {
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
	}*/
	
	

}
