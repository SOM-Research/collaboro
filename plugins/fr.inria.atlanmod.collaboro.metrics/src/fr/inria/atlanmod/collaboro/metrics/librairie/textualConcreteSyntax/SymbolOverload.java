package fr.inria.atlanmod.collaboro.metrics.librairie.textualConcreteSyntax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.model.Symbol;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class SymbolOverload extends ConcreteSyntaxGraphicalMetricImpl {

	private static String dimension = "Ontological";
	private static String description = "Symbol overload occurs when a single concrete symbol can represent multiple abstract concepts";
	
	public SymbolOverload(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
	}
	
	public SymbolOverload(ModelMapping modelMapping) {
		super("symbol overload", "Ontological", "Symbol overload occurs when a single concrete symbol can represent multiple abstract concepts", 0);
		this.modelMapping = modelMapping;
	}
	
	public List<MetricResult> execute() {
		System.out.println("Execute SymbolOverload");
		List<MetricResult> results = new ArrayList<MetricResult>();
		List<ReferredElement> overloadSymbols = new ArrayList<ReferredElement>();
		
		Map<Symbol,Integer> countSymbolOccurenceMap = countSymbolOccurences();
		
		for(Symbol concreteSymbol : countSymbolOccurenceMap.keySet()) {
			if(countSymbolOccurenceMap.get(concreteSymbol) > 1) {
				String concreteSymbolName = concreteSymbol.getFullName();
				NotationElement concreteSymbolNotationElement = concreteSymbol.getNotationElement();
				ReferredElement symbolReferredElement = new ConcreteSyntaxReferredElementImpl(concreteSymbolName, ReferredElementReason.WRONG, concreteSymbolNotationElement); 
			}
		}
		
		int totalElements = countSymbolOccurenceMap.size();
		MetricResultImpl metricResult = new MetricResultImpl();
		int excessElements = overloadSymbols.size();
		if(excessElements > this.acceptanceRatio) {
			metricResult.setReason("There are concrete symbols that represents more than one abstract concept (" + excessElements + ")");
			float ratio = (float)(excessElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			metricResult.setReferredElements(overloadSymbols);
			results.add(metricResult);
		} else if (excessElements > 0) {
			metricResult.setReason("There are concrete symbols that represents more than one abstract concept (" + excessElements + ")");
			float ratio = (float)(excessElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.MIDDLE);
			metricResult.setReferredElements(overloadSymbols);
			results.add(metricResult);
		} else {
			metricResult.setReason("Every concrete symbol represents at most one abstract concept");
			float ratio = (float)(excessElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.GOOD);
			metricResult.setReferredElements(overloadSymbols);
			results.add(metricResult);
		}
		return results;
	}
	
	private Map<Symbol,Integer> countSymbolOccurences() {
		Map<Symbol,Integer> countSymbolOccurenceMap = new HashMap<Symbol, Integer>();
		
		List<Symbol> concreteSymbols = this.modelMapping.getConcreteSymbols();
		for(Symbol concreteSymbol : concreteSymbols) {
			countSymbolOccurenceMap.put(concreteSymbol, this.modelMapping.countSymbolOccurence(concreteSymbol));
		}
		
		return countSymbolOccurenceMap;
		
	}

}
