package fr.inria.atlanmod.collaboro.metrics.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public class AttributeConcept extends Concept {
	
	private ClassConcept classConcept;
	private ClassConcept containingSuperClass;
	private List<AttributeConcept> subAttributes;
	private List<AttributeConcept> superAttributes;
	
	public AttributeConcept(String abstractModelId, String name,
			EObject abstractModelElement) {
		super(abstractModelId, name, abstractModelElement);
		this.classConcept = null;
		this.containingSuperClass = null;
		this.subAttributes = new ArrayList<AttributeConcept>();
		this.superAttributes = new ArrayList<AttributeConcept>();
	}

	public ClassConcept getClassConcept() {
		return classConcept;
	}

	public void setClassConcept(ClassConcept classConcept) {
		this.classConcept = classConcept;
	}

	public ClassConcept getContainingSuperClass() {
		return containingSuperClass;
	}

	public void setContainingSuperClass(ClassConcept containingSuperClass) {
		this.containingSuperClass = containingSuperClass;
	}

	public List<AttributeConcept> getSubAttributes() {
		return subAttributes;
	}

	public void setSubAttributes(List<AttributeConcept> subAttributes) {
		this.subAttributes = subAttributes;
	}
	
	public void addSubAttribute(AttributeConcept subAttribute) {
		this.subAttributes.add(subAttribute);
	}

	public List<AttributeConcept> getSuperAttributes() {
		return superAttributes;
	}

	public void setSuperAttributes(List<AttributeConcept> superAttributes) {
		this.superAttributes = superAttributes;
	}
	
	public void addSuperAttribute(AttributeConcept superAttribute) {
		this.superAttributes.add(superAttribute);
	}
	
	public String toString() {
		return this.getName();
	}
	
}
