package fr.inria.atlanmod.collaboro.metrics.librairie.abstractSyntax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
	private static String description = "A super class with only one sub class doesn't make sense";
	
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
		
		if(results.size() > this.acceptanceRatio) {
			
		} else {

		}
		
		return results;
		
	}

}
