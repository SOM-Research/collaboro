package fr.inria.atlanmod.collaboro.ui.views.notation.builder;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import fr.inria.atlanmod.collaboro.notation.AttributeValue;
import fr.inria.atlanmod.collaboro.notation.Composite;
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
import fr.inria.atlanmod.collaboro.ui.Controller;

public class DotNotationBuilder extends AbstractNotationBuilder implements INotationBuilder {


	/**
	 * Obtains the string representation of the value
	 * @author Javier Canovas (javier.canovas@inria.fr) 
	 * @param value
	 * @return
	 */
	protected String convert(Object value) {
		String result = "ND";
		result = value.toString();

		return result;
	}


	@Override
	public String create(Keyword keyword) {
		StringBuilder sb = new StringBuilder();
		sb.append(" fontcolor=").append(keyword.getFill().toString()).append(", label=\"").append(keyword.getId()).append("\"");
		return sb.toString();
	}

	@Override
	public String create(Token token) {
		StringBuilder sb = new StringBuilder();
		sb.append(" fontcolor=").append(token.getFill().toString()).append(", label=\"").append(token.getId()).append("\"");
		return sb.toString();
	}

	@Override
	public String create(AttributeValue attributeValue) {
		StringBuilder sb = new StringBuilder();
		sb.append(" fontcolor=").append(attributeValue.getFill().toString()).append(", label=\"").append(attributeValue.getId());
		sb.append(" <value of '").append(attributeValue.getAttribute().getName()).append("' attribute>").append("\"");
		return sb.toString();
	}

	@Override
	public String create(ReferenceValue referenceValue) {
		StringBuilder sb = new StringBuilder();
		sb.append(" fontcolor=").append(referenceValue.getFill().toString()).append(", label=\"").append(referenceValue.getId());
		sb.append(" <value of '").append(referenceValue.getAttribute().getName()).append("' reference>").append("\"");
		return sb.toString();
	}

	@Override
	public String create(Label label) {
		StringBuilder sb = new StringBuilder();
		sb.append(label.getId()).append(" [shape=none, ").append(this.create(label.getText())).append("];\n");
		return sb.toString();
	}

	@Override
	public String create(Line line) {
		StringBuilder sb = new StringBuilder();
		sb.append("in--out;\n");
		return sb.toString();
	}

	/**
	 * create an image node for dot
	 * syntax:
	 *  p1 [margin=0 shape=hexagon, style=bold, label=<<TABLE border="0" cellborder="0">
		 <TR><TD><IMG SRC="image_name.jpg"/></TD></TR>
		 <TR><TD>first line<br/><font point-size="8">second line</font></TD></TR>
		 </TABLE>>]
	 */
	@Override
	public String create(Image image) {
		StringBuilder sb = new StringBuilder();
		StringBuilder imagePathBuilder = new StringBuilder();
		imagePathBuilder.append(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString());
		if (!image.getPath().startsWith("/")) {
			imagePathBuilder.append("/");
		}
		imagePathBuilder.append(image.getPath());
		sb.append(image.getId()).append(" [label=<<TABLE border=\"0\" cellborder=\"0\"><TR><TD><IMG SRC=\"")
		.append(imagePathBuilder.toString()).append("\"/></TD></TR><TR><TD>").append(image.getId()).append("</TD></TR></TABLE>>, shape=none];\n");
		return sb.toString();		
	}
	

	@Override
	public String create(Rectangle rectangle) {
		StringBuilder sb = new StringBuilder();
		sb.append(rectangle.getId()).append(" [shape=box, color=");
		sb.append(convert(rectangle.getStroke()));
		sb.append(", fillcolor=").append(convert(rectangle.getFill())).append(", label=\"").append(rectangle.getId()).append("\"];\n");
		return sb.toString();
	}

	@Override
	public String create(Composite composite) {
		StringBuilder sb = new StringBuilder();
		sb.append(" subgraph cluster_").append(composite.getId()).append(" {\nstyle=solid;\n");
		for (NotationElement element : composite.getSubElements()) {
			sb.append(create(element));
		}
		sb.append("}; \n");
		return sb.toString();
	}

	@Override
	public String create(SyntaxOf syntaxOf) {
		StringBuilder sb = new StringBuilder();
		sb.append("label=\"").append(syntaxOf.getId());
		sb.append("<syntax of '").append(syntaxOf.getReference().getName()).append("' reference>").append("\"");
		return sb.toString();

	}

