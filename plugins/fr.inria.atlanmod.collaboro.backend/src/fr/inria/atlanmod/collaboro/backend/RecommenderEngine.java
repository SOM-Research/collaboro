package fr.inria.atlanmod.collaboro.backend;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.collaboro.metrics.AbstractSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.ConcreteSyntaxMetric;
import fr.inria.atlanmod.collaboro.metrics.Metric;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;
import fr.inria.atlanmod.collaboro.metrics.MetricsFactory;
import fr.inria.atlanmod.collaboro.metrics.ReferredElement;
import fr.inria.atlanmod.collaboro.metrics.impl.MetricsFactoryImpl;
import fr.inria.atlanmod.collaboro.notation.Definition;

public class RecommenderEngine {
	private ModelManager modelManager;
	private CollaboroBackend backend;
	private MetricsFactory factory;
	private String userId;
	
	public RecommenderEngine(String userId, CollaboroBackend backend) {
		this.userId = userId;
		this.backend = backend;
		this.modelManager = backend.getModelManager();
		
		EPackage abstractSyntaxModel = modelManager.getEcoreModel();
		Definition concreteSyntaxModel = modelManager.getNotation();
		factory = new MetricsFactoryImpl(abstractSyntaxModel, concreteSyntaxModel);
	}
	
	public void checkMetrics() {
		checkAbstractSyntaxMetrics();
		checkConcretSyntaxMetrics();
	}
	
	public void checkAbstractSyntaxMetrics() {
		List<AbstractSyntaxMetric> metrics = factory.getAbstractSyntaxMetrics();
		for(AbstractSyntaxMetric metric : metrics) {
			List<MetricResult> results = metric.execute();
			for(MetricResult result : results) {
				digestMetricResult(metric, result);
			}
		}
	}
	
	public void checkConcretSyntaxMetrics() {
		List<ConcreteSyntaxMetric> metrics = factory.getConcreteSyntaxMetrics();
		for(ConcreteSyntaxMetric metric : metrics) {
			List<MetricResult> results = metric.execute();
			for(MetricResult result : results) {
				digestMetricResult(metric, result);
			}
		}
	}
	
	private void digestMetricResult(Metric metric, MetricResult metricResult) {
		String rationale = "Metric: " + metric.getName() + "\n"
				+ "Description: " + metric.getDescription() + "\n"
				+ "Reason: " + metricResult.getReason();
		String referredElements = "";
		for(ReferredElement referredElement : metricResult.getReferredElements()) {
			referredElements += referredElement + ",";
		}
		if(!referredElements.equals(""))
			referredElements = referredElements.substring(0, referredElements.length() - 1);
		backend.createProposalPlain(userId, rationale, referredElements);
	}
}
