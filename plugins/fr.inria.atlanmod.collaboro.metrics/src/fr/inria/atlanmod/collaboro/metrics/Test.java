package fr.inria.atlanmod.collaboro.metrics;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import fr.inria.atlanmod.collaboro.metrics.impl.MetricsFactoryImpl;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.NotationPackage;

public class Test {
	
	public static void main(String[] args) {
		
		ResourceSet rset = new ResourceSetImpl();
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new EcoreResourceFactoryImpl());	
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());	
		rset.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);
		rset.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		
		String exampleTransportPath = "C:\\Users\\rboncorps\\Projects\\Dev\\collaboro\\examples\\fr.inria.atlanmod.collaboro.examples.transport\\model\\";
		
		Resource abstractSyntaxModel = rset.getResource(URI.createFileURI(exampleTransportPath + "transport.ecore"), true);
		try {
			
			abstractSyntaxModel.load(null);
			System.out.println("abstract loaded");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Resource concreteSyntaxModel = rset.getResource(URI.createFileURI(exampleTransportPath + "transport-graphical.notation1.xmi"), true);
		try {
			
			concreteSyntaxModel.load(null);
			System.out.println("concrete loaded");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EPackage abstractSyntax = (EPackage)abstractSyntaxModel.getContents().get(0);
		Definition concreteSyntax = (Definition) concreteSyntaxModel.getContents().get(0);
		MetricsFactory metricFactory = new MetricsFactoryImpl(abstractSyntax, concreteSyntax);
		List<ConcreteSyntaxMetric> metrics = metricFactory.getConcreteSyntaxMetrics();
		for(ConcreteSyntaxMetric metric : metrics) {
			List<MetricResult> metricResults = metric.execute();
			for(MetricResult metricResult : metricResults) {
				digestMetricResult(metric,metricResult);
			}
		}
		
		
		// Retrieve all concepts from Abstract Syntax
	
//		
//		Map<String,List<String>> absMap = new HashMap<String,List<String>>();
//		
//		
//		TreeIterator<EObject> abstractSyntaxContents = abstractSyntaxModel.getAllContents();
//		List<EClass> eClassConcepts = new ArrayList<EClass>();
//		Map<EClass,List<EAttribute>> eAttributeConcepts = new HashMap<EClass,List<EAttribute>>();
//		Map<EClass,List<EReference>> eReferenceConcepts = new HashMap<EClass,List<EReference>>();
//		
//		
//		
//		while(abstractSyntaxContents.hasNext()) {
//			EObject abstractElement = abstractSyntaxContents.next();
//			//System.out.println(abstractElement);
//			if(abstractElement instanceof EClass) {
//				EClass eClass = (EClass) abstractElement;
//				
//				eClassConcepts.add(eClass);
//				eAttributeConcepts.put(eClass, new ArrayList<EAttribute>());
//				//eClassConceptsNames.add(eClass);
//				/*String eClassName = eClass.getName();
//				System.out.println(eClass.getName());
//				System.out.println(eClass.isAbstract());
//				System.out.println(eClass.isInterface());
//				System.out.println(eClass.getESuperTypes());
//				System.out.println(eClass.getEAllSuperTypes());
//				System.out.println("--------------------------------------");*/
//				List<EAttribute> eClassAttributes = eClass.getEAllAttributes();
//				if(!eClassAttributes.isEmpty()) {
//					eAttributeConcepts.put(eClass, eClassAttributes);
//				}
//			} else if(abstractElement instanceof EReference) {
//				EReference eReference = (EReference) abstractElement;
//				EClass eReferenceContainingClass = eReference.getEContainingClass();
//				if(eReferenceConcepts.get(eReferenceContainingClass) == null) {
//					eReferenceConcepts.put(eReferenceContainingClass, new ArrayList<EReference>());
//				}
//				List<EReference> abstractElementReferences = eReferenceConcepts.get(eReferenceContainingClass);
//				abstractElementReferences.add(eReference);
//				
//				/*System.out.println(eReference.getName());
//				System.out.println(eReference.getLowerBound());
//				System.out.println(eReference.getUpperBound());
//				System.out.println(eReference.isContainment());
//				System.out.println(eReference.isContainer());
//				System.out.println(eReference.isMany());
//				System.out.println(eReference.getEContainingClass());
//				System.out.println(eReference.getEReferenceType());
//				System.out.println(eReference.getEOpposite());*/
//			} else {
//				
//			}
//		}
//		
//		List<String> classSymbols = new ArrayList<String>();
//		Map<String,List<String>> attributeSymbols = new HashMap<String,List<String>>();
//		Map<String,List<String>> referenceSymbols = new HashMap<String,List<String>>();
//		
//		TreeIterator<EObject> concreteSyntaxContents = concreteSyntaxModel.getAllContents();
//		while(concreteSyntaxContents.hasNext()) {
//			EObject concreteElement = concreteSyntaxContents.next();
//			if(concreteElement instanceof SyntaxOf) {
//				
//			} else if(concreteElement instanceof AttributeValue) {
//				AttributeValue attributeValueElement = (AttributeValue) concreteElement;
//				String attributeClassName = "";
//				String attributeName = "";
//				if(attributeValueElement.getAttribute() != null) {
//					EAttribute abstractAttributeElement = attributeValueElement.getAttribute();
//					
//				} else {
//					EObject attributeValueSymbol = attributeValueElement.eContainer();
//					String attributeValueId = attributeValueElement.getId();
//					String[] splitAttributeValueId = attributeValueId.split("\\.");
//					if(splitAttributeValueId.length == 2) {
//						attributeClassName = splitAttributeValueId[0];
//						attributeName = splitAttributeValueId[1];
//						System.out.println("attribute : " + attributeClassName + " . " + attributeName);
//					}
//				}
//				if(attributeSymbols.get(attributeClassName) == null) {
//					attributeSymbols.put(attributeClassName, new ArrayList<String>());
//				}
//				attributeSymbols.get(attributeClassName).add(attributeName);
//				
//			} else if(concreteElement instanceof ReferenceValue) {
//				
//			} else if(concreteElement instanceof Composite) {
//				Composite compositeElement = (Composite) concreteElement;
//				if(compositeElement.eContainer() instanceof Definition) {
//					// Primary Symbol
//					List<NotationElement> compositeSubElements = compositeElement.getSubElements();
//					Integer compositeSyntaxOfCounter = 0;
//					for(NotationElement compositeSubElement : compositeSubElements) {
//						if(compositeSubElement instanceof SyntaxOf) {
//							compositeSyntaxOfCounter++;
//						}
//					}
//					if(compositeSyntaxOfCounter == compositeSubElements.size()) {
//						// Structural Component
//					} else {
//						String symbolName = compositeElement.getId();
//						String[] splitSymbolName = symbolName.split("\\.");
//						if(splitSymbolName.length < 2) {
//							classSymbols.add(symbolName);
//						} else {
//							String symbolClassName = splitSymbolName[0];
//							String referenceName = splitSymbolName[1];
//							if(referenceSymbols.get(symbolClassName) == null) {
//								referenceSymbols.put(symbolClassName, new ArrayList<String>());
//							}
//							referenceSymbols.get(symbolClassName).add(referenceName);
//						}
//					}
//					
//				} else {
//					
//				}
//			}	
//		}
//		
//		System.out.println(classSymbols);
//		System.out.println(attributeSymbols);
//		System.out.println(referenceSymbols);
//
//		// Symbol deficit
//		Double abstractAttributeConcepts = 0.0;
//		Double notRepresentedAttributes = 0.0;
//		for(EClass eClass : eClassConcepts) {
//			abstractAttributeConcepts++;
//			String eClassName = eClass.getName();
//			if(!classSymbols.contains(eClassName)) {
//				System.out.println("AbstractClass : " + eClassName + " -> Representation : KO");
//				notRepresentedAttributes++;
//			} else {
//				System.out.println("AbstractClass : " + eClassName + " -> Representation : OK");
//			}
//			List<EAttribute> eClassAttributes = eAttributeConcepts.get(eClass);
//			if(eClassAttributes != null) {
//				for(EAttribute eClassAttribute : eClassAttributes) {
//					abstractAttributeConcepts++;
//					String eAttributeName = eClassAttribute.getName();
//					if(attributeSymbols.containsKey(eClassName)) {
//						if(attributeSymbols.get(eClassName).contains(eAttributeName)) {
//							System.out.println("Abstract Attribute : " + eClassName  +"."+ eAttributeName + " -> Representation : OK");
//							
//						} else {
//							System.out.println("Abstract Attribute : " + eClassName  +"."+ eAttributeName + " -> Representation : KO");
//							notRepresentedAttributes++;
//						}
//					} else {
//						System.out.println("Abstract Attribute : " + eClassName  +"."+ eAttributeName + " -> Representation : KO");
//						notRepresentedAttributes++;
//					}
//				}
//			}
//			List<EReference> eClassReferences = eReferenceConcepts.get(eClass);
//			if(eClassReferences != null) {
//				for(EReference eClassReference : eClassReferences) {
//					abstractAttributeConcepts++;
//					String eReferenceName = eClassReference.getName();
//					if(referenceSymbols.containsKey(eClassName)) {
//						if(referenceSymbols.get(eClassName).contains(eReferenceName)) {
//							System.out.println("Abstract Reference : " + eClassName + "." + eReferenceName + " -> Representation : OK");
//						} else {
//							System.out.println("Abstract Reference : " + eClassName + "." + eReferenceName + " -> Representation : KO");
//							notRepresentedAttributes++;
//						}
//					} else {
//						System.out.println("Abstract Reference : " + eClassName + "." + eReferenceName + " -> Representation : KO");
//						notRepresentedAttributes++;
//					}
//				}
//			}
//			
//		}
//		
//		System.out.println(notRepresentedAttributes);
//		System.out.println(abstractAttributeConcepts);
//		float symbolDeficit = (float)((notRepresentedAttributes*100)/abstractAttributeConcepts);
//		System.out.println("Symbol deficit : " + symbolDeficit);
//		
//		// Symbol redundancy
//		for(EClass eClass : eClassConcepts) {
//			String eClassName = eClass.getName();
//			// Check Class symbol
//			if(Collections.frequency(classSymbols, eClassName) > 1) {
//				System.out.println("Abstract Class : " + eClassName + " -> Overload : " + Collections.frequency(classSymbols, eClassName));
//			}
//			// Check Attribute symbol
//			List<EAttribute> eClassAttributes = eAttributeConcepts.get(eClass);
//			if(eClassAttributes != null) {
//				for(EAttribute eClassAttribute : eClassAttributes) {
//					String eAttributeName = eClassAttribute.getName();
//					if(attributeSymbols.containsKey(eClassName)) {
//						if(attributeSymbols.get(eClassName).contains(eAttributeName)) {
//							if(Collections.frequency(attributeSymbols.get(eClassName), eAttributeName) > 1) {
//								System.out.println("Abstract Attribute : " + eClassName + "." + eAttributeName + " -> Overload : " + Collections.frequency(attributeSymbols.get(eClassName), eAttributeName));
//							}
//						}
//					}
//				}
//			}
//			List<EReference> eClassReferences = eReferenceConcepts.get(eClass);
//			if(eClassReferences != null) {
//				for(EReference eClassReference : eClassReferences) {
//					String eReferenceName = eClassReference.getName();
//					if(referenceSymbols.containsKey(eClassName)) {
//						if(referenceSymbols.get(eClassName).contains(eReferenceName)) {
//							if(Collections.frequency(referenceSymbols.get(eClassName), eReferenceName) > 1) {
//								System.out.println("Abstract Reference : " + eClassName + "." + eReferenceName + " -> OverLoad : " + Collections.frequency(referenceSymbols.get(eClassName), eReferenceName));
//							}
//						}
//					}
//				}
//			}
//			
//			
//			
//			// Validate concrete syntax
//			
//		}
		
		
				
	}
	
	private static void digestMetricResult(Metric metric, MetricResult metricResult) {
		String rationale = "Metric: " + metric.getName() + "\n"
				+ "Description: " + metric.getDescription() + "\n"
				+ "Reason: " + metricResult.getReason();
		String referredElements = "";
		for(ReferredElement referredElement : metricResult.getReferredElements()) {
			referredElements += referredElement.getName() + ",";
		}
		if(!referredElements.equals(""))
			referredElements = referredElements.substring(0, referredElements.length() - 1);
		
		System.out.println(rationale + "\n " + referredElements);
	
	}
	
}
