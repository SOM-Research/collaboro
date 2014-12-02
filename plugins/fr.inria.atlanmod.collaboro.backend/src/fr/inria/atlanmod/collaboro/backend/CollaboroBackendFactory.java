package fr.inria.atlanmod.collaboro.backend;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import fr.inria.atlanmod.collaboro.history.User;

/**
 * Creates Collaboro backends for DSLs 
 */
public class CollaboroBackendFactory {
	private static CollaboroBackendFactory instance;

	private HashMap<String, CollaboroLanguageConfig> configs;
	private HashMap<String, CollaboroBackend> backends;
	private HashMap<String, ModelManager> modelManagers;
	private CollaboroBackend lastBackendCreated;

	private CollaboroBackendFactory() {
		this.backends = new HashMap<>();
		this.configs = new HashMap<>();
		this.modelManagers = new HashMap<String, ModelManager>();
	}

	public static boolean isActive() {
		return !(instance == null);
	}

	public static void init(List<CollaboroLanguageConfig> languages) {
		instance = new CollaboroBackendFactory();
		for(CollaboroLanguageConfig language : languages) {
			instance.configs.put(language.getLanguageName().toLowerCase(), language);
		}
	}

	private ModelManager getModelManager(String dsl) {
		ModelManager modelManager = modelManagers.get(dsl);
		if(modelManager == null) {
			CollaboroLanguageConfig config = configs.get(dsl.toLowerCase());
			if(config == null)
				throw new IllegalArgumentException("There is no config for such DSL");

			File ecoreFile = config.getEcoreFile();
			modelManager = ModelManagerFactory.createModelManager(ecoreFile);
			modelManagers.put(dsl, modelManager);
		}
		return modelManager;
	}
	
	public static User loginUser(String email, String password, String dsl) {
		if(email == null || password == null || dsl == null)
			throw new IllegalArgumentException("Parameters cannot be null");

		User found = null;
		ModelManager modelManager = instance.getModelManager(dsl);
		
		if(modelManager.getHistory() != null) 
			for (User user : modelManager.getHistory().getUsers()) 
				if(user.getEmail() != null && user.getEmail().equals(email) && user.getPassword() != null && user.getPassword().equals(password))
					return user;

		return found;
	}
	
	public static CollaboroRecommenderBackend getRecommenderBackend(String dsl) {
		if(instance == null) 
			instance = new CollaboroBackendFactory();
		
		String userId = "recommender";

		String dslUser = dsl + "-" + userId;		
		CollaboroRecommenderBackend recommenderBackend = (CollaboroRecommenderBackend) instance.backends.get(dslUser.toLowerCase());
		if(recommenderBackend == null) {
			ModelManager modelManager = instance.getModelManager(dsl);
			recommenderBackend = new CollaboroRecommenderBackend(modelManager, userId);
			instance.backends.put(dslUser.toLowerCase(), recommenderBackend);
		}
		
		return recommenderBackend;
	}
	
	public static CollaboroBackend getBackend(String dsl, String user) {
		if(instance == null) 
			instance = new CollaboroBackendFactory();

		String dslUser = dsl + "-" + user;		
		CollaboroBackend backend = instance.backends.get(dslUser.toLowerCase());
		CollaboroLanguageConfig config = instance.configs.get(dsl.toLowerCase());
		if(backend == null && config != null) {
			ModelManager modelManager = instance.getModelManager(dsl);
			backend = new CollaboroBackend(modelManager);
			backend.setPreviousEcores(config.getPreviousEcores());
			backend.setPreviousModels(config.getPreviousModels());
			backend.moveToLastVersion();
			instance.backends.put(dslUser, backend);
			instance.lastBackendCreated = backend;
		} 
		return backend;
	}
	
	public static String[] getActiveLanguages() {
		return instance.configs.keySet().toArray(new String[] { } );
	}

	public static CollaboroBackend getLastBackend() {
		return instance.lastBackendCreated;
	}

}
