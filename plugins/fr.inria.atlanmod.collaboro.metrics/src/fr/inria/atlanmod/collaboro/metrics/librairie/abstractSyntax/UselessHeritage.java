package fr.inria.atlanmod.collaboro.metrics.librairie.abstractSyntax;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;
import fr.inria.atlanmod.collaboro.metrics.impl.AbstractSyntaxMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.AbstractSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.model.ClassConcept;

public class UselessHeritage extends AbstractSyntaxMetricImpl {
	
	private static String dimension = "Abstract syntax";
	private static String description = "Inheritage errors";
	
	public UselessHeritage(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive) {
		super(name, dimension, description, acceptanceRatio, priority, isActive);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MetricResult> execute() {
		
		System.out.println("Excecute Useless Heritage");
		List<MetricResult> results = new ArrayList<MetricResult>();

		List<ClassConcept> abstractClassConcepts = this.abstractConceptContainer.getClassConcepts();
		
		for(ClassConcept classConcept : abstractClassConcepts) {
			List<ClassConcept> classSubTypes = classConcept.getSubTypes();
			if(classSubTypes.size() == 1) {
				// Super Class has only one subType
				ClassConcept subClass = classSubTypes.get(0);
				
				MetricResultImpl metricResult = new MetricResultImpl();
				metricResult.setStatus(MetricResultStatus.BAD);
				metricResult.setReason("The super type " + classConcept.getName() + " should have more than one subType (" + subClass.getName() + ")");
				
				List<ReferredElement> elements = new ArrayList<ReferredElement>();
				
				ReferredElement referredSuperElement = new AbstractSyntaxReferredElementImpl(classConcept.getName(), ReferredElementReason.WRONG, classConcept.getAbstractModelElement());
				ReferredElement referredSubElement = new AbstractSyntaxReferredElementImpl(subClass.getName(), ReferredElementReason.WRONG, subClass.getAbstractModelElement());
				elements.add(referredSubElement);
				elements.add(referredSuperElement);
				metricResult.setReferredElements(elements);
				
				results.add(metricResult);
			}
			
			
		}
		Set<ClassConcept> duplicateInheritageList = new HashSet<ClassConcept>();
		for(ClassConcept classConcept : abstractClassConcepts) {
			List<ClassConcept> allClassSubTypes = gatherSubConcepts(classConcept);
			List<ClassConcept> duplicateClassConcept = getDuplicateClassConcept(allClassSubTypes);
			if(duplicateClassConcept.size() > 0) {
				duplicateInheritageList.addAll(duplicateClassConcept);
			}
		}
		
		if(duplicateInheritageList.size() > 0) {
			for(ClassConcept duplicateConcept : duplicateInheritageList) {
				System.out.println("found duplicate : " + duplicateInheritageList);
				
				MetricResultImpl metricResult = new MetricResultImpl();
				metricResult.setStatus(MetricResultStatus.BAD);
				metricResult.setReason("The sub-class " + duplicateConcept.getName() + " has duplicate inheritage");
				
				List<ReferredElement> elements = new ArrayList<ReferredElement>();
				
				ReferredElement referredElement = new AbstractSyntaxReferredElementImpl(duplicateConcept.getName(), ReferredElementReason.WRONG, duplicateConcept.getAbstractModelElement());
				elements.add(referredElement);
				metricResult.setReferredElements(elements);
			
				results.add(metricResult);
			}
		}
		
		if(results.size() > this.acceptanceRatio) {
			
		} else {

		}
		
		return results;
		
	}
	
	private List<ClassConcept> gatherSubConcepts(ClassConcept classConcept) {
		List<ClassConcept> result = new ArrayList<ClassConcept>();
		
		List<ClassConcept> subConcepts = classConcept.getSubTypes();
		result.addAll(subConcepts);
		for(ClassConcept subConcept : subConcepts) {
			result.addAll(gatherSubConcepts(subConcept));
		}
		
		return result;

	}
	
	private List<ClassConcept> getDuplicateClassConcept(List<ClassConcept> classConcepts) {
		List<ClassConcept> duplicateClassConcepts = new ArrayList<ClassConcept>();
		List<ClassConcept> otherClassConcepts = new ArrayList<ClassConcept>();
		for(ClassConcept classConcept : classConcepts) {
			if(otherClassConcepts.contains(classConcept)) {
				duplicateClassConcepts.add(classConcept);
			} else {
				otherClassConcepts.add(classConcept);
			}
		}
		return duplicateClassConcepts;
	}


}
