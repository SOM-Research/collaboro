package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

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

public class SymbolRedundancy extends ConcreteSyntaxGraphicalMetricImpl{
	
	private static String dimension = "Ontological";
	private static String description = "Symbol redundancy occurs when multiple notation constructs can be used to represent a single ontological concept";
	
	public SymbolRedundancy(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
	}
	
	public SymbolRedundancy(ModelMapping modelMapping) {
		super("symbol redundancy", "Ontological", "Symbol redundancy occurs when multiple notation constructs can be used to represent a single ontological concept.",0);
		this.modelMapping = modelMapping;
	}
	
	public List<MetricResult> execute() {
		System.out.println("Execute SymbolRedundancy");
		List<MetricResult> metricResults = new ArrayList<MetricResult>();
		List<ReferredElement> redundantSymbols = new ArrayList<ReferredElement>();
		
		List<Relationship> mapping = modelMapping.getMapping();
		
		for(Concept abstractSyntaxElement : modelMapping.getAbstractConcepts()) {
			String abstractSyntaxElementName = abstractSyntaxElement.getName();
			EObject abstractSyntaxElementObject = abstractSyntaxElement.getAbstractModelElement();
			int relationshipCount = 0;
			for(Relationship relationship : mapping) {
				if(relationship.getRelationshipFrom().equals(abstractSyntaxElement)) {
					relationshipCount++;
				}
			}
			
			if(relationshipCount > 1) {
				// Symbol redundancy
				ReferredElement referredElement = new AbstractSyntaxReferredElementImpl(abstractSyntaxElementName, ReferredElementReason.MISSING, abstractSyntaxElementObject);
				redundantSymbols.add(referredElement);
			}
		}
		
		if(redundantSymbols.size() > this.acceptanceRatio) {
			System.out.println("Fail");
			int count = redundantSymbols.size();
			MetricResultImpl metricResult = new MetricResultImpl();
			metricResult.setReason("There are semantic constructs that are represented by more than one notation construct (" + count + ")");
			float ratio = (float)(count*100/modelMapping.getAbstractConcepts().size());
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			metricResult.setReferredElements(redundantSymbols);
			metricResults.add(metricResult);
		} else {
			System.out.println("Success");
		}
		
		return metricResults;
	}


}
