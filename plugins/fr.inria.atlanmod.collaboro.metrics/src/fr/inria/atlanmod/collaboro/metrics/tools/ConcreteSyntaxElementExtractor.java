package fr.inria.atlanmod.collaboro.metrics.tools;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.collaboro.metrics.model.Colour;
import fr.inria.atlanmod.collaboro.metrics.model.Position;
import fr.inria.atlanmod.collaboro.metrics.model.ShapeType;
import fr.inria.atlanmod.collaboro.metrics.model.Size;
import fr.inria.atlanmod.collaboro.metrics.model.Symbol;
import fr.inria.atlanmod.collaboro.metrics.model.SymbolType;
import fr.inria.atlanmod.collaboro.metrics.model.VisualRepresentation;
import fr.inria.atlanmod.collaboro.notation.AttributeValue;
import fr.inria.atlanmod.collaboro.notation.Color;
import fr.inria.atlanmod.collaboro.notation.Composite;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.Figure;
import fr.inria.atlanmod.collaboro.notation.GraphicalElement;
import fr.inria.atlanmod.collaboro.notation.Image;
import fr.inria.atlanmod.collaboro.notation.Keyword;
import fr.inria.atlanmod.collaboro.notation.Label;
import fr.inria.atlanmod.collaboro.notation.Line;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.notation.Rectangle;
import fr.inria.atlanmod.collaboro.notation.ReferenceValue;
import fr.inria.atlanmod.collaboro.notation.SyntaxOf;
import fr.inria.atlanmod.collaboro.notation.TextualElement;
import fr.inria.atlanmod.collaboro.notation.Token;
import fr.inria.atlanmod.collaboro.notation.Value;

public class ConcreteSyntaxElementExtractor {
	
	private Definition concreteSyntaxModel;
	private List<Symbol> symbols;
	
	public ConcreteSyntaxElementExtractor(Definition concreteSyntaxModel) {
		this.concreteSyntaxModel = concreteSyntaxModel;
		this.symbols = new ArrayList<Symbol>();
	}
	
