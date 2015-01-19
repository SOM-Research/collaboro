package fr.inria.atlanmod.collaboro.metrics.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public class ReferenceConcept extends Concept {

	private ClassConcept containingClass;
	private ClassConcept superClassConceptFrom;
	private ClassConcept superClassConceptTo;
	private ClassConcept classConceptTo;
	private ReferenceConcept referenceOpposite;
	private List<ReferenceConcept> subReferences;
	private List<ReferenceConcept> superReferences;
	
	public ReferenceConcept(String abstractModelId, String name,
			EObject abstractModelElement) {
		super(abstractModelId, name, abstractModelElement);
		this.classConceptTo = null;
		this.superClassConceptFrom = null;
		this.referenceOpposite = null;
		this.containingClass = null;
		this.subReferences = new ArrayList<ReferenceConcept>();
		this.superReferences = new ArrayList<ReferenceConcept>();
	}
	
	public ClassConcept getSuperClassConceptFrom() {
		return superClassConceptFrom;
	}

	public void setSuperClassConceptFrom(ClassConcept superClassConceptFrom) {
		this.superClassConceptFrom = superClassConceptFrom;
	}

	public ClassConcept getSuperClassConceptTo() {
		return superClassConceptTo;
	}

	public void setSuperClassConceptTo(ClassConcept superClassConceptTo) {
		this.superClassConceptTo = superClassConceptTo;
	}

	public ClassConcept getClassConceptTo() {
		return classConceptTo;
	}

	public void setClassConceptTo(ClassConcept classConceptTo) {
		this.classConceptTo = classConceptTo;
	}

	public ReferenceConcept getReferenceOpposite() {
		return referenceOpposite;
	}

	public void setReferenceOpposite(ReferenceConcept referenceOpposite) {
		this.referenceOpposite = referenceOpposite;
	}

	public ClassConcept getContainingClass() {
		return containingClass;
	}

	public void setContainingClass(ClassConcept containingClass) {
		this.containingClass = containingClass;
	}

	public List<ReferenceConcept> getSubReferences() {
		return subReferences;
	}

	public void setSubReferences(List<ReferenceConcept> subReferences) {
		this.subReferences = subReferences;
	}
	
	public void addSubReference(ReferenceConcept subReference) {
		this.subReferences.add(subReference);
	}

	public List<ReferenceConcept> getSuperReferences() {
		return superReferences;
	}

	public void setSuperReferences(List<ReferenceConcept> superReferences) {
		this.superReferences = superReferences;
	}
	
	public void addSuperReference(ReferenceConcept superReference) {
		this.superReferences.add(superReference);
	}
	
	
	
	public String toString() {
		return this.getName() + "." + this.classConceptTo.getName();
	}
	
}
