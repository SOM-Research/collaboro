package fr.inria.atlanmod.collaboro.metrics;

import java.util.List;

public interface MetricsFactory {
	
	public List<AbstractSyntaxMetric> getAbstractSyntaxMetrics();
	public List<ConcreteSyntaxMetric> getConcreteSyntaxMetrics();
	

}
