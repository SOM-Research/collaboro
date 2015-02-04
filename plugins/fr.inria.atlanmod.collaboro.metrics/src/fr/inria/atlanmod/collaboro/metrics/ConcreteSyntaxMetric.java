package fr.inria.atlanmod.collaboro.metrics;

import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;

public interface ConcreteSyntaxMetric extends Metric {

	public void setModelMapping(ModelMapping modelMapping);
}
