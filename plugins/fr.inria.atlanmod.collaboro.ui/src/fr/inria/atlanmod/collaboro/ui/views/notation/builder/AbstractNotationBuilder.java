package fr.inria.atlanmod.collaboro.ui.views.notation.builder;

//import fr.inria.atlanmod.collaboro.notation.AttributeValue;
//import fr.inria.atlanmod.collaboro.notation.Composite;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import fr.inria.atlanmod.collaboro.notation.AttributeValue;
import fr.inria.atlanmod.collaboro.notation.Composite;
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
//import fr.inria.atlanmod.collaboro.notation.GraphicalElement;
//import fr.inria.atlanmod.collaboro.notation.Keyword;
//import fr.inria.atlanmod.collaboro.notation.Label;
//import fr.inria.atlanmod.collaboro.notation.Line;
//import fr.inria.atlanmod.collaboro.notation.Rectangle;
//import fr.inria.atlanmod.collaboro.notation.ReferenceValue;
//import fr.inria.atlanmod.collaboro.notation.SyntaxOf;
//import fr.inria.atlanmod.collaboro.notation.TextualElement;
//import fr.inria.atlanmod.collaboro.notation.Token;

public abstract class AbstractNotationBuilder implements INotationBuilder {


	@Override
	public String create(NotationElement notationElement) {
		String result;
		
		if (notationElement instanceof Composite) {
			Composite elem = (Composite) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof GraphicalElement) {
			GraphicalElement elem = (GraphicalElement) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof Figure) {
			Figure elem = (Figure) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof SyntaxOf) {
			SyntaxOf elem = (SyntaxOf) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof TextualElement) {
			TextualElement elem = (TextualElement) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof Rectangle) {
			Rectangle elem = (Rectangle) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof Image) {
			Image elem = (Image) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof Line) {
			Line elem = (Line) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof Label) {
			Label elem = (Label) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof Value) {
			Value elem = (Value) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof Token) {
			Token elem = (Token) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof Keyword) {
			Keyword elem = (Keyword) notationElement;
			result = create(elem);
		}	else if (notationElement instanceof AttributeValue) {
			AttributeValue elem = (AttributeValue) notationElement;
			result = create(elem);
		}	else /*notationElement instanceof ReferenceValue*/ {
			ReferenceValue elem = (ReferenceValue) notationElement;
			result = create(elem);
		}
		
//		result = create(notationElement);
		return result;
	}


	@Override
	public String create(Figure figure) {
		String result;
		if (figure instanceof Rectangle) {
			Rectangle elem = (Rectangle) figure;
			result = create(elem);
		}	else /*value instanceof anything else*/ {
			result = "unknown figure";
		}
		return result;
	}
	
	@Override
	public String create(GraphicalElement graphicalElement) {
		String result;
		if (graphicalElement instanceof Figure) {
			Figure elem = (Figure) graphicalElement;
			result = create(elem);
		}	else if (graphicalElement instanceof Line) {
			Line elem = (Line) graphicalElement;
			result = create(elem);
		}	else if (graphicalElement instanceof Image) {
			Image elem = (Image) graphicalElement;
			result = create(elem);
		}	else /* graphicalElement instanceof Label */ {
			Label elem = (Label) graphicalElement;
			result = create(elem);
		}
		return result;
	}
	
	
	@Override
	public String create(Value value) {

		String result;
	if (value instanceof AttributeValue) {
		AttributeValue elem = (AttributeValue) value;
		result = create(elem);
	}	else /*value instanceof ReferenceValue*/ {
		ReferenceValue elem = (ReferenceValue) value;
		result = create(elem);
	}
		return result;
	}
	
	
	
	//
	
	
	@Override
	public String create(EObject eObject, NotationElement notationElement, Map<EObject, EObject> historyLinks) {
		String result;

		if (notationElement instanceof Composite) {
			Composite elem = (Composite) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof GraphicalElement) {
			GraphicalElement elem = (GraphicalElement) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof Figure) {
			Figure elem = (Figure) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof SyntaxOf) {
			SyntaxOf elem = (SyntaxOf) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof TextualElement) {
			TextualElement elem = (TextualElement) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof Rectangle) {
			Rectangle elem = (Rectangle) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof Line) {
			Line elem = (Line) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof Label) {
			Label elem = (Label) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof Value) {
			Value elem = (Value) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof Token) {
			Token elem = (Token) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof Keyword) {
			Keyword elem = (Keyword) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else if (notationElement instanceof AttributeValue) {
			AttributeValue elem = (AttributeValue) notationElement;
			result = create(eObject, elem, historyLinks);
		}	else /*notationElement instanceof ReferenceValue*/ {
			ReferenceValue elem = (ReferenceValue) notationElement;
			result = create(eObject, elem, historyLinks);
		}
		return result;
	}


	@Override
	public String create(EObject eObject, Figure figure, Map<EObject, EObject> historyLinks) {
		String result;
		if (figure instanceof Rectangle) {
			Rectangle elem = (Rectangle) figure;
			result = create(eObject, elem, historyLinks);
		}	else /*value instanceof anything else*/ {
			result = "unknown figure";
		}

		return result;
	}
	
	@Override
	public String create(EObject eObject, GraphicalElement graphicalElement, Map<EObject, EObject> historyLinks) {
		String result;
		if (graphicalElement instanceof Figure) {
			Figure elem = (Figure) graphicalElement;
			result = create(eObject, elem, historyLinks);
		}	else if (graphicalElement instanceof Line) {
			Line elem = (Line) graphicalElement;
			result = create(eObject, elem, historyLinks);
		}	else if (graphicalElement instanceof Label ) {
			Label elem = (Label) graphicalElement;
			result = create(eObject, elem, historyLinks);
		}	else { /*Image*/
			Image elem = (Image) graphicalElement;
			result = create(eObject, elem, historyLinks);
		}

		return result;
	}
	
	
	@Override
	public String create(EObject eObject, Value value, Map<EObject, EObject> historyLinks) {
		String result;
		if (value instanceof AttributeValue) {
			AttributeValue elem = (AttributeValue) value;
			result = create(eObject, elem, historyLinks);
		}	else /*value instanceof ReferenceValue*/ {
			ReferenceValue elem = (ReferenceValue) value;
			result = create(eObject, elem, historyLinks);
		}

		return result;
	}

}
