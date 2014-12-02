package fr.inria.atlanmod.collaboro.metrics;

import java.util.List;

public interface MetricsFactory {
	
	public List<AbstractSyntaxMetric> getAbstractSyntaxMetrics();
	public List<ConcreteSyntaxMetric> getConcreteSyntaxMetrics();
	
	public void saveConfiguration();
	public void loadConfiguration();
	
	public void activate(String metricName);
	public void deactivate(String metricName);
	
	public void setMetricPriority(String metricName, MetricPriority priority);
	
	

}
