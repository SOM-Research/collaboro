package fr.inria.atlanmod.collaboro.metrics;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.metrics.impl.MetricsFactoryImpl;
import fr.inria.atlanmod.collaboro.notation.Definition;

public interface MetricsFactory {
	
	MetricsFactory metricsFactoryInstance = MetricsFactoryImpl.init();
	
	public void setAbstractSyntaxModel(EPackage abstractSyntaxModel);
	public void setConcreteSyntaxModel(Definition concreteSyntaxModel);
	
	public List<AbstractSyntaxMetric> getAbstractSyntaxMetrics();
	public List<ConcreteSyntaxMetric> getConcreteSyntaxMetrics();
	

}
