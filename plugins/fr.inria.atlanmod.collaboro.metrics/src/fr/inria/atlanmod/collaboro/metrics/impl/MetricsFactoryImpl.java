package fr.inria.atlanmod.collaboro.metrics.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxGraphicalMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxTextualMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
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
	private List<ConcreteSyntaxTextualMetric> concreteSyntaxTextualMetrics;
	private List<AbstractSyntaxMetric> abstractSyntaxMetrics;
	private MetricConfigurationHandler configurationHandler;
	
	public MetricsFactoryImpl(EPackage abstractSyntaxModel, Definition concreteSyntaxModel) {
		//TEST METHOD
		this.abstractSyntaxModel = abstractSyntaxModel;
		this.concreteSyntaxModel = concreteSyntaxModel;
		
		this.concreteSyntaxGraphicalMetrics = new ArrayList<ConcreteSyntaxGraphicalMetric>();
		this.concreteSyntaxTextualMetrics = new ArrayList<ConcreteSyntaxTextualMetric>();
		this.abstractSyntaxMetrics = new ArrayList<AbstractSyntaxMetric>();
		
		this.isGraphical = isConcreteSyntaxGraphical();
		this.modelElementExtractor = new ModelElementExtractor(abstractSyntaxModel, concreteSyntaxModel);
		this.configurationHandler = new MetricConfigurationHandler();
		
		initialize();
		System.out.println("MetricFactory initialised");
	}
	
	public MetricsFactoryImpl(EPackage abstractSyntaxModel, Definition concreteSyntaxModel, InputStream configurationFileStream) {
		this.abstractSyntaxModel = abstractSyntaxModel;
		this.concreteSyntaxModel = concreteSyntaxModel;
		
		this.concreteSyntaxGraphicalMetrics = new ArrayList<ConcreteSyntaxGraphicalMetric>();
		this.concreteSyntaxTextualMetrics = new ArrayList<ConcreteSyntaxTextualMetric>();
		this.abstractSyntaxMetrics = new ArrayList<AbstractSyntaxMetric>();
		
		this.isGraphical = isConcreteSyntaxGraphical();
		this.modelElementExtractor = new ModelElementExtractor(abstractSyntaxModel, concreteSyntaxModel);
		this.configurationHandler = new MetricConfigurationHandler(configurationFileStream);
		
		initialize();
		System.out.println("MetricFactory initialised");
	}
	
	public void initialize() {
		MetricInstanciator metricInstanciator = new MetricInstanciator(this.configurationHandler);
		List<ConcreteSyntaxGraphicalMetric> graphicalMetrics = metricInstanciator.loadConcreteGraphicalMetrics();
		List<ConcreteSyntaxTextualMetric> textualMetrics = metricInstanciator.loadConcreteTextualMetrics();
		List<AbstractSyntaxMetric> abstractMetrics = metricInstanciator.loadAbstractMetrics();
		concreteSyntaxGraphicalMetrics.addAll(graphicalMetrics);
		concreteSyntaxTextualMetrics.addAll(textualMetrics);
		abstractSyntaxMetrics.addAll(abstractMetrics);
	}
	
	public List<AbstractSyntaxMetric> getAbstractSyntaxMetrics() {
		List<AbstractSyntaxMetric> abstractSyntaxMetrics = new ArrayList<AbstractSyntaxMetric>();
		// TODO
		return abstractSyntaxMetrics;
	}

	public List<ConcreteSyntaxMetric> getConcreteSyntaxMetrics() {
		// TODO
		ModelMapping modelMapping = modelElementExtractor.getModelMapping();
		List<ConcreteSyntaxMetric> concreteSyntaxMetrics = new ArrayList<ConcreteSyntaxMetric>();
		if(this.isGraphical) {
			System.out.println(concreteSyntaxGraphicalMetrics);
			for(ConcreteSyntaxGraphicalMetric graphicalMetric : this.concreteSyntaxGraphicalMetrics) {
				graphicalMetric.setModelMapping(modelMapping);
				concreteSyntaxMetrics.add(graphicalMetric);
			}
		} else {
			// Find all metrics instance of ConcreteSyntaxTextualMetrics
		}
		return concreteSyntaxMetrics;
	}

	public void activate(String metricName) {
		System.out.println("In MetricFactory.activate : " + metricName);
		MetricImpl metric = getMetricByName(metricName);
		if(metric != null) {
			metric.setIsActive(true);
			this.configurationHandler.saveMetric(metric);
		}
		
	}

	public void deactivate(String metricName) {
		System.out.println("In MetricFactory.deactivate : " + metricName);
		MetricImpl metric = getMetricByName(metricName);
		if(metric != null) {
			metric.setIsActive(false);
			this.configurationHandler.saveMetric(metric);
		}
		
	}
	
	public void setMetricPriority(String metricName, MetricPriority priority) {
		System.out.println("In MetricFactory.setPriority : " + metricName + " , " + priority);
		MetricImpl metric = getMetricByName(metricName);
		if(metric != null) {
			metric.setPriority(priority);
			this.configurationHandler.saveMetric(metric);
		}
	}
	
	private boolean isConcreteSyntaxGraphical() {
		NotationType concreteSyntaxModelType = concreteSyntaxModel.getType();
		if(concreteSyntaxModelType.equals(NotationType.TEXTUAL)) {
			return false;
		}
		return true;
	}
	
	private MetricImpl getMetricByName(String metricName) {
		System.out.println("\tIn MetricFactory.getMetricByName : " + metricName);
		for(ConcreteSyntaxGraphicalMetric metricTmp : concreteSyntaxGraphicalMetrics) {
			if(metricTmp.getName().equals(metricName)) {
				System.out.println("\t\tFound graphical metric");
				return ((ConcreteSyntaxGraphicalMetricImpl) metricTmp);
			}
		}
		for(ConcreteSyntaxTextualMetric metricTmp : concreteSyntaxTextualMetrics) {
			if(metricTmp.getName().equals(metricName)) {
				System.out.println("\t\tFound textual metric");
				return ((ConcreteSyntaxTextualMetricImpl) metricTmp);
			}
		}
		for(AbstractSyntaxMetric metricTmp : abstractSyntaxMetrics) {
			if(metricTmp.getName().equals(metricName)) {
				System.out.println("\t\tFound abstract metric");
				return ((AbstractSyntaxMetricImpl) metricTmp);
			}
		}
		System.out.println("\t\tNo metric found");
		return null;
	}

}
