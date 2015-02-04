package fr.inria.atlanmod.collaboro.metrics.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxGraphicalMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxTextualMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.exceptions.MetricConfigurationFileException;
import fr.inria.atlanmod.collaboro.metrics.exceptions.MetricNotFoundException;
import fr.inria.atlanmod.collaboro.metrics.impl.AbstractSyntaxMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxTextualMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricImpl;

public class MetricHandler {
	
	MetricConfigurationHandler configurationHandler;
	MetricInstanciator metricInstanciator;
	
	private List<ConcreteSyntaxGraphicalMetric> concreteSyntaxGraphicalMetrics;
	private List<ConcreteSyntaxTextualMetric> concreteSyntaxTextualMetrics;
	private List<AbstractSyntaxMetric> abstractSyntaxMetrics;
	
	public MetricHandler(File metricConfigurationFile) {
		concreteSyntaxGraphicalMetrics = new ArrayList<ConcreteSyntaxGraphicalMetric>();
		concreteSyntaxTextualMetrics = new ArrayList<ConcreteSyntaxTextualMetric>();
		abstractSyntaxMetrics = new ArrayList<AbstractSyntaxMetric>();
		
		try {
			this.configurationHandler = new MetricConfigurationHandler(metricConfigurationFile);
			initialize();
		} catch (MetricConfigurationFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		MetricInstanciator metricInstanciator = new MetricInstanciator(this.configurationHandler);
		List<ConcreteSyntaxGraphicalMetric> graphicalMetrics = metricInstanciator.loadConcreteGraphicalMetrics();
		List<ConcreteSyntaxTextualMetric> textualMetrics = metricInstanciator.loadConcreteTextualMetrics();
		List<AbstractSyntaxMetric> abstractMetrics = metricInstanciator.loadAbstractMetrics();
		concreteSyntaxGraphicalMetrics.addAll(graphicalMetrics);
		concreteSyntaxTextualMetrics.addAll(textualMetrics);
		abstractSyntaxMetrics.addAll(abstractMetrics);
	}
	
	/**
	 * 
	 * @param metricName
	 * @return
	 * @throws MetricNotFoundException 
	 * @throws MetricConfigurationFileException 
	 */
	public void activateMetric(String metricName) throws MetricNotFoundException, MetricConfigurationFileException {
		System.out.println("Activate metric : " + metricName);
		MetricImpl metric = getMetricByName(metricName);
		if(metric != null) {
			metric.setIsActive(true);
			this.configurationHandler.saveMetric(metric);
		}
	}
	
	/**
	 * 
	 * @param metricName
	 * @return
	 * @throws MetricNotFoundException 
	 * @throws MetricConfigurationFileException 
	 */
	public void deactivateMetric(String metricName) throws MetricNotFoundException, MetricConfigurationFileException {
		System.out.println("In MetricFactory.deactivate : " + metricName);
		MetricImpl metric = getMetricByName(metricName);
		if(metric != null) {
			metric.setIsActive(false);
			this.configurationHandler.saveMetric(metric);
		}
	}
	
	public void setMetricPriority(String metricName, MetricPriority priority) throws MetricNotFoundException, MetricConfigurationFileException {
		System.out.println("In MetricFactory.setPriority : " + metricName + " , " + priority);
		MetricImpl metric = getMetricByName(metricName);
		if(metric != null) {
			metric.setPriority(priority);
			this.configurationHandler.saveMetric(metric);
		}
	}

	public List<ConcreteSyntaxGraphicalMetric> getConcreteSyntaxGraphicalMetrics() {
		return concreteSyntaxGraphicalMetrics;
	}

//	public void setConcreteSyntaxGraphicalMetrics(
//			List<ConcreteSyntaxGraphicalMetric> concreteSyntaxGraphicalMetrics) {
//		this.concreteSyntaxGraphicalMetrics = concreteSyntaxGraphicalMetrics;
//	}

	public List<ConcreteSyntaxTextualMetric> getConcreteSyntaxTextualMetrics() {
		return concreteSyntaxTextualMetrics;
	}

//	public void setConcreteSyntaxTextualMetrics(
//			List<ConcreteSyntaxTextualMetric> concreteSyntaxTextualMetrics) {
//		this.concreteSyntaxTextualMetrics = concreteSyntaxTextualMetrics;
//	}

	public List<AbstractSyntaxMetric> getAbstractSyntaxMetrics() {
		return abstractSyntaxMetrics;
	}

//	public void setAbstractSyntaxMetrics(
//			List<AbstractSyntaxMetric> abstractSyntaxMetrics) {
//		this.abstractSyntaxMetrics = abstractSyntaxMetrics;
//	}
	
	private MetricImpl getMetricByName(String metricName) throws MetricNotFoundException{
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
		
		throw new MetricNotFoundException("Metric with name \"" + metricName + "\" was not found");
	}
	
	
	
	

}
