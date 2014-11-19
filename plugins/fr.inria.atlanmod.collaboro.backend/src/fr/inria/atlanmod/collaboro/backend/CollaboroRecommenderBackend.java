package fr.inria.atlanmod.collaboro.backend;

public class CollaboroRecommenderBackend extends CollaboroBackend {
	private RecommenderEngine recommender;
	private String userId;
	
	public CollaboroRecommenderBackend(ModelManager modelManager, String userId) {
		super(modelManager);
		this.userId = userId;
		this.recommender = new RecommenderEngine(userId, this);
	}
	
	public void launchRecommender() {
		recommender.checkMetrics();
	}
	

}
