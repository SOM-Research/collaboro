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
	
	public MetricsFactoryImpl(EPackage abstractSyntaxModel, Definition concreteSyntaxModel) {
		this.abstractSyntaxModel = abstractSyntaxModel;
		this.concreteSyntaxModel = concreteSyntaxModel;
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
}
