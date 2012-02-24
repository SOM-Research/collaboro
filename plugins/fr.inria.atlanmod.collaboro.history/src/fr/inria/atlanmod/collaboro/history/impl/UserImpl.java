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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
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
	 * The cached value of the '{@link #getVotes() <em>Votes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVotes()
	 * @generated
	 * @ordered
	 */
	protected EList<Vote> votes;
	/**
	 * The cached value of the '{@link #getCollaborations() <em>Collaborations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollaborations()
	 * @generated
	 * @ordered
	 */
	protected EList<Collaboration> collaborations;

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
	public EList<Vote> getVotes() {
		if (votes == null) {
			votes = new EObjectWithInverseResolvingEList<Vote>(Vote.class, this, HistoryPackage.USER__VOTES, HistoryPackage.VOTE__USER);
		}
		return votes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Collaboration> getCollaborations() {
		if (collaborations == null) {
			collaborations = new EObjectWithInverseResolvingEList<Collaboration>(Collaboration.class, this, HistoryPackage.USER__COLLABORATIONS, HistoryPackage.COLLABORATION__PROPOSED_BY);
		}
		return collaborations;
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
				return votes != null && !votes.isEmpty();
			case HistoryPackage.USER__COLLABORATIONS:
				return collaborations != null && !collaborations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UserImpl
