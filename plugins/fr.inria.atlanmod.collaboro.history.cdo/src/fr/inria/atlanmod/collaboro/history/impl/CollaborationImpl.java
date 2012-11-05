/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Comment;
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
 * An implementation of the model object '<em><b>Collaboration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.CollaborationImpl#getRationale <em>Rationale</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.CollaborationImpl#getProposedBy <em>Proposed By</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.CollaborationImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.CollaborationImpl#getVotes <em>Votes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollaborationImpl extends IdElementImpl implements Collaboration {
	/**
	 * The default value of the '{@link #getRationale() <em>Rationale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRationale()
	 * @generated
	 * @ordered
	 */
	protected static final String RATIONALE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CollaborationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.COLLABORATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRationale() {
		return (String)eDynamicGet(HistoryPackage.COLLABORATION__RATIONALE, HistoryPackage.Literals.COLLABORATION__RATIONALE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRationale(String newRationale) {
		eDynamicSet(HistoryPackage.COLLABORATION__RATIONALE, HistoryPackage.Literals.COLLABORATION__RATIONALE, newRationale);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User getProposedBy() {
		return (User)eDynamicGet(HistoryPackage.COLLABORATION__PROPOSED_BY, HistoryPackage.Literals.COLLABORATION__PROPOSED_BY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User basicGetProposedBy() {
		return (User)eDynamicGet(HistoryPackage.COLLABORATION__PROPOSED_BY, HistoryPackage.Literals.COLLABORATION__PROPOSED_BY, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProposedBy(User newProposedBy, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newProposedBy, HistoryPackage.COLLABORATION__PROPOSED_BY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProposedBy(User newProposedBy) {
		eDynamicSet(HistoryPackage.COLLABORATION__PROPOSED_BY, HistoryPackage.Literals.COLLABORATION__PROPOSED_BY, newProposedBy);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Comment> getComments() {
		return (EList<Comment>)eDynamicGet(HistoryPackage.COLLABORATION__COMMENTS, HistoryPackage.Literals.COLLABORATION__COMMENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Vote> getVotes() {
		return (EList<Vote>)eDynamicGet(HistoryPackage.COLLABORATION__VOTES, HistoryPackage.Literals.COLLABORATION__VOTES, true, true);
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
			case HistoryPackage.COLLABORATION__PROPOSED_BY:
				User proposedBy = basicGetProposedBy();
				if (proposedBy != null)
					msgs = ((InternalEObject)proposedBy).eInverseRemove(this, HistoryPackage.USER__COLLABORATIONS, User.class, msgs);
				return basicSetProposedBy((User)otherEnd, msgs);
			case HistoryPackage.COLLABORATION__COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getComments()).basicAdd(otherEnd, msgs);
			case HistoryPackage.COLLABORATION__VOTES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getVotes()).basicAdd(otherEnd, msgs);
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
			case HistoryPackage.COLLABORATION__PROPOSED_BY:
				return basicSetProposedBy(null, msgs);
			case HistoryPackage.COLLABORATION__COMMENTS:
				return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
			case HistoryPackage.COLLABORATION__VOTES:
				return ((InternalEList<?>)getVotes()).basicRemove(otherEnd, msgs);
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
			case HistoryPackage.COLLABORATION__RATIONALE:
				return getRationale();
			case HistoryPackage.COLLABORATION__PROPOSED_BY:
				if (resolve) return getProposedBy();
				return basicGetProposedBy();
			case HistoryPackage.COLLABORATION__COMMENTS:
				return getComments();
			case HistoryPackage.COLLABORATION__VOTES:
				return getVotes();
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
			case HistoryPackage.COLLABORATION__RATIONALE:
				setRationale((String)newValue);
				return;
			case HistoryPackage.COLLABORATION__PROPOSED_BY:
				setProposedBy((User)newValue);
				return;
			case HistoryPackage.COLLABORATION__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case HistoryPackage.COLLABORATION__VOTES:
				getVotes().clear();
				getVotes().addAll((Collection<? extends Vote>)newValue);
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
			case HistoryPackage.COLLABORATION__RATIONALE:
				setRationale(RATIONALE_EDEFAULT);
				return;
			case HistoryPackage.COLLABORATION__PROPOSED_BY:
				setProposedBy((User)null);
				return;
			case HistoryPackage.COLLABORATION__COMMENTS:
				getComments().clear();
				return;
			case HistoryPackage.COLLABORATION__VOTES:
				getVotes().clear();
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
			case HistoryPackage.COLLABORATION__RATIONALE:
				return RATIONALE_EDEFAULT == null ? getRationale() != null : !RATIONALE_EDEFAULT.equals(getRationale());
			case HistoryPackage.COLLABORATION__PROPOSED_BY:
				return basicGetProposedBy() != null;
			case HistoryPackage.COLLABORATION__COMMENTS:
				return !getComments().isEmpty();
			case HistoryPackage.COLLABORATION__VOTES:
				return !getVotes().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CollaborationImpl
