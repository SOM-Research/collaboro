/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.History;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.VersionHistory;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>History</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.HistoryImpl#getUsers <em>Users</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.HistoryImpl#getHistories <em>Histories</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.HistoryImpl#getLanguage <em>Language</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HistoryImpl extends CDOObjectImpl implements History {
	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HistoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.HISTORY;
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
	@SuppressWarnings("unchecked")
	public EList<User> getUsers() {
		return (EList<User>)eDynamicGet(HistoryPackage.HISTORY__USERS, HistoryPackage.Literals.HISTORY__USERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<VersionHistory> getHistories() {
		return (EList<VersionHistory>)eDynamicGet(HistoryPackage.HISTORY__HISTORIES, HistoryPackage.Literals.HISTORY__HISTORIES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLanguage() {
		return (String)eDynamicGet(HistoryPackage.HISTORY__LANGUAGE, HistoryPackage.Literals.HISTORY__LANGUAGE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLanguage(String newLanguage) {
		eDynamicSet(HistoryPackage.HISTORY__LANGUAGE, HistoryPackage.Literals.HISTORY__LANGUAGE, newLanguage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HistoryPackage.HISTORY__USERS:
				return ((InternalEList<?>)getUsers()).basicRemove(otherEnd, msgs);
			case HistoryPackage.HISTORY__HISTORIES:
				return ((InternalEList<?>)getHistories()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HistoryPackage.HISTORY__USERS:
				return getUsers();
			case HistoryPackage.HISTORY__HISTORIES:
				return getHistories();
			case HistoryPackage.HISTORY__LANGUAGE:
				return getLanguage();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HistoryPackage.HISTORY__USERS:
				getUsers().clear();
				getUsers().addAll((Collection<? extends User>)newValue);
				return;
			case HistoryPackage.HISTORY__HISTORIES:
				getHistories().clear();
				getHistories().addAll((Collection<? extends VersionHistory>)newValue);
				return;
			case HistoryPackage.HISTORY__LANGUAGE:
				setLanguage((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case HistoryPackage.HISTORY__USERS:
				getUsers().clear();
				return;
			case HistoryPackage.HISTORY__HISTORIES:
				getHistories().clear();
				return;
			case HistoryPackage.HISTORY__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case HistoryPackage.HISTORY__USERS:
				return !getUsers().isEmpty();
			case HistoryPackage.HISTORY__HISTORIES:
				return !getHistories().isEmpty();
			case HistoryPackage.HISTORY__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? getLanguage() != null : !LANGUAGE_EDEFAULT.equals(getLanguage());
		}
		return super.eIsSet(featureID);
	}

} //HistoryImpl
