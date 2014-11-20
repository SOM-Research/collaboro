package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
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
		List<EObject> noRepresentationElements = new ArrayList<EObject>();
		int count = 0;
		for(EObject abstractSyntaxElement : modelMapping.getAbstractConcepts().values()) {
			System.out.println(abstractSyntaxElement);
			boolean found = false;
			for(Relationship relationship : mapping) {
				if(relationship.getRelationshipFrom().equals(abstractSyntaxElement)) {
					found = true;
				}
			}
			if(!found) {
				noRepresentationElements.add(abstractSyntaxElement);
				count++;
			}
		}
		if(count > 0) {
			MetricResultImpl metricResult = new MetricResultImpl();
			metricResult.setReason("There are semantic constructs not represented by any graphical symbol (" + count + ")");
			float ratio = (float)(count*100/modelMapping.getAbstractConcepts().size());
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			//metricResult.setReferredElements(noRepresentationElements);
		}
		return results;
	}

}
