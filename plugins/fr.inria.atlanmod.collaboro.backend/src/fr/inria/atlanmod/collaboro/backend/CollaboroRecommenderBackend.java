package fr.inria.atlanmod.collaboro.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.inria.atlanmod.collaboro.history.HistoryFactory;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.metrics.Metric;
import fr.inria.atlanmod.collaboro.metrics.MetricResult;

public class CollaboroRecommenderBackend extends CollaboroBackend {
	private RecommenderEngine recommender;
	private String userId;
	private List<Proposal> recommendations = new ArrayList<Proposal>();
	
	public CollaboroRecommenderBackend(ModelManager modelManager, String userId) {
		super(modelManager);
		this.userId = userId;
		checkRecommenderUser();
	}
	
	public void checkRecommenderUser() {
		boolean found = false;
		for(User user : getHistory().getUsers()) {
			if(user.getId().equals(this.userId)) {
				found = true;
				break;
			}
		}
		
		if(!found) {
			User recommenderUser = HistoryFactory.eINSTANCE.createUser();
			recommenderUser.setId(userId);
			recommenderUser.setFirstName("Recommender");
			recommenderUser.setLastName("System");
			recommenderUser.setEmail("recommender@collaboro.com");
			recommenderUser.setPasword("");
			
			getHistory().getUsers().add(recommenderUser);
			saveHistory();
		}
	}
	
	@Override
	public String createProposalPlain(String userId, String rationale, String referredElements) {
		Proposal newProposal = HistoryFactory.eINSTANCE.createProposal();
		initCollaborationPlain(newProposal, userId, rationale, referredElements);
		recommendations = new ArrayList<Proposal>();
		recommendations.add(newProposal);
		return newProposal.getId();
	}
	
	public void launchRecommender() {
		recommendations = new ArrayList<Proposal>();
		this.recommender = new RecommenderEngine(userId, this);
		recommender.checkMetrics();
	}
	
	public void applyRecommendations() {
		for(Proposal recommendation : recommendations) {
			createProposal(recommendation);
		}
	}
	
	public HashMap<Metric, List<MetricResult>> getResults() {
		if(recommender == null) 
			throw new IllegalStateException("There is no recommender yet. Please, call launcheRecommender before");
		return recommender.getResults();
	}

	public List<Metric> getMetrics() {
		return recommender.getMetrics();
	}	
	
	

}