	@Override
	public String create(TextualElement textualElement) {
		String result;
		if (textualElement instanceof Value) {
			Value elem = (Value) textualElement;
			result = create(elem);
		}	else if (textualElement instanceof Token) {
			Token elem = (Token) textualElement;
			result = create(elem);
		}	else if (textualElement instanceof Keyword) {
			Keyword elem = (Keyword) textualElement;
			result = create(elem);
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(" fontcolor=").append(textualElement.getFill().toString()).append(", label=\"").append(textualElement.getId()).append("\"");
			result = sb.toString(); 
		}
		return result;
	}


	@Override
	public String create(EObject eObject, Keyword keyword, Map<EObject, EObject> historyLinks) {

		StringBuilder sb = new StringBuilder();
		sb.append(" fontcolor=").append(keyword.getFill().toString()).append(", label=\"").append(keyword.getId()).append("\"");
		return sb.toString();
	}


	@Override
	public String create(EObject eObject, Token token, Map<EObject, EObject> historyLinks) {
		StringBuilder sb = new StringBuilder();
		sb.append(" fontcolor=").append(token.getFill().toString()).append(", label=\"").append(token.getId()).append("\"");
		return sb.toString();
	}


	@Override
	public String create(EObject eObject, AttributeValue attributeValue, Map<EObject, EObject> historyLinks) {
		EAttribute eAttribute = attributeValue.getAttribute();
		StringBuilder sb = new StringBuilder();
		sb.append(" fontcolor=").append(attributeValue.getFill().toString()).append(", label=\"").append(attributeValue.getId());
		sb.append(" -> ").append(convert(eObject.eGet(eObject.eClass().getEStructuralFeature(eAttribute.getName()))));
		sb.append("\"");
		return sb.toString();
	}


	@Override
	public String create(EObject eObject, ReferenceValue referenceValue, Map<EObject, EObject> historyLinks) {
		StringBuilder sb = new StringBuilder();
		sb.append(" fontcolor=").append(referenceValue.getFill().toString()).append(", label=\"").append(referenceValue.getId()).append(" -> ");
		
		EReference eReference = referenceValue.getReference();
		EAttribute eAttribute = referenceValue.getAttribute();
		String separator = referenceValue.getSeparator();

		Object referredObjs = eObject.eGet(eObject.eClass().getEStructuralFeature(eReference.getName()));
		if (referredObjs instanceof EList) {
			EList<EObject> eReferenceList = (EList<EObject>) referredObjs;
			for(EObject elementList : eReferenceList) {
				Object attributeValue = elementList.eGet(elementList.eClass().getEStructuralFeature(eAttribute.getName()));
				sb.append( convert(attributeValue) );
				if(eReferenceList.indexOf(elementList) != eReferenceList.size() - 1) {
					sb.append(separator);
				}
			}
		} else if (referredObjs instanceof EObject) {
			EObject elementList = (EObject) referredObjs;
			Object attributeValue = elementList.eGet(elementList.eClass().getEStructuralFeature(eAttribute.getName()));
			sb.append(convert(attributeValue));					
		}
		
		sb.append("\"");
		return sb.toString();
	}


