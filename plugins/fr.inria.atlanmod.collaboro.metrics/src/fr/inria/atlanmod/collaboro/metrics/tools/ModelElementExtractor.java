package fr.inria.atlanmod.collaboro.metrics.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.collaboro.metrics.symbol.AttributeSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.ClassSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.Concept;
import fr.inria.atlanmod.collaboro.metrics.symbol.ReferenceSymbol;
import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;
import fr.inria.atlanmod.collaboro.notation.AttributeValue;
import fr.inria.atlanmod.collaboro.notation.Composite;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.notation.ReferenceValue;
import fr.inria.atlanmod.collaboro.notation.SyntaxOf;

public class ModelElementExtractor {
	
	private List<EObject> abstractSyntaxElements;
	private List<NotationElement> concreteSyntaxElements;
	private List<Symbol> concreteSymbols;

	
	
	public List<Concept> discoverAbstractConcepts(EPackage abstractSyntaxModel) {
		List<Concept> concepts = new ArrayList<Concept>();
		Map<String,EObject> abstractConcepts = new HashMap<String,EObject>();
		
		System.out.println("Discovery of Abstract Concepts : ");
		List<EObject> abstractSyntaxContents = abstractSyntaxModel.eContents();
		for(EObject abstractSyntaxElement : abstractSyntaxContents) {
			if(abstractSyntaxElement instanceof EClass) {
				EClass eClassElement = (EClass) abstractSyntaxElement;
				String eClassElementName = eClassElement.getName();
				abstractConcepts.put(eClassElementName,eClassElement);
				System.out.println("\t Class Found : " + eClassElementName + " -> " + eClassElement);
				Concept classConcept = new Concept(eClassElementName, eClassElement);
				concepts.add(classConcept);
				List<EAttribute> eClassAttributes = eClassElement.getEAllAttributes();
				for(EAttribute eClassAttribute : eClassAttributes) {
					String eClassAttributeName = eClassAttribute.getName();
					abstractConcepts.put(eClassElementName + "." + eClassAttributeName,eClassAttribute);
					System.out.println("\t\t Attribute : " + eClassElementName + "." + eClassAttributeName + " -> " + eClassAttribute);
					Concept attributeConcept = new Concept(eClassElementName + "." + eClassAttributeName, eClassAttribute);
					concepts.add(attributeConcept);
				}
				List<EReference> eClassReferences = eClassElement.getEAllReferences();
				for(EReference eClassReference : eClassReferences) {
					String eClassReferenceName = eClassReference.getName();
					abstractConcepts.put(eClassElementName + "." + eClassReferenceName, eClassReference);
					System.out.println("\t\t Reference : " + eClassElementName + "." + eClassReferenceName + " -> " + eClassReference);
					Concept referenceConcept = new Concept(eClassElementName + "." + eClassReferenceName, eClassReference);
					concepts.add(referenceConcept);
				}
			}
		}
		
		return concepts;
	}
	
