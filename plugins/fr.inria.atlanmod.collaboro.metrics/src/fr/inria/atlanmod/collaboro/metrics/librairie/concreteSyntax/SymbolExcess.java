package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.List;

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

public class SymbolExcess extends ConcreteSyntaxGraphicalMetricImpl{
	
	private ModelMapping modelMapping;
	
	public SymbolExcess(ModelMapping modelMapping) {
		super("symbol excess","Ontological", "Symbol excess occurs when a notation construct does not correspond to any ontological concept.",0);
		this.modelMapping = modelMapping;
	}
	
	public List<MetricResult> execute() {
		List<MetricResult> metricResults = new ArrayList<MetricResult>();
		List<ReferredElement> excessSymbol = new ArrayList<ReferredElement>();
		
		List<Relationship> mapping = modelMapping.getMapping();
		for(Symbol concreteSyntaxElement : modelMapping.getConcreteSymbols()) {
			String concreteSyntaxElementName = concreteSyntaxElement.getName();
			NotationElement concreteSyntaxElementObject = concreteSyntaxElement.getConcreteSyntaxElement();
			int count = 0;
			for(Relationship relationship : mapping) {
				if(relationship.getRelationshipTo().equals(concreteSyntaxElement)) {
					count++;
				}
			}
			
			if(count == 0) {
				// Symbol excess
				ReferredElement referredElement = new AbstractSyntaxReferredElementImpl(concreteSyntaxElementName, ReferredElementReason.MISSING, concreteSyntaxElementObject);
				excessSymbol.add(referredElement);
			}
		}
		if(excessSymbol.size() > this.acceptanceRatio) {
			int count = excessSymbol.size();
			MetricResultImpl metricResult = new MetricResultImpl();
			metricResult.setReason("There are notation constructs that representes no semantic constructs (" + count + ")");
			float ratio = (float)(count*100/modelMapping.getConcreteSymbols().size());
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			metricResult.setReferredElements(excessSymbol);
			metricResults.add(metricResult);
		} else {
			
		}
		
		return metricResults;
	}

}
