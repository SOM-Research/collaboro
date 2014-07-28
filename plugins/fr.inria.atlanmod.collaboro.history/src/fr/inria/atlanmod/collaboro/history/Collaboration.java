/**
 */
package fr.inria.atlanmod.collaboro.history;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collaboration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Collaboration#getRationale <em>Rationale</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Collaboration#getProposedBy <em>Proposed By</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Collaboration#getComments <em>Comments</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Collaboration#getVotes <em>Votes</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getCollaboration()
 * @model
 * @generated
 */
public interface Collaboration extends IdElement {
	/**
	 * Returns the value of the '<em><b>Rationale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rationale</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rationale</em>' attribute.
	 * @see #setRationale(String)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getCollaboration_Rationale()
	 * @model
	 * @generated
	 */
	String getRationale();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Collaboration#getRationale <em>Rationale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rationale</em>' attribute.
	 * @see #getRationale()
	 * @generated
	 */
	void setRationale(String value);

	/**
	 * Returns the value of the '<em><b>Proposed By</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.User#getCollaborations <em>Collaborations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proposed By</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proposed By</em>' reference.
	 * @see #setProposedBy(User)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getCollaboration_ProposedBy()
	 * @see fr.inria.atlanmod.collaboro.history.User#getCollaborations
	 * @model opposite="collaborations"
	 * @generated
	 */
	User getProposedBy();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Collaboration#getProposedBy <em>Proposed By</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proposed By</em>' reference.
	 * @see #getProposedBy()
	 * @generated
	 */
	void setProposedBy(User value);

	/**
	 * Returns the value of the '<em><b>Comments</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.history.Comment}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Comment#getCommentedElement <em>Commented Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' containment reference list.
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getCollaboration_Comments()
	 * @see fr.inria.atlanmod.collaboro.history.Comment#getCommentedElement
	 * @model opposite="commentedElement" containment="true"
	 * @generated
	 */
	EList<Comment> getComments();

	/**
	 * Returns the value of the '<em><b>Votes</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.history.Vote}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Vote#getCollaboration <em>Collaboration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Votes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Votes</em>' containment reference list.
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getCollaboration_Votes()
	 * @see fr.inria.atlanmod.collaboro.history.Vote#getCollaboration
	 * @model opposite="collaboration" containment="true"
	 * @generated
	 */
	EList<Vote> getVotes();

} // Collaboration
