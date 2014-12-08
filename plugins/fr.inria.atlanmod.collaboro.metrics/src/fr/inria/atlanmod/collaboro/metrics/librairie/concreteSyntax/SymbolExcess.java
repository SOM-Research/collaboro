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
import fr.inria.atlanmod.collaboro.metrics.symbol.Concept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ReferenceConcept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ReferenceSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.metrics.tools.Relationship;
import fr.inria.atlanmod.collaboro.notation.NotationElement;

public class SymbolExcess extends ConcreteSyntaxGraphicalMetricImpl{
	
	private static String dimension = "Ontological";
	private static String description = "Symbol excess occurs when a concrete symbols does not correspond to any abstract concept";
	
	private Map<ClassSymbol,Boolean> isClassSymbolRepresentedMap;
	private Map<AttributeSymbol,Boolean> isAttributeSymbolRepresentedMap;
	private Map<ReferenceSymbol,Boolean> isReferenceSymbolRepresentedMap;
	
	public SymbolExcess(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
		this.isClassSymbolRepresentedMap = new HashMap<ClassSymbol, Boolean>();
		this.isAttributeSymbolRepresentedMap = new HashMap<AttributeSymbol, Boolean>();
		this.isReferenceSymbolRepresentedMap = new HashMap<ReferenceSymbol, Boolean>();
	}
	
	public SymbolExcess(ModelMapping modelMapping) {
		super("symbol excess","Ontological", "Symbol excess occurs when a concrete symbols does not correspond to any abstract concept",0);
		this.modelMapping = modelMapping;
		this.isClassSymbolRepresentedMap = new HashMap<ClassSymbol, Boolean>();
		this.isAttributeSymbolRepresentedMap = new HashMap<AttributeSymbol, Boolean>();
		this.isReferenceSymbolRepresentedMap = new HashMap<ReferenceSymbol, Boolean>();
	}
	
	public List<MetricResult> execute() {
		System.out.println("Execute SymbolExcess");
		List<MetricResult> results = new ArrayList<MetricResult>();
		List<ReferredElement> excessSymbol = new ArrayList<ReferredElement>();
		
		checkClassSymbols();
		checkAttributeSymbols();
		checkReferenceSymbols();
		
		int totalElements = 0;
		for(ClassSymbol classSymbol : this.modelMapping.getConcreteClassSymbols()) {
			totalElements++;
			if((isClassSymbolRepresentedMap.get(classSymbol) != null) && (!isClassSymbolRepresentedMap.get(classSymbol))) {
				ReferredElement classReferredElement = new ConcreteSyntaxReferredElementImpl(classSymbol.getName(), ReferredElementReason.MISSING, classSymbol.getConcreteSyntaxElement());
				excessSymbol.add(classReferredElement);
			}
		}
		
		for(AttributeSymbol attributeSymbol : this.modelMapping.getConcreteAttributeSymbols()) {
			totalElements++;
			if((isAttributeSymbolRepresentedMap.get(attributeSymbol) != null) && (!isAttributeSymbolRepresentedMap.get(attributeSymbol))) {
				ReferredElement attributeReferredElement = new ConcreteSyntaxReferredElementImpl(attributeSymbol.getName(), ReferredElementReason.MISSING, attributeSymbol.getConcreteSyntaxElement());
				excessSymbol.add(attributeReferredElement);
			}
		}
		
		for(ReferenceSymbol referenceSymbol : this.modelMapping.getConcreteReferenceSymbols()) {
			totalElements++;
			if((isReferenceSymbolRepresentedMap.get(referenceSymbol) != null) && (!isReferenceSymbolRepresentedMap.get(referenceSymbol))) {
				ReferredElement referenceReferredElement = new ConcreteSyntaxReferredElementImpl(referenceSymbol.getName(), ReferredElementReason.MISSING, referenceSymbol.getConcreteSyntaxElement());
				excessSymbol.add(referenceReferredElement);
			}
		}
		
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
	
	private void checkClassSymbols() {
		List<ClassSymbol> concreteClassSymbols = this.modelMapping.getConcreteClassSymbols();
		for(ClassSymbol classSymbol : concreteClassSymbols) {
			isClassSymbolRepresentedMap.put(classSymbol, containsInTo(classSymbol));
		}
	}
	
	private void checkAttributeSymbols() {
		List<AttributeSymbol> concreteAttributeSymbols = this.modelMapping.getConcreteAttributeSymbols();
		for(AttributeSymbol attributeSymbol : concreteAttributeSymbols) {
			isAttributeSymbolRepresentedMap.put(attributeSymbol, containsInTo(attributeSymbol));
		}
	}
	
	private void checkReferenceSymbols() {
		List<ReferenceSymbol> concreteReferenceSymbols = this.modelMapping.getConcreteReferenceSymbols();
		for(ReferenceSymbol referenceSymbol : concreteReferenceSymbols) {
			isReferenceSymbolRepresentedMap.put(referenceSymbol, containsInTo(referenceSymbol));
		}
	}
	
	private boolean containsInTo(Symbol symbol) {
		List<Relationship> relationships = this.modelMapping.getMapping();
		for(Relationship relationship : relationships) {
			Symbol concreteSymbol = relationship.getRelationshipTo();
			if(concreteSymbol.equals(symbol)) {
				return true;
			}
		}
		return false;
	}

}
