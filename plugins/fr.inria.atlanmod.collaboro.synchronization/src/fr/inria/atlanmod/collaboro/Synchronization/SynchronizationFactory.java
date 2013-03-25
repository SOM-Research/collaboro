/**
 */
package fr.inria.atlanmod.collaboro.Synchronization;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see fr.inria.atlanmod.collaboro.Synchronization.SynchronizationPackage
 * @generated
 */
public interface SynchronizationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SynchronizationFactory eINSTANCE = fr.inria.atlanmod.collaboro.Synchronization.impl.SynchronizationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Event List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event List</em>'.
	 * @generated
	 */
	EventList createEventList();

	/**
	 * Returns a new object of class '<em>Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event</em>'.
	 * @generated
	 */
	Event createEvent();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SynchronizationPackage getSynchronizationPackage();

} //SynchronizationFactory
