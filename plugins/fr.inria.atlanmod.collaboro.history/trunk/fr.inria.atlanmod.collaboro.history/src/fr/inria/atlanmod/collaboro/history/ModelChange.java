/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.ModelChange#getTarget <em>Target</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.ModelChange#getReferredElement <em>Referred Element</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.ModelChange#getSolution <em>Solution</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getModelChange()
 * @model abstract="true"
 * @generated
 */
public interface ModelChange extends EObject {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see #setTarget(String)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getModelChange_Target()
	 * @model
	 * @generated
	 */
	String getTarget();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.ModelChange#getTarget <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' attribute.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(String value);

	/**
	 * Returns the value of the '<em><b>Referred Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Element</em>' attribute.
	 * @see #setReferredElement(String)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getModelChange_ReferredElement()
	 * @model
	 * @generated
	 */
	String getReferredElement();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.ModelChange#getReferredElement <em>Referred Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Element</em>' attribute.
	 * @see #getReferredElement()
	 * @generated
	 */
	void setReferredElement(String value);

	/**
	 * Returns the value of the '<em><b>Solution</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Solution#getChanges <em>Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Solution</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solution</em>' container reference.
	 * @see #setSolution(Solution)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getModelChange_Solution()
	 * @see fr.inria.atlanmod.collaboro.history.Solution#getChanges
	 * @model opposite="changes" transient="false"
	 * @generated
	 */
	Solution getSolution();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.ModelChange#getSolution <em>Solution</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Solution</em>' container reference.
	 * @see #getSolution()
	 * @generated
	 */
	void setSolution(Solution value);

} // ModelChange
