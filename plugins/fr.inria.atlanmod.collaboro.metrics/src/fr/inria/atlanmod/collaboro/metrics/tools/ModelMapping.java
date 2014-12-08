package fr.inria.atlanmod.collaboro.metrics.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.collaboro.metrics.symbol.AttributeConcept;
import fr.inria.atlanmod.collaboro.metrics.symbol.AttributeSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.ClassConcept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ClassSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.Concept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ReferenceConcept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ReferenceSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;

public class ModelMapping {

	private List<Relationship> mapping;
	
	private List<ClassConcept> abstractClassConcepts;
	private List<AttributeConcept> abstractAttributeConcepts;
	private List<ReferenceConcept> abstractReferenceConcepts;
	
	private List<ClassSymbol> concreteClassSymbols;
	private List<AttributeSymbol> concreteAttributeSymbols;
	private List<ReferenceSymbol> concreteReferenceSymbols;
	
	public ModelMapping() {
		this.mapping = new ArrayList<Relationship>();
		this.abstractClassConcepts = new ArrayList<ClassConcept>();
		this.abstractAttributeConcepts = new ArrayList<AttributeConcept>();
		this.abstractReferenceConcepts = new ArrayList<ReferenceConcept>();
		this.concreteClassSymbols = new ArrayList<ClassSymbol>();
		this.concreteAttributeSymbols = new ArrayList<AttributeSymbol>();
		this.concreteReferenceSymbols = new ArrayList<ReferenceSymbol>();
	}
	
	public void mapClassConceptsToClassSymbols(List<ClassConcept> classConcepts, List<ClassSymbol> classSymbols) {
		this.abstractClassConcepts.addAll(classConcepts);
		this.concreteClassSymbols.addAll(classSymbols);
		for(ClassSymbol classSymbol : classSymbols) {
			String classSymbolName = classSymbol.getClassName();
			for(ClassConcept classConcept : classConcepts) {
				if(classConcept.getName().equals(classSymbolName)) {
					Relationship relationship = new Relationship(classConcept, classSymbol);
					mapping.add(relationship);
				}
			}
		}
	}
	
	public void mapAttributeConceptsToAttributeSymbols(List<AttributeConcept> attributeConcepts, List<AttributeSymbol> attributeSymbols) {
		this.abstractAttributeConcepts.addAll(attributeConcepts);
		this.concreteAttributeSymbols.addAll(attributeSymbols);
		for(AttributeSymbol attributeSymbol : attributeSymbols) {
			String attributeSymbolName = attributeSymbol.getClassName() + "." + attributeSymbol.getAttributeName();
			for(AttributeConcept attributeConcept : attributeConcepts) {
				if(attributeConcept.getName().equals(attributeSymbolName)) {
					Relationship relationship = new Relationship(attributeConcept, attributeSymbol);
					mapping.add(relationship);
				}
			}
		}
	}
	
	public void mapReferenceConceptsToReferenceSymbols(List<ReferenceConcept> referenceConcepts, List<ReferenceSymbol> referenceSymbols) {
		this.abstractReferenceConcepts.addAll(referenceConcepts);
		this.concreteReferenceSymbols.addAll(referenceSymbols);
		for(ReferenceSymbol referenceSymbol : referenceSymbols) {
			String referenceSymbolName = referenceSymbol.getClassName() + "." + referenceSymbol.getReferenceName();
			for(ReferenceConcept referenceConcept : referenceConcepts) {
				if(referenceConcept.getName().equals(referenceSymbolName)) {
					Relationship relationship = new Relationship(referenceConcept, referenceSymbol);
					mapping.add(relationship);
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

	public List<ClassSymbol> getConcreteClassSymbols() {
		return concreteClassSymbols;
	}

	public List<AttributeSymbol> getConcreteAttributeSymbols() {
		return concreteAttributeSymbols;
	}

	public List<ReferenceSymbol> getConcreteReferenceSymbols() {
		return concreteReferenceSymbols;
	}

	public String toString() {
		return mapping.toString();
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
