package fr.inria.atlanmod.collaboro.backend;

import java.io.File;

/**
 * Main class to configure a new language in the backend
 */
public class CollaboroLanguageConfig {
	private String languageName;
	private File historyFile;
	private File ecoreFile;
	
	public CollaboroLanguageConfig(String languageName, File historyFile, File ecoreFile) {
		if(historyFile == null || !historyFile.exists() || ecoreFile == null || !ecoreFile.exists())
			throw new IllegalArgumentException("The files must exist");
		if(languageName == null || languageName.equals("")) 
			throw new IllegalArgumentException("The name cannot be null or empty");
		this.languageName = languageName;
		this.historyFile = historyFile;
		this.ecoreFile = ecoreFile;
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
	
	
}
