/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.User;
import fr.inria.atlanmod.collaboro.history.Vote;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.UserImpl#getVotes <em>Votes</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.UserImpl#getCollaborations <em>Collaborations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserImpl extends IdElementImpl implements User {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.USER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Vote> getVotes() {
		return (EList<Vote>)eDynamicGet(HistoryPackage.USER__VOTES, HistoryPackage.Literals.USER__VOTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Collaboration> getCollaborations() {
		return (EList<Collaboration>)eDynamicGet(HistoryPackage.USER__COLLABORATIONS, HistoryPackage.Literals.USER__COLLABORATIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HistoryPackage.USER__VOTES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getVotes()).basicAdd(otherEnd, msgs);
			case HistoryPackage.USER__COLLABORATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCollaborations()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HistoryPackage.USER__VOTES:
				return ((InternalEList<?>)getVotes()).basicRemove(otherEnd, msgs);
			case HistoryPackage.USER__COLLABORATIONS:
				return ((InternalEList<?>)getCollaborations()).basicRemove(otherEnd, msgs);
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
			case HistoryPackage.USER__VOTES:
				return getVotes();
			case HistoryPackage.USER__COLLABORATIONS:
				return getCollaborations();
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
			case HistoryPackage.USER__VOTES:
				getVotes().clear();
				getVotes().addAll((Collection<? extends Vote>)newValue);
				return;
			case HistoryPackage.USER__COLLABORATIONS:
				getCollaborations().clear();
				getCollaborations().addAll((Collection<? extends Collaboration>)newValue);
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
			case HistoryPackage.USER__VOTES:
				getVotes().clear();
				return;
			case HistoryPackage.USER__COLLABORATIONS:
				getCollaborations().clear();
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
			case HistoryPackage.USER__VOTES:
				return !getVotes().isEmpty();
			case HistoryPackage.USER__COLLABORATIONS:
				return !getCollaborations().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UserImpl
