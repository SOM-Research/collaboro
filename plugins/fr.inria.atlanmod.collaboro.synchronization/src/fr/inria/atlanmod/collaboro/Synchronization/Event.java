/**
 */
package fr.inria.atlanmod.collaboro.Synchronization;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getTimeStamp <em>Time Stamp</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getUser <em>User</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getModifiedElement <em>Modified Element</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getModifiedModel <em>Modified Model</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getStatus <em>Status</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.Synchronization.SynchronizationPackage#getEvent()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface Event extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Time Stamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Stamp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Stamp</em>' attribute.
	 * @see #setTimeStamp(long)
	 * @see fr.inria.atlanmod.collaboro.Synchronization.SynchronizationPackage#getEvent_TimeStamp()
	 * @model
	 * @generated
	 */
	long getTimeStamp();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getTimeStamp <em>Time Stamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Stamp</em>' attribute.
	 * @see #getTimeStamp()
	 * @generated
	 */
	void setTimeStamp(long value);

	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #setUser(String)
	 * @see fr.inria.atlanmod.collaboro.Synchronization.SynchronizationPackage#getEvent_User()
	 * @model
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

	/**
	 * Returns the value of the '<em><b>Modified Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modified Element</em>' attribute.
	 * @see #setModifiedElement(String)
	 * @see fr.inria.atlanmod.collaboro.Synchronization.SynchronizationPackage#getEvent_ModifiedElement()
	 * @model
	 * @generated
	 */
	String getModifiedElement();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getModifiedElement <em>Modified Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modified Element</em>' attribute.
	 * @see #getModifiedElement()
	 * @generated
	 */
	void setModifiedElement(String value);

	/**
	 * Returns the value of the '<em><b>Modified Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified Model</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modified Model</em>' attribute.
	 * @see #setModifiedModel(String)
	 * @see fr.inria.atlanmod.collaboro.Synchronization.SynchronizationPackage#getEvent_ModifiedModel()
	 * @model
	 * @generated
	 */
	String getModifiedModel();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getModifiedModel <em>Modified Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modified Model</em>' attribute.
	 * @see #getModifiedModel()
	 * @generated
	 */
	void setModifiedModel(String value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(String)
	 * @see fr.inria.atlanmod.collaboro.Synchronization.SynchronizationPackage#getEvent_Status()
	 * @model
	 * @generated
	 */
	String getStatus();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(String value);

} // Event
