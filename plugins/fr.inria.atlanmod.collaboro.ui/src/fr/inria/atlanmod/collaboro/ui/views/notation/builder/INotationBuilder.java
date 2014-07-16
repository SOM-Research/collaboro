package fr.inria.atlanmod.collaboro.ui.views.notation.builder;

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

public interface INotationBuilder {
	public String create(Keyword keyword); 
	public String create(Token token); 
	public String create(AttributeValue attributeValue); 
	public String create(ReferenceValue referenceValue); 
	public String create(Label label); 
	public String create(Line line);
	public String create(Rectangle rectangle);
	public String create(Composite composite);
	public String create(SyntaxOf syntaxOf);
	public String create(NotationElement notationElement);
	public String create(TextualElement textualElement);
	public String create(Value value);
	public String create(GraphicalElement graphicalElement);
	public String create(Figure figure);
	public String create(Image image);

	//with the associated EObjects
	public String create(EObject eObject, Keyword keyword, Map<EObject, EObject> historyLinks); 
	public String create(EObject eObject, Token token, Map<EObject, EObject> historyLinks); 
	public String create(EObject eObject, AttributeValue attributeValue, Map<EObject, EObject> historyLinks); 
	public String create(EObject eObject, ReferenceValue referenceValue, Map<EObject, EObject> historyLinks); 
	public String create(EObject eObject, Label label, Map<EObject, EObject> historyLinks); 
	public String create(EObject eObject, Line line, Map<EObject, EObject> historyLinks);
	public String create(EObject eObject, Rectangle rectangle, Map<EObject, EObject> historyLinks);
	public String create(EObject eObject, Composite composite, Map<EObject, EObject> historyLinks);
	public String create(EObject eObject, SyntaxOf syntaxOf, Map<EObject, EObject> historyLinks);
	public String create(EObject eObject, NotationElement notationElement, Map<EObject, EObject> historyLinks);
	public String create(EObject eObject, TextualElement textualElement, Map<EObject, EObject> historyLinks);
	public String create(EObject eObject, Value value, Map<EObject, EObject> historyLinks);
	public String create(EObject eObject, GraphicalElement graphicalElement, Map<EObject, EObject> historyLinks);
	public String create(EObject eObject, Image image, Map<EObject, EObject> historyLinks);
	public String create(EObject eObject, Figure figure, Map<EObject, EObject> historyLinks);
}
