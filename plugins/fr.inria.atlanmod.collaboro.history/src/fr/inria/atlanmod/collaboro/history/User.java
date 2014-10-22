/**
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
 *   <li>{@link fr.inria.atlanmod.collaboro.history.User#getEmail <em>Email</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.User#getPasword <em>Pasword</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.User#getFirstName <em>First Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.User#getLastName <em>Last Name</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Email</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Email</em>' attribute.
	 * @see #setEmail(String)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getUser_Email()
	 * @model
	 * @generated
	 */
	String getEmail();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.User#getEmail <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Email</em>' attribute.
	 * @see #getEmail()
	 * @generated
	 */
	void setEmail(String value);

	/**
	 * Returns the value of the '<em><b>Pasword</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pasword</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pasword</em>' attribute.
	 * @see #setPasword(String)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getUser_Pasword()
	 * @model
	 * @generated
	 */
	String getPasword();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.User#getPasword <em>Pasword</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pasword</em>' attribute.
	 * @see #getPasword()
	 * @generated
	 */
	void setPasword(String value);

	/**
	 * Returns the value of the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Name</em>' attribute.
	 * @see #setFirstName(String)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getUser_FirstName()
	 * @model
	 * @generated
	 */
	String getFirstName();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.User#getFirstName <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Name</em>' attribute.
	 * @see #getFirstName()
	 * @generated
	 */
	void setFirstName(String value);

	/**
	 * Returns the value of the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Name</em>' attribute.
	 * @see #setLastName(String)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getUser_LastName()
	 * @model
	 * @generated
	 */
	String getLastName();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.User#getLastName <em>Last Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Name</em>' attribute.
	 * @see #getLastName()
	 * @generated
	 */
	void setLastName(String value);

} // User
