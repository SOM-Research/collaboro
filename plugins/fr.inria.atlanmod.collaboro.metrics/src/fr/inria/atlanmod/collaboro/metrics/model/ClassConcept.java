package fr.inria.atlanmod.collaboro.metrics.symbol;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public class ClassConcept extends Concept{
	
	private List<ClassConcept> subTypes;
	private List<ClassConcept> superType;
	private List<AttributeConcept> attributes;
	private List<ReferenceConcept> references;
	
	public ClassConcept(String abstractModelId, String name,
			EObject abstractModelElement) {
		super(abstractModelId, name, abstractModelElement);
		this.subTypes = new ArrayList<ClassConcept>();
		this.superType = new ArrayList<ClassConcept>();
		this.attributes = new ArrayList<AttributeConcept>();
		this.references = new ArrayList<ReferenceConcept>();
	}

	public List<ClassConcept> getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(List<ClassConcept> subTypes) {
		this.subTypes = subTypes;
	}
	
	public void addSubType(ClassConcept subType) {
		this.subTypes.add(subType);
	}

	public List<ClassConcept> getSuperType() {
		return superType;
	}

	public void setSuperType(List<ClassConcept> superType) {
		this.superType = superType;
	}
	
	public void addSuperType(ClassConcept superType) {
		this.superType.add(superType);
	}
	
	public List<AttributeConcept> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeConcept> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(AttributeConcept attribute) {
		this.attributes.add(attribute);
	}

	public List<ReferenceConcept> getReferences() {
		return references;
	}

	public void setReferences(List<ReferenceConcept> references) {
		this.references = references;
	}
	
	public void addReference(ReferenceConcept reference) {
		this.references.add(reference);
	}

	public String toString() {
		return this.getName();
	}
	
	

}
