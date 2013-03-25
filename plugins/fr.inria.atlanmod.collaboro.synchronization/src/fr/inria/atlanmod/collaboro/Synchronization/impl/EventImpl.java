/**
 */
package fr.inria.atlanmod.collaboro.Synchronization.impl;

import fr.inria.atlanmod.collaboro.Synchronization.Event;
import fr.inria.atlanmod.collaboro.Synchronization.SynchronizationPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.impl.EventImpl#getTimeStamp <em>Time Stamp</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.impl.EventImpl#getUser <em>User</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.impl.EventImpl#getModifiedElement <em>Modified Element</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.impl.EventImpl#getModifiedModel <em>Modified Model</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.Synchronization.impl.EventImpl#getStatus <em>Status</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventImpl extends CDOObjectImpl implements Event {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SynchronizationPackage.Literals.EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getTimeStamp() {
		return (Long)eGet(SynchronizationPackage.Literals.EVENT__TIME_STAMP, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeStamp(long newTimeStamp) {
		eSet(SynchronizationPackage.Literals.EVENT__TIME_STAMP, newTimeStamp);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUser() {
		return (String)eGet(SynchronizationPackage.Literals.EVENT__USER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUser(String newUser) {
		eSet(SynchronizationPackage.Literals.EVENT__USER, newUser);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModifiedElement() {
		return (String)eGet(SynchronizationPackage.Literals.EVENT__MODIFIED_ELEMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifiedElement(String newModifiedElement) {
		eSet(SynchronizationPackage.Literals.EVENT__MODIFIED_ELEMENT, newModifiedElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModifiedModel() {
		return (String)eGet(SynchronizationPackage.Literals.EVENT__MODIFIED_MODEL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifiedModel(String newModifiedModel) {
		eSet(SynchronizationPackage.Literals.EVENT__MODIFIED_MODEL, newModifiedModel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStatus() {
		return (String)eGet(SynchronizationPackage.Literals.EVENT__STATUS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(String newStatus) {
		eSet(SynchronizationPackage.Literals.EVENT__STATUS, newStatus);
	}

} //EventImpl
