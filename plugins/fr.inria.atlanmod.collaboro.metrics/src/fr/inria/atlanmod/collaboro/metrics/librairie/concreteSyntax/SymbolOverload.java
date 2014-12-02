package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;
import fr.inria.atlanmod.collaboro.metrics.impl.AbstractSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.metrics.tools.Relationship;
import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class SymbolOverload extends ConcreteSyntaxGraphicalMetricImpl {

	private static String dimension = "Ontological";
	private static String description = "Symbol overload occurs when a single notation construct can represent multiple ontological concepts";
	
	public SymbolOverload(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
	}
	
	public SymbolOverload(ModelMapping modelMapping) {
		super("symbol overload", "Ontological", "Symbol overload occurs when a single notation construct can represent multiple ontological concepts.", 0);
		this.modelMapping = modelMapping;
	}
	
	public List<MetricResult> execute() {
		System.out.println("Execute SymbolOverload");
		List<MetricResult> metricResults = new ArrayList<MetricResult>();
		List<ReferredElement> overloadSymbols = new ArrayList<ReferredElement>();
		
		List<Relationship> mapping = modelMapping.getMapping();
		for(Symbol concreteSyntaxElement : modelMapping.getConcreteSymbols()) {
			String concreteSyntaxElementName = concreteSyntaxElement.getName();
			NotationElement concreteSyntaxElementObject = concreteSyntaxElement.getConcreteSyntaxElement();
			int relationshipCount = 0;
			for(Relationship relationship : mapping) {
				if(relationship.getRelationshipTo().equals(concreteSyntaxElement)) {
					relationshipCount++;
				}
			}
			
			if(relationshipCount > 1) {
				// Symbol redundancy
				ReferredElement referredElement = new AbstractSyntaxReferredElementImpl(concreteSyntaxElementName, ReferredElementReason.MISSING, concreteSyntaxElementObject);
				overloadSymbols.add(referredElement);
			}
		}
		if(overloadSymbols.size() > this.acceptanceRatio) {
			System.out.println("Fail");
			int count = overloadSymbols.size();
			MetricResultImpl metricResult = new MetricResultImpl();
			metricResult.setReason("There are notation constructs that representes more than one semantic construct (" + count + ")");
			float ratio = (float)(count*100/modelMapping.getConcreteSymbols().size());
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			metricResult.setReferredElements(overloadSymbols);
			metricResults.add(metricResult);
		} else {
			System.out.println("Success");
		}
		
		return metricResults;
	}

}
