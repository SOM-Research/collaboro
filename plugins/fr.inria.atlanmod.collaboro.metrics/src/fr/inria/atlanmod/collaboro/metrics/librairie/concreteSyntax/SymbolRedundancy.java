package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;
import fr.inria.atlanmod.collaboro.metrics.impl.AbstractSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.model.AttributeConcept;
import fr.inria.atlanmod.collaboro.metrics.model.ClassConcept;
import fr.inria.atlanmod.collaboro.metrics.model.Concept;
import fr.inria.atlanmod.collaboro.metrics.model.ReferenceConcept;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.metrics.tools.model.Relationship;

public class SymbolRedundancy extends ConcreteSyntaxGraphicalMetricImpl{
	
	private static String dimension = "Ontological";
	private static String description = "Symbol redundancy occurs when multiple concrete symbols can be used to represent a single abstract concept";
	private Map<ClassConcept,Integer> countClassConceptRepresentedMap;
	private Map<AttributeConcept,Integer> countAttributeConceptRepresentedMap;
	private Map<ReferenceConcept,Integer> countReferenceConceptRepresentedMap;
	
	public SymbolRedundancy(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
		this.countClassConceptRepresentedMap = new HashMap<ClassConcept, Integer>();
		this.countAttributeConceptRepresentedMap = new HashMap<AttributeConcept, Integer>();
		this.countReferenceConceptRepresentedMap = new HashMap<ReferenceConcept, Integer>();
	}
	
	public SymbolRedundancy(ModelMapping modelMapping) {
		super("symbol redundancy", "Ontological", "Symbol redundancy occurs when multiple concrete symbols can be used to represent a single abstract concept",0);
		this.modelMapping = modelMapping;
		this.countClassConceptRepresentedMap = new HashMap<ClassConcept, Integer>();
		this.countAttributeConceptRepresentedMap = new HashMap<AttributeConcept, Integer>();
		this.countReferenceConceptRepresentedMap = new HashMap<ReferenceConcept, Integer>();
	}
		
