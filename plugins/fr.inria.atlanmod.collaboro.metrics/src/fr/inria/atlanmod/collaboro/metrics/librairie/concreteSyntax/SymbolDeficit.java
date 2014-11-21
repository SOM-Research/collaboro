package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;
import fr.inria.atlanmod.collaboro.metrics.impl.AbstractSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.metrics.tools.Relationship;

public class SymbolDeficit extends ConcreteSyntaxGraphicalMetricImpl {
	
	ModelMapping modelMapping;
	
	public SymbolDeficit(ModelMapping modelMapping) {
		this.name = "symbol deficit";
		this.description = "Symbol deficit occurs when there are semantic constructs that are not represented by any graphical symbol";
		this.dimension = "Ontological";
		this.modelMapping = modelMapping;
	}
	
	public List<MetricResult> execute() {
		List<MetricResult> results = new ArrayList<MetricResult>();
		List<Relationship> mapping = modelMapping.getMapping();
		List<ReferredElement> noRepresentationElements = new ArrayList<ReferredElement>();
		int count = 0;
		
		for(String abstractSyntaxElementName : modelMapping.getAbstractConcepts().keySet()) {
			EObject abstractSyntaxElement = modelMapping.getAbstractConcepts().get(abstractSyntaxElementName);
			boolean found = false;
			for(Relationship relationship : mapping) {
				if(relationship.getRelationshipFrom().equals(abstractSyntaxElement)) {
					found = true;
				}
			}
			if(!found) {
				ReferredElement referredElement = new AbstractSyntaxReferredElementImpl(abstractSyntaxElementName, ReferredElementReason.MISSING, abstractSyntaxElement);
				noRepresentationElements.add(referredElement);
				count++;
			}
		}
		
		if(count > 0) {
			MetricResultImpl metricResult = new MetricResultImpl();
			metricResult.setReason("There are semantic constructs not represented by any graphical symbol (" + count + ")");
			float ratio = (float)(count*100/modelMapping.getAbstractConcepts().size());
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			metricResult.setReferredElements(noRepresentationElements);
		}
		return results;
	}

}