	public List<Symbol> discoverConcreteSyntax() {
		List<NotationElement> concreteSyntaxElements = concreteSyntaxModel.getElements();
		for(NotationElement concreteSyntaxElement : concreteSyntaxElements) {
			if(concreteSyntaxElement instanceof Composite)  {
				Composite composite = (Composite) concreteSyntaxElement;
				Symbol symbol = resolveComposite(composite, null);
				this.symbols.add(symbol);
			} else if(concreteSyntaxElement instanceof GraphicalElement) {
				GraphicalElement graphicalElement = (GraphicalElement) concreteSyntaxElement;
			} else if(concreteSyntaxElement instanceof TextualElement) {
				TextualElement textualElement = (TextualElement) concreteSyntaxElement;
			} else if(concreteSyntaxElement instanceof SyntaxOf) {
				SyntaxOf syntaxOf = (SyntaxOf) concreteSyntaxElement;
			} else {
				//error
			}
		}
		return this.symbols;
	}
	
private Symbol resolveComposite(Composite composite, Composite parentComposite) {
		
		Symbol compositeSymbol = null;
		if(parentComposite == null) {
			//primary composite
			// Check composite referred abstract element type
			String compositeName = composite.getId();
			String abstractEntityReferencedName = compositeName;
			SymbolType symbolType = SymbolType.UNKNOWN;
			
			String[] splitCompositeName = compositeName.split("\\.");
			if(splitCompositeName.length == 3) {
				// referred element -> Reference
				String compositeClassName = splitCompositeName[0];
				String compositeReferenceName = splitCompositeName[1];
				String compositeAttributeName = splitCompositeName[2];
				
				abstractEntityReferencedName = compositeClassName + "." + compositeReferenceName;
				symbolType = SymbolType.REFERENCE;
//				ReferenceConcept correspondingReferenceConcept = getReferenceConcept(abstractEntityReferencedName);
//				if(correspondingReferenceConcept != null) {

//				}
			} else if(splitCompositeName.length == 2) {
				// referred element -> Attribute
				String compositeClassName = splitCompositeName[0];
				String compositeAttributeName = splitCompositeName[1];
				
				abstractEntityReferencedName = compositeClassName + "." + compositeAttributeName;
				symbolType = SymbolType.ATTRIBUTE;
//				AttributeConcept correspondingAttributeConcept = getAttributeConcept(abstractEntityReferencedName);
//				if(correspondingAttributeConcept != null) {

//				}
			} else if(splitCompositeName.length == 1) {
				// referred element -> Class
				abstractEntityReferencedName = compositeName;
				symbolType = SymbolType.CLASS;
//				ClassConcept correspondingClassConcept = getClassConcept(abstractEntityReferencedName);
//				if(correspondingClassConcept != null) {

//				}
			} else {
				
			}
			
			compositeSymbol = new Symbol(compositeName,abstractEntityReferencedName,symbolType,composite);
			
		} else {
			
		}
		
		List<NotationElement> compositeElements = composite.getSubElements();
		if(!compositeElements.isEmpty()) {
			for(NotationElement compositeElement : compositeElements) {
				if(compositeElement instanceof Composite) {
					Composite secondaryComposite = (Composite) compositeElement;
					//TODO
				} else if (compositeElement instanceof GraphicalElement) {
					GraphicalElement graphicalElement = (GraphicalElement) compositeElement;
					VisualRepresentation visualRepresentation = resolveGraphicalElement(graphicalElement, composite);
					
					compositeSymbol.addVisualRepresentation(visualRepresentation);
				} else if (compositeElement instanceof TextualElement) {
					TextualElement textualElement = (TextualElement) compositeElement;
					//TODO
				} else if (compositeElement instanceof SyntaxOf) {
					SyntaxOf syntaxOf = (SyntaxOf) compositeElement;
				} else {
					//error
				}
			}
		}
		return compositeSymbol;
	}
	
	
	private VisualRepresentation resolveGraphicalElement(GraphicalElement graphicalElement, NotationElement parentElement) {
		String elementId = graphicalElement.getId();
		
		VisualRepresentation visualRepresentation = new VisualRepresentation();
		
		int elementHeight = graphicalElement.getHeight();
		int elementWidth = graphicalElement.getWidth();
		Size visualRepresentationSize = new Size(elementHeight,elementWidth);
		visualRepresentation.setSize(visualRepresentationSize);

		int elementX = graphicalElement.getX();
		int elementY = graphicalElement.getY();
		Position visualRepresentationPosition = new Position(elementX, elementY);
		visualRepresentation.setPosition(visualRepresentationPosition);
		
		Color elementFill = graphicalElement.getFill();
		Color elementStroke = graphicalElement.getStroke();
		Colour visualRepresentationColour = new Colour(elementStroke, elementFill);
		visualRepresentation.setColour(visualRepresentationColour);
		
		ShapeType visualRepresentationShape = ShapeType.UNKNOWN;
		if(graphicalElement instanceof Image) {
//			Image imageElement = (Image) graphicalElement;
			visualRepresentationShape = ShapeType.IMAGE;
		} else if(graphicalElement instanceof Figure) {
			Figure figureElement = (Figure) graphicalElement;
			ShapeType figureShapeType = resolveFigureShapeType(figureElement);
			visualRepresentationShape = figureShapeType;
		} else if(graphicalElement instanceof Line) {
//			Line lineElement = (Line) graphicalElement;
			visualRepresentationShape = ShapeType.LINE;
		} else if(graphicalElement instanceof Label) {
			Label labelElement = (Label) graphicalElement;
			visualRepresentationShape = ShapeType.LABEL;
			
			TextualElement labelTextElement = labelElement.getText();
			if(labelTextElement instanceof Value) {
				String labelTextElementId = labelTextElement.getId();
				if(labelTextElement instanceof ReferenceValue) {
					ReferenceValue labelTextReferenceValue = (ReferenceValue) labelTextElement;
					Symbol labelTextReferenceSymbol = resolveReferenceValue(labelTextReferenceValue, labelElement);
					labelTextReferenceSymbol.setNotationElement(labelElement);
					if(!(parentElement.getId().equals(labelTextReferenceSymbol.getFullName())))  {
						// register symbol
						this.symbols.add(labelTextReferenceSymbol);
					}
				} else if(labelTextElement instanceof AttributeValue) {
					AttributeValue labelTextAttributeValue = (AttributeValue) labelTextElement;
					Symbol labelTextAttributeSymbol = resolveAttributeValue(labelTextAttributeValue, labelElement);
					labelTextAttributeSymbol.setNotationElement(labelElement);
					if(!(parentElement.getId().equals(labelTextAttributeSymbol.getFullName())))  {
						// register symbol
						this.symbols.add(labelTextAttributeSymbol);
					}
				}
			} else {
				
			}
			
		} else {
			// error
		}
		visualRepresentation.setShape(visualRepresentationShape);
		visualRepresentation.setGraphicalElement(graphicalElement);
		return visualRepresentation;
	}
	