	public void resolveHeritage(Map<String,EObject> abstractConcepts) {
		Set<String> abstractConceptsKeySet = abstractConcepts.keySet();
		for(String abstractConceptId : abstractConceptsKeySet) {
			EObject abstractConcept = abstractConcepts.get(abstractConceptId);
			
		}
		
		Collection<EObject> abstractSyntaxContents = abstractConcepts.values();
		for(EObject abstractSyntaxElement : abstractSyntaxContents) {
			if(abstractSyntaxElement instanceof EClass) {
				EClass eClass = (EClass) abstractSyntaxElement;
				List<EClass> eClassSuperClasses = eClass.getEAllSuperTypes();
				System.out.println("CLass : " + eClass.getName());
				System.out.println(eClassSuperClasses);
			}
		}
	}
	
	
	public List<Symbol> discoverConcreteConcepts(Definition concreteSyntaxModel) {
		List<Symbol> concreteSymbols = new ArrayList<Symbol>();
		
		System.out.println("Discovery of Concrete Symbols : ");
		List<NotationElement> concreteSyntaxElements = concreteSyntaxModel.getElements();
		
		for(NotationElement concreteSyntaxElement : concreteSyntaxElements) {
			if(concreteSyntaxElement instanceof Composite) {
				Composite compositeElement = (Composite) concreteSyntaxElement;
				String compositeElementId = compositeElement.getId();
				boolean isClassComposite = false;
				boolean isAttributeComposite = false;
				boolean isReferenceComposite = false;
				// Check the type of the composite (Class, Attribute, Reference)
				String[] splitCompositeElementId = compositeElementId.split("\\.");
				if(splitCompositeElementId.length == 3) {
					// Reference symbol
					String referenceClassName = splitCompositeElementId[0];
					String referenceReferenceName = splitCompositeElementId[1];
					String referenceAttributeName = splitCompositeElementId[2];
					isReferenceComposite = true;
					/*Symbol referenceSymbol = new ReferenceSymbol(compositeElementId, referenceClassName, referenceReferenceName, referenceAttributeName, compositeElement);
					concreteSymbols.add(referenceSymbol);
					
					System.out.println("Found Reference : " + compositeElementId + " -> " + compositeElement);*/
				} else if (splitCompositeElementId.length == 2){
					// Attribute symbol
					String attributeClassName = splitCompositeElementId[0];
					String attributeAttributeName = splitCompositeElementId[1];
					isAttributeComposite = true;
					
					/*Symbol referenceSymbol = new AttributeSymbol(compositeElementId, attributeClassName, attributeAttributeName, compositeElement);
					concreteSymbols.add(referenceSymbol);
					System.out.println("Found Attribute : " + compositeElementId + " -> " + compositeElement);*/
				} else if (splitCompositeElementId.length == 1) {
					//Class symbol
					isClassComposite = true;
					String className = compositeElementId;
					
					Symbol classSymbol = new ClassSymbol(compositeElementId, compositeElementId, compositeElement);
					concreteSymbols.add(classSymbol);
					System.out.println("Found Class : " + compositeElementId + " -> " + compositeElement);
				}
				
				// Discovering component contents
				TreeIterator<EObject> compositeContents = compositeElement.eAllContents();
				while(compositeContents.hasNext()) {
					EObject compositeContent = compositeContents.next();
					if(compositeContent instanceof AttributeValue) {
						AttributeValue compositeAttributeValue = (AttributeValue) compositeContent;
						String attributeValueId = compositeAttributeValue.getId();
						EAttribute attributeValueEAttribute = compositeAttributeValue.getAttribute();
						String attributeValueClassName = "";
						String attributeValueAttributeName = "";
						if(attributeValueEAttribute != null) {
							//TODO Check 
							attributeValueClassName = attributeValueEAttribute.getEContainingClass().getName();
							attributeValueAttributeName = attributeValueEAttribute.getName();
						} else {
							String[] splitAttributeValueId = attributeValueId.split("\\.");
							attributeValueClassName = splitAttributeValueId[0];
							attributeValueAttributeName = splitAttributeValueId[1];
						}
						Symbol attributeSymbol = new AttributeSymbol(attributeValueId, attributeValueClassName, attributeValueAttributeName, compositeAttributeValue);
						concreteSymbols.add(attributeSymbol);
						System.out.println("Found Attribute : " + attributeValueId + " -> " + compositeAttributeValue);
						
						
					} else if (compositeContent instanceof ReferenceValue) {
						ReferenceValue compositeReferenceValue = (ReferenceValue) compositeContent;
						String referenceValueId = compositeReferenceValue.getId();
						EAttribute referenceValueEAttribute = compositeReferenceValue.getAttribute();
						EReference referenceValueEReference = compositeReferenceValue.getReference();
						String referenceValueClassName = "";
						String referenceValueReferenceName = "";
						String referenceValueAttributeName = "";
						if(referenceValueEReference != null) {
							referenceValueClassName = referenceValueEReference.getEContainingClass().getName();
							referenceValueReferenceName = referenceValueEReference.getName();
							if(referenceValueEAttribute != null) {
								referenceValueAttributeName = referenceValueEAttribute.getName();
							}
							
						} else {
							String[] splitReferenceValueId = referenceValueId.split("\\.");
							if(splitReferenceValueId.length == 3) {
								referenceValueClassName = splitReferenceValueId[0];
								referenceValueReferenceName = splitReferenceValueId[1];
								referenceValueAttributeName = splitReferenceValueId[2];
							}
						}
						
						Symbol referenceSymbol = new ReferenceSymbol(referenceValueId, referenceValueClassName, referenceValueReferenceName, referenceValueAttributeName, compositeReferenceValue);
						concreteSymbols.add(referenceSymbol);
						System.out.println("Found Reference : " + referenceValueId + " -> " + compositeReferenceValue);
						
						
					} else if (compositeContent instanceof SyntaxOf) {
						SyntaxOf compositeSyntaxOf = (SyntaxOf) compositeContent;
						String syntaxOfId = compositeSyntaxOf.getId();
						EReference syntaxOfEReference = compositeSyntaxOf.getReference();
						if(syntaxOfEReference != null) {
							
						} else {
							String[] splitSyntaxOfId = syntaxOfId.split("\\.");
							if(splitSyntaxOfId.length == 3) {
								//Reference syntax
								String syntaxOfClassName = splitSyntaxOfId[0];
								if(isClassComposite) {
									syntaxOfClassName = compositeElementId;
								}
								String syntaxOfReferenceName = splitSyntaxOfId[1];
								String syntaxOfAttributeName = splitSyntaxOfId[2];
								String syntaxOfName = syntaxOfClassName + "." + syntaxOfReferenceName + "." + syntaxOfAttributeName;
								Symbol referenceSymbol = new ReferenceSymbol(syntaxOfName, syntaxOfClassName, syntaxOfReferenceName, syntaxOfAttributeName, compositeSyntaxOf);
								concreteSymbols.add(referenceSymbol);
								System.out.println("Found Reference : " + syntaxOfName + " -> " + compositeSyntaxOf);
								
							} else if(splitSyntaxOfId.length == 2) {
								// Attribute syntax
								String syntaxOfClassName = splitSyntaxOfId[0];
								String syntaxOfAttributeName = splitSyntaxOfId[1];
								
								if(isClassComposite) {
									syntaxOfClassName = compositeElementId;
								}
								String syntaxOfName = syntaxOfClassName + "." + syntaxOfAttributeName;
								Symbol attributeSymbol = new AttributeSymbol(syntaxOfName, syntaxOfClassName, syntaxOfAttributeName, compositeSyntaxOf);
								concreteSymbols.add(attributeSymbol);
								System.out.println("Found Attribute : " + syntaxOfName + " -> " + compositeSyntaxOf);
								
							} else if(splitSyntaxOfId.length == 1) {
								// Class syntax
							}
						}
						
					}
				}
			}
		}
		
		return concreteSymbols;
	}	
	
	
	
	private void resolveSyntaxOf() {
		
	}
	
	public ModelMapping getModelMapping(EPackage abstractSyntaxModel, Definition concreteSyntaxModel) {
		List<Concept> abstractConcepts = discoverAbstractConcepts(abstractSyntaxModel);
		//Map<String,EObject> abstractConcepts = discoverAbstractConcepts(abstractSyntaxModel);
		//resolveHeritage(abstractConcepts);
		//System.out.println(abstractConcepts);
		this.concreteSymbols = discoverConcreteConcepts(concreteSyntaxModel);
		//System.out.println(concreteSymbols);
		ModelMapping modelMapping = new ModelMapping(abstractConcepts, concreteSymbols);
		//System.out.println(modelMapping);
		return modelMapping;
	}
}
