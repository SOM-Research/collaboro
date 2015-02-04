package fr.inria.atlanmod.collaboro.metrics.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.collaboro.metrics.model.AttributeConcept;
import fr.inria.atlanmod.collaboro.metrics.model.ClassConcept;
import fr.inria.atlanmod.collaboro.metrics.model.ReferenceConcept;
import fr.inria.atlanmod.collaboro.metrics.tools.model.AbstractConceptContainer;

public class AbstractSyntaxElementExtractor {
	
	private List<ClassConcept> classConcepts;
	private List<AttributeConcept> attributeConcepts;
	private List<ReferenceConcept> referenceConcepts;
	
	private EPackage abstractSyntaxModel;
	
	public AbstractSyntaxElementExtractor(EPackage abstractSyntaxModel) {
		this.classConcepts = new ArrayList<ClassConcept>();
		this.attributeConcepts = new ArrayList<AttributeConcept>();
		this.referenceConcepts = new ArrayList<ReferenceConcept>();
		
		this.abstractSyntaxModel = abstractSyntaxModel;
	}
	
	/* 
	 *  ================================================================
	 * 			Abstract Syntax Concept Extraction Methods
	 *  ================================================================
	*/
	
	public AbstractConceptContainer discoverAbstractConcepts() {
		discoverAbstractClasses();
		discoverAbstractAttribute();
		discoverAbstractReference();
		
		AbstractConceptContainer container = new AbstractConceptContainer(classConcepts, attributeConcepts, referenceConcepts);
		return container;
	}
	
	private void discoverAbstractClasses() {
		System.out.println("* Discover Abstract Class");
		List<EObject> abstractSyntaxContents = abstractSyntaxModel.eContents();
		for(EObject abstractSyntaxElement : abstractSyntaxContents) {
			if(abstractSyntaxElement instanceof EClass) {
				EClass eClassElement = (EClass) abstractSyntaxElement;
				ClassConcept classConcept = new ClassConcept(eClassElement.getName(), eClassElement.getName(), eClassElement);
				classConcepts.add(classConcept);
				System.out.println("\t Found Class : " + classConcept.getName());
			}
		}
		
		// extracting heritage information
		resolveClassHeritage();
	}
	
	private void resolveClassHeritage() {
		System.out.println("* Resolve class heritage");
		for(ClassConcept classConcept : classConcepts) {
			EClass eClass = (EClass) classConcept.getAbstractModelElement();
			List<EClass> eClassSuperTypes = eClass.getESuperTypes();
			for(EClass eClassSuperType : eClassSuperTypes) {
				ClassConcept classConceptSuperType = getClassConceptById(eClassSuperType.getName());
				if(classConceptSuperType != null) {
					classConceptSuperType.addSubType(classConcept);
					System.out.println("\t Class " + classConceptSuperType.getName() + " subType : " + classConcept.getName());
					classConcept.addSuperType(classConceptSuperType);
					System.out.println("\t Class " + classConcept.getName() + " superType : " + classConceptSuperType.getName());
				}
			}
		}
	}
	
	private void discoverAbstractAttribute() {
		System.out.println("* Discover Abstract Attribute");
		for(ClassConcept classConcept : classConcepts) {
			EClass eClass = (EClass) classConcept.getAbstractModelElement();
			List<EAttribute> eClassAttributes = eClass.getEAllAttributes();
			for(EAttribute eClassAttribute : eClassAttributes) {
				String attributeName = eClass.getName() + "." + eClassAttribute.getName();
				AttributeConcept attributeConcept = new AttributeConcept(eClassAttribute.getName(), attributeName, eClassAttribute);
				attributeConcept.setClassConcept(classConcept);
				System.out.println("\t Found Attribute : " + attributeConcept.getName() + " in Class : " + classConcept.getName());

				EClass eContainingClass = eClassAttribute.getEContainingClass();
				if(!eContainingClass.equals(eClass)) {
					ClassConcept containingClassConcept = getClassConceptById(eContainingClass.getName());
					if(containingClassConcept != null) {
						attributeConcept.setContainingSuperClass(containingClassConcept);
						System.out.println("\t\t From superClass : " + containingClassConcept.getName());
					}
				}
				this.attributeConcepts.add(attributeConcept);
				classConcept.addAttribute(attributeConcept);
			}
		}
		
		// extracting heritage information
		for(AttributeConcept attributeConcept : attributeConcepts) {
			ClassConcept attributeSuperClass = attributeConcept.getContainingSuperClass();
			if(attributeSuperClass == null) {
				// not heritated attribute
				resolveAttributeHeritage(attributeConcept);
			}
		}
	}
	
