/*******************************************************************************
 * Copyright (c) 2008, 2012
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Javier Canovas (javier.canovas@inria.fr) 
 *******************************************************************************/

package fr.inria.atlanmod.collaboro.ui.views;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import fr.inria.atlanmod.collaboro.notation.AttributeValue;
import fr.inria.atlanmod.collaboro.notation.Composite;
import fr.inria.atlanmod.collaboro.notation.Keyword;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.notation.ReferenceValue;
import fr.inria.atlanmod.collaboro.notation.SyntaxOf;
import fr.inria.atlanmod.collaboro.notation.TextualElement;
import fr.inria.atlanmod.collaboro.notation.Token;
import fr.inria.atlanmod.collaboro.ui.Controller;


/**
 * This view renders the notation for abstract/concrete elemetns and show it in a view.
 * 
 * @author Javier Canovas (javier.canovas@inria.fr)
 *
 */
public class NotationView extends ViewPart implements ISelectionListener {
	public static final String ID = "atlanmod.collaboro.ui.notationView";

	// Default starting coordinates, fonts and so on
	private static int START_X = 10;
	private static int START_Y = 20;

	private static int VERTICAL_SEP = 23;
	private static int TAB = 20;
	private static int CHAR_SEP = 8;

	private static String DEFAULT_FONT_SIZE = "12px";
	private static String DEFAULT_FONT_FAMILY = "monospace";

	// The composite showing the notation
	private NotationComposite notation;


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
	 * Updates the view
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(part.getSite().getId().equals(Controller.ECORE_PLUGIN_ID)) {
			TreeSelection treeSelection = (TreeSelection) selection;
			Object element = treeSelection.getFirstElement();

			if (element instanceof EClassifier) {
				EClassifier modelElement = (EClassifier) element;
				updateView(modelElement);
			}
		}

		if(part.getSite().getId().equals(Controller.PACKAGE_EXPLORER_PLUGIN_ID)) {
			TreeSelection treeSelection = (TreeSelection) selection;
			Object element = treeSelection.getFirstElement();
			if (element instanceof File) {
				File file = (File) element;
				java.io.File ioFile = new java.io.File(file.getLocation().toOSString());

				if(ioFile.isFile() && (ioFile.getAbsolutePath().substring(ioFile.getAbsolutePath().lastIndexOf("."), ioFile.getAbsolutePath().length()).equals("." + Controller.MODEL_EXTENSION))) {
					EObject eObject = Controller.INSTANCE.loadModel(ioFile);
					if(eObject != null) {
						updateView(eObject);
					} 
				}
			}
		}	

