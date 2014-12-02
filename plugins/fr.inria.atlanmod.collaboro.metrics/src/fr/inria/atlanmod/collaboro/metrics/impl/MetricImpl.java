package fr.inria.atlanmod.collaboro.metrics.impl;

import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.Metric;
import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;

public abstract class MetricImpl implements Metric {
	
	protected String name;
	protected String dimension;
	protected String description;
	protected Integer acceptanceRatio;
	protected MetricPriority priority;
	protected boolean isActive;
	
	public MetricImpl(String name,String dimension, String description, Integer acceptanceRatio,MetricPriority priority,boolean isActive) {
		this.name = name;
		this.dimension = dimension;
		this.description = description;
		this.acceptanceRatio = acceptanceRatio;
		this.priority = priority;
		this.isActive = isActive;
	}
	
	public MetricImpl(String name, String dimension, String description, Integer acceptanceRatio) {
		this.name = name;
		this.dimension = dimension;
		this.description = description;
		this.acceptanceRatio = acceptanceRatio;
		this.priority = MetricPriority.NORMAL;
		this.isActive = true;
	}

	public abstract List<MetricResult> execute();

	public String getName() {
		return this.name;
	}

	public String getDimension() {
		return this.dimension;
	}

	public String getDescription() {
		return this.description;
	}
	
	public Integer getAcceptanceRatio() {
		return this.acceptanceRatio;
	}

	public MetricPriority getPriority() {
		return priority;
	}

}
