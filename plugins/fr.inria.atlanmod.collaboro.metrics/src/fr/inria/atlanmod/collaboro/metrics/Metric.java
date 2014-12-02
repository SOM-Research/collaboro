package fr.inria.atlanmod.collaboro.metrics;

import java.util.List;

public interface Metric {
	
	public List<MetricResult> execute();
	
	public String getName();
	
	public String getDimension();
	
	public String getDescription();
	
	public Integer getAcceptanceRatio();
	
	public MetricPriority getPriority();
	
	public boolean isActive();
}
