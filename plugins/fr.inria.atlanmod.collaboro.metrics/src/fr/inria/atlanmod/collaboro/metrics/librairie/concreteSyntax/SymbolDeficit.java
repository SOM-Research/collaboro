package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;
import fr.inria.atlanmod.collaboro.metrics.impl.AbstractSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.symbol.AttributeConcept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ClassConcept;
import fr.inria.atlanmod.collaboro.metrics.symbol.Concept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ReferenceConcept;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.metrics.tools.Relationship;

public class SymbolDeficit extends ConcreteSyntaxGraphicalMetricImpl {
	
	private static String description = "Symbol deficit occurs when there are abstract concepts that are not represented by any concrete symbol";
	private static String dimension = "Ontological";
	
	private Map<ClassConcept,Boolean> isClassConceptRepresentedMap;
	private Map<AttributeConcept,Boolean> isAttributeConceptRepresentedMap;
	private Map<ReferenceConcept,Boolean> isReferenceConceptRepresentedMap;
	
	public SymbolDeficit(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
		this.isClassConceptRepresentedMap = new HashMap<ClassConcept, Boolean>();
		this.isAttributeConceptRepresentedMap = new HashMap<AttributeConcept, Boolean>();
		this.isReferenceConceptRepresentedMap = new HashMap<ReferenceConcept, Boolean>();
	}
	
	public SymbolDeficit(ModelMapping modelMapping) {
		super("symbol deficit", dimension, description, 0);
		this.modelMapping = modelMapping;
		this.isClassConceptRepresentedMap = new HashMap<ClassConcept, Boolean>();
		this.isAttributeConceptRepresentedMap = new HashMap<AttributeConcept, Boolean>();
		this.isReferenceConceptRepresentedMap = new HashMap<ReferenceConcept, Boolean>();
	}
	
