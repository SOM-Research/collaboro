/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.Definition#getElements <em>Elements</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.Definition#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getDefinition()
 * @model
 * @generated
 */
public interface Definition extends EObject {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.notation.NotationElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getDefinition_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<NotationElement> getElements();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.inria.atlanmod.collaboro.notation.NotationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see fr.inria.atlanmod.collaboro.notation.NotationType
	 * @see #setType(NotationType)
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getDefinition_Type()
	 * @model required="true"
	 * @generated
	 */
	NotationType getType();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.notation.Definition#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see fr.inria.atlanmod.collaboro.notation.NotationType
	 * @see #getType()
	 * @generated
	 */
	void setType(NotationType value);

} // Definition
