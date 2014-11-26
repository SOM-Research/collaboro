package fr.inria.atlanmod.collaboro.metrics.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxGraphicalMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.MetricsFactory;
import fr.inria.atlanmod.collaboro.metrics.tools.ClassFinder;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelElementExtractor;
import fr.inria.atlanmod.collaboro.metrics.tools.ModelMapping;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.NotationType;

public class MetricsFactoryImpl implements MetricsFactory {
	
	private EPackage abstractSyntaxModel;
	private Definition concreteSyntaxModel;
	private boolean isGraphical;
	private ModelElementExtractor modelElementExtractor;
	private ClassFinder classFinder;
	private Properties metricsProperties;
	
	public MetricsFactoryImpl(EPackage abstractSyntaxModel, Definition concreteSyntaxModel) {
		this.abstractSyntaxModel = abstractSyntaxModel;
		this.concreteSyntaxModel = concreteSyntaxModel;
		this.isGraphical = isConcreteSyntaxGraphical();
		this.modelElementExtractor = new ModelElementExtractor();
		this.classFinder = new ClassFinder();
		this.metricsProperties = loadProrperties();
	}
	
	public Properties loadProrperties() {
		Properties prop = new Properties();
		InputStream input = null;
		System.out.println("here");
		try {
			input = new FileInputStream("resources/metrics.properties");
			prop.load(input);			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return prop;
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
			try {
				List<Class<?>> concreteSyntaxGraphicalMetrics = classFinder.getClassesFromPackage(ConcreteSyntaxGraphicalMetricImpl.class, metricsProperties.getProperty("concreteGraphicalSyntaxMetricsPath"));
				for(int i=0 ; i < concreteSyntaxGraphicalMetrics.size() ; i++) {
					Class<?> metricImpl = (Class<?>) concreteSyntaxGraphicalMetrics.get(i);
					Constructor<?> metricConstructor = metricImpl.getConstructor(ModelMapping.class);
					ConcreteSyntaxGraphicalMetric metric = (ConcreteSyntaxGraphicalMetric) metricConstructor.newInstance(modelMapping);
					concreteSyntaxMetrics.add(metric);
				}
			} catch (IOException | ClassNotFoundException | SecurityException | IllegalArgumentException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
}
