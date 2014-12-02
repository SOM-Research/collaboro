package fr.inria.atlanmod.collaboro.metrics.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fr.inria.atlanmod.collaboro.metrics.impl.MetricsFactoryImpl;

public class MetricConfigurationHandler {

	private Properties metricProperties;
	private String configurationFileName;
	private static String concreteGraphicalSyntaxMetricProperty = "concreteGraphicalSyntaxMetrics";
	
	
	public MetricConfigurationHandler() {
		metricProperties = new Properties();
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
	
	public String getConcreteGraphicalSyntaxMetricProperty() {
		return this.concreteGraphicalSyntaxMetricProperty;
	}
	
	

	
}
