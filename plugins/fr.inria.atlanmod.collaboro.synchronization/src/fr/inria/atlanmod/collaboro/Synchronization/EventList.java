/**
 */
package fr.inria.atlanmod.collaboro.Synchronization;

import org.eclipse.emf.cdo.CDOObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.EventList#getEvents <em>Events</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.Synchronization.SynchronizationPackage#getEventList()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface EventList extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Events</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.Synchronization.Event}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Events</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' containment reference list.
	 * @see fr.inria.atlanmod.collaboro.Synchronization.SynchronizationPackage#getEventList_Events()
	 * @model containment="true"
	 * @generated
	 */
	EList<Event> getEvents();

} // EventList
