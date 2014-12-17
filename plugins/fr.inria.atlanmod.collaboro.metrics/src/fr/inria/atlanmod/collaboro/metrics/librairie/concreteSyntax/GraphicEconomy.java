package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;

public class GraphicEconomy extends ConcreteSyntaxGraphicalMetricImpl {
	
	private static String dimension = "Ontological";
	private static String description = "The number of different graphical symbols should be cognitively manageable";
	
	public GraphicEconomy(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
	}
	
	public GraphicEconomy(ModelMapping modelMapping) {
		super("graphic economy","Ontological", "The number of different graphical symbols should be cognitively manageable",6);
		this.modelMapping = modelMapping;
	}
	
	public List<MetricResult> execute() {
		System.out.println("Excecute GraphicEconomy");
		List<MetricResult> results = new ArrayList<MetricResult>();
		
//		List<ClassSymbol> classSymbols = modelMapping.getConcreteClassSymbols();
//		List<AttributeSymbol> attributeSymbols = modelMapping.getConcreteAttributeSymbols();
//		List<ReferenceSymbol> referenceSymbols = modelMapping.getConcreteReferenceSymbols();
//		
//		int numberOfSymbols = 0;
//		numberOfSymbols += classSymbols.size();
//		numberOfSymbols += attributeSymbols.size();
//		numberOfSymbols += referenceSymbols.size();
//		
//		MetricResultImpl metricResult = new MetricResultImpl();
//		if(numberOfSymbols > this.acceptanceRatio) {
//			metricResult.setReason("There are too many different graphical symbols (" + numberOfSymbols + ")");
//			float ratio = 0;
//			metricResult.setRatio(ratio);
//			metricResult.setStatus(MetricResultStatus.BAD);
//			metricResult.setReferredElements(new ArrayList<ReferredElement>());
//			results.add(metricResult);
//		} else {
//			metricResult.setReason("The number of symbols is cognitively manageable (" + numberOfSymbols + ")");
//			float ratio = 0;
//			metricResult.setRatio(ratio);
//			metricResult.setStatus(MetricResultStatus.GOOD);
//			metricResult.setReferredElements(new ArrayList<ReferredElement>());
//			results.add(metricResult);
//		}
	
		return results;
	}

}