	@Override
	public String create(EObject eObject, Label label, Map<EObject, EObject> historyLinks) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getInstanceName(eObject)).append(" [shape=none, ").append(this.create(eObject, label.getText(), historyLinks)).append("];\n");
		return sb.toString();
	}


	@Override
	public String create(EObject eObject, Line line, Map<EObject, EObject> historyLinks) {
		StringBuilder sb = new StringBuilder();
		EObject eRef = null;
		for (Entry<EObject, EObject> entry : historyLinks.entrySet()) {
			if (entry.getValue().equals(line)) {
				eRef = entry.getKey();
			}
		}
		if (eRef != null) {
			if (eRef instanceof EReference) {
				EReference ref = (EReference) eRef;
				String source = this.getInstanceName(eObject);
				String target = this.getInstanceName((EObject) eObject.eGet(ref));
				sb.append(source).append("--").append(target).append(";\n");
			}else {
				sb.append("in--out;\n");
			}
		} else {
			sb.append("in--out;\n");
		}
		return sb.toString();
	}


	@Override
	public String create(EObject eObject, Rectangle rectangle, Map<EObject, EObject> historyLinks) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getInstanceName(eObject)).append(" [shape=box, color=").append(convert(rectangle.getStroke()));
		sb.append(", fillcolor=").append(convert(rectangle.getFill())).append(", label=\"").append(this.getInstanceName(eObject)).append("\"];\n");
		sb.append(this.launchOnReferences(eObject, historyLinks));
		return sb.toString();
	}


	@Override
	public String create(EObject eObject, Composite composite, Map<EObject, EObject> historyLinks) {
		StringBuilder sb = new StringBuilder();
		sb.append(" subgraph cluster_").append(this.getInstanceName(eObject)).append(" {\nstyle=solid;\n");
		
		//Iterate over the input model
		for (TreeIterator<EObject> iterator = eObject.eAllContents(); iterator.hasNext();) {
			EObject element = (EObject) iterator.next();
			for (EObject type : historyLinks.keySet()) {
				if (type.equals(element.eClass()) ) {
					sb.append(this.create(element, (NotationElement)historyLinks.get(type), historyLinks));
				}
			}
		}
		sb.append(this.getInstanceName(eObject)).append("[shape=none]\n");
		sb.append("}; \n");
		return sb.toString();
	}


	@Override
	public String create(EObject eObject, SyntaxOf syntaxOf, Map<EObject, EObject> historyLinks) {

		StringBuilder sb = new StringBuilder();

		EReference eReference = syntaxOf.getReference();
		Object referredObjs = eObject.eGet(eObject.eClass().getEStructuralFeature(eReference.getName()));

		if (referredObjs instanceof EList) {
			EList<EObject> eReferenceList = (EList<EObject>) referredObjs;
			if(eReferenceList.size() > 0) {
				for(EObject elementList : eReferenceList) {
					NotationElement subNotationElement = Controller.INSTANCE.getNotation(elementList.eClass());
					if(subNotationElement != null) {
						sb.append(create(elementList, subNotationElement, historyLinks));
					}
				}
			}
		}
		return sb.toString();	
	}


	@Override
	public String create(EObject eObject, TextualElement textualElement, Map<EObject, EObject> historyLinks) {
		String result;
		if (textualElement instanceof Value) {
			Value elem = (Value) textualElement;
			result = create(eObject, elem, historyLinks);
		}	else if (textualElement instanceof Token) {
			Token elem = (Token) textualElement;
			result = create(eObject, elem, historyLinks);
		}	else if (textualElement instanceof Keyword)  {
			Keyword elem = (Keyword) textualElement;
			result = create(eObject, elem, historyLinks);
		}	else {
			StringBuilder sb = new StringBuilder();
			sb.append(" fontcolor=").append(textualElement.getFill().toString()).append(", label=\"").append(this.getInstanceName(eObject)).append("\"");
			result = sb.toString(); 
		}
		return result;
	}


	@Override
	public String create(EObject eObject, Image image, Map<EObject, EObject> historyLinks) {
		StringBuilder sb = new StringBuilder();
		StringBuilder imagePathBuilder = new StringBuilder();
		imagePathBuilder.append(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString());
		if (!image.getPath().startsWith("/")) {
			imagePathBuilder.append("/");
		}
		imagePathBuilder.append(image.getPath());
		sb.append(this.getInstanceName(eObject)).append(" [label=<<TABLE border=\"0\" cellborder=\"0\"><TR><TD><IMG SRC=\"")
		.append(imagePathBuilder.toString()).append("\"/></TD></TR><TR><TD>").append(this.getInstanceName(eObject)).append("</TD></TR></TABLE>>, shape=none];\n");
		sb.append(this.launchOnReferences(eObject, historyLinks));
		return sb.toString();
	}

	
	/**
	 * Launch the create on contained EReferences if needed
	 * @return the dot string
	 */
	protected String launchOnReferences(EObject eObject, Map<EObject, EObject> historyLinks) {
		StringBuilder sb = new StringBuilder();
		if (eObject.eClass() instanceof EClass) {
			for (EReference eRef : eObject.eClass().getEAllReferences()) {
				for (EObject ref : historyLinks.keySet()) {
					if (ref.equals(eRef) ) {
						sb.append(this.create(eObject, (NotationElement)historyLinks.get(ref), historyLinks));
					}
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param instance
	 * @return the name or id if no name else class name + id object 
	 */
	protected String getInstanceName(EObject instance) {
		StringBuilder sb = new StringBuilder();
		//get the name or id attribute
		EClass mElem = instance.eClass();
		for (EAttribute eAttr : mElem.getEAllAttributes()) {
			if (eAttr.getName().equals("name")) {
				sb.append(instance.eGet(eAttr));
			} else if (eAttr.getName().equals("Name")) {
				sb.append(instance.eGet(eAttr));
			} else if (eAttr.getName().equals("identifier")) {
				sb.append(instance.eGet(eAttr));
			} else if (eAttr.getName().equals("Identifier")) {
				sb.append(instance.eGet(eAttr));
			} else if (eAttr.getName().equals("id")) {
				sb.append(instance.eGet(eAttr));
			} else if (eAttr.getName().equals("Id")) {
				sb.append(instance.eGet(eAttr));
			} else {
				sb.append(instance.eClass().getName()).append('_').append(instance.hashCode());
			}
		}
		return sb.toString();
	}

}
