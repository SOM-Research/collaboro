package fr.inria.atlanmod.collaboro.metrics.tools.model;

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.collaboro.metrics.model.AttributeConcept;
import fr.inria.atlanmod.collaboro.metrics.model.ClassConcept;
import fr.inria.atlanmod.collaboro.metrics.model.ReferenceConcept;


public class AbstractConceptContainer {
	
	private List<ClassConcept> classConcepts;
	private List<AttributeConcept> attributeConcepts;
	private List<ReferenceConcept> referenceConcepts;
	
	public AbstractConceptContainer() {
		this.classConcepts = new ArrayList<ClassConcept>();
		this.attributeConcepts = new ArrayList<AttributeConcept>();
		this.referenceConcepts = new ArrayList<ReferenceConcept>();
	}

	public AbstractConceptContainer(List<ClassConcept> classConcepts,
			List<AttributeConcept> attributeConcepts,
			List<ReferenceConcept> referenceConcepts) {
		this.classConcepts = classConcepts;
		this.attributeConcepts = attributeConcepts;
		this.referenceConcepts = referenceConcepts;
	}

	public List<ClassConcept> getClassConcepts() {
		return classConcepts;
	}

	public void setClassConcepts(List<ClassConcept> classConcepts) {
		this.classConcepts = classConcepts;
	}

	public List<AttributeConcept> getAttributeConcepts() {
		return attributeConcepts;
	}

	public void setAttributeConcepts(List<AttributeConcept> attributeConcepts) {
		this.attributeConcepts = attributeConcepts;
	}

	public List<ReferenceConcept> getReferenceConcepts() {
		return referenceConcepts;
	}

	public void setReferenceConcepts(List<ReferenceConcept> referenceConcepts) {
		this.referenceConcepts = referenceConcepts;
	}
	
	
	
	
	

}
