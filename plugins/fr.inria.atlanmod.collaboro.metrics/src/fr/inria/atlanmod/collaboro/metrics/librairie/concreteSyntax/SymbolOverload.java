package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.ReferredElementReason;
import fr.inria.atlanmod.collaboro.metrics.impl.AbstractSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxReferredElementImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.symbol.AttributeConcept;
import fr.inria.atlanmod.collaboro.metrics.symbol.AttributeSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.ClassConcept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ClassSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.ReferenceConcept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ReferenceSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.metrics.tools.Relationship;
import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class SymbolOverload extends ConcreteSyntaxGraphicalMetricImpl {

	private static String dimension = "Ontological";
	private static String description = "Symbol overload occurs when a single concrete symbol can represent multiple abstract concepts";
	
	private Map<ClassSymbol,Integer> countClassSymbolRepresentedMap;
	private Map<AttributeSymbol,Integer> countAttributeSymbolRepresentedMap;
	private Map<ReferenceSymbol,Integer> countReferenceSymbolRepresentedMap;
	
	public SymbolOverload(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
		this.countClassSymbolRepresentedMap = new HashMap<ClassSymbol, Integer>();
		this.countAttributeSymbolRepresentedMap = new HashMap<AttributeSymbol, Integer>();
		this.countReferenceSymbolRepresentedMap = new HashMap<ReferenceSymbol, Integer>();
	}
	
	public SymbolOverload(ModelMapping modelMapping) {
		super("symbol overload", "Ontological", "Symbol overload occurs when a single concrete symbol can represent multiple abstract concepts", 0);
		this.modelMapping = modelMapping;
		this.countClassSymbolRepresentedMap = new HashMap<ClassSymbol, Integer>();
		this.countAttributeSymbolRepresentedMap = new HashMap<AttributeSymbol, Integer>();
		this.countReferenceSymbolRepresentedMap = new HashMap<ReferenceSymbol, Integer>();
	}
	
	public List<MetricResult> execute() {
		System.out.println("Execute SymbolOverload");
		List<MetricResult> results = new ArrayList<MetricResult>();
		List<ReferredElement> overloadSymbols = new ArrayList<ReferredElement>();
		
		checkClassSymbols();
		checkAttributeSymbols();
		checkReferenceSymbols();
		
		int totalElements = 0;
		for(ClassSymbol classSymbol : this.modelMapping.getConcreteClassSymbols()) {
			totalElements++;
			if((countClassSymbolRepresentedMap.get(classSymbol) != null) && (countClassSymbolRepresentedMap.get(classSymbol) > 1)) {
				ReferredElement classReferredElement = new ConcreteSyntaxReferredElementImpl(classSymbol.getName(), ReferredElementReason.WRONG, classSymbol.getConcreteSyntaxElement());
				overloadSymbols.add(classReferredElement);
			}
		}
		
		for(AttributeSymbol attributeSymbol : this.modelMapping.getConcreteAttributeSymbols()) {
			totalElements++;
			if((countAttributeSymbolRepresentedMap.get(attributeSymbol) != null) && (countAttributeSymbolRepresentedMap.get(attributeSymbol) > 1)) {
				ReferredElement attributeReferredElement = new ConcreteSyntaxReferredElementImpl(attributeSymbol.getName(), ReferredElementReason.WRONG, attributeSymbol.getConcreteSyntaxElement());
				overloadSymbols.add(attributeReferredElement);
			}
		}
		
		for(ReferenceSymbol referenceSymbol : this.modelMapping.getConcreteReferenceSymbols()) {
			totalElements++;
			if((countReferenceSymbolRepresentedMap.get(referenceSymbol) != null) && (countReferenceSymbolRepresentedMap.get(referenceSymbol) > 1)) {
				ReferredElement referenceReferredElement = new ConcreteSyntaxReferredElementImpl(referenceSymbol.getName(), ReferredElementReason.WRONG, referenceSymbol.getConcreteSyntaxElement());
				overloadSymbols.add(referenceReferredElement);
			}
		}
		
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
	
	private void checkClassSymbols() {
		List<ClassSymbol> concreteClassSymbols = this.modelMapping.getConcreteClassSymbols();
		for(ClassSymbol classSymbol : concreteClassSymbols) {
			countClassSymbolRepresentedMap.put(classSymbol, containsInToCount(classSymbol));
		}
	}
	
	private void checkAttributeSymbols() {
		List<AttributeSymbol> concreteAttributeSymbols = this.modelMapping.getConcreteAttributeSymbols();
		for(AttributeSymbol attributeSymbol : concreteAttributeSymbols) {
			countAttributeSymbolRepresentedMap.put(attributeSymbol, containsInToCount(attributeSymbol));
		}
	}
	
	private void checkReferenceSymbols() {
		List<ReferenceSymbol> concreteReferenceSymbols = this.modelMapping.getConcreteReferenceSymbols();
		for(ReferenceSymbol referenceSymbol : concreteReferenceSymbols) {
			countReferenceSymbolRepresentedMap.put(referenceSymbol, containsInToCount(referenceSymbol));
		}
	}
	
	private int containsInToCount(Symbol symbol) {
		List<Relationship> relationships = this.modelMapping.getMapping();
		int count = 0;
		for(Relationship relationship : relationships) {
			if(relationship.getRelationshipTo().equals(symbol)) {
				count++;
			}
		}
		return count;
	}

}
