package fr.inria.atlanmod.collaboro.metrics.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxGraphicalMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricsFactory;
import fr.inria.atlanmod.collaboro.metrics.tools.MetricConfigurationHandler;
import fr.inria.atlanmod.collaboro.metrics.tools.MetricInstanciator;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelElementExtractor;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.NotationType;

public class MetricsFactoryImpl implements MetricsFactory {
	
	private EPackage abstractSyntaxModel;
	private Definition concreteSyntaxModel;
	private boolean isGraphical;
	private ModelElementExtractor modelElementExtractor;
	private List<ConcreteSyntaxGraphicalMetric> concreteSyntaxGraphicalMetrics;
	private MetricConfigurationHandler configurationHandler;
	
	public MetricsFactoryImpl(EPackage abstractSyntaxModel, Definition concreteSyntaxModel) {
		this.abstractSyntaxModel = abstractSyntaxModel;
		this.concreteSyntaxModel = concreteSyntaxModel;
		this.isGraphical = isConcreteSyntaxGraphical();
		this.modelElementExtractor = new ModelElementExtractor();
		this.concreteSyntaxGraphicalMetrics = new ArrayList<ConcreteSyntaxGraphicalMetric>();
		this.configurationHandler = new MetricConfigurationHandler();
		//FIXME 
		loadConfiguration();
		//this.configurationHandler = new MetricConfigurationHandler("metrics.properties");
	}
	
	public List<AbstractSyntaxMetric> getAbstractSyntaxMetrics() {
		List<AbstractSyntaxMetric> abstractSyntaxMetrics = new ArrayList<AbstractSyntaxMetric>();
		// TODO
		return abstractSyntaxMetrics;
	}

	public List<ConcreteSyntaxMetric> getConcreteSyntaxMetrics() {
		// TODO
		ModelMapping modelMapping = modelElementExtractor.getModelMapping(abstractSyntaxModel,concreteSyntaxModel);
		List<ConcreteSyntaxMetric> concreteSyntaxMetrics = new ArrayList<ConcreteSyntaxMetric>();
		if(this.isGraphical) {
			for(ConcreteSyntaxGraphicalMetric graphicalMetric : this.concreteSyntaxGraphicalMetrics) {
				graphicalMetric.setModelMapping(modelMapping);
				System.out.println("early execute");
				System.out.println(graphicalMetric.execute());
				
				concreteSyntaxMetrics.add(graphicalMetric);
			}
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

	@Override
	public void saveConfiguration() {
		// TODO Auto-generated method stub
		
	}

	public void loadConfiguration() {
		boolean isLoaded = this.configurationHandler.load("metrics.properties");
		if(isLoaded) {
			MetricInstanciator metricInstanciator = new MetricInstanciator(this.configurationHandler);
			List<ConcreteSyntaxGraphicalMetric> graphicalMetrics = metricInstanciator.loadConcreteGraphicalMetric();
			concreteSyntaxGraphicalMetrics.addAll(graphicalMetrics);
			System.out.println(concreteSyntaxGraphicalMetrics);
			System.out.println("Configuration file is loaded");
		} else {
			System.out.println("Configuration file is not loaded");
		}
	}

	@Override
	public void activate(String metricName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate(String metricName) {
		// TODO Auto-generated method stub
		
	}
	
}
