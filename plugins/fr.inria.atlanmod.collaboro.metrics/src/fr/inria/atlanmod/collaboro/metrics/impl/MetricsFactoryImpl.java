package fr.inria.atlanmod.collaboro.metrics.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxGraphicalMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxTextualMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.MetricsFactory;
import fr.inria.atlanmod.collaboro.metrics.exceptions.MetricConfigurationFileException;
import fr.inria.atlanmod.collaboro.metrics.exceptions.MetricNotFoundException;
import fr.inria.atlanmod.collaboro.metrics.tools.ConcreteGraphicalSyntaxElementExtractor;
import fr.inria.atlanmod.collaboro.metrics.tools.MetricConfigurationHandler;
import fr.inria.atlanmod.collaboro.metrics.tools.MetricHandler;
import fr.inria.atlanmod.collaboro.metrics.tools.MetricInstanciator;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelElementExtractor;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.metrics.tools.model.AbstractConceptContainer;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.NotationType;

public class MetricsFactoryImpl implements MetricsFactory {
	
	private ModelElementExtractor modelElementExtractor;
	private MetricHandler metricHandler;
	
	public MetricsFactoryImpl(EPackage abstractSyntaxModel, Definition concreteSyntaxModel, File configurationFileStream) {
		
		this.modelElementExtractor = new ModelElementExtractor(abstractSyntaxModel, concreteSyntaxModel);
		this.metricHandler = new MetricHandler(configurationFileStream);
		
		System.out.println("MetricFactory initialised");
	}
	
	public List<AbstractSyntaxMetric> getAbstractSyntaxMetrics() {
		AbstractConceptContainer abstractConcepts = this.modelElementExtractor.extractAbstractSyntaxElements();
		
		List<AbstractSyntaxMetric> abstractSyntaxMetrics = this.metricHandler.getAbstractSyntaxMetrics();
		for(AbstractSyntaxMetric abstractMetric : abstractSyntaxMetrics) {
			abstractMetric.setAbstractConcepts(abstractConcepts);
		}
		return abstractSyntaxMetrics;
	}

	public List<ConcreteSyntaxMetric> getConcreteSyntaxMetrics() {
		// TODO
		ModelMapping modelMapping = this.modelElementExtractor.extractModelMapping();
		List<ConcreteSyntaxMetric> concreteSyntaxMetrics = new ArrayList<ConcreteSyntaxMetric>();
		
		if(this.modelElementExtractor.isGraphical()) {
			for(ConcreteSyntaxMetric concreteMetric : this.metricHandler.getConcreteSyntaxGraphicalMetrics()) {
				concreteMetric.setModelMapping(modelMapping);
				concreteSyntaxMetrics.add(concreteMetric);
			}
			return concreteSyntaxMetrics;
		} else {
			for(ConcreteSyntaxMetric concreteMetric : this.metricHandler.getConcreteSyntaxTextualMetrics()) {
				concreteMetric.setModelMapping(modelMapping);
				concreteSyntaxMetrics.add(concreteMetric);
			}
		}
		return concreteSyntaxMetrics;
	}

	public void activate(String metricName) {
		try {
			this.metricHandler.activateMetric(metricName);
		} catch (MetricConfigurationFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MetricNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deactivate(String metricName) {
		try {
			this.metricHandler.deactivateMetric(metricName);
		} catch (MetricConfigurationFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MetricNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setMetricPriority(String metricName, MetricPriority priority) {
		try {
			this.metricHandler.setMetricPriority(metricName, priority);
		} catch (MetricConfigurationFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MetricNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
