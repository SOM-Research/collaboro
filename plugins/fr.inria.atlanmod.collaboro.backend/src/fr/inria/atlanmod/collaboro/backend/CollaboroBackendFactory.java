package fr.inria.atlanmod.collaboro.backend;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class CollaboroBackendFactory {
	private static CollaboroBackendFactory instance;

	private HashMap<String, CollaboroLanguageConfig> configs;
	private HashMap<String, CollaboroBackend> backends;
	private CollaboroBackend lastBackendCreated;

	private CollaboroBackendFactory() {
		this.backends = new HashMap<>();
		this.configs = new HashMap<>();
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

	public static CollaboroBackend getBackend(String dsl) {
		if(instance == null) 
			instance = new CollaboroBackendFactory();

		CollaboroBackend backend = instance.backends.get(dsl.toLowerCase());
		CollaboroLanguageConfig config = instance.configs.get(dsl.toLowerCase());
		if(backend == null && config != null) {
			File historyFile = config.getHistoryFile();
			File ecoreFile = config.getEcoreFile();
			backend = new CollaboroBackend(historyFile, ecoreFile);
			backend.setPreviousEcores(config.getPreviousEcores());
			backend.setPreviousModels(config.getPreviousModels());
			instance.backends.put(dsl.toLowerCase(), backend);
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
