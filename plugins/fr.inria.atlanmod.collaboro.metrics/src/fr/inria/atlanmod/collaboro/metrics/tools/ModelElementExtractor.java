package fr.inria.atlanmod.collaboro.metrics.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.collaboro.metrics.symbol.Symbol;
import fr.inria.atlanmod.collaboro.notation.AttributeValue;
import fr.inria.atlanmod.collaboro.notation.Composite;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.GraphicalElement;
import fr.inria.atlanmod.collaboro.notation.Label;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.notation.ReferenceValue;
import fr.inria.atlanmod.collaboro.notation.SyntaxOf;

public class ModelElementExtractor {
	
	private List<EObject> abstractSyntaxElements;
	private List<NotationElement> concreteSyntaxElements;
	
	public Map<String,EObject> discoverAbstractConcepts(EPackage abstractSyntaxModel) {
		Map<String,EObject> abstractConcepts = new HashMap<String,EObject>();
		
		List<EObject> abstractSyntaxContents = abstractSyntaxModel.eContents();
		for(EObject abstractSyntaxElement : abstractSyntaxContents) {
			if(abstractSyntaxElement instanceof EClass) {
				EClass eClassElement = (EClass) abstractSyntaxElement;
				String eClassElementName = eClassElement.getName();
				abstractConcepts.put(eClassElementName,eClassElement);
				//Add EClass
				List<EAttribute> eClassAttributes = eClassElement.getEAllAttributes();
				for(EAttribute eClassAttribute : eClassAttributes) {
					String eClassAttributeName = eClassAttribute.getName();
					abstractConcepts.put(eClassElementName + "." + eClassAttributeName,eClassAttribute);
				}
				List<EReference> eClassReferences = eClassElement.getEAllReferences();
				for(EReference eClassReference : eClassReferences) {
					String eClassReferenceName = eClassReference.getName();
					abstractConcepts.put(eClassElementName + "." + eClassReferenceName, eClassReference);
				}
			}
		}
		
		return abstractConcepts;
	}
	
	public List<Symbol> discoverConcreteConcepts(Definition concreteSyntaxModel) {
		List<Symbol> concreteSymbols = new ArrayList<Symbol>();
		
		List<NotationElement> concreteSyntaxElements = concreteSyntaxModel.getElements();
		for(NotationElement concreteSyntaxElement : concreteSyntaxElements) {
			if (concreteSyntaxElement instanceof Composite) {
				// this should be a classSymbol or a referenceSymbol
				Composite compositeElement = (Composite) concreteSyntaxElement;
				String compositeName = compositeElement.getId();
				String[] splitCompositeName = compositeName.split("\\.");
				if(splitCompositeName.length > 1) {
					// the name implies a reference
					Symbol referenceSymbol = new Symbol(compositeName,compositeElement);
					concreteSymbols.add(referenceSymbol);
				} else {
					// the name implies a class
					Symbol classSymbol = new Symbol(compositeName, compositeElement);
					concreteSymbols.add(classSymbol);
				}
				TreeIterator<EObject> compositeContents = compositeElement.eAllContents();
				while(compositeContents.hasNext()) {
					EObject compositeContent = compositeContents.next();
					if(compositeContent instanceof AttributeValue) {
						AttributeValue compositeAttributeValue = (AttributeValue) compositeContent;
						String compositeAttributeValueName = compositeAttributeValue.getId();
						if( compositeAttributeValue.eContainer() instanceof Label) {
							Symbol attributeSymbol = new Symbol(compositeAttributeValueName, (Label)compositeAttributeValue.eContainer());
							concreteSymbols.add(attributeSymbol);
						} else {
							Symbol attributeSymbol = new Symbol(compositeAttributeValueName,compositeAttributeValue);
							concreteSymbols.add(attributeSymbol);
						}
					} else if(compositeContent instanceof ReferenceValue) {
						
					} else if(compositeContent instanceof SyntaxOf) {
						
					} else if(compositeContent instanceof GraphicalElement) {
						
					} else if (compositeContent instanceof Composite) {
						
					}
				}
			} else if (concreteSyntaxElement instanceof AttributeValue) {
				
			} else if (concreteSyntaxElement instanceof ReferenceValue) {
				
			} else if (concreteSyntaxElement instanceof SyntaxOf) {
				
			}
		}
		return concreteSymbols;
	}

