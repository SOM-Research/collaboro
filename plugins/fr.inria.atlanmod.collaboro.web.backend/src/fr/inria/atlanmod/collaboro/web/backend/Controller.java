package fr.inria.atlanmod.collaboro.web.backend;

import org.eclipse.emf.common.util.URI;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.web.backend.LocalModelManager;

public class Controller
{

	LocalModelManager modelManager;
	
	public Controller(URI uriHistoryModel)
	{
		modelManager = new LocalModelManager();
		modelManager.initialize(uriHistoryModel);
	}
	
	public History getHistory()
	{
		return modelManager.getHistory();
	}
	
}
