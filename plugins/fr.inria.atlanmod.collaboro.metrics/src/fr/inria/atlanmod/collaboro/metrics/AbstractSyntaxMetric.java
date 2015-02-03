package fr.inria.atlanmod.collaboro.metrics;

import org.eclipse.emf.ecore.EPackage;

public interface AbstractSyntaxMetric extends Metric {
	
	public void setAbstractModel(EPackage abstractModel);

}
