package fr.inria.atlanmod.collaboro.metrics.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import fr.inria.atlanmod.collaboro.metrics.Metric;

public class MetricConfigurationHandler {

	private Properties metricProperties;
	private String configurationFileName;
	private static String concreteGraphicalSyntaxMetricProperty = "concreteGraphicalSyntaxMetrics";
	private static String concreteTextualSyntaxMetricProperty = "concreteTextualSyntaxMetrics";
	private static String abstractSyntaxMetricsProperty = "abstractSyntaxMetrics";
	private Map<String,String> mapMetricIdProperty;
	
	
	public MetricConfigurationHandler() {
		metricProperties = new Properties();
		mapMetricIdProperty = new HashMap<String,String>();
	}
	
	public MetricConfigurationHandler(String configurationFileName) {
		/*this.configurationFileName = configurationFileName;
		InputStream input = null;
		try {
			input = MetricConfigurationHandler.class.getClassLoader().getResourceAsStream(configurationFileName);
			if(input != null) {
				metricProperties.load(input);
			}		
		} catch(IOException e) {
			e.printStackTrace();
		}*/
	}
	
	public boolean load(String configurationFileName) {
		this.configurationFileName = configurationFileName;
		InputStream input = null;
		try {
			input = MetricConfigurationHandler.class.getClassLoader().getResourceAsStream(configurationFileName);
			if(input != null) {
				metricProperties.load(input);
				List<String> concreteGraphicalMetricList = getMetricList(concreteGraphicalSyntaxMetricProperty);
				for(String concreteGraphicalMetric : concreteGraphicalMetricList) {
					mapMetricIdProperty.put(metricProperties.getProperty(concreteGraphicalSyntaxMetricProperty + "." + concreteGraphicalMetric + ".id"), concreteGraphicalSyntaxMetricProperty + "." + concreteGraphicalMetric + ".id");
				}
				List<String> concreteTextualMetricList = getMetricList(concreteTextualSyntaxMetricProperty);
				for(String concreteTextualMetric : concreteTextualMetricList) {
					mapMetricIdProperty.put(metricProperties.getProperty(concreteTextualSyntaxMetricProperty + "." + concreteTextualMetric + ".id"), concreteTextualSyntaxMetricProperty + "." + concreteTextualMetric + ".id");
				}
				List<String> abstractMetricList = getMetricList(abstractSyntaxMetricsProperty);
				for(String abstractMetric : abstractMetricList) {
					mapMetricIdProperty.put(metricProperties.getProperty(abstractSyntaxMetricsProperty + "." + abstractMetric + ".id"), abstractSyntaxMetricsProperty + "." + abstractMetric + ".id");
				}
			}		
		} catch(IOException e) {
			return false;
		}
		return true;
	}
	
	public List<String> getMetricListByType(String metricType) {
		List<String> metricList = new ArrayList<String>();
		String metricsStringList = metricProperties.getProperty(metricType);
		String[] splitMetricsStringList = metricsStringList.split(",");
		for(String metricName : splitMetricsStringList) {
			metricList.add(metricName);
		}
		return metricList;
	}
	
	public String getMetricTypePackage(String metricType) {
		String packageProperty = metricType + ".package";
		return metricProperties.getProperty(packageProperty);
	}
	
	public String getMetricProperty(String metricType, String metricName, String property) {
		String fullPropertyName = metricType + "." + metricName + "." + property;
		return metricProperties.getProperty(fullPropertyName);
	}
	
	public void saveMetric(Metric metric) {
		if(mapMetricIdProperty.containsKey(metric.getName())) {
			String metricName = metric.getName();
			metricProperties.setProperty(getMetricProperty(metricName,"active"), String.valueOf(metric.isActive()));
			metricProperties.setProperty(getMetricProperty(metricName, "acceptanceRatio"), metric.getAcceptanceRatio().toString());
			metricProperties.setProperty(getMetricProperty(metricName, "priority"), metric.getPriority().toString());
		}
	}

	public static String getConcreteGraphicalSyntaxMetricProperty() {
		return concreteGraphicalSyntaxMetricProperty;
	}
	
	public static String getConcreteTextualSyntaxMetricProperty() {
		return concreteTextualSyntaxMetricProperty;
	}

	public static String getAbstractSyntaxMetricsProperty() {
		return abstractSyntaxMetricsProperty;
	}
	
	private String getMetricProperty(String metricId, String property) {
		String MetricPropertiesName = mapMetricIdProperty.get(metricId);
		String fullPropertyName = MetricPropertiesName + "." + property;
		return metricProperties.getProperty(fullPropertyName);
	}
	
	private List<String> getMetricList(String type) {
		List<String> metricList = new ArrayList<String>();
		String metricsStringList = metricProperties.getProperty(type);
		String[] splitMetricsStringList = metricsStringList.split(",");
		for(String metricName : splitMetricsStringList) {
			metricList.add(metricName);
		}
		return metricList;
	}
	
	
}
