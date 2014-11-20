package fr.inria.atlanmod.collaboro.metrics.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxGraphicalMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricsFactory;
import fr.inria.atlanmod.collaboro.metrics.librairie.concreteSyntax.SymbolDeficit;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelElementExtractor;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.notation.Definition;

public class MetricsFactoryImpl implements MetricsFactory {
	
	private EPackage abstractSyntaxModel;
	private Definition concreteSyntaxModel;
	private boolean isGraphical;
	private ModelElementExtractor modelElementExtractor;
	
	public MetricsFactoryImpl(EPackage abstractSyntaxModel, Definition concreteSyntaxModel) {
		this.abstractSyntaxModel = abstractSyntaxModel;
		this.concreteSyntaxModel = concreteSyntaxModel;
		this.isGraphical = isConcreteSyntaxGraphical();
		System.out.println("isGraph : " + isGraphical);
		this.modelElementExtractor = new ModelElementExtractor();
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
			ConcreteSyntaxGraphicalMetric metric = new SymbolDeficit(modelMapping);
			concreteSyntaxMetrics.add(metric);
		} else {
			// Find all metrics instance of ConcreteSyntaxTextualMetrics
		}
		return concreteSyntaxMetrics;
	}
	
	private boolean isConcreteSyntaxGraphical() {
		return true;
//		List<NotationElement> concreteSyntaxElements = concreteSyntaxModel.getElements();
//		for(NotationElement concreteSyntaxElement : concreteSyntaxElements) {
//			if(concreteSyntaxElement instanceof GraphicalElement) {
//				return true;
//			}
//		}
//		return false;
	}
	
	private void loadMetrics() {
		// Find and instanciate all the metrics in the librairies
	}
}