		if(part.getSite().getId().equals(Controller.REFLECTIVE_EDITOR_PLUGIN_ID)) {	
			TreeSelection treeSelection = (TreeSelection) selection;		
			Object element = treeSelection.getFirstElement();
			if (element instanceof XMIResourceImpl) {
				XMIResourceImpl factory = (XMIResourceImpl) element;
				URI uri = factory.getURI(); 
				java.io.File ioFile = new java.io.File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().toString() + uri.toPlatformString(true));
				if(ioFile.isFile() && (ioFile.getAbsolutePath().substring(ioFile.getAbsolutePath().lastIndexOf("."), ioFile.getAbsolutePath().length()).equals("." + Controller.MODEL_EXTENSION))) {
					EObject eObject = Controller.INSTANCE.loadModel(ioFile);
					if(eObject != null) {
						updateView(eObject);
					} 
				}
			}
		}
		
		if(part.getSite().getId().equals(Controller.NOTATION_EDITOR_PLUGIN_ID)) {
			Controller.INSTANCE.inNotation();
		} else {
			Controller.INSTANCE.outNotation();
		}
	}

	/**
	 * Debug method. Prints out the generated SVG
	 * 
	 * @param eObject
	 * @param indent
	 */
	public void printModel(EObject eObject, String indent) {
		System.out.println("Instance " + eObject.eClass().getName());
		for(EStructuralFeature structuralFeature : eObject.eClass().getEAllStructuralFeatures()) {
			System.out.println("  Feature: " + structuralFeature.eClass().getName() + " " + structuralFeature.getName() +  " value " + eObject.eGet(structuralFeature));
		}
	}

	@Override
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {
		parent.setLayout(new FillLayout());
		notation = new NotationComposite(parent, createTestImage());
		parent.layout();

		getSite().getPage().addSelectionListener(this); 
	}


	@Override
	public void setFocus() {

	}

	/**
	 * Updates the vied with the notation of the modelElement (abstract syntax element).
	 * 
	 * @param modelElement
	 */
	public void updateView(EClassifier modelElement) {
		NotationElement notationElement = Controller.INSTANCE.getNotation(modelElement);
		SVGDocument svgImage = null;
		if(notationElement == null) {
			svgImage = createNoSyntaxImage();
		} else {
			svgImage = buildSVG(notationElement);		
		}
		notation.setSVGDocument(svgImage);
		notation.layout(true);
	}


	/**
	 * Updates the view with the notation of the modelElement instance (instance of 
	 * the abstract syntax element).
	 * 
	 * @param instanceModelElement
	 */
	public void updateView(EObject instanceModelElement) {
		EClassifier modelElement = instanceModelElement.eClass();
		NotationElement notationElement = Controller.INSTANCE.getNotation(modelElement);
		SVGDocument svgImage = null;
		if(notationElement == null) {
			svgImage = createNoSyntaxImage();
		} else {
			svgImage = buildSVG(instanceModelElement, notationElement);		
		}
		notation.setSVGDocument(svgImage);
		notation.layout(true);
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
//				text.setAttributeNS(null, "fill", keyword.getFill().getLiteral());
				text.setAttributeNS(null, "stroke", "none");
				value = keyword.getId();
			} else if (textualElement instanceof Token) {
				Token token = (Token) notationElement;
//				text.setAttributeNS(null, "fill", token.getFill().getLiteral());
				text.setAttributeNS(null, "stroke", "none");
				value = token.getId();
			} else if (textualElement instanceof AttributeValue) {
				AttributeValue attributeValue = (AttributeValue) notationElement;
//				text.setAttributeNS(null, "fill", attributeValue.getFill().getLiteral());

				EAttribute eAttribute = attributeValue.getAttribute();
				
				value = convert(eObject.eGet(eObject.eClass().getEStructuralFeature(eAttribute.getName())));
//				value = convert(eObject.eGet(eAttribute));
			} else if (textualElement instanceof ReferenceValue) {
				ReferenceValue referenceValue = (ReferenceValue) notationElement;
//				text.setAttributeNS(null, "fill", referenceValue.getFill().getLiteral());

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
	private String convert(Object value) {
		String result = "ND";

		if (value instanceof String) {
			result = (String) value;
		} else if (value instanceof EEnumLiteral) {
			EEnumLiteral literal = (EEnumLiteral) value;
			result = literal.toString();
		}

		return result;
	}

	/**
	 * Creates a splash image with the message "No item selected"
	 * 
	 * @return
	 */
	private SVGDocument createTestImage() {
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
		SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);

		Element root = doc.getDocumentElement();
		root.setAttributeNS(null, "width", "500px");
		root.setAttributeNS(null, "height", "500px");

		Element text = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "text");
		text.setAttributeNS(null, "x", "10");
		text.setAttributeNS(null, "y", "20");
		text.setAttributeNS(null, "font-size", DEFAULT_FONT_SIZE);
		text.setAttributeNS(null, "font-family", DEFAULT_FONT_FAMILY);
		text.setAttributeNS(null, "font-weight", "bold");
		text.setAttributeNS(null, "fill", "red");
		text.setAttributeNS(null, "stroke", "none");
		text.setTextContent("No item selected");
		root.appendChild(text);

		return doc;
	}

	/**
	 * Creates a splash image with the message "No syntax defined"
	 * 
	 * @return
	 */
	private SVGDocument createNoSyntaxImage() {
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
		SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);

		Element root = doc.getDocumentElement();
		root.setAttributeNS(null, "width", "500px");
		root.setAttributeNS(null, "height", "500px");

		Element text = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "text");
		text.setAttributeNS(null, "x", "10");
		text.setAttributeNS(null, "y", "20");
		text.setAttributeNS(null, "font-size", DEFAULT_FONT_SIZE);
		text.setAttributeNS(null, "font-family", DEFAULT_FONT_FAMILY);
		text.setAttributeNS(null, "font-weight", "bold");
		text.setAttributeNS(null, "fill", "red");
		text.setAttributeNS(null, "stroke", "none");
		text.setTextContent("No syntax defined");
		root.appendChild(text);

		return doc;
	}

}
