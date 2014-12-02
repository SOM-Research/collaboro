package fr.inria.atlanmod.collaboro.metrics.impl;

import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxGraphicalMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;

public abstract class ConcreteSyntaxGraphicalMetricImpl extends ConcreteSyntaxMetricImpl implements ConcreteSyntaxGraphicalMetric{
	
	protected ModelMapping modelMapping;

	public ConcreteSyntaxGraphicalMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio,
			MetricPriority priority, boolean isActive) {
		super(name, dimension, description, acceptanceRatio, priority, isActive);
	}

	public ConcreteSyntaxGraphicalMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio) {
		super(name, dimension, description, acceptanceRatio);
	}
	
	public void setModelMapping(ModelMapping modelMapping) {
		this.modelMapping = modelMapping;
	}
	
	


}
