package fr.inria.atlanmod.collaboro.ui.views.notation.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.util.DOMUtilities;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import fr.inria.atlanmod.collaboro.history.AbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement;
import fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.notation.AttributeValue;
import fr.inria.atlanmod.collaboro.notation.Composite;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.Keyword;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.notation.NotationPackage;
import fr.inria.atlanmod.collaboro.notation.ReferenceValue;
import fr.inria.atlanmod.collaboro.notation.SyntaxOf;
import fr.inria.atlanmod.collaboro.notation.TextualElement;
import fr.inria.atlanmod.collaboro.notation.Token;
import fr.inria.atlanmod.collaboro.ui.Controller;

public class SVGBuilder {
	// Default starting coordinates, fonts and so on
	private static int START_X = 10;
	private static int START_Y = 20;
	
	private static int VERTICAL_SEP = 23;
	private static int TAB = 20;
	private static int CHAR_SEP = 8;

	private static String DEFAULT_FONT_SIZE = "12px";
	private static String DEFAULT_FONT_FAMILY = "monospace";
	
	/**
	 * Inner class to represent shown boxes.
	 * 
	 * @author Javier Canovas (javier.canovas@inria.fr)
	 *
	 */
	private class Box {
		private int width, height;
		private int x, y;
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}

		public Box(int width, int height, int x, int y) {
			super();
			this.width = width;
			this.height = height;
			this.x = x;
			this.y = y;
		}

	}
	
	/**
	 * Builds the SVG for the corresponding notationElement. The representation is for
	 * the abstract syntax element.
	 * 
	 * @param notationElement
	 * @return
	 */
	public SVGDocument buildSVG(NotationElement notationElement) {
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
		SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);
		buildSVG(notationElement, doc, START_X, START_Y);
		return doc;
	}
	
	/**
	 * Builds the SVG for the corresponding notationElement and particularizes the 
	 * representation with the model element instance.
	 * 
	 * @param instanceModelElement
	 * @param notationElement
	 * @return
	 */
	public SVGDocument buildSVG(EObject instanceModelElement, NotationElement notationElement) {
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
		SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);
		buildSVG(instanceModelElement, notationElement, doc, START_X, START_Y);
		return doc;
	}
	
	/**
	 * Builds the SVG for the corresponding notationElement. The representation is for
	 * the abstract syntax element.
	 * 
	 * @param notationElement
	 * @param doc
	 * @param x
	 * @param y
	 */
	private Box buildSVG(NotationElement notationElement, SVGDocument doc, int x, int y) {
		Element svgRoot = doc.getDocumentElement();

		Box result = new Box(0, 0, x, y);
		if (notationElement instanceof Composite) {
			Composite composite = (Composite) notationElement;
			result.setX(x);
			int oldX = x;
			for(NotationElement subElement : composite.getSubElements()) {
				if (subElement instanceof Composite) {
					x = oldX + TAB;
					y = y + VERTICAL_SEP; 
				}

				Box subBox = buildSVG(subElement, doc, x, y);

				if ((subElement instanceof Composite) || (subElement instanceof SyntaxOf)) {
					x = oldX;
					y = y + subBox.getHeight(); 
					if(result.getWidth() < subBox.getWidth()) result.setWidth(subBox.getWidth());
					result.setHeight(result.getHeight() + subBox.getHeight());
				} else {
					x = x + subBox.getWidth();
					result.setWidth(result.getWidth() + subBox.getWidth());
					if(result.getHeight() < subBox.getHeight()) result.setHeight(subBox.getHeight());
				}
			}
		} else if (notationElement instanceof TextualElement) {
			TextualElement textualElement = (TextualElement) notationElement;

			Element text = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "text");
			text.setAttributeNS(null, "x", String.valueOf(x));
			text.setAttributeNS(null, "y", String.valueOf(y));
			text.setAttributeNS(null, "font-size", DEFAULT_FONT_SIZE);
			text.setAttributeNS(null, "font-family", DEFAULT_FONT_FAMILY);

			String value = "";
			if (textualElement instanceof Keyword) {
				Keyword keyword = (Keyword) notationElement; 
				text.setAttributeNS(null, "font-weight", "bold");
				text.setAttributeNS(null, "fill", "green");
				text.setAttributeNS(null, "stroke", "none");
				value = keyword.getId();
			} else if (textualElement instanceof Token) {
				Token token = (Token) notationElement;
				text.setAttributeNS(null, "fill", "black");
				text.setAttributeNS(null, "stroke", "none");
				value =  token.getId();
			} else if (textualElement instanceof AttributeValue) {
				AttributeValue attributeValue = (AttributeValue) notationElement;
				text.setAttributeNS(null, "fill", "orange");
				value = "<value of '" + attributeValue.getAttribute().getName() + "' attribute>";				
			} else if (textualElement instanceof ReferenceValue) {
				ReferenceValue referenceValue = (ReferenceValue) notationElement;
				text.setAttributeNS(null, "fill", "orange");
				value = "<value of '" + referenceValue.getReference().getName() + "' reference>";				
			}
			result.setWidth(value.length() * CHAR_SEP + CHAR_SEP);
			result.setHeight(VERTICAL_SEP);

			text.setTextContent(value);		
			svgRoot.appendChild(text);
		} else if (notationElement instanceof SyntaxOf) {
			SyntaxOf syntaxOf = (SyntaxOf) notationElement;

			Element text = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "text");
			text.setAttributeNS(null, "x", String.valueOf(x));
			text.setAttributeNS(null, "y", String.valueOf(y));
			text.setAttributeNS(null, "font-size", DEFAULT_FONT_SIZE);
			text.setAttributeNS(null, "font-family", DEFAULT_FONT_FAMILY);
			text.setAttributeNS(null, "fill", "blue");

			String value = "<syntax of '" + syntaxOf.getReference().getName() + "' reference>";
			text.setTextContent(value);	

			result.setWidth(value.length() * CHAR_SEP + CHAR_SEP);
			result.setHeight(VERTICAL_SEP);
			svgRoot.appendChild(text);
		}

		return result;
	}
	
	/**
	 * Builds the SVG for the corresponding notationElement and particularizes the 
	 * representation with the model element instance.
	 * 
	 * @param eObject
	 * @param notationElement
	 * @param doc
	 * @param x
	 * @param y
	 * @return
	 */
	public Box buildSVG(EObject eObject, NotationElement notationElement, SVGDocument doc, int x, int y) {
		Element svgRoot = doc.getDocumentElement();
		Box result = new Box(0, 0, x, y);

		if (notationElement instanceof Composite) {
			Composite composite = (Composite) notationElement;
			result.setX(x);
			int oldX = x;
			for(NotationElement subElement : composite.getSubElements()) {
				if (subElement instanceof Composite) {
					x = oldX + TAB;
					y = y + VERTICAL_SEP; 
				}

				Box subBox = buildSVG(eObject, subElement, doc, x, y);

				if ((subElement instanceof Composite) || (subElement instanceof SyntaxOf)) {
					x = oldX;
					y = y + subBox.getHeight(); 
					if(result.getWidth() < subBox.getWidth()) result.setWidth(subBox.getWidth());
					result.setHeight(result.getHeight() + subBox.getHeight());
				} else {
					x = x + subBox.getWidth();
					result.setWidth(result.getWidth() + subBox.getWidth());
					if(result.getHeight() < subBox.getHeight()) result.setHeight(subBox.getHeight());
				}
			}
		} else if (notationElement instanceof TextualElement) {
			TextualElement textualElement = (TextualElement) notationElement;

			Element text = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "text");
			text.setAttributeNS(null, "x", String.valueOf(x));
			text.setAttributeNS(null, "y", String.valueOf(y));
			text.setAttributeNS(null, "font-size", DEFAULT_FONT_SIZE);
			text.setAttributeNS(null, "font-family", DEFAULT_FONT_FAMILY);

			String value = "";

			if (textualElement instanceof Keyword) {
				Keyword keyword = (Keyword) notationElement; 
				text.setAttributeNS(null, "font-weight", "bold");
				text.setAttributeNS(null, "fill", keyword.getFill().getLiteral());
				text.setAttributeNS(null, "stroke", "none");
				value = keyword.getId();
			} else if (textualElement instanceof Token) {
				Token token = (Token) notationElement;
				text.setAttributeNS(null, "fill", token.getFill().getLiteral());
				text.setAttributeNS(null, "stroke", "none");
				value = token.getId();
			} else if (textualElement instanceof AttributeValue) {
				AttributeValue attributeValue = (AttributeValue) notationElement;
				text.setAttributeNS(null, "fill", attributeValue.getFill().getLiteral());

				EAttribute eAttribute = attributeValue.getAttribute();

				value = convert(eObject.eGet(eObject.eClass().getEStructuralFeature(eAttribute.getName())));
				//				value = convert(eObject.eGet(eAttribute));
			} else if (textualElement instanceof ReferenceValue) {
				ReferenceValue referenceValue = (ReferenceValue) notationElement;
				text.setAttributeNS(null, "fill", referenceValue.getFill().getLiteral());

				EReference eReference = referenceValue.getReference();
				EAttribute eAttribute = referenceValue.getAttribute();
				String separator = referenceValue.getSeparator();

				Object referredObjs = eObject.eGet(eObject.eClass().getEStructuralFeature(eReference.getName()));
				//				Object referredObjs = eObject.eGet(eReference);
				if (referredObjs instanceof EList) {
					EList<EObject> eReferenceList = (EList<EObject>) referredObjs;
					for(EObject elementList : eReferenceList) {
						//						Object attributeValue = elementList.eGet(eAttribute);
						Object attributeValue = elementList.eGet(elementList.eClass().getEStructuralFeature(eAttribute.getName()));
						value += convert(attributeValue);
						if(eReferenceList.indexOf(elementList) != eReferenceList.size() - 1) {
							value += separator;
						}
					}
				} else if (referredObjs instanceof EObject) {
					EObject elementList = (EObject) referredObjs;
					//					Object attributeValue = elementList.eGet(eAttribute);
					Object attributeValue = elementList.eGet(elementList.eClass().getEStructuralFeature(eAttribute.getName()));
					value += convert(attributeValue);					
				}			
			}

			result.setWidth(value.length() * CHAR_SEP + CHAR_SEP);
			result.setHeight(VERTICAL_SEP);

			text.setTextContent(value);		
			svgRoot.appendChild(text);
		} else if (notationElement instanceof SyntaxOf) {
			SyntaxOf syntaxOf = (SyntaxOf) notationElement;

			EReference eReference = syntaxOf.getReference();

			Object referredObjs = eObject.eGet(eObject.eClass().getEStructuralFeature(eReference.getName()));
			//			Object referredObjs = eObject.eGet(eReference);
			if (referredObjs instanceof EList) {
				EList<EObject> eReferenceList = (EList<EObject>) referredObjs;
				if(eReferenceList.size() > 0) {
					int oldX = x;
					for(EObject elementList : eReferenceList) {
						NotationElement subNotationElement = Controller.INSTANCE.getNotation(elementList.eClass());
						if(subNotationElement != null) {
							Box subBox = buildSVG(elementList, subNotationElement, doc, x, y);
							x = oldX;
							y = y + subBox.getHeight(); 

							if(result.getWidth() < subBox.getWidth()) result.setWidth(subBox.getWidth());
							result.setHeight(result.getHeight() + subBox.getHeight());
						}
					}
				}
			}
		}
		return result;
	}
	

	/**
	 * Obtains the string representation of the value
	 * 
	 * @param value
	 * @return
	 */
	protected String convert(Object value) {
		String result = "ND";

		if (value instanceof String) {
			result = (String) value;
		} else if(value instanceof Float) {
			
		} else if (value instanceof EEnumLiteral) {
			EEnumLiteral literal = (EEnumLiteral) value;
			result = literal.toString();
		}
		return result;
	}

	private void serialize(SVGDocument image, String location) {
		try {
			String replaceFirst = location.substring(6);
			FileWriter fileWriter = new FileWriter(replaceFirst);
			DOMUtilities.writeDocument(image, fileWriter);
			fileWriter.close();
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Looks for a ConcreteSyntaxElement linked with the Eclassifier received
	 * 
	 * @param modelElement
	 * @return
	 */
	public NotationElement getNotation(History history, int historyTracked, int versionTracked, EClassifier modelElement) {
		List<Proposal> proposals = history.getHistories().get(historyTracked).getVersions().get(versionTracked).getProposals();
		for(Proposal proposal : proposals) {
			List<Solution> solutions = proposal.getSols();
			for(Solution solution : solutions) {
				List<ModelChange> changes = solution.getChanges();
				for(ModelChange change : changes) {
					if(change.getReferredElement() instanceof AbstractSyntaxElement && change.getTarget() instanceof ConcreteSyntaxElement) {
						EModelElement refModelElement = null;
						if (change.getReferredElement() instanceof ExistingAbstractSyntaxElement) {
							refModelElement = ((ExistingAbstractSyntaxElement) change.getReferredElement()).getElement();
						}
						if (change.getReferredElement() instanceof NewAbstractSyntaxElement) {
							refModelElement = ((NewAbstractSyntaxElement) change.getReferredElement()).getElement();
						}
						if(refModelElement != null) {
							if (refModelElement instanceof EClassifier) {
								EClassifier classifier = (EClassifier) refModelElement;
								if(classifier.getName().equals(modelElement.getName())) {
									ConcreteSyntaxElement concreteElement = (ConcreteSyntaxElement) change.getTarget();
									NotationElement result = concreteElement.getElement();
									return result;
								}
							}
						} 
					} 
				}
			}
		}

		return null;
	}
	
	public static void main(String[] args) {
		SVGBuilder builder = new SVGBuilder();

		File ecoreModelFile = new File("C:\\Users\\useradm\\git\\collaboro\\examples\\fr.inria.atlanmod.collaboro.examples.workflow\\model\\ModiscoWorkflow.ecore");
		File notationModelFile = new File("C:\\Users\\useradm\\git\\collaboro\\examples\\fr.inria.atlanmod.collaboro.examples.workflow\\model\\ModiscoWorkflow.notation");
		File historyModelFile = new File("C:\\Users\\useradm\\git\\collaboro\\examples\\fr.inria.atlanmod.collaboro.examples.workflow\\model\\ModiscoWorkflow.history");
		File modelExample = new File("C:\\Users\\useradm\\git\\collaboro\\examples\\fr.inria.atlanmod.collaboro.examples.workflow\\examples\\example1.xmi");

		// Preparing the resource
		ResourceSet rset = null;
		rset = new ResourceSetImpl();
		rset.getPackageRegistry().put(HistoryPackage.eNS_URI, HistoryPackage.eINSTANCE);
		rset.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);
		rset.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new EcoreResourceFactoryImpl());
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("history", new EcoreResourceFactoryImpl());
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("notation", new EcoreResourceFactoryImpl());
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

		// Loading the history model
		Resource res1 = rset.getResource(URI.createFileURI(historyModelFile.getAbsolutePath()), true);
		try {
			res1.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		History historyModel = (History) res1.getContents().get(0);
		
		// Loading the ecore model (abstract syntax)
		Resource res2 = rset.getResource(URI.createFileURI(ecoreModelFile.getAbsolutePath()), true);
		try {
			res2.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		EPackage ecoreModel = (EPackage) res2.getContents().get(0);
		
		// Loading the notation model (concrete syntax)
		Resource res3 = rset.getResource(URI.createFileURI(notationModelFile.getAbsolutePath()), true);
		try {
			res3.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Definition notationModel = (Definition) res3.getContents().get(0);
		
		// Loading model
		Resource res4 = rset.getResource(URI.createFileURI(modelExample.getAbsolutePath()), true);
		try {
			res4.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		EObject instanceModelElement = res4.getContents().get(0);

		EClassifier modelElement = instanceModelElement.eClass();
		NotationElement notationElement = builder.getNotation(historyModel, 0, 0, modelElement);
		SVGDocument document = builder.buildSVG(instanceModelElement, notationElement);
		builder.serialize(document, "test.svg");
	}
}