	private void resolveAttributeHeritage(AttributeConcept attributeConcept) {
		System.out.println("* Resolve attribute heritage : " + attributeConcept.getName());
		EObject modelAttribute = attributeConcept.getAbstractModelElement();
		ClassConcept containingClass = attributeConcept.getClassConcept();
		List<ClassConcept> containingClassSubClasses = containingClass.getSubTypes();
		for(ClassConcept subClassConcept : containingClassSubClasses) {
			AttributeConcept subAttributeConcept = getAttributeConceptByEObjectFromClassConcept(subClassConcept, modelAttribute);
			if(subAttributeConcept != null) {
				attributeConcept.addSubAttribute(subAttributeConcept);
				subAttributeConcept.addSuperAttribute(attributeConcept);
				System.out.println("\t " + attributeConcept.getName() + " is superType of " + subAttributeConcept.getName());
				resolveAttributeHeritage(subAttributeConcept);
			}
		}
		
	}
	
	private AttributeConcept getAttributeConceptByEObjectFromClassConcept(ClassConcept classConcept, EObject modelObject) {
		List<AttributeConcept> classAttributes = classConcept.getAttributes();
		for(AttributeConcept classAttribute : classAttributes) {
			if(classAttribute.getAbstractModelElement().equals(modelObject)) {
				return classAttribute;
			}
		}
		return null;
	}
	
	private void discoverAbstractReference() {
		System.out.println("* Discover Abstract Reference");
		for(ClassConcept classConcept : classConcepts) {
			EClass eClass = (EClass) classConcept.getAbstractModelElement();
			List<EReference> eClassReferences = eClass.getEAllReferences();
			for(EReference eClassReference : eClassReferences) {
				String referenceName = eClass.getName() + "." + eClassReference.getName();
				ReferenceConcept referenceConcept = new ReferenceConcept(eClassReference.getName(), referenceName, eClassReference);
				
				referenceConcept.setContainingClass(classConcept);
				//From
				EClass eClassReferenceFrom = eClassReference.getEContainingClass();
				ClassConcept referenceClassConceptFrom = getClassConceptById(eClassReferenceFrom.getName());
				referenceConcept.setSuperClassConceptFrom(referenceClassConceptFrom);
				//To
				EClass eClassReferenceTo = eClassReference.getEReferenceType();
				ClassConcept referenceClassConceptTo = getClassConceptById(eClassReferenceTo.getName());
				referenceConcept.setClassConceptTo(referenceClassConceptTo);
				
				referenceConcept.setSuperClassConceptTo(referenceClassConceptTo);
				
				referenceConcepts.add(referenceConcept);
				classConcept.addReference(referenceConcept);
				System.out.println("\t Found Reference : " + referenceConcept.getName() + " in Class : " + classConcept.getName());
			}
		}
		
		//Opposite Discovery
		List<ReferenceConcept> oppositeReferenceConcepts = resolveReferenceConceptOpposite();
		referenceConcepts.addAll(oppositeReferenceConcepts);
		
		//extracting heritage information
		resolveReferenceHeritage();
		
		
	}
	
	private List<ReferenceConcept> resolveReferenceConceptOpposite() {
		System.out.println("* Resolve Reference Opposite");
		List<ReferenceConcept> oppositeReferenceConcepts = new ArrayList<ReferenceConcept>();
		for(ReferenceConcept referenceConcept : referenceConcepts) {
			EReference eReference = (EReference) referenceConcept.getAbstractModelElement();
			EReference eOppositeReference = eReference.getEOpposite();
			if(eOppositeReference != null) {
				System.out.println("\t Found opposite for " + referenceConcept.getName());
				//To
				ClassConcept referenceContainingClass = referenceConcept.getContainingClass();
				EClass eOppositeReferenceToEClass = eOppositeReference.getEReferenceType();
				ClassConcept oppositeReferenceToClassConcept = getClassConceptById(eOppositeReferenceToEClass.getName());
				//From
				EClass eOppositeReferenceContainingClass = eOppositeReference.getEContainingClass();
				ClassConcept oppositeReferenceContainingClass = getClassConceptById(eOppositeReferenceContainingClass.getName());
				
				ReferenceConcept oppositeReferenceConcept = getExistingReferenceConcept(oppositeReferenceContainingClass,referenceContainingClass,eOppositeReference);
				
				if(oppositeReferenceConcept == null) {
					
					String oppositeReferenceName = eOppositeReferenceContainingClass.getName() + "." + eOppositeReference.getName();
					oppositeReferenceConcept = new ReferenceConcept(eOppositeReference.getName(), oppositeReferenceName, eOppositeReference);
					oppositeReferenceConcept.setContainingClass(oppositeReferenceContainingClass);
					oppositeReferenceConcept.setSuperClassConceptFrom(oppositeReferenceContainingClass);
					oppositeReferenceConcept.setSuperClassConceptTo(oppositeReferenceToClassConcept);
					oppositeReferenceConcept.setClassConceptTo(referenceContainingClass);
					oppositeReferenceConcept.setReferenceOpposite(referenceConcept);
					referenceConcept.setReferenceOpposite(oppositeReferenceConcept);
					oppositeReferenceConcepts.add(oppositeReferenceConcept);
					oppositeReferenceContainingClass.addReference(oppositeReferenceConcept);
					System.out.println("\t\t non existing opposite : " + oppositeReferenceConcept.getName());
				} else {
					referenceConcept.setReferenceOpposite(oppositeReferenceConcept);
					System.out.println("\t\t existing opposite " + oppositeReferenceConcept.getName());
				}
			}
		}
		return oppositeReferenceConcepts;
	}
	
