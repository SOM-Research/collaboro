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
import fr.inria.atlanmod.collaboro.metrics.symbol.Concept;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.metrics.tools.Relationship;

public class SymbolDeficit extends ConcreteSyntaxGraphicalMetricImpl {
	
	private static String description = "Symbol deficit occurs when there are semantic constructs that are not represented by any graphical symbol";
	private static String dimension = "Ontological";
	
	public SymbolDeficit(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
	}
	
	public SymbolDeficit(ModelMapping modelMapping) {
		super("symbol deficit", "Ontological", "Symbol deficit occurs when there are semantic constructs that are not represented by any graphical symbol", 0);
		this.modelMapping = modelMapping;
	}
	
	public List<MetricResult> execute() {
		System.out.println("Execute : SymbolDeficit");
		List<MetricResult> results = new ArrayList<MetricResult>();
		List<ReferredElement> noRepresentationElements = new ArrayList<ReferredElement>();
		
		List<Relationship> mapping = modelMapping.getMapping();
		Map<Concept,Boolean> isAbstractElementRepresentedMap = new HashMap<Concept,Boolean>();
		int count = 0;
		
		for(Concept abstractSyntaxElement : modelMapping.getAbstractConcepts()) {
			isAbstractElementRepresentedMap.put(abstractSyntaxElement, false);
			String abstractSyntaxElementName = abstractSyntaxElement.getName();
			for(Relationship relationship : mapping) {
				if(relationship.getRelationshipFrom().equals(abstractSyntaxElement)) {
					isAbstractElementRepresentedMap.put(abstractSyntaxElement, true);
				}
			}
		}
		
		for(Concept abstractSyntaxElement : modelMapping.getAbstractConcepts()) {
			Boolean isAbstractSyntaxElementRepresented = isAbstractElementRepresentedMap.get(abstractSyntaxElement);
			String abstractSyntaxElementName = abstractSyntaxElement.getName();
			EObject abstractSyntaxElementObject = abstractSyntaxElement.getAbstractModelElement();
			if(!isAbstractSyntaxElementRepresented) {
				// Check for Eopposite (if EOpposite is represented, this reference does not need to be represented)
				if(abstractSyntaxElementObject instanceof EReference) {
					EReference eReference  = (EReference) abstractSyntaxElementObject;
					EReference eReferenceOpposite = eReference.getEOpposite();
					if(eReferenceOpposite != null) {
						Concept referenceConceptOpposite = modelMapping.getConceptByAbstractSyntaxElement(eReferenceOpposite);
						if(isAbstractElementRepresentedMap.get(referenceConceptOpposite) == false) {
							ReferredElement referredElement = new AbstractSyntaxReferredElementImpl(abstractSyntaxElementName, ReferredElementReason.MISSING, abstractSyntaxElementObject);
							noRepresentationElements.add(referredElement);
							count++;
						}
					}
				} else {
					ReferredElement referredElement = new AbstractSyntaxReferredElementImpl(abstractSyntaxElementName, ReferredElementReason.MISSING, abstractSyntaxElementObject);
					noRepresentationElements.add(referredElement);
					count++;
				}
			}
		}
		
		
		if(count > this.acceptanceRatio) {
			System.out.println("Fail");
			MetricResultImpl metricResult = new MetricResultImpl();
			metricResult.setReason("There are semantic constructs not represented by any graphical symbol (" + count + ")");
			float ratio = (float)(count*100/modelMapping.getAbstractConcepts().size());
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			metricResult.setReferredElements(noRepresentationElements);
			results.add(metricResult);
		} else {
			System.out.println("Success");
		}
		return results;
	}

}
