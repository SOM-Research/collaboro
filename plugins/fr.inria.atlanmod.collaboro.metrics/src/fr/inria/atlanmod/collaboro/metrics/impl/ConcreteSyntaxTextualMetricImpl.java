package fr.inria.atlanmod.collaboro.metrics.impl;

import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxTextualMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricPriority;

public abstract class ConcreteSyntaxTextualMetricImpl extends ConcreteSyntaxMetricImpl implements ConcreteSyntaxTextualMetric{

	public ConcreteSyntaxTextualMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio,
			MetricPriority priority, boolean isActive) {
		super(name, dimension, description, acceptanceRatio, priority, isActive);
	}

	public ConcreteSyntaxTextualMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio) {
		super(name, dimension, description, acceptanceRatio);
	}
}
