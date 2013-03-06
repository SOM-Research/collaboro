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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
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
	 * The cached value of the '{@link #getRationale() <em>Rationale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRationale()
	 * @generated
	 * @ordered
	 */
	protected String rationale = RATIONALE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProposedBy() <em>Proposed By</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProposedBy()
	 * @generated
	 * @ordered
	 */
	protected User proposedBy;

	/**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> comments;

	/**
	 * The cached value of the '{@link #getVotes() <em>Votes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVotes()
	 * @generated
	 * @ordered
	 */
	protected EList<Vote> votes;

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
		return rationale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRationale(String newRationale) {
		String oldRationale = rationale;
		rationale = newRationale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HistoryPackage.COLLABORATION__RATIONALE, oldRationale, rationale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User getProposedBy() {
		if (proposedBy != null && proposedBy.eIsProxy()) {
			InternalEObject oldProposedBy = (InternalEObject)proposedBy;
			proposedBy = (User)eResolveProxy(oldProposedBy);
			if (proposedBy != oldProposedBy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, HistoryPackage.COLLABORATION__PROPOSED_BY, oldProposedBy, proposedBy));
			}
		}
		return proposedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User basicGetProposedBy() {
		return proposedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProposedBy(User newProposedBy, NotificationChain msgs) {
		User oldProposedBy = proposedBy;
		proposedBy = newProposedBy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HistoryPackage.COLLABORATION__PROPOSED_BY, oldProposedBy, newProposedBy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProposedBy(User newProposedBy) {
		if (newProposedBy != proposedBy) {
			NotificationChain msgs = null;
			if (proposedBy != null)
				msgs = ((InternalEObject)proposedBy).eInverseRemove(this, HistoryPackage.USER__COLLABORATIONS, User.class, msgs);
			if (newProposedBy != null)
				msgs = ((InternalEObject)newProposedBy).eInverseAdd(this, HistoryPackage.USER__COLLABORATIONS, User.class, msgs);
			msgs = basicSetProposedBy(newProposedBy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HistoryPackage.COLLABORATION__PROPOSED_BY, newProposedBy, newProposedBy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Comment> getComments() {
		if (comments == null) {
			comments = new EObjectContainmentWithInverseEList<Comment>(Comment.class, this, HistoryPackage.COLLABORATION__COMMENTS, HistoryPackage.COMMENT__COMMENTED_ELEMENT);
		}
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Vote> getVotes() {
		if (votes == null) {
			votes = new EObjectContainmentWithInverseEList<Vote>(Vote.class, this, HistoryPackage.COLLABORATION__VOTES, HistoryPackage.VOTE__COLLABORATION);
		}
		return votes;
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
				return RATIONALE_EDEFAULT == null ? rationale != null : !RATIONALE_EDEFAULT.equals(rationale);
			case HistoryPackage.COLLABORATION__PROPOSED_BY:
				return proposedBy != null;
			case HistoryPackage.COLLABORATION__COMMENTS:
				return comments != null && !comments.isEmpty();
			case HistoryPackage.COLLABORATION__VOTES:
				return votes != null && !votes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (rationale: ");
		result.append(rationale);
		result.append(')');
		return result.toString();
	}

} //CollaborationImpl
