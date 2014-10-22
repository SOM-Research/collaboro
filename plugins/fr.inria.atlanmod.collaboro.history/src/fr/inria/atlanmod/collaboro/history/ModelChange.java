/**
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
 *   <li>{@link fr.inria.atlanmod.collaboro.history.ModelChange#getSolution <em>Solution</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.ModelChange#getReferredElement <em>Referred Element</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.ModelChange#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getModelChange()
 * @model abstract="true"
 * @generated
 */
public interface ModelChange extends EObject {
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

	/**
	 * Returns the value of the '<em><b>Referred Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Element</em>' containment reference.
	 * @see #setReferredElement(SyntaxElement)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getModelChange_ReferredElement()
	 * @model containment="true" required="true"
	 * @generated
	 */
	SyntaxElement getReferredElement();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.ModelChange#getReferredElement <em>Referred Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Element</em>' containment reference.
	 * @see #getReferredElement()
	 * @generated
	 */
	void setReferredElement(SyntaxElement value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' containment reference.
	 * @see #setTarget(SyntaxElement)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getModelChange_Target()
	 * @model containment="true" required="true"
	 * @generated
	 */
	SyntaxElement getTarget();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.ModelChange#getTarget <em>Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' containment reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(SyntaxElement value);

} // ModelChange
