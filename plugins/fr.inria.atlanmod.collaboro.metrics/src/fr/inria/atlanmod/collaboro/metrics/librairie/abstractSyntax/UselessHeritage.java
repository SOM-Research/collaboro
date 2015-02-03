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
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;

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
		List<ReferredElement> referredElements = new ArrayList<ReferredElement>();
		
		//Find all instance of heritage
		Map<EClass,List<EClass>> classSubTypeMap = new HashMap<EClass,List<EClass>>();
		List<EObject> abstractSyntaxElements = this.abstractModel.eContents();
		
		//initialize subTypeMap 
		for(EObject element : abstractSyntaxElements) {
			if(element instanceof EClass) {
				EClass classElement = (EClass) element;
				classSubTypeMap.put(classElement, new ArrayList<EClass>());
			}
		}
		
		for(EObject element : abstractSyntaxElements) {
			if(element instanceof EClass) {
				EClass classElement = (EClass) element;
				List<EClass> classSuperType = classElement.getESuperTypes();
				for(EClass superClass : classSuperType) {
					List<EClass> subTypes = classSubTypeMap.get(superClass);
					subTypes.add(classElement);
				}
				
				//System.out.println("class : " + classElement.getName() + " -> " + classSuperType);
			}
		}
		
		for(EClass eClass : classSubTypeMap.keySet()) {
			List<EClass> classSubTypes = classSubTypeMap.get(eClass);
			if(classSubTypes.size() == 1) {
				EClass subElement = classSubTypes.get(0);
				
				MetricResultImpl metricResult = new MetricResultImpl();
				metricResult.setStatus(MetricResultStatus.BAD);
				metricResult.setReason("The super type " + eClass.getName() + " should have more than one subType (" + subElement.getName() + ")");
				
				List<ReferredElement> elements = new ArrayList<ReferredElement>();
				
				ReferredElement referredSuperElement = new AbstractSyntaxReferredElementImpl(eClass.getName(), ReferredElementReason.WRONG, eClass);
				ReferredElement referredSubElement = new AbstractSyntaxReferredElementImpl(subElement.getName(), ReferredElementReason.WRONG, subElement);
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
