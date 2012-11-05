/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Textual Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.TextualElement#getFill <em>Fill</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getTextualElement()
 * @model
 * @generated
 */
public interface TextualElement extends NotationElement {

	/**
	 * Returns the value of the '<em><b>Fill</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.inria.atlanmod.collaboro.notation.Color}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fill</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill</em>' attribute.
	 * @see fr.inria.atlanmod.collaboro.notation.Color
	 * @see #setFill(Color)
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getTextualElement_Fill()
	 * @model
	 * @generated
	 */
	Color getFill();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.notation.TextualElement#getFill <em>Fill</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill</em>' attribute.
	 * @see fr.inria.atlanmod.collaboro.notation.Color
	 * @see #getFill()
	 * @generated
	 */
	void setFill(Color value);
} // TextualElement