	private void resolveReferenceHeritage() {
		System.out.println("* Resolve reference heritage");
		for(ReferenceConcept referenceConcept : referenceConcepts) {
			ClassConcept referenceContainingClass = referenceConcept.getContainingClass();
			ClassConcept referenceSuperContainingClass = referenceConcept.getSuperClassConceptFrom();
			ClassConcept referenceToClass = referenceConcept.getClassConceptTo();
			ClassConcept referenceSuperToClass = referenceConcept.getSuperClassConceptTo();
			if(referenceSuperContainingClass.equals(referenceContainingClass)) {
				if(referenceSuperToClass.equals(referenceToClass)) {
					// not heritated Reference
					resolveRH(referenceConcept);
					resolveRH2(referenceConcept);
				}	
			}
		}
	}

	
	private void resolveRH(ReferenceConcept referenceConcept) {
		EReference modelReference = (EReference) referenceConcept.getAbstractModelElement();
		ClassConcept containingClass = referenceConcept.getContainingClass();
		ClassConcept referenceToSuperClass = referenceConcept.getSuperClassConceptTo();
		List<ClassConcept> containingClassSubClasses = containingClass.getSubTypes();
		for(ClassConcept subClassConcept : containingClassSubClasses) {
			ReferenceConcept subReferenceConcept = getExistingReferenceConcept(subClassConcept,referenceToSuperClass,modelReference);
			if(subReferenceConcept != null) {
				referenceConcept.addSubReference(subReferenceConcept);
				subReferenceConcept.addSuperReference(referenceConcept);
				resolveRH(subReferenceConcept);
			}
		}
	}
	
	private void resolveRH2(ReferenceConcept referenceConcept) {
		EReference modelReference = (EReference) referenceConcept.getAbstractModelElement();
		ClassConcept referenceFromClass = referenceConcept.getSuperClassConceptFrom();
		ClassConcept referenceToClass = referenceConcept.getClassConceptTo();
		List<ClassConcept> referenceToClassSubClasses = referenceToClass.getSubTypes();
		for(ClassConcept referenceToClassSubClass : referenceToClassSubClasses) {
			ReferenceConcept subReferenceConcept = getExistingReferenceConcept(referenceFromClass,referenceToClassSubClass,modelReference);
			if(subReferenceConcept != null) {
				subReferenceConcept.addSuperReference(referenceConcept);
				referenceConcept.addSubReference(subReferenceConcept);
				resolveRH2(subReferenceConcept);
			}
		}
	}
	
	private ReferenceConcept getExistingReferenceConcept(ClassConcept classConceptFrom,ClassConcept classConceptTo, EReference eReference) {
		List<ReferenceConcept> classConceptReferences = classConceptFrom.getReferences();
		for(ReferenceConcept classConceptReference : classConceptReferences) {
			ClassConcept classConceptReferenceTo = classConceptReference.getClassConceptTo();
			if(classConceptReferenceTo.equals(classConceptTo)) {
				EReference classConceptReferenceEReference = (EReference) classConceptReference.getAbstractModelElement();
				if(classConceptReferenceEReference.equals(eReference)) {
					return classConceptReference;
				}
			}
		}
		return null;
	}
	
	private ClassConcept getClassConceptById(String id) {
		for(ClassConcept classConcept : classConcepts) {
			if(classConcept.getAbstractModelId().equals(id)) {
				return classConcept;
			}
		}
		return null;
	}

}
