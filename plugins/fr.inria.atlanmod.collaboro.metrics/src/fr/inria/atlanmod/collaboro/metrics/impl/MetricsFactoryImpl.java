package fr.inria.atlanmod.collaboro.metrics.impl;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricsFactory;
import fr.inria.atlanmod.collaboro.notation.Definition;

public class MetricsFactoryImpl implements MetricsFactory {
	
	private EPackage abstractSyntaxModel;
	private Definition concreteSyntaxModel;
	
	private MetricsFactoryImpl() {	
	}
	
	public static MetricsFactory init() {
		if (MetricsFactory.metricsFactoryInstance != null) {
			return MetricsFactory.metricsFactoryInstance;
		}
		return new MetricsFactoryImpl();
	}
	
	
	public MetricsFactoryImpl(EPackage abstractSyntaxModel, Definition concreteSyntaxModel) {
		
	}
	
	@Override
	public List<AbstractSyntaxMetric> getAbstractSyntaxMetrics() {
		// TODO Auto-generated method stub
		System.out.println("blab");
		return null;
	}

	@Override
	public List<ConcreteSyntaxMetric> getConcreteSyntaxMetrics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAbstractSyntaxModel(EPackage abstractSyntaxModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConcreteSyntaxModel(Definition concreteSyntaxModel) {
		// TODO Auto-generated method stub
		
	}

}
