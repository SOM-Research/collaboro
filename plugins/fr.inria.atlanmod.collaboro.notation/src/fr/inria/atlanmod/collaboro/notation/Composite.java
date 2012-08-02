/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.Composite#getSubElements <em>Sub Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getComposite()
 * @model
 * @generated
 */
public interface Composite extends NotationElement {
	/**
	 * Returns the value of the '<em><b>Sub Elements</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.notation.NotationElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Elements</em>' containment reference list.
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getComposite_SubElements()
	 * @model containment="true"
	 * @generated
	 */
	EList<NotationElement> getSubElements();

} // Composite
