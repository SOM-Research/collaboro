package fr.inria.atlanmod.collaboro.web.backend;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;



import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;

public class LocalModelManager {
	

	//TODO Replace with url received from servlet
	public static final String HISTORY_MODEL = "/model/ModiscoWorkflow.history";
	
	// Extensions for the models supported
	public static final String HISTORY_EXTENSION = "history";
	public static final String MODEL_EXTENSION = "xmi";
	
	// Track of history model
	private URI historyModelUri;
	private History history;
	
	// Resource unified
	ResourceSet rset = null;
	
	public void initialize(URI historyModelUri)
	{
		
			// Preparing the resource
			rset = new ResourceSetImpl();
			rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(HISTORY_EXTENSION, new EcoreResourceFactoryImpl());
			rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(MODEL_EXTENSION, new EcoreResourceFactoryImpl());
			rset.getPackageRegistry().put(HistoryPackage.eNS_URI, HistoryPackage.eINSTANCE);
			
			// Loading the history model
			this.historyModelUri=historyModelUri;
			history = loadHistory();
	}
	
	public History loadHistory() {
		if(rset == null) return null;

		History result = null;
		
		Resource res = rset.getResource(historyModelUri,true);
			try {
				res.load(null);
			} catch (IOException e) {
				e.printStackTrace();
			}

			result = (History) res.getContents().get(0);
		
		
		history = result;
		
		return result;
	}
	
	public History getHistory()
	{
		return history;
		
	}
	
	

}