	private ShapeType resolveFigureShapeType(Figure figure) {
		if(figure instanceof Rectangle) {
			return ShapeType.RECTANGLE;
		} else {
			
		}
		return ShapeType.UNKNOWN;
	}
	
	private void resolveTextualElement(TextualElement textualElement, NotationElement parentElement) {
		String elementId = textualElement.getId();
		Color elementFill = textualElement.getFill();
		
		if(textualElement instanceof Token) {
			Token tokenElement = (Token) textualElement;
		} else if(textualElement instanceof Keyword) {
			Keyword keywordElement = (Keyword) textualElement;
		} else if(textualElement instanceof AttributeValue) {
			AttributeValue attributeValueElement = (AttributeValue) textualElement;
			Symbol attributeSymbol = resolveAttributeValue(attributeValueElement, parentElement);
			if(attributeSymbol != null) {
				if(parentElement instanceof Label) {
					
				}
			} else {
				
			}
		} else if(textualElement instanceof ReferenceValue) {
			ReferenceValue referenceValueElement = (ReferenceValue) textualElement;
		} else {
			//error
		}
	}
	
	private void resolveSyntaxOf(SyntaxOf syntaxOf, NotationElement parentElement) {
		String elementId = syntaxOf.getId();
		EReference elementReference = syntaxOf.getReference();
		NotationElement elementSeparator = syntaxOf.getSeparator();
	}
	
	private Symbol resolveAttributeValue(AttributeValue attributeValue, NotationElement parentElement) {
		Symbol attributeSymbol = null;
		String elementId = attributeValue.getId();
		String elementSeparator = attributeValue.getSeparator();
		EAttribute elementAttribute = attributeValue.getAttribute();
		
		String abstractEntityReferencedName = "";
		SymbolType symbolType = SymbolType.ATTRIBUTE;
		
		if(elementAttribute != null) {
			
		} else {
			String[] splitElementId = elementId.split("\\.");
			if(splitElementId.length == 2) {
				String attributeClassName = splitElementId[0];
				String attributeAttributeName = splitElementId[1];
				abstractEntityReferencedName = attributeClassName + "." + attributeAttributeName;
				
				attributeSymbol = new Symbol(elementId, abstractEntityReferencedName, symbolType, attributeValue);
			} else {
				// format error
			}
		}
		
		
		return attributeSymbol;
	}
	
	private Symbol resolveReferenceValue(ReferenceValue referenceValue, NotationElement parentElement) {
		Symbol referenceSymbol = null;
		String elementId = referenceValue.getId();
		EReference elementReference = referenceValue.getReference();
		EAttribute elementAttribute = referenceValue.getAttribute();
		String elementSeparator = referenceValue.getSeparator();
		
		String abstractEntityReferencedName = "";
		SymbolType symbolType = SymbolType.REFERENCE;
		
		if(elementReference != null) {
			if(elementAttribute != null) {
				
			} else {
				
			}
		} else {
			if(elementAttribute != null) {
				
			} else {
				String[] splitElementId = elementId.split("\\.");
				if(splitElementId.length == 3) {
					String referenceClassName = splitElementId[0];
					String referenceReferenceName = splitElementId[1];
					String referenceAttributeName = splitElementId[2];
					
					abstractEntityReferencedName = referenceClassName + "." + referenceReferenceName;
					referenceSymbol = new Symbol(elementId, abstractEntityReferencedName, symbolType, referenceValue);
				} else {
					// format error
				}
			}
		}
		return referenceSymbol;
	}
	
	

}
