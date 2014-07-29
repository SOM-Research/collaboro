package fr.inria.atlanmod.collaboro.backend;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class CollaboroBackendFactory {
	private static CollaboroBackendFactory instance;

	private HashMap<String, File> histories;
	private HashMap<String, File> ecores;

	private HashMap<String, CollaboroBackend> backends;
	private CollaboroBackend lastBackendCreated;

	private CollaboroBackendFactory() {
		this.histories = new HashMap<>();
		this.ecores = new HashMap<>();
		this.backends = new HashMap<>();
	}

	public static boolean isActive() {
		return !(instance == null);
	}

	public static void init(List<CollaboroLanguageConfig> languages) {
		instance = new CollaboroBackendFactory();
		for(CollaboroLanguageConfig language : languages) {
			instance.histories.put(language.getLanguageName(), language.getHistoryFile());
			instance.ecores.put(language.getLanguageName(), language.getEcoreFile());
		}
	}

	public static CollaboroBackend getBackend(String dsl) {
		if(instance == null) 
			instance = new CollaboroBackendFactory();

		CollaboroBackend backend = instance.backends.get(dsl.toLowerCase());
		if(backend == null) {
			File historyFile = instance.histories.get(dsl.toLowerCase());
			File ecoreFile = instance.ecores.get(dsl.toLowerCase());
			backend = new CollaboroBackend(historyFile, ecoreFile);
			instance.backends.put(dsl.toLowerCase(), backend);
			instance.lastBackendCreated = backend;
		} 
		return backend;
	}
	
	public static String[] getActiveLanguages() {
		return instance.histories.keySet().toArray(new String[] { } );
	}

	public static CollaboroBackend getLastBackend() {
		return instance.lastBackendCreated;
	}

}
