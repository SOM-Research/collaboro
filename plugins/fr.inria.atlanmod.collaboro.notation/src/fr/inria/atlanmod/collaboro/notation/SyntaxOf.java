/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation;

import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Syntax Of</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.SyntaxOf#getReference <em>Reference</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.SyntaxOf#getSeparator <em>Separator</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getSyntaxOf()
 * @model
 * @generated
 */
public interface SyntaxOf extends NotationElement {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(EReference)
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getSyntaxOf_Reference()
	 * @model required="true"
	 * @generated
	 */
	EReference getReference();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.notation.SyntaxOf#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(EReference value);

	/**
	 * Returns the value of the '<em><b>Separator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Separator</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Separator</em>' reference.
	 * @see #setSeparator(NotationElement)
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getSyntaxOf_Separator()
	 * @model
	 * @generated
	 */
	NotationElement getSeparator();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.notation.SyntaxOf#getSeparator <em>Separator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Separator</em>' reference.
	 * @see #getSeparator()
	 * @generated
	 */
	void setSeparator(NotationElement value);

} // SyntaxOf
