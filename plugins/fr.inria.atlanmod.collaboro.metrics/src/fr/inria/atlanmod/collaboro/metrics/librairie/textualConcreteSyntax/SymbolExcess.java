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
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxTextualMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.model.Symbol;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class SymbolExcess extends ConcreteSyntaxTextualMetricImpl{
	
	private static String dimension = "Ontological";
	private static String description = "Symbol excess occurs when a concrete symbols does not correspond to any abstract concept";
	
	public SymbolExcess(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
	}
	
	public SymbolExcess(ModelMapping modelMapping) {
		super("symbol excess","Ontological", "Symbol excess occurs when a concrete symbols does not correspond to any abstract concept",0);
		this.modelMapping = modelMapping;
	}
	
	public List<MetricResult> execute() {
		System.out.println("Execute SymbolExcess");
		List<MetricResult> results = new ArrayList<MetricResult>();
		List<ReferredElement> excessSymbol = new ArrayList<ReferredElement>();
		
		
		Map<Symbol,Boolean> isSymbolRepresentedMap = checkSymbols();
		
		for(Symbol concreteSymbol : isSymbolRepresentedMap.keySet()) {
			if(!(isSymbolRepresentedMap.get(concreteSymbol))) {
				String concreteSymbolName = concreteSymbol.getFullName();
				NotationElement concreteSymbolNotationElement = concreteSymbol.getNotationElement();
				ReferredElement symbolReferredElement = new ConcreteSyntaxReferredElementImpl(concreteSymbolName, ReferredElementReason.MISSING, concreteSymbolNotationElement);
				excessSymbol.add(symbolReferredElement);
			}
		}
		
		int totalElements = isSymbolRepresentedMap.size();
		System.out.println(totalElements);
		MetricResultImpl metricResult = new MetricResultImpl();
		int excessElements = excessSymbol.size();
		if(excessElements > this.acceptanceRatio) {
			metricResult.setReason("There are concrete symbols that representes no abstract concepts (" + excessElements + ")");
			float ratio = (float)(excessElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			metricResult.setReferredElements(excessSymbol);
			results.add(metricResult);
		} else if (excessElements > 0) {
			metricResult.setReason("There are concrete symbols that representes no abstract concepts (" + excessElements + ")");
			float ratio = (float)(excessElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.MIDDLE);
			metricResult.setReferredElements(excessSymbol);
			results.add(metricResult);
		} else {
			metricResult.setReason("Every concrete symbol represents at least one abstract concept");
			float ratio = (float)(excessElements*100/totalElements);
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.GOOD);
			metricResult.setReferredElements(excessSymbol);
			results.add(metricResult);
		}
		return results;
	}
	
	private Map<Symbol,Boolean> checkSymbols() {
		Map<Symbol,Boolean> isSymbolRepresentedMap = new HashMap<Symbol, Boolean>();
		
		List<Symbol> concreteSymbols = this.modelMapping.getConcreteSymbols();
		for(Symbol symbol : concreteSymbols) {
			isSymbolRepresentedMap.put(symbol, this.modelMapping.isSymbolMapped(symbol));
		}
		
		return isSymbolRepresentedMap;
	}

}
