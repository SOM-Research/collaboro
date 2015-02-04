package fr.inria.atlanmod.collaboro.metrics.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import fr.inria.atlanmod.collaboro.metrics.Metric;
import fr.inria.atlanmod.collaboro.metrics.exceptions.MetricConfigurationFileException;

public class MetricConfigurationHandler {
	
	private Properties metricProperties;
	private File configurationFilePath;
	private static String concreteGraphicalSyntaxMetricProperty = "concreteGraphicalSyntaxMetrics";
	private static String concreteTextualSyntaxMetricProperty = "concreteTextualSyntaxMetrics";
	private static String abstractSyntaxMetricsProperty = "abstractSyntaxMetrics";
	private Map<String,String> mapMetricId2Property;
	
	
	/*public MetricConfigurationHandler() {
		URL url = ClassLoader.getSystemClassLoader().getResource("metrics.properties");
		System.out.println("Found URL : " + url);
		
		InputStream input;
		try {
			input = MetricConfigurationHandler.class.getClassLoader().getResourceAsStream("resources/metrics.properties");
			System.out.println("Available inputSTream bytes : " +input.available());
			if(input != null) {
				metricProperties.load(input);
				initialize();
			}		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}*/
	
	public MetricConfigurationHandler(File configurationFileStream) throws MetricConfigurationFileException {
		metricProperties = new Properties();
		mapMetricId2Property = new HashMap<String,String>();
		this.configurationFilePath = configurationFileStream;
		
		InputStream input = null;
		try {
			input = new FileInputStream(configurationFilePath);
			metricProperties.load(input);
			// Instantiating all the metrics within the properties file
			initialize();
		} catch (IOException e) {
			throw new MetricConfigurationFileException("Error loading the property file : " + configurationFileStream.getAbsolutePath());
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("MetricConfigurationHandler initialized");
	}
	
	private void initialize() {
		// Populate map linking metrics Id to their property path
		List<String> concreteGraphicalMetricList = getMetricList(concreteGraphicalSyntaxMetricProperty);
		for(String concreteGraphicalMetric : concreteGraphicalMetricList) {
			mapMetricId2Property.put(metricProperties.getProperty(concreteGraphicalSyntaxMetricProperty + "." + concreteGraphicalMetric + ".id"), concreteGraphicalSyntaxMetricProperty + "." + concreteGraphicalMetric);
		}
		List<String> concreteTextualMetricList = getMetricList(concreteTextualSyntaxMetricProperty);
		for(String concreteTextualMetric : concreteTextualMetricList) {
			mapMetricId2Property.put(metricProperties.getProperty(concreteTextualSyntaxMetricProperty + "." + concreteTextualMetric + ".id"), concreteTextualSyntaxMetricProperty + "." + concreteTextualMetric);
		}
		List<String> abstractMetricList = getMetricList(abstractSyntaxMetricsProperty);
		for(String abstractMetric : abstractMetricList) {
			mapMetricId2Property.put(metricProperties.getProperty(abstractSyntaxMetricsProperty + "." + abstractMetric + ".id"), abstractSyntaxMetricsProperty + "." + abstractMetric);
		}
	}
	
	
	
	public List<String> getMetricListByType(String metricType) {
		List<String> metricList = new ArrayList<String>();
		String metricsStringList = metricProperties.getProperty(metricType);
		if(!metricsStringList.isEmpty()) {
			String[] splitMetricsStringList = metricsStringList.split(",");
			for(String metricName : splitMetricsStringList) {
				metricList.add(metricName);
			}
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
	
	public void saveMetric(Metric metric) throws MetricConfigurationFileException {
		System.out.println("in MetricConfigurationHandler.saveMetric : " + metric);
		if(mapMetricId2Property.containsKey(metric.getName())) {
			String metricName = metric.getName();
			String metricActiveProperty = getMetricPropertyPath(metricName,"active");
			metricProperties.setProperty(metricActiveProperty, String.valueOf(metric.isActive()));
			String metricAcceptanceRatioProperty = getMetricPropertyPath(metricName, "acceptanceRatio");
			metricProperties.setProperty(metricAcceptanceRatioProperty, metric.getAcceptanceRatio().toString());
			String metricPriorityProperty = getMetricPropertyPath(metricName, "property");
			metricProperties.setProperty(metricPriorityProperty, metric.getPriority().toString());
			try {
				save();
			} catch (IOException e) {
				throw new MetricConfigurationFileException("Error saving metric \"" + metric.getName() + "\"");
			}
		} else {
			throw new MetricConfigurationFileException("Error Configuration File : could not find metric \"" + metric.getName() + "\"");
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
	
	private void save() throws IOException {
		System.out.println("saving....");
		OutputStream output = new FileOutputStream(this.configurationFilePath);
		metricProperties.store(output, "");
		output.close();
	}
	
	private String getMetricProperty(String metricId, String property) {
		String metricPropertiesName = mapMetricId2Property.get(metricId);
		String fullPropertyName = metricPropertiesName + "." + property;
		
		return metricProperties.getProperty(fullPropertyName);
	}
	
	private String getMetricPropertyPath(String metricId, String property) {
		String metricPropertiesName = mapMetricId2Property.get(metricId);
		String fullPropertyName = metricPropertiesName + "." + property;
		
		return fullPropertyName;
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
