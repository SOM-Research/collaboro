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

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.util.DOMUtilities;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.commons.io.IOUtils;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.model.CDOClassifierRef;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.internal.cdo.object.DynamicCDOObjectImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import com.abstratt.graphviz.GraphViz;
import com.abstratt.graphviz.GraphVizActivator;

import fr.inria.atlanmod.collaboro.history.Add;
import fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement;
import fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.notation.AttributeValue;
import fr.inria.atlanmod.collaboro.notation.Composite;
import fr.inria.atlanmod.collaboro.notation.GraphicalElement;
import fr.inria.atlanmod.collaboro.notation.Keyword;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.notation.ReferenceValue;
import fr.inria.atlanmod.collaboro.notation.SyntaxOf;
import fr.inria.atlanmod.collaboro.notation.TextualElement;
import fr.inria.atlanmod.collaboro.notation.Token;
import fr.inria.atlanmod.collaboro.ui.Controller;
import fr.inria.atlanmod.collaboro.ui.views.notation.builder.DotNotationBuilder;


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
	//	protected NotationComposite notation;
	protected Browser notation;

	private boolean inCDO = false;


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

		if(part.getSite().getId().equals(Controller.PACKAGE_EXPLORER_PLUGIN_ID) || (part.getSite().getId().equals(Controller.PROJECT_EXPLORER_PLUGIN_ID))) {
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

		if(part.getSite().getId().equals(VersionView.CDO_SESSIONS_VIEW)) {
			TreeSelection treeSelection = (TreeSelection) selection;
			Object element = treeSelection.getFirstElement();
			if (element instanceof CDOResource) {
				inCDO = true;
				updateView((EObject) element);
				inCDO = false;
			}
		}
		
		if(part.getSite().getId().equals(VersionView.CDO_EDITOR)) {
			TreeSelection treeSelection = (TreeSelection) selection;
			Object element = treeSelection.getFirstElement();
				inCDO = true;
				if (element instanceof EClassifier) {
					updateView((EClassifier) element);
				}
				inCDO = false;
			
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
		//		notation = new NotationComposite(parent, createTestImage());
		//TODO
		notation = new Browser(parent, SWT.NONE); 
		parent.layout();

		getSite().getPage().addSelectionListener(this); 
	}


	@Override
	public void setFocus() {

	}

	private boolean checkGraphical(NotationElement notationElement) {
		boolean toReturn = false;
		if (notationElement instanceof GraphicalElement) {
			toReturn = true;
		} else if (notationElement instanceof Composite) {
			for (NotationElement element : ((Composite)notationElement).getSubElements()) {
				toReturn = toReturn || this.checkGraphical(element);
			} 
		}else {
			toReturn = false;
		}
		return toReturn;
	}

	/**
	 * Updates the vied with the notation of the modelElement (abstract syntax element).
	 * 
	 * @param modelElement
	 */
	public void updateView(EClassifier modelElement) {
		NotationElement notationElement = Controller.INSTANCE.getNotation(modelElement);
		SVGDocument svgImage = null;
		String svgLocation;
		if (inCDO) {
			String loc = "file:/" + System.getProperty("java.io.tmpdir") + "temp.svg";
			svgLocation = loc.replaceAll("\\\\", "/");
		} else {
			svgLocation  = Controller.INSTANCE.getEcoreModel().eResource().getURI().toString() + ".svg";
		}
		if(notationElement == null) {
			svgImage = createNoSyntaxImage();
			serialize(svgImage, svgLocation);
		} else {
			if (this.checkGraphical(notationElement)) {
				svgImage = buildSVGFromDot(notationElement);
			} else {
				svgImage = buildSVG(notationElement);
				serialize(svgImage, svgLocation);
			}	
		}
		//		notation.setSVGDocument(svgImage);
		notation.setUrl(svgLocation);
		notation.layout(true);
	}


	/**
	 * Updates the view with the notation of the modelElement instance (instance of 
	 * the abstract syntax element).
	 * 
	 * @param instanceModelElement
	 */
	public void updateView(EObject instanceModelElement) {
		EClassifier modelElement = null;
		if (inCDO) {
			instanceModelElement = instanceModelElement.eContents().get(0);
		} 
		modelElement = instanceModelElement.eClass();
		NotationElement notationElement = Controller.INSTANCE.getNotation(modelElement);
		SVGDocument svgImage = null;
		String location;
		if (inCDO) {
			String loc = "file:/" + System.getProperty("java.io.tmpdir") + "temp.svg";
			location = loc.replaceAll("\\\\", "/");
		} else {
			location = instanceModelElement.eResource().getURI().toString() + ".svg";
		}
		if(notationElement == null) {
			svgImage = createNoSyntaxImage();
			serialize(svgImage, location);
		} else {
			if (this.checkGraphical(notationElement)) {
				svgImage = buildSVGFromDot(instanceModelElement, notationElement);
			} else {
				svgImage = buildSVG(instanceModelElement, notationElement);
				serialize(svgImage, location);
			}
		}
		notation.setUrl(location);
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
					y = y + subBox.getHeight() - VERTICAL_SEP;   
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

				if ((subElement instanceof Composite)) { // || (subElement instanceof SyntaxOf)) {
					x = oldX;
					y = y + subBox.getHeight() - VERTICAL_SEP;  
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
			} else if (textualElement instanceof ReferenceValue) {
				ReferenceValue referenceValue = (ReferenceValue) notationElement;
				text.setAttributeNS(null, "fill", referenceValue.getFill().getLiteral());

				EReference eReference = referenceValue.getReference();
				EAttribute eAttribute = referenceValue.getAttribute();
				String separator = referenceValue.getSeparator();

				Object referredObjs = eObject.eGet(eObject.eClass().getEStructuralFeature(eReference.getName()));
				if (referredObjs instanceof EList) {
					EList<EObject> eReferenceList = (EList<EObject>) referredObjs;
					for(EObject elementList : eReferenceList) {
						Object attributeValue = elementList.eGet(elementList.eClass().getEStructuralFeature(eAttribute.getName()));
						value += convert(attributeValue);
						if(eReferenceList.indexOf(elementList) != eReferenceList.size() - 1) {
							value += separator;
						}
					}
				} else if (referredObjs instanceof EObject) {
					EObject elementList = (EObject) referredObjs;
					Object attributeValue = elementList.eGet(elementList.eClass().getEStructuralFeature(eAttribute.getName()));
					value += convert(attributeValue);					
				}			
			}

			if(value.length() > 0) {
				result.setWidth(value.length() * CHAR_SEP + CHAR_SEP);
				result.setHeight(VERTICAL_SEP);
				text.setTextContent(value);		
				svgRoot.appendChild(text);
			}
		} else if (notationElement instanceof SyntaxOf) {
			SyntaxOf syntaxOf = (SyntaxOf) notationElement;

			EReference eReference = syntaxOf.getReference();

			Object referredObjs = eObject.eGet(eObject.eClass().getEStructuralFeature(eReference.getName()));
			if (referredObjs instanceof EList) {
				EList<EObject> eReferenceList = (EList<EObject>) referredObjs;
				if(eReferenceList.size() > 0) {
					for(EObject elementList : eReferenceList) {
						NotationElement subNotationElement = Controller.INSTANCE.getNotation(elementList.eClass());
						if(subNotationElement != null) {
							Box subBox = buildSVG(elementList, subNotationElement, doc, x, y);
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

	private SVGDocument buildSVGFromDot(EObject instanceModelElement,
			NotationElement notationElement) {

		//Create the dot graph definition
		//get history and put the model changes in a map with MM element --> Syntax element
		Map<EObject, EObject> modelChanges = new HashMap<EObject, EObject>();
		History history = Controller.INSTANCE.getHistory();
		for (TreeIterator<EObject> iterator = history.eAllContents(); iterator.hasNext();) {
			EObject eObject = (EObject) iterator.next();
			if (eObject instanceof Add) {
				EObject targetSE = ((ModelChange)eObject).getTarget();
				EObject referredElementSE = ((ModelChange)eObject).getReferredElement();
				if (targetSE instanceof ConcreteSyntaxElement) {
					targetSE = ((ConcreteSyntaxElement)targetSE).getElement();
				} else if (targetSE instanceof ExistingAbstractSyntaxElement) {
					targetSE = ((ExistingAbstractSyntaxElement)targetSE).getElement();
				} else {
					targetSE = ((NewAbstractSyntaxElement)targetSE).getElement();
				}
				if (referredElementSE instanceof ConcreteSyntaxElement) {
					referredElementSE = ((ConcreteSyntaxElement)referredElementSE).getElement();
				} else if (referredElementSE instanceof ExistingAbstractSyntaxElement) {
					referredElementSE = ((ExistingAbstractSyntaxElement)referredElementSE).getElement();
				} else {
					referredElementSE = ((NewAbstractSyntaxElement)referredElementSE).getElement();
				}
				modelChanges.put(referredElementSE, targetSE);
			}
		}

		//Build the dot represetation
		StringBuilder dotGraph = new StringBuilder();
		dotGraph.append("graph ").append(instanceModelElement.eClass().getName()).append(" {\n rankdir=\"LR\";\n");

		DotNotationBuilder dotBuilder = new DotNotationBuilder();
		Map<String, EObject> list = new HashMap<String, EObject>();
		for (EObject eObj : modelChanges.keySet()) {
			ENamedElement eClass = (ENamedElement) eObj;
			list.put(eClass.getName(), modelChanges.get(eObj));
		}
		if(list.keySet().contains(instanceModelElement.eClass().getName())) {//(modelChanges.keySet().contains(instanceModelElement.eClass())){
			dotGraph.append(dotBuilder.create(instanceModelElement, (NotationElement)list.get(instanceModelElement.eClass().getName()), modelChanges));
		}
		dotGraph.append(" }\n");

		String svgLocation;
		if (inCDO) {
			String loc = "file:/" + System.getProperty("java.io.tmpdir") + "temp.svg";
			svgLocation = loc.replaceAll("\\\\", "/");
		} else {
			svgLocation = instanceModelElement.eResource().getURI().toString() + ".svg";
		}
		
		Document doc = null;
		try {
			ByteArrayInputStream input = new ByteArrayInputStream(dotGraph.toString().getBytes());
			//GraphViz.generate(input, "svg", new Point(300, 300), new Path(instanceModelElement.eResource().getURI().toFileString()+".svg"));
			runDot(input, new Path(svgLocation));//instanceModelElement.eResource().getURI().toFileString()+".svg"));
			//String svgLocation = instanceModelElement.eResource().getURI().toString() + ".svg";
			String parser = XMLResourceDescriptor.getXMLParserClassName();
			SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
			doc = f.createDocument(svgLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (SVGDocument) doc;
	}





	private SVGDocument buildSVGFromDot(NotationElement notationElement) {
		StringBuilder dotGraph = new StringBuilder();
		dotGraph.append("graph ").append(notationElement.getId()).append(" {\n rankdir=\"LR\";\n");
		DotNotationBuilder dotBuilder = new DotNotationBuilder();
		dotGraph.append(dotBuilder.create(notationElement));
		dotGraph.append(" }\n");
		Document doc = null;

		String fullPath;
		if (inCDO) {
			String loc = "file:/" + System.getProperty("java.io.tmpdir") + "temp.svg";
			fullPath = loc.replaceAll("\\\\", "/");
		} else {
			fullPath = Controller.INSTANCE.getEcoreModel().eResource().getURI().toFileString()+".svg";
		}
		
		try {
			ByteArrayInputStream input = new ByteArrayInputStream(dotGraph.toString().getBytes());
			//		GraphViz.generate(input, "svg", new Point(200, 200), new Path(Controller.INSTANCE.getEcoreModel().eResource().getURI().toFileString()+".svg"));
			runDot(input, new Path(fullPath));
//			String svgLocation = Controller.INSTANCE.getEcoreModel().eResource().getURI().toString() + ".svg";
			String parser = XMLResourceDescriptor.getXMLParserClassName();
			SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
			if (inCDO) {
				doc = f.createDocument(fullPath);
			} else {
				java.io.File file = new java.io.File(fullPath);
				doc = f.createDocument(file.toURL().toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (SVGDocument) doc;
	}

	//method inspired from the generate method of the com.abstratt.graphviz plugin
	private void runDot(ByteArrayInputStream input, IPath outputLocation) {
		MultiStatus status = new MultiStatus(GraphVizActivator.ID, 0, "Errors occurred while running Graphviz", null);
		java.io.File dotInput = null;
		java.io.File dotOutput = outputLocation.toFile();
		try {
			// determine the temp input location
			dotInput = java.io.File.createTempFile("graphviz", ".dot");
			// dump the contents from the input stream into the temporary file
			// to be submitted to dot
			FileOutputStream tmpDotOutputStream = null;
			try {
				tmpDotOutputStream = new FileOutputStream(dotInput);
				IOUtils.copy(input, tmpDotOutputStream);
			} finally {
				IOUtils.closeQuietly(tmpDotOutputStream);
			}
			String absolutePath;
			if (outputLocation.isAbsolute()) {
				absolutePath = dotOutput.getPath().replaceFirst("file:\\\\", "");
			} else {
				absolutePath = dotOutput.getAbsolutePath();
			}
			IStatus result = GraphViz.runDot("-Tsvg", "-Gsize=200,200", "-o"+absolutePath, dotInput.getAbsolutePath() );
			if (dotOutput.isFile()) {
				// success!
				return;
			}
		} catch (IOException e) {
			status.add(new Status(IStatus.ERROR, GraphVizActivator.ID, "", e));
		} finally {
			dotInput.delete();
			IOUtils.closeQuietly(input);
		}
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
	protected SVGDocument createTestImage() {
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
	protected SVGDocument createNoSyntaxImage() {
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
