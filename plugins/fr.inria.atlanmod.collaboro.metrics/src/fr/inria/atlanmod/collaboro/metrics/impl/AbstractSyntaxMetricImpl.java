package fr.inria.atlanmod.collaboro.metrics.impl;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.tools.model.AbstractConceptContainer;

public abstract class AbstractSyntaxMetricImpl extends MetricImpl implements AbstractSyntaxMetric {
	
	protected AbstractConceptContainer abstractConceptContainer;

	public AbstractSyntaxMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio,
			MetricPriority priority, boolean isActive) {
		super(name, dimension, description, acceptanceRatio, priority, isActive);
	}

	public AbstractSyntaxMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio) {
		super(name, dimension, description, acceptanceRatio);
	}
	
	public void setAbstractConcepts(AbstractConceptContainer abstractConceptContainer) {
		this.abstractConceptContainer = abstractConceptContainer;
		
	}

}
