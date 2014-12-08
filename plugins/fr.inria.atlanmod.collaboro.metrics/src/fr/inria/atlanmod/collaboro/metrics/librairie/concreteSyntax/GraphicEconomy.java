package fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax;

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricResultStatus;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricResultImpl;
import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;

public class GraphicEconomy extends ConcreteSyntaxGraphicalMetricImpl {
	
	private static String dimension = "Ontological";
	private static String description = "The number of different graphical symbols should be cognitively manageable";
	//private ModelMapping modelMapping;
	
	public GraphicEconomy(String name, Integer acceptanceRatio, MetricPriority priority, boolean isActive)  {
		super(name,dimension,description,acceptanceRatio,priority,isActive);
	}
	
	public GraphicEconomy(ModelMapping modelMapping) {
		super("graphic economy","Ontological", "The number of different graphical symbols should be cognitively manageable",6);
		this.modelMapping = modelMapping;
	}
	
	public List<MetricResult> execute() {
		System.out.println("Excecute GraphicEconomy");
		List<MetricResult> metricResults = new ArrayList<MetricResult>();
		/*List<Symbol> symbols = modelMapping.getConcreteSymbols();
		
		if(symbols.size() > this.acceptanceRatio) {
			System.out.println("Fail");
			MetricResultImpl metricResult = new MetricResultImpl();
			metricResult.setReason("There are too many different graphical symbols (" + symbols.size() + ")");
			float ratio = 0;
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.BAD);
			metricResult.setReferredElements(new ArrayList<ReferredElement>());
			metricResults.add(metricResult);
		} else {
			System.out.println("Success");
			int count = symbols.size();
			MetricResultImpl metricResult = new MetricResultImpl();
			metricResult.setReason("The number of symbol is cognitively manageable");
			float ratio = count;
			metricResult.setRatio(ratio);
			metricResult.setStatus(MetricResultStatus.MIDDLE);
			metricResult.setReferredElements(new ArrayList<ReferredElement>());
			metricResults.add(metricResult);
		}
		*/
		return metricResults;
	}

}