	public List<MetricResult> execute() {
		System.out.println("Execute : SymbolDeficit");
		List<MetricResult> results = new ArrayList<MetricResult>();
		List<ReferredElement> noRepresentationElements = new ArrayList<ReferredElement>();
		
		checkClassConcepts();
		System.out.println(isClassConceptRepresentedMap);
		checkAttributeConcepts();
		System.out.println(isAttributeConceptRepresentedMap);
		checkReferenceConcepts();
		System.out.println(isReferenceConceptRepresentedMap);
		
		int totalElements = 0;
		for(ClassConcept classConcept : this.modelMapping.getAbstractClassConcepts()) {
			totalElements++;
			if((isClassConceptRepresentedMap.get(classConcept) != null) && (!isClassConceptRepresentedMap.get(classConcept))) {
				ReferredElement classReferredElement = new AbstractSyntaxReferredElementImpl(classConcept.getName(), ReferredElementReason.MISSING, classConcept.getAbstractModelElement());
				noRepresentationElements.add(classReferredElement);
			}
		}
		
		for(AttributeConcept attributeConcept : this.modelMapping.getAbstractAttributeConcepts()) {
			totalElements++;
			if((!isAttributeConceptRepresentedMap.get(attributeConcept)) && (isAttributeConceptRepresentedMap.get(attributeConcept) != null)) {
				ReferredElement classReferredElement = new AbstractSyntaxReferredElementImpl(attributeConcept.getName(), ReferredElementReason.MISSING, attributeConcept.getAbstractModelElement());
				noRepresentationElements.add(classReferredElement);
			}
		}
		
		for(ReferenceConcept referenceConcept : this.modelMapping.getAbstractReferenceConcepts()) {
			totalElements++;
			if((!isReferenceConceptRepresentedMap.get(referenceConcept)) && (isReferenceConceptRepresentedMap.get(referenceConcept) != null)) {
				ReferredElement classReferredElement = new AbstractSyntaxReferredElementImpl(referenceConcept.getName(), ReferredElementReason.MISSING, referenceConcept.getAbstractModelElement());
				noRepresentationElements.add(classReferredElement);
			}
		}
		
		MetricResultImpl metricResult = new MetricResultImpl();
		int missingElements = noRepresentationElements.size();
		if(missingElements > this.acceptanceRatio) {
			metricResult.setReason("There are abstract concepts not represented by any concrete symbol (" + missingElements + ")");
			float ratio = (float)(missingElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			metricResult.setReferredElements(noRepresentationElements);
			results.add(metricResult);
		} else if(missingElements > 0) {
			metricResult.setReason("There are abstract concepts not represented by any concrete symbol (" + missingElements + ")");
			float ratio = (float)(missingElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.MIDDLE);
			metricResult.setReferredElements(noRepresentationElements);
			results.add(metricResult);
		} else {
			metricResult.setReason("All abstract concepts are represented by at least one concrete symbol");
			float ratio = (float)(missingElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.GOOD);
			metricResult.setReferredElements(noRepresentationElements);
			results.add(metricResult);
		}
		return results;
	}
	
	private void checkClassConcepts() {
		List<ClassConcept> abstractClassConcepts = this.modelMapping.getAbstractClassConcepts();
		//initialise map 
		for(ClassConcept classConcept : abstractClassConcepts) {
			isClassConceptRepresentedMap.put(classConcept, containsInFrom(classConcept));
		}
		System.out.println(isClassConceptRepresentedMap);
		
		// check Heritage
		for(ClassConcept classConcept : abstractClassConcepts) {
			Boolean isClassRepresented = isClassConceptRepresentedMap.get(classConcept);
			if(!isClassRepresented) {
				System.out.println("class Concept " + classConcept);
				boolean alreadyRepresentedByHeritage = isClassRepresentedBySubType(classConcept);
				System.out.println("does not need rep : " + alreadyRepresentedByHeritage);
				if(alreadyRepresentedByHeritage) {
					isClassConceptRepresentedMap.put(classConcept, true);
				}
			}
		}
	}
	
	private void checkAttributeConcepts() {
		List<AttributeConcept> abstractAttributeConcepts = this.modelMapping.getAbstractAttributeConcepts();
		//initialise map
		for(AttributeConcept attributeConcept : abstractAttributeConcepts) {
			isAttributeConceptRepresentedMap.put(attributeConcept, containsInFrom(attributeConcept));
		}
		System.out.println(isAttributeConceptRepresentedMap);
		
		// check heritage
		for(AttributeConcept attributeConcept : abstractAttributeConcepts) {
			Boolean isAttributeRepresented = isAttributeConceptRepresentedMap.get(attributeConcept);
			if(!isAttributeRepresented) {
				boolean alreadyRepresentedByHeritage = isAttributeRepresentedBySubType(attributeConcept);
				if(alreadyRepresentedByHeritage) {
					isAttributeConceptRepresentedMap.put(attributeConcept, true);
				}
			}
		}
	}
	
	private void checkReferenceConcepts() {
		List<ReferenceConcept> abstractReferenceConcepts = this.modelMapping.getAbstractReferenceConcepts();
		//initialise map
		for(ReferenceConcept referenceConcept : abstractReferenceConcepts) {
			isReferenceConceptRepresentedMap.put(referenceConcept, containsInFrom(referenceConcept));
		}
		System.out.println(isReferenceConceptRepresentedMap);
		
		//check Heritage
		for(ReferenceConcept referenceConcept : abstractReferenceConcepts) {
			Boolean isReferenceRepresented = isReferenceConceptRepresentedMap.get(referenceConcept);
			if(!isReferenceRepresented) {
				boolean alreadyRepresentedByHeritage = isReferenceRepresentedBySubType(referenceConcept);
				if(alreadyRepresentedByHeritage) {
					isReferenceConceptRepresentedMap.put(referenceConcept, true);
				}
			}
		}
		
		//check opposite
		for(ReferenceConcept referenceConcept : abstractReferenceConcepts) {
			Boolean isReferenceRepresented = isReferenceConceptRepresentedMap.get(referenceConcept);
			if(!isReferenceRepresented) {
				ReferenceConcept oppositeReference = referenceConcept.getReferenceOpposite();
				if(oppositeReference != null) {
					boolean oppositeAlreadyRepresented = isReferenceConceptRepresentedMap.get(oppositeReference);
					if(oppositeAlreadyRepresented) {
						isReferenceConceptRepresentedMap.put(referenceConcept, true);
					}
				}
			}
		}
	}
	
	private boolean isClassRepresentedBySubType(ClassConcept classConcept) {
		boolean classIsRepresented = isClassConceptRepresentedMap.get(classConcept);
		if(classIsRepresented) {
			return true;
		} else {
			List<ClassConcept> subClassConcepts = classConcept.getSubTypes();
			if(subClassConcepts.isEmpty()) {
				return classIsRepresented;
			} else {
				boolean classIsRepresentedByHeritage = true;
				for(ClassConcept subClassConcept : subClassConcepts) {
					boolean subClassRepresentedByHeritage = isClassRepresentedBySubType(subClassConcept);
					classIsRepresentedByHeritage = classIsRepresentedByHeritage && subClassRepresentedByHeritage;
				}
				return classIsRepresentedByHeritage;
			}
		}
	}
	
	private boolean isAttributeRepresentedBySubType(AttributeConcept attributeConcept) {
		boolean attributeIsRepresented = isAttributeConceptRepresentedMap.get(attributeConcept);
		if(attributeIsRepresented) {
			return true;
		} else {
			List<AttributeConcept> subAttributeConcepts = attributeConcept.getSubAttributes();
			if(subAttributeConcepts.isEmpty()) {
				return attributeIsRepresented;
			} else {
				boolean attributeIsRepresentedByHeritage = true;
				for(AttributeConcept subAttributeConcept : subAttributeConcepts) {
					boolean subAttributeRepresentedByHeritage = isAttributeRepresentedBySubType(subAttributeConcept);
					attributeIsRepresentedByHeritage = attributeIsRepresentedByHeritage && subAttributeRepresentedByHeritage;
				}
				return attributeIsRepresentedByHeritage;
			}
		}
	}
	
	private boolean isReferenceRepresentedBySubType(ReferenceConcept referenceConcept) {
		boolean referenceIsRepresented = isReferenceConceptRepresentedMap.get(referenceConcept);
		if(referenceIsRepresented) {
			return true;
		} else {
			List<ReferenceConcept> subReferenceConcepts = referenceConcept.getSubReferences();
			if(subReferenceConcepts.isEmpty()) {
				return referenceIsRepresented;
			} else {
				boolean referenceIsRepresentedByHeritage = true;
				for(ReferenceConcept subReferenceConcept : subReferenceConcepts) {
					boolean subReferenceRepresentedByHeritage = isReferenceRepresentedBySubType(subReferenceConcept);
					referenceIsRepresentedByHeritage = referenceIsRepresentedByHeritage && subReferenceRepresentedByHeritage;
				}
				return referenceIsRepresentedByHeritage;
			}
		}
	}
	
	private boolean containsInFrom(Concept concept) {
		List<Relationship> relationships = this.modelMapping.getMapping();
		for(Relationship relationship : relationships) {
			Concept abstractConcept = relationship.getRelationshipFrom();
			if(abstractConcept.equals(concept)) {
				return true;
			}
		}
		return false;
	}
	
	

	
}
