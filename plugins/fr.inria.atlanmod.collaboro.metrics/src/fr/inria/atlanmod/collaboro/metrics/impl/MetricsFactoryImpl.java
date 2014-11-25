package fr.inria.atlanmod.collaboro.metrics.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxGraphicalMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.Metric;
import fr.inria.atlanmod.collaboro.metrics.MetricsFactory;
import fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax.SymbolDeficit;
import fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax.SymbolExcess;
import fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax.SymbolOverload;
import fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax.SymbolRedundancy;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelElementExtractor;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.NotationType;

public class MetricsFactoryImpl implements MetricsFactory {
	
	private EPackage abstractSyntaxModel;
	private Definition concreteSyntaxModel;
	private boolean isGraphical;
	private ModelElementExtractor modelElementExtractor;
	
	public MetricsFactoryImpl(EPackage abstractSyntaxModel, Definition concreteSyntaxModel) {
		this.abstractSyntaxModel = abstractSyntaxModel;
		this.concreteSyntaxModel = concreteSyntaxModel;
		this.isGraphical = isConcreteSyntaxGraphical();
		this.modelElementExtractor = new ModelElementExtractor();
	}
	
	public static void registerMetric(String metricName, Class<? extends Metric> metricClass) {
		System.out.println(metricName + " : registered");
	}
	
	@Override
	public List<AbstractSyntaxMetric> getAbstractSyntaxMetrics() {
		List<AbstractSyntaxMetric> abstractSyntaxMetrics = new ArrayList<AbstractSyntaxMetric>();
		// TODO
		return abstractSyntaxMetrics;
	}

	@Override
	public List<ConcreteSyntaxMetric> getConcreteSyntaxMetrics() {
		// TODO
		ModelMapping modelMapping = modelElementExtractor.getModelMapping(abstractSyntaxModel,concreteSyntaxModel);
		List<ConcreteSyntaxMetric> concreteSyntaxMetrics = new ArrayList<ConcreteSyntaxMetric>();
		if(this.isGraphical) {
			// Find all metrics instance of ConcreteSyntaxGraphicalMetrics
			ConcreteSyntaxGraphicalMetric symbolDeficit = new SymbolDeficit(modelMapping);
			ConcreteSyntaxGraphicalMetric symbolRedundancy = new SymbolRedundancy(modelMapping);
			ConcreteSyntaxGraphicalMetric symbolOverload = new SymbolOverload(modelMapping);
			ConcreteSyntaxGraphicalMetric symbolExcess = new SymbolExcess(modelMapping);
			concreteSyntaxMetrics.add(symbolDeficit);
			concreteSyntaxMetrics.add(symbolRedundancy);
			concreteSyntaxMetrics.add(symbolOverload);
			concreteSyntaxMetrics.add(symbolExcess);
		} else {
			// Find all metrics instance of ConcreteSyntaxTextualMetrics
		}
		return concreteSyntaxMetrics;
	}
	
	private boolean isConcreteSyntaxGraphical() {
		NotationType concreteSyntaxModelType = concreteSyntaxModel.getType();
		if(concreteSyntaxModelType.equals(NotationType.TEXTUAL)) {
			return false;
		}
		return true;
	}
	
	private void loadMetrics() {
		// Find and instanciate all the metrics in the librairies
	}
}
