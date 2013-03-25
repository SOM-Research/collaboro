/**
 */
package fr.inria.atlanmod.collaboro.Synchronization.impl;

import fr.inria.atlanmod.collaboro.Synchronization.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SynchronizationFactoryImpl extends EFactoryImpl implements SynchronizationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SynchronizationFactory init() {
		try {
			SynchronizationFactory theSynchronizationFactory = (SynchronizationFactory)EPackage.Registry.INSTANCE.getEFactory("http://atlanmod.fr/collaboro/synchronization"); 
			if (theSynchronizationFactory != null) {
				return theSynchronizationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SynchronizationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronizationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SynchronizationPackage.EVENT_LIST: return (EObject)createEventList();
			case SynchronizationPackage.EVENT: return (EObject)createEvent();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventList createEventList() {
		EventListImpl eventList = new EventListImpl();
		return eventList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event createEvent() {
		EventImpl event = new EventImpl();
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronizationPackage getSynchronizationPackage() {
		return (SynchronizationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SynchronizationPackage getPackage() {
		return SynchronizationPackage.eINSTANCE;
	}

} //SynchronizationFactoryImpl
