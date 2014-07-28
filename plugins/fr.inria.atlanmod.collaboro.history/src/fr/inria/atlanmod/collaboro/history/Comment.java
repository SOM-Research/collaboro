/**
 */
package fr.inria.atlanmod.collaboro.history;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Comment#getCommentedElement <em>Commented Element</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Comment#isIncluded <em>Included</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getComment()
 * @model
 * @generated
 */
public interface Comment extends Collaboration {
	/**
	 * Returns the value of the '<em><b>Commented Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Collaboration#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Commented Element</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Commented Element</em>' container reference.
	 * @see #setCommentedElement(Collaboration)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getComment_CommentedElement()
	 * @see fr.inria.atlanmod.collaboro.history.Collaboration#getComments
	 * @model opposite="comments" transient="false"
	 * @generated
	 */
	Collaboration getCommentedElement();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Comment#getCommentedElement <em>Commented Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Commented Element</em>' container reference.
	 * @see #getCommentedElement()
	 * @generated
	 */
	void setCommentedElement(Collaboration value);

	/**
	 * Returns the value of the '<em><b>Included</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included</em>' attribute.
	 * @see #setIncluded(boolean)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getComment_Included()
	 * @model
	 * @generated
	 */
	boolean isIncluded();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Comment#isIncluded <em>Included</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Included</em>' attribute.
	 * @see #isIncluded()
	 * @generated
	 */
	void setIncluded(boolean value);

} // Comment