	public List<MetricResult> execute() {
		System.out.println("Execute SymbolRedundancy");
		List<MetricResult> results = new ArrayList<MetricResult>();
		List<ReferredElement> redundantSymbols = new ArrayList<ReferredElement>();
		
		checkClassConcepts();
		checkAttributeConcept();
		checkReferenceConcept();
		
		int totalElements = 0;
		for(ClassConcept classConcept : this.modelMapping.getAbstractClassConcepts()) {
			totalElements++;
			if((countClassConceptRepresentedMap.get(classConcept) != null) && (countClassConceptRepresentedMap.get(classConcept) > 1)) {
				ReferredElement classReferredElement = new AbstractSyntaxReferredElementImpl(classConcept.getName(), ReferredElementReason.WRONG, classConcept.getAbstractModelElement());
				redundantSymbols.add(classReferredElement);
			}
		}
		
		for(AttributeConcept attributeConcept : this.modelMapping.getAbstractAttributeConcepts()) {
			totalElements++;
			if((countAttributeConceptRepresentedMap.get(attributeConcept) != null) && (countAttributeConceptRepresentedMap.get(attributeConcept) > 1)) {
				ReferredElement attributeReferredElement = new AbstractSyntaxReferredElementImpl(attributeConcept.getName(), ReferredElementReason.WRONG, attributeConcept.getAbstractModelElement());
				redundantSymbols.add(attributeReferredElement);
			}
		}
		
		for(ReferenceConcept referenceConcept : this.modelMapping.getAbstractReferenceConcepts()) {
			totalElements++;
			if((countReferenceConceptRepresentedMap.get(referenceConcept) != null) && (countReferenceConceptRepresentedMap.get(referenceConcept) > 1)) {
				ReferredElement referenceReferredElement = new AbstractSyntaxReferredElementImpl(referenceConcept.getName(), ReferredElementReason.WRONG, referenceConcept.getAbstractModelElement());
				redundantSymbols.add(referenceReferredElement);
			}
		}
		
		MetricResultImpl metricResult = new MetricResultImpl();
		int redundantElements = redundantSymbols.size();
		if(redundantElements > this.acceptanceRatio) {
			metricResult.setReason("There are abstract concepts that are represented by more than one concrete symbol (" + redundantElements + ")");
			float ratio = (float)(redundantElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			metricResult.setReferredElements(redundantSymbols);
			results.add(metricResult);
		} else if (redundantElements > 0) {
			metricResult.setReason("There are abstract concepts that are represented by more than one concrete symbol (" + redundantElements + ")");
			float ratio = (float)(redundantElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.MIDDLE);
			metricResult.setReferredElements(redundantSymbols);
			results.add(metricResult);
		} else {
			metricResult.setReason("Every abstract concept is represented by at most one concrete symbol");
			float ratio = (float)(redundantElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.GOOD);
			metricResult.setReferredElements(redundantSymbols);
			results.add(metricResult);
		}
		return results;
	}
	
	private void checkClassConcepts() {
		List<ClassConcept> classConcepts = this.modelMapping.getAbstractClassConcepts();
		for(ClassConcept classConcept : classConcepts) {
			countClassConceptRepresentedMap.put(classConcept, this.modelMapping.countConceptOccurence(classConcept));
		}
		
		// check heritage
		// If subTypes are all represented -> super type representation is redundant
		for(ClassConcept classConcept : classConcepts) {
			Integer isClassRepresentedCount = countClassConceptRepresentedMap.get(classConcept);
			if(isClassRepresentedCount > 0) {
				boolean alreadyRepresentedByHeritage = checkClassConceptHeritage(classConcept);
				if(alreadyRepresentedByHeritage) {
					// put class concept in redundancy (count > 1)
					countClassConceptRepresentedMap.put(classConcept, 2);
				}
			}
		}
	}
	
	private boolean checkClassConceptHeritage(ClassConcept classConcept) {
		List<ClassConcept> subClassConcepts = classConcept.getSubTypes();
		if(!subClassConcepts.isEmpty()) {
			return isClassConceptSubTypesRepresented(classConcept);
		}
		return false;
	}
	
	private boolean isClassConceptSubTypesRepresented(ClassConcept classConcept) {
		Integer classIsRepresentedCount = countClassConceptRepresentedMap.get(classConcept);
		List<ClassConcept> subClassConcepts = classConcept.getSubTypes();
		if(subClassConcepts.isEmpty()) {
			if(classIsRepresentedCount > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			boolean classIsRepresentedByHeritage = true;
			for(ClassConcept subClassConcept : subClassConcepts) {
				boolean subClassRepresentedByHeritage = isClassConceptSubTypesRepresented(subClassConcept);
				classIsRepresentedByHeritage = classIsRepresentedByHeritage && subClassRepresentedByHeritage;
			}
			return classIsRepresentedByHeritage;
		}
	}
	
	private void checkAttributeConcept() {
		List<AttributeConcept> attributeConcepts = this.modelMapping.getAbstractAttributeConcepts();
		for(AttributeConcept attributeConcept : attributeConcepts) {
			countAttributeConceptRepresentedMap.put(attributeConcept, this.modelMapping.countConceptOccurence(attributeConcept));
		}
		
		// check heritage
		// If subTypes are all represented -> super type representation is redundant
		for(AttributeConcept attributeConcept : attributeConcepts) {
			Integer isAttributeRepresentedCount = countAttributeConceptRepresentedMap.get(attributeConcept);
			if(isAttributeRepresentedCount > 0) {
				boolean alreadyRepresentedByHeritage = checkAttributeConceptHeritage(attributeConcept);
				if(alreadyRepresentedByHeritage) {
					// put attribute concept in redundancy (count > 1)
					countAttributeConceptRepresentedMap.put(attributeConcept, 2);
				}
			}
		}
	}
	
	private boolean checkAttributeConceptHeritage(AttributeConcept attributeConcept) {
		List<AttributeConcept> subAttributeConcepts = attributeConcept.getSubAttributes();
		if(!subAttributeConcepts.isEmpty()) {
			return isAttributeConceptSubTypesRepresented(attributeConcept);
		}
		return false;
	}
	
	private boolean isAttributeConceptSubTypesRepresented(AttributeConcept attributeConcept) {
		Integer attributeIsRepresentedCount = countAttributeConceptRepresentedMap.get(attributeConcept);
		List<AttributeConcept> subAttributeConcepts = attributeConcept.getSubAttributes();
		if(subAttributeConcepts.isEmpty()) {
			if(attributeIsRepresentedCount > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			boolean attributeIsRepresentedByHeritage = true;
			for(AttributeConcept subAttributeConcept : subAttributeConcepts) {
				boolean subAttributeRepresentedByHeritage = isAttributeConceptSubTypesRepresented(subAttributeConcept);
				attributeIsRepresentedByHeritage = attributeIsRepresentedByHeritage && subAttributeRepresentedByHeritage;
			}
			return attributeIsRepresentedByHeritage;
		}
	}
	
	
	private void checkReferenceConcept() {
		List<ReferenceConcept> referenceConcepts = this.modelMapping.getAbstractReferenceConcepts();
		for(ReferenceConcept referenceConcept : referenceConcepts) {
			countReferenceConceptRepresentedMap.put(referenceConcept, this.modelMapping.countConceptOccurence(referenceConcept));
		}
		
		// check heritage
		// If subTypes are all represented -> super type representation is redundant
		for(ReferenceConcept referenceConcept : referenceConcepts) {
			Integer isReferenceRepresentedCount = countReferenceConceptRepresentedMap.get(referenceConcept);
			if(isReferenceRepresentedCount > 0) {
				boolean alreadyRepresentedByHeritage = checkReferenceConceptHeritage(referenceConcept);
				if(alreadyRepresentedByHeritage) {
					// put attribute concept in redundancy (count > 1)
					countReferenceConceptRepresentedMap.put(referenceConcept, 2);
				}
			}
		}
	}
	
	private boolean checkReferenceConceptHeritage(ReferenceConcept referenceConcept) {
		List<ReferenceConcept> subReferenceConcepts = referenceConcept.getSubReferences();
		if(!subReferenceConcepts.isEmpty()) {
			return isReferenceConceptSubTypesRepresented(referenceConcept);
		}
		return false;
	}
	
	private boolean isReferenceConceptSubTypesRepresented(ReferenceConcept referenceConcept) {
		Integer referenceIsRepresentedCount = countReferenceConceptRepresentedMap.get(referenceConcept);
		List<ReferenceConcept> subReferenceConcepts = referenceConcept.getSubReferences();
		if(subReferenceConcepts.isEmpty()) {
			if(referenceIsRepresentedCount > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			boolean referenceIsRepresentedByHeritage = true;
			for(ReferenceConcept subReferenceConcept : subReferenceConcepts) {
				boolean subReferenceRepresentedByHeritage = isReferenceConceptSubTypesRepresented(subReferenceConcept);
				referenceIsRepresentedByHeritage = referenceIsRepresentedByHeritage && subReferenceRepresentedByHeritage;
			}
			return referenceIsRepresentedByHeritage;
		}
	}

}
