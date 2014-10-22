/**
 */
package fr.inria.atlanmod.collaboro.history;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Update</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Update#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getUpdate()
 * @model
 * @generated
 */
public interface Update extends ModelChange {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(SyntaxElement)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getUpdate_Source()
	 * @model containment="true" required="true"
	 * @generated
	 */
	SyntaxElement getSource();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Update#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(SyntaxElement value);

} // Update
