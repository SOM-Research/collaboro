package fr.inria.atlanmod.collaboro.metrics.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxGraphicalMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxTextualMetric;
import fr.inria.atlanmod.collaboro.metrics.Metric;
import fr.inria.atlanmod.collaboro.metrics.MetricPriority;
import fr.inria.atlanmod.collaboro.metrics.impl.AbstractSyntaxMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxGraphicalMetricImpl;
import fr.inria.atlanmod.collaboro.metrics.impl.ConcreteSyntaxTextualMetricImpl;

public class MetricInstanciator {
	
	private MetricConfigurationHandler configurationHandler;
	private ClassFinder classFinder;

	public MetricInstanciator(MetricConfigurationHandler configurationHandler) {
		this.configurationHandler = configurationHandler;
		this.classFinder = new ClassFinder();
	}
	
	public List<ConcreteSyntaxGraphicalMetric> loadConcreteGraphicalMetrics() {
		System.out.println("Loading Graphical metrics");
		List<ConcreteSyntaxGraphicalMetric> metrics = new ArrayList<ConcreteSyntaxGraphicalMetric>();
		String metricType = MetricConfigurationHandler.getConcreteGraphicalSyntaxMetricProperty();
		String metricPackage = configurationHandler.getMetricTypePackage(metricType);
		List<String> metricList = configurationHandler.getMetricListByType(metricType);
		for(String metricName : metricList) {
			String metricId = configurationHandler.getMetricProperty(metricType, metricName, "id");
			String metricIsActiveString = configurationHandler.getMetricProperty(metricType, metricName, "active");
			boolean metricIsActive = Boolean.valueOf(metricIsActiveString);
			String metricAcceptanceRatioString = configurationHandler.getMetricProperty(metricType, metricName, "acceptanceRatio");
			Integer metricAcceptanceRatio = Integer.decode(metricAcceptanceRatioString);
			String metricPriorityString = configurationHandler.getMetricProperty(metricType, metricName, "priority");
			MetricPriority metricPriority = MetricPriority.getByName(metricPriorityString);
			System.out.println("metric : " + metricId + ", " + metricIsActiveString + ", " + metricAcceptanceRatio+ ", " + metricPriority);
			
			try {
				Class<?> metricClass = classFinder.getClassByName(metricName, metricPackage);
				Constructor<?> metricConstructor = metricClass.getConstructor(String.class, Integer.class, MetricPriority.class, boolean.class);
				ConcreteSyntaxGraphicalMetric metric = (ConcreteSyntaxGraphicalMetricImpl) metricConstructor.newInstance(metricId,metricAcceptanceRatio,metricPriority, metricIsActive );
				metrics.add(metric);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return metrics;
	}
	
	public List<ConcreteSyntaxTextualMetric> loadConcreteTextualMetrics() {
		System.out.println("Loading Textual metrics");
		List<ConcreteSyntaxTextualMetric> metrics = new ArrayList<ConcreteSyntaxTextualMetric>();
		String metricType = MetricConfigurationHandler.getConcreteTextualSyntaxMetricProperty();
		String metricPackage = configurationHandler.getMetricTypePackage(metricType);
		List<String> metricList = configurationHandler.getMetricListByType(metricType);
		for(String metricName : metricList) {
			String metricId = configurationHandler.getMetricProperty(metricType, metricName, "id");
			String metricIsActiveString = configurationHandler.getMetricProperty(metricType, metricName, "active");
			boolean metricIsActive = Boolean.getBoolean(metricIsActiveString);
			String metricAcceptanceRatioString = configurationHandler.getMetricProperty(metricType, metricName, "acceptanceRatio");
			Integer metricAcceptanceRatio = Integer.decode(metricAcceptanceRatioString);
			String metricPriorityString = configurationHandler.getMetricProperty(metricType, metricName, "priority");
			MetricPriority metricPriority = MetricPriority.getByName(metricPriorityString);
			System.out.println("metric : " + metricId + ", " + metricIsActiveString + ", " + metricAcceptanceRatio+ ", " + metricPriority);
			
			try {
				Class<?> metricClass = classFinder.getClassByName(metricName, metricPackage);
				Constructor<?> metricConstructor = metricClass.getConstructor(String.class, Integer.class, MetricPriority.class, boolean.class);
				ConcreteSyntaxTextualMetric metric = (ConcreteSyntaxTextualMetricImpl) metricConstructor.newInstance(metricId,metricAcceptanceRatio,metricPriority, metricIsActive );
				metrics.add(metric);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return metrics;
	}
	
	public List<AbstractSyntaxMetric> loadAbstractMetrics() {
		System.out.println("Loading Abstract metrics");
		List<AbstractSyntaxMetric> metrics = new ArrayList<AbstractSyntaxMetric>();
		String metricType = MetricConfigurationHandler.getAbstractSyntaxMetricsProperty();
		String metricPackage = configurationHandler.getMetricTypePackage(metricType);
		List<String> metricList = configurationHandler.getMetricListByType(metricType);
		for(String metricName : metricList) {
			String metricId = configurationHandler.getMetricProperty(metricType, metricName, "id");
			String metricIsActiveString = configurationHandler.getMetricProperty(metricType, metricName, "active");
			boolean metricIsActive = Boolean.getBoolean(metricIsActiveString);
			String metricAcceptanceRatioString = configurationHandler.getMetricProperty(metricType, metricName, "acceptanceRatio");
			Integer metricAcceptanceRatio = Integer.decode(metricAcceptanceRatioString);
			String metricPriorityString = configurationHandler.getMetricProperty(metricType, metricName, "priority");
			MetricPriority metricPriority = MetricPriority.getByName(metricPriorityString);
			System.out.println("metric : " + metricId + ", " + metricIsActiveString + ", " + metricAcceptanceRatio+ ", " + metricPriority);
			
			try {
				Class<?> metricClass = classFinder.getClassByName(metricName, metricPackage);
				Constructor<?> metricConstructor = metricClass.getConstructor(String.class, Integer.class, MetricPriority.class, boolean.class);
				AbstractSyntaxMetric metric = (AbstractSyntaxMetricImpl) metricConstructor.newInstance(metricId,metricAcceptanceRatio,metricPriority, metricIsActive );
				metrics.add(metric);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return metrics;
	}
	
	/*public <T> List<T> loadMetricsByType(String metricType,T metricClassType) {
		List<T> metrics = new ArrayList<T>();
		String metricPackage = configurationHandler.getMetricTypePackage(metricType);
		List<String> metricList = configurationHandler.getMetricListByType(metricType);
		for(String metricName : metricList) {
			String metricId = configurationHandler.getMetricProperty(metricType, metricName, "id");
			String metricIsActiveString = configurationHandler.getMetricProperty(metricType, metricName, "active");
			boolean metricIsActive = Boolean.getBoolean(metricIsActiveString);
			String metricAcceptanceRatioString = configurationHandler.getMetricProperty(metricType, metricName, "acceptanceRatio");
			Integer metricAcceptanceRatio = Integer.getInteger(metricAcceptanceRatioString);
			String metricPriorityString = configurationHandler.getMetricProperty(metricType, metricName, "priority");
			MetricPriority metricPriority = MetricPriority.getByName(metricPriorityString);
			
			try {
				Class<?> metricClass = classFinder.getClassByName(metricName, metricPackage);
				Constructor<?> metricConstructor = metricClass.getConstructor(String.class, Integer.class, MetricPriority.class, boolean.class);
				T metric = (T) metricConstructor.newInstance(metricId,metricAcceptanceRatio,metricPriority, metricIsActive );
				metrics.add(metric);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return metrics;
	}*/

}
