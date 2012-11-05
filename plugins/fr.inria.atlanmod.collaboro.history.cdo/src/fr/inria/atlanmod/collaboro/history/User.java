/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.User#getVotes <em>Votes</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.User#getCollaborations <em>Collaborations</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getUser()
 * @model
 * @generated
 */
public interface User extends IdElement {
	/**
	 * Returns the value of the '<em><b>Votes</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.history.Vote}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Vote#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Votes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Votes</em>' reference list.
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getUser_Votes()
	 * @see fr.inria.atlanmod.collaboro.history.Vote#getUser
	 * @model opposite="user"
	 * @generated
	 */
	EList<Vote> getVotes();

	/**
	 * Returns the value of the '<em><b>Collaborations</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.history.Collaboration}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Collaboration#getProposedBy <em>Proposed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collaborations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collaborations</em>' reference list.
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getUser_Collaborations()
	 * @see fr.inria.atlanmod.collaboro.history.Collaboration#getProposedBy
	 * @model opposite="proposedBy"
	 * @generated
	 */
	EList<Collaboration> getCollaborations();

} // User
