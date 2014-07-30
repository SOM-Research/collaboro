package fr.inria.atlanmod.collaboro.backend;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Main class to configure a new language in the backend
 */
public class CollaboroLanguageConfig {
	private String languageName;
	private File historyFile;
	private File ecoreFile;
	
	private HashMap<String, List<File>> previousEcores;
	private HashMap<String, List<File>> previousModels;
	
	public CollaboroLanguageConfig(String languageName, File historyFile, File ecoreFile) {
		if(historyFile == null || !historyFile.exists() || ecoreFile == null || !ecoreFile.exists())
			throw new IllegalArgumentException("The files must exist");
		if(languageName == null || languageName.equals("")) 
			throw new IllegalArgumentException("The name cannot be null or empty");
		this.languageName = languageName;
		this.historyFile = historyFile;
		this.ecoreFile = ecoreFile;
		this.previousEcores = new HashMap<>();
		this.previousModels = new HashMap<>();
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public File getHistoryFile() {
		return historyFile;
	}
	public void setHistoryFile(File historyFile) {
		this.historyFile = historyFile;
	}
	public File getEcoreFile() {
		return ecoreFile;
	}
	public void setEcoreFile(File ecoreFile) {
		this.ecoreFile = ecoreFile;
	}
	public List<File> getPreviousEcores(String version) {
		return previousEcores.get(version);
	}
	public void addPreviousEcore(String version, File previousEcore) {
		List<File> previousEcoresVersion = previousEcores.get(version);
		if(previousEcoresVersion == null) {
			previousEcoresVersion = new ArrayList<>();
		}
		previousEcoresVersion.add(previousEcore);
		previousEcores.put(version, previousEcoresVersion);
	}
	public List<File> getPreviousModels(String version) {
		return previousModels.get(version);
	}
	public void addPreviousModel(String version, File previousModel) {
		List<File> previousModelsVersion = previousModels.get(version);
		if(previousModelsVersion == null) {
			previousModelsVersion = new ArrayList<>();
		}
		previousModelsVersion.add(previousModel);
		previousModels.put(version, previousModelsVersion);
	}
	public HashMap<String, List<File>> getPreviousEcores() {
		return previousEcores;
	}
	public HashMap<String, List<File>> getPreviousModels() {
		return previousModels;
	}
	
	
}
