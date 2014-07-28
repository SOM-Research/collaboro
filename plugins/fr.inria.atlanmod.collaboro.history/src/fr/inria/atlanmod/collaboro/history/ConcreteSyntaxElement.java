/**
 */
package fr.inria.atlanmod.collaboro.history;

import fr.inria.atlanmod.collaboro.notation.NotationElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concrete Syntax Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getConcreteSyntaxElement()
 * @model
 * @generated
 */
public interface ConcreteSyntaxElement extends SyntaxElement {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(NotationElement)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getConcreteSyntaxElement_Element()
	 * @model required="true"
	 * @generated
	 */
	NotationElement getElement();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(NotationElement value);

} // ConcreteSyntaxElement
