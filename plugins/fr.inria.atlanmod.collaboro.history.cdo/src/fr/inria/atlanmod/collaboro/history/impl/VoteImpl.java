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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vote</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.VoteImpl#isAgreement <em>Agreement</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.VoteImpl#getUser <em>User</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.VoteImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.VoteImpl#getCollaboration <em>Collaboration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VoteImpl extends CDOObjectImpl implements Vote {
	/**
	 * The default value of the '{@link #isAgreement() <em>Agreement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAgreement()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AGREEMENT_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VoteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.VOTE;
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
	public boolean isAgreement() {
		return (Boolean)eDynamicGet(HistoryPackage.VOTE__AGREEMENT, HistoryPackage.Literals.VOTE__AGREEMENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAgreement(boolean newAgreement) {
		eDynamicSet(HistoryPackage.VOTE__AGREEMENT, HistoryPackage.Literals.VOTE__AGREEMENT, newAgreement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User getUser() {
		return (User)eDynamicGet(HistoryPackage.VOTE__USER, HistoryPackage.Literals.VOTE__USER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User basicGetUser() {
		return (User)eDynamicGet(HistoryPackage.VOTE__USER, HistoryPackage.Literals.VOTE__USER, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUser(User newUser, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newUser, HistoryPackage.VOTE__USER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUser(User newUser) {
		eDynamicSet(HistoryPackage.VOTE__USER, HistoryPackage.Literals.VOTE__USER, newUser);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Comment getComment() {
		return (Comment)eDynamicGet(HistoryPackage.VOTE__COMMENT, HistoryPackage.Literals.VOTE__COMMENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Comment basicGetComment() {
		return (Comment)eDynamicGet(HistoryPackage.VOTE__COMMENT, HistoryPackage.Literals.VOTE__COMMENT, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComment(Comment newComment) {
		eDynamicSet(HistoryPackage.VOTE__COMMENT, HistoryPackage.Literals.VOTE__COMMENT, newComment);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collaboration getCollaboration() {
		return (Collaboration)eDynamicGet(HistoryPackage.VOTE__COLLABORATION, HistoryPackage.Literals.VOTE__COLLABORATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCollaboration(Collaboration newCollaboration, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCollaboration, HistoryPackage.VOTE__COLLABORATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollaboration(Collaboration newCollaboration) {
		eDynamicSet(HistoryPackage.VOTE__COLLABORATION, HistoryPackage.Literals.VOTE__COLLABORATION, newCollaboration);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HistoryPackage.VOTE__USER:
				User user = basicGetUser();
				if (user != null)
					msgs = ((InternalEObject)user).eInverseRemove(this, HistoryPackage.USER__VOTES, User.class, msgs);
				return basicSetUser((User)otherEnd, msgs);
			case HistoryPackage.VOTE__COLLABORATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCollaboration((Collaboration)otherEnd, msgs);
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
			case HistoryPackage.VOTE__USER:
				return basicSetUser(null, msgs);
			case HistoryPackage.VOTE__COLLABORATION:
				return basicSetCollaboration(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case HistoryPackage.VOTE__COLLABORATION:
				return eInternalContainer().eInverseRemove(this, HistoryPackage.COLLABORATION__VOTES, Collaboration.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HistoryPackage.VOTE__AGREEMENT:
				return isAgreement();
			case HistoryPackage.VOTE__USER:
				if (resolve) return getUser();
				return basicGetUser();
			case HistoryPackage.VOTE__COMMENT:
				if (resolve) return getComment();
				return basicGetComment();
			case HistoryPackage.VOTE__COLLABORATION:
				return getCollaboration();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HistoryPackage.VOTE__AGREEMENT:
				setAgreement((Boolean)newValue);
				return;
			case HistoryPackage.VOTE__USER:
				setUser((User)newValue);
				return;
			case HistoryPackage.VOTE__COMMENT:
				setComment((Comment)newValue);
				return;
			case HistoryPackage.VOTE__COLLABORATION:
				setCollaboration((Collaboration)newValue);
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
			case HistoryPackage.VOTE__AGREEMENT:
				setAgreement(AGREEMENT_EDEFAULT);
				return;
			case HistoryPackage.VOTE__USER:
				setUser((User)null);
				return;
			case HistoryPackage.VOTE__COMMENT:
				setComment((Comment)null);
				return;
			case HistoryPackage.VOTE__COLLABORATION:
				setCollaboration((Collaboration)null);
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
			case HistoryPackage.VOTE__AGREEMENT:
				return isAgreement() != AGREEMENT_EDEFAULT;
			case HistoryPackage.VOTE__USER:
				return basicGetUser() != null;
			case HistoryPackage.VOTE__COMMENT:
				return basicGetComment() != null;
			case HistoryPackage.VOTE__COLLABORATION:
				return getCollaboration() != null;
		}
		return super.eIsSet(featureID);
	}

} //VoteImpl
