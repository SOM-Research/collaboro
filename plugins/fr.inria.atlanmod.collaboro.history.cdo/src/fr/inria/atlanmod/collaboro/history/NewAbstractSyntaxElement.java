/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history;

import org.eclipse.emf.ecore.EModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>New Abstract Syntax Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getNewAbstractSyntaxElement()
 * @model
 * @generated
 */
public interface NewAbstractSyntaxElement extends AbstractSyntaxElement {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' containment reference.
	 * @see #setElement(EModelElement)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getNewAbstractSyntaxElement_Element()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EModelElement getElement();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement#getElement <em>Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' containment reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(EModelElement value);

} // NewAbstractSyntaxElement
