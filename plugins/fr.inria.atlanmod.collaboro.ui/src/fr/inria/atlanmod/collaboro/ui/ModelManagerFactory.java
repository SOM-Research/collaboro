package fr.inria.atlanmod.collaboro.ui;

import java.io.File;

import org.eclipse.emf.cdo.eresource.CDOResource;


public class ModelManagerFactory {
	
	public ModelManager createModelManager(Object resource) {
		ModelManager modelManager = null;
		
		if(resource instanceof File) {
			modelManager = new LocalModelManager();
			((LocalModelManager) modelManager).initialize((File) resource);
		} else if(resource instanceof CDOResource) {
			modelManager = new CDOModelManager();
			((CDOModelManager) modelManager).initialize((CDOResource) resource);
		} else {
			modelManager = new LocalModelManager();
		}
		
		return modelManager;
	}
	
	public ModelManager createEmptyModelManager() {
		return new LocalModelManager();
	}
}
