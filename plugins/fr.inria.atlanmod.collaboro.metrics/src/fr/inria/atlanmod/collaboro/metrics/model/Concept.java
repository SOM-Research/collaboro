package fr.inria.atlanmod.collaboro.metrics.symbol;

import org.eclipse.emf.ecore.EObject;

public class Concept {
	
	private String abstractModelId;
	private String name;
	private EObject abstractModelElement;
	
	public Concept(String abstractModelId, String name,
			EObject abstractModelElement) {
		this.abstractModelId = abstractModelId;
		this.name = name;
		this.abstractModelElement = abstractModelElement;
	}

	public String getAbstractModelId() {
		return abstractModelId;
	}

	public String getName() {
		return name;
	}

	public EObject getAbstractModelElement() {
		return abstractModelElement;
	}
	
	
	
	

}
