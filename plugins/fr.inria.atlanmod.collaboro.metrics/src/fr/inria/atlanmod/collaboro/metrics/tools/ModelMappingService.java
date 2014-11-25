package fr.inria.atlanmod.collaboro.metrics.tools;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.collaboro.metrics.symbol.Concept;

public class ModelMappingService {
	
	private ModelMapping modelMapping;
	
	public ModelMappingService(ModelMapping modelMapping) {
		this.modelMapping = modelMapping;
	}
	
	public Concept getConceptByName(String conceptName) {
		List<Concept> conceptList = this.modelMapping.getAbstractConcepts();
		for(Concept concept : conceptList) {
			if(concept.getName().equals(conceptName)) {
				return concept;
			}
		}
		return null;
	}
	
	public Concept getConceptByAbstractSyntaxElement(EObject abstractSyntaxElement) {
		List<Concept> conceptList = this.modelMapping.getAbstractConcepts();
		for(Concept concept : conceptList) {
			if(concept.getAbstractSyntaxElement().equals(abstractSyntaxElement)) {
				return concept;
			}
		}
		return null;
	}

}
