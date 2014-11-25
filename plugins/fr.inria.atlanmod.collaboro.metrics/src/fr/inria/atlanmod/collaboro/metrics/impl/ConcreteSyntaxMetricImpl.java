package fr.inria.atlanmod.collaboro.metrics.impl;

import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxMetric;

public class ConcreteSyntaxMetricImpl extends MetricImpl implements ConcreteSyntaxMetric {

	public ConcreteSyntaxMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio) {
		super(name, dimension, description, acceptanceRatio);
	}

}
