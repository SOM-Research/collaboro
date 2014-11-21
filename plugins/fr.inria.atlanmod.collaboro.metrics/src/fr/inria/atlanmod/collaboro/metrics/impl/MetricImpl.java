package fr.inria.atlanmod.collaboro.metrics.impl;

import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.Metric;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;

public class MetricImpl implements Metric {
	
	protected String name;
	protected String dimension;
	protected String description;
	
	public MetricImpl(String name,String dimension, String description) {
		this.name = name;
		this.dimension = dimension;
		this.description = description;
	}

	@Override
	public List<MetricResult> execute() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return this.name;
	}

	public String getDimension() {
		return this.dimension;
	}

	public String getDescription() {
		return this.description;
	}

}
