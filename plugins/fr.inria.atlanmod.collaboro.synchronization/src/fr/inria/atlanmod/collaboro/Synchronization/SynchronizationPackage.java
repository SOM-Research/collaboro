/**
 */
package fr.inria.atlanmod.collaboro.Synchronization;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.inria.atlanmod.collaboro.Synchronization.SynchronizationFactory
 * @model kind="package"
 * @generated
 */
public interface SynchronizationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Synchronization";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://atlanmod.fr/collaboro/synchronization";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "synchronization";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SynchronizationPackage eINSTANCE = fr.inria.atlanmod.collaboro.Synchronization.impl.SynchronizationPackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.Synchronization.impl.EventListImpl <em>Event List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.Synchronization.impl.EventListImpl
	 * @see fr.inria.atlanmod.collaboro.Synchronization.impl.SynchronizationPackageImpl#getEventList()
	 * @generated
	 */
	int EVENT_LIST = 0;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LIST__EVENTS = 0;

	/**
	 * The number of structural features of the '<em>Event List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LIST_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.Synchronization.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.Synchronization.impl.EventImpl
	 * @see fr.inria.atlanmod.collaboro.Synchronization.impl.SynchronizationPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 1;

	/**
	 * The feature id for the '<em><b>Time Stamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__TIME_STAMP = 0;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__USER = 1;

	/**
	 * The feature id for the '<em><b>Modified Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__MODIFIED_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Modified Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__MODIFIED_MODEL = 3;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__STATUS = 4;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = 5;


	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.Synchronization.EventList <em>Event List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event List</em>'.
	 * @see fr.inria.atlanmod.collaboro.Synchronization.EventList
	 * @generated
	 */
	EClass getEventList();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.Synchronization.EventList#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Events</em>'.
	 * @see fr.inria.atlanmod.collaboro.Synchronization.EventList#getEvents()
	 * @see #getEventList()
	 * @generated
	 */
	EReference getEventList_Events();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.Synchronization.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see fr.inria.atlanmod.collaboro.Synchronization.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getTimeStamp <em>Time Stamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Stamp</em>'.
	 * @see fr.inria.atlanmod.collaboro.Synchronization.Event#getTimeStamp()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_TimeStamp();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see fr.inria.atlanmod.collaboro.Synchronization.Event#getUser()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_User();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getModifiedElement <em>Modified Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.Synchronization.Event#getModifiedElement()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_ModifiedElement();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getModifiedModel <em>Modified Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified Model</em>'.
	 * @see fr.inria.atlanmod.collaboro.Synchronization.Event#getModifiedModel()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_ModifiedModel();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.Synchronization.Event#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see fr.inria.atlanmod.collaboro.Synchronization.Event#getStatus()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_Status();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SynchronizationFactory getSynchronizationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.Synchronization.impl.EventListImpl <em>Event List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.Synchronization.impl.EventListImpl
		 * @see fr.inria.atlanmod.collaboro.Synchronization.impl.SynchronizationPackageImpl#getEventList()
		 * @generated
		 */
		EClass EVENT_LIST = eINSTANCE.getEventList();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_LIST__EVENTS = eINSTANCE.getEventList_Events();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.Synchronization.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.Synchronization.impl.EventImpl
		 * @see fr.inria.atlanmod.collaboro.Synchronization.impl.SynchronizationPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Time Stamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__TIME_STAMP = eINSTANCE.getEvent_TimeStamp();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__USER = eINSTANCE.getEvent_User();

		/**
		 * The meta object literal for the '<em><b>Modified Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__MODIFIED_ELEMENT = eINSTANCE.getEvent_ModifiedElement();

		/**
		 * The meta object literal for the '<em><b>Modified Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__MODIFIED_MODEL = eINSTANCE.getEvent_ModifiedModel();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__STATUS = eINSTANCE.getEvent_Status();

	}

} //SynchronizationPackage
