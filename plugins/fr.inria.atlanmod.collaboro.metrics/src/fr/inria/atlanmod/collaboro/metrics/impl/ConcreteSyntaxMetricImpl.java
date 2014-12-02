package fr.inria.atlanmod.collaboro.metrics.impl;

import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricPriority;

public abstract class ConcreteSyntaxMetricImpl extends MetricImpl implements ConcreteSyntaxMetric {

	public ConcreteSyntaxMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio,
			MetricPriority priority, boolean isActive) {
		super(name, dimension, description, acceptanceRatio, priority, isActive);
	}

	public ConcreteSyntaxMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio) {
		super(name, dimension, description, acceptanceRatio);
	}

}
