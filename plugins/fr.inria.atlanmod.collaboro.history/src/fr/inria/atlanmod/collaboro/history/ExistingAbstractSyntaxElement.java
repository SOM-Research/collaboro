/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Existing Abstract Syntax Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getExistingAbstractSyntaxElement()
 * @model
 * @generated
 */
public interface ExistingAbstractSyntaxElement extends AbstractSyntaxElement {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(EModelElement)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getExistingAbstractSyntaxElement_Element()
	 * @model required="true"
	 * @generated
	 */
	EModelElement getElement();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(EModelElement value);

} // ExistingAbstractSyntaxElement
