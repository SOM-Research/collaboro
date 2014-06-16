package fr.inria.atlanmod.collaboro.backend;

import java.io.File;


public class ModelManagerFactory {
	
	public ModelManager createModelManager(Object resource) {
		ModelManager modelManager = null;
		
		if(resource instanceof File) {
			modelManager = new LocalModelManager();
			((LocalModelManager) modelManager).initialize((File) resource);
//		} else if(resource instanceof CDOResource) {
			//modelManager = new CDOModelManager();
			//((CDOModelManager) modelManager).initialize((CDOResource) resource);
		} else {
			modelManager = new LocalModelManager();
		}
		
		return modelManager;
	}
	
	public ModelManager createEmptyModelManager() {
		return new LocalModelManager();
	}
}
