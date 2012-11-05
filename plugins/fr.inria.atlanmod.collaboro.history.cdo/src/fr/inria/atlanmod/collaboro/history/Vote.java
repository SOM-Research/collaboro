/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vote</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Vote#isAgreement <em>Agreement</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Vote#getUser <em>User</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Vote#getComment <em>Comment</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Vote#getCollaboration <em>Collaboration</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getVote()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface Vote extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Agreement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Agreement</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agreement</em>' attribute.
	 * @see #setAgreement(boolean)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getVote_Agreement()
	 * @model
	 * @generated
	 */
	boolean isAgreement();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Vote#isAgreement <em>Agreement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Agreement</em>' attribute.
	 * @see #isAgreement()
	 * @generated
	 */
	void setAgreement(boolean value);

	/**
	 * Returns the value of the '<em><b>User</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.User#getVotes <em>Votes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' reference.
	 * @see #setUser(User)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getVote_User()
	 * @see fr.inria.atlanmod.collaboro.history.User#getVotes
	 * @model opposite="votes"
	 * @generated
	 */
	User getUser();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Vote#getUser <em>User</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' reference.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(User value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment</em>' reference.
	 * @see #setComment(Comment)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getVote_Comment()
	 * @model
	 * @generated
	 */
	Comment getComment();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Vote#getComment <em>Comment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' reference.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(Comment value);

	/**
	 * Returns the value of the '<em><b>Collaboration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Collaboration#getVotes <em>Votes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collaboration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collaboration</em>' container reference.
	 * @see #setCollaboration(Collaboration)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getVote_Collaboration()
	 * @see fr.inria.atlanmod.collaboro.history.Collaboration#getVotes
	 * @model opposite="votes" transient="false"
	 * @generated
	 */
	Collaboration getCollaboration();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Vote#getCollaboration <em>Collaboration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collaboration</em>' container reference.
	 * @see #getCollaboration()
	 * @generated
	 */
	void setCollaboration(Collaboration value);

} // Vote
