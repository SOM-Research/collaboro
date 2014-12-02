package fr.inria.atlanmod.collaboro.backend;

import java.util.ArrayList;
import java.util.HashMap;
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
	private HashMap<Metric, List<MetricResult>> results;
	private HashMap<Metric, List<String>> metric2proposalId;
	
	public RecommenderEngine(String userId, CollaboroBackend backend) {
		this.userId = userId;
		this.backend = backend;
		this.modelManager = backend.getModelManager();
		this.results = new HashMap<Metric, List<MetricResult>>();
		this.metric2proposalId = new HashMap<Metric, List<String>>();
		
		EPackage abstractSyntaxModel = modelManager.getEcoreModel();
		Definition concreteSyntaxModel = modelManager.getNotation();
		factory = new MetricsFactoryImpl(abstractSyntaxModel, concreteSyntaxModel);
	}
	
	public List<Metric> getMetrics() {
		List<Metric> result = new ArrayList<Metric>();
		List<AbstractSyntaxMetric> ASmetrics = factory.getAbstractSyntaxMetrics();
		result.addAll(ASmetrics);
		List<ConcreteSyntaxMetric> CSmetrics = factory.getConcreteSyntaxMetrics();
		result.addAll(CSmetrics);
		return result;
	}
	
	public List<AbstractSyntaxMetric> getAbstractSyntaMetrics() {
		return factory.getAbstractSyntaxMetrics();
	}
	
	public List<ConcreteSyntaxMetric> getConcreteSyntaxMetrics() {
		return factory.getConcreteSyntaxMetrics();
	}
	
	public void checkMetrics() {
		checkAbstractSyntaxMetrics();
		checkConcreteSyntaxMetrics();
	}
	
	private void checkAbstractSyntaxMetrics() {
		List<AbstractSyntaxMetric> metrics = factory.getAbstractSyntaxMetrics();
		for(AbstractSyntaxMetric metric : metrics) {
			List<MetricResult> results = metric.execute();
			for(MetricResult result : results) {
				String proposalId = createProposal(metric, result);
				registerResult(metric, result, proposalId);
			}
		}
	}
	
	private void checkConcreteSyntaxMetrics() {
		List<ConcreteSyntaxMetric> metrics = factory.getConcreteSyntaxMetrics();
		for(ConcreteSyntaxMetric metric : metrics) {
			List<MetricResult> results = metric.execute();
			for(MetricResult result : results) {
				String proposalId = createProposal(metric, result);
				registerResult(metric, result, proposalId);
			}
		}
	}

	private String createProposal(Metric metric, MetricResult metricResult) {
		String rationale = "Metric: " + metric.getName() + " - "
				+ "Description: " + metric.getDescription() + " - "
				+ "Reason: " + metricResult.getReason();
		String referredElements = "";
		for(ReferredElement referredElement : metricResult.getReferredElements()) {
			referredElements += referredElement.getName() + ",";
		}
		if(!referredElements.equals(""))
			referredElements = referredElements.substring(0, referredElements.length() - 1);
		return backend.createProposalPlain(userId, rationale, referredElements);
		
	}
	
	public void registerResult(Metric metric, MetricResult metricResult, String proposalId) {
		List<MetricResult> metricResults = results.get(metric);
		if(metricResults == null) {
			metricResults = new ArrayList<MetricResult>();
			results.put(metric, metricResults);
		}
		metricResults.add(metricResult);
		
		List<String> metricProposals = metric2proposalId.get(metric);
		if(metricProposals == null) {
			metricProposals = new ArrayList<String>();
			metric2proposalId.put(metric, metricProposals);
		}
		metricProposals.add(proposalId);
	}

	public HashMap<Metric, List<MetricResult>> getResults() {
		return results;
	}

	public HashMap<Metric, List<String>> getMetric2proposalId() {
		return metric2proposalId;
	}	
	
	public void deactivateMetric(String metricId) {
		factory.deactivate(metricId);
	}
	
	public void activateMetric(String metricId) {
		factory.activate(metricId);
	}
	
	
}
