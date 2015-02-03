package fr.inria.atlanmod.collaboro.metrics.impl;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricPriority;

public abstract class AbstractSyntaxMetricImpl extends MetricImpl implements AbstractSyntaxMetric {
	
	protected EPackage abstractModel;

	public AbstractSyntaxMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio,
			MetricPriority priority, boolean isActive) {
		super(name, dimension, description, acceptanceRatio, priority, isActive);
	}

	public AbstractSyntaxMetricImpl(String name, String dimension,
			String description, Integer acceptanceRatio) {
		super(name, dimension, description, acceptanceRatio);
	}
	
	public void setAbstractModel(EPackage abstractModel) {
		this.abstractModel = abstractModel;
	}

}
