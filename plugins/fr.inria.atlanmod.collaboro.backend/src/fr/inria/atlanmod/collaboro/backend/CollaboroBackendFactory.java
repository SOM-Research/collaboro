package fr.inria.atlanmod.collaboro.backend;

import java.io.File;
import java.util.HashMap;

public class CollaboroBackendFactory {
	private static CollaboroBackendFactory instance;

	private HashMap<String, String> histories;
	private HashMap<String, String> ecores;

	private HashMap<String, CollaboroBackend> backends;
	private CollaboroBackend lastBackendCreated;

	private CollaboroBackendFactory() {
		this.histories = new HashMap<>();
		this.histories.put("workflow", "C:\\Users\\useradm\\git\\collaboro\\plugins\\fr.inria.atlanmod.collaboro.web.servlets\\WebContent\\WEB-INF\\model\\ModiscoWorkflow.history");
		this.histories.put("aadlba", "C:\\Users\\useradm\\git\\collaboro\\plugins\\fr.inria.atlanmod.collaboro.web.servlets\\WebContent\\WEB-INF\\model\\AADLBA.history");

		this.ecores = new HashMap<>();
		this.ecores.put("workflow", "C:\\Users\\useradm\\git\\collaboro\\plugins\\fr.inria.atlanmod.collaboro.web.servlets\\WebContent\\WEB-INF\\model\\ModiscoWorkflow.ecore");
		this.ecores.put("aadlba", "C:\\Users\\useradm\\git\\collaboro\\plugins\\fr.inria.atlanmod.collaboro.web.servlets\\WebContent\\WEB-INF\\model\\AADLBA.ecore");
		
		this.backends = new HashMap<>();
	}

	public static CollaboroBackend getBackend(String dsl) {
		if(instance == null) 
			instance = new CollaboroBackendFactory();

		CollaboroBackend backend = instance.backends.get(dsl.toLowerCase());
		if(backend == null) {
			String historyFileString = instance.histories.get(dsl);
			String ecoreFileString = instance.ecores.get(dsl);

			if(historyFileString != null && ecoreFileString != null){
				File historyFile = new File(historyFileString);
				File ecoreFile = new File(ecoreFileString);
				if(historyFile.exists() && ecoreFile.exists()) {
					backend = new CollaboroBackend(historyFile, ecoreFile);
					instance.backends.put(dsl.toLowerCase(), backend);
					instance.lastBackendCreated = backend;
				}
			}

		} 
		return backend;
	}
	
	public static CollaboroBackend getLastBackend() {
		return instance.lastBackendCreated;
	}

}