	public List<EClass> getClassConcepts(EPackage abstractSyntaxModel) {
		List<EClass> abstractSyntaxClassConcepts = new ArrayList<EClass>();
		
		TreeIterator<EObject> abstractSyntaxContents = abstractSyntaxModel.eAllContents();
		while(abstractSyntaxContents.hasNext()) {
			EObject abstractSyntaxElement = abstractSyntaxContents.next();
			if(abstractSyntaxElement instanceof EClass) {
				EClass eClassElement = (EClass) abstractSyntaxElement;
				abstractSyntaxClassConcepts.add(eClassElement);
			}
		}
		
		return abstractSyntaxClassConcepts;
	}
	
	public List<EAttribute> getAttributeConcepts(EPackage abstractSyntaxModel) {
		List<EClass> abstractSyntaxClassConcepts = this.getClassConcepts(abstractSyntaxModel);
		List<EAttribute> abstractSyntaxAttributeConcepts = new ArrayList<EAttribute>();
		
		for(EClass eClassElement : abstractSyntaxClassConcepts) {
			List<EAttribute> eClassAttributes = eClassElement.getEAllAttributes();
			abstractSyntaxAttributeConcepts.addAll(eClassAttributes);
		}
		
		return abstractSyntaxAttributeConcepts;
	}
	
	public List<EReference> getReferenceConcepts(EPackage abstractSyntaxModel) {
		List<EReference> abstractSyntaxReferenceConcepts = new ArrayList<EReference>();
		
		TreeIterator<EObject> abstractSyntaxElements = abstractSyntaxModel.eAllContents();
		while(abstractSyntaxElements.hasNext()) {
			EObject abstractSyntaxElement = abstractSyntaxElements.next();
			if(abstractSyntaxElement instanceof EReference) {
				EReference eReferenceElement = (EReference) abstractSyntaxElement;
				abstractSyntaxReferenceConcepts.add(eReferenceElement);
			}
		}
		
		return abstractSyntaxReferenceConcepts;
	}
	
	public List<Composite> getClassSymbols(Definition concreteSyntaxModel) {
		List<Composite> concreteSyntaxClassSymbols = new ArrayList<Composite>();
		
		TreeIterator<EObject> concreteSyntaxElements = concreteSyntaxModel.eAllContents();
		while(concreteSyntaxElements.hasNext()) {
			EObject concreteSyntaxElement = concreteSyntaxElements.next();
			if(concreteSyntaxElement instanceof Composite) {
				Composite compositeElement = (Composite) concreteSyntaxElement; 
				if(concreteSyntaxElement.eContainer() instanceof Definition) {
					// Primary Symbol
					String compositeName = compositeElement.getId();
					String[] splitCompositeName = compositeName.split("\\.");
					if(splitCompositeName.length == 1) {
						// Id is a class name
						concreteSyntaxClassSymbols.add(compositeElement);
					}
				}
			}
		}
		
		return concreteSyntaxClassSymbols;
	}
	
	public List<NotationElement> getAttributeSymbols(Definition concreteSyntaxModel) {
		List<NotationElement> concreteSyntaxAttributeSymbols = new ArrayList<NotationElement>();
		
		TreeIterator<EObject> concreteSyntaxElements = concreteSyntaxModel.eAllContents();
		while(concreteSyntaxElements.hasNext()) {
			EObject concreteSyntaxElement = concreteSyntaxElements.next();
			if(concreteSyntaxElement instanceof AttributeValue) {
				AttributeValue attributeElement = (AttributeValue) concreteSyntaxElements;
				if(attributeElement.eContainer() instanceof Label) {
					// This Label is the attribute symbol
					concreteSyntaxAttributeSymbols.add((Label)attributeElement.eContainer());
				} else {
					concreteSyntaxAttributeSymbols.add(attributeElement);
				}
			}
		}
		
		return concreteSyntaxAttributeSymbols;
	}
	
	public List<NotationElement> getReferenceSymbols(Definition concreteSyntaxModel) {
		List<NotationElement> concreteSyntaxReferenceSymbols = new ArrayList<NotationElement>();
		
		TreeIterator<EObject> concreteSyntaxElements = concreteSyntaxModel.eAllContents();
		while(concreteSyntaxElements.hasNext()) {
			EObject conreteSyntaxElement = concreteSyntaxElements.next();
		}
		return concreteSyntaxReferenceSymbols;
	}
	
	public ModelMapping getModelMapping(EPackage abstractSyntaxModel, Definition concreteSyntaxModel) {
		Map<String,EObject> abstractConcepts = discoverAbstractConcepts(abstractSyntaxModel);
		//System.out.println(abstractConcepts);
		List<Symbol> concreteSymbols = discoverConcreteConcepts(concreteSyntaxModel);
		//System.out.println(concreteSymbols);
		ModelMapping modelMapping = new ModelMapping(abstractConcepts, concreteSymbols);
		//System.out.println(modelMapping);
		return modelMapping;
	}
}
