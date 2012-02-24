/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.MetaInfo;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.Version;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Proposal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ProposalImpl#getSols <em>Sols</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ProposalImpl#getDecision <em>Decision</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ProposalImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ProposalImpl#isAccepted <em>Accepted</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ProposalImpl#getMeta <em>Meta</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProposalImpl extends CollaborationImpl implements Proposal {
	/**
	 * The cached value of the '{@link #getSols() <em>Sols</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSols()
	 * @generated
	 * @ordered
	 */
	protected EList<Solution> sols;

	/**
	 * The cached value of the '{@link #getDecision() <em>Decision</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecision()
	 * @generated
	 * @ordered
	 */
	protected Solution decision;

	/**
	 * The default value of the '{@link #isAccepted() <em>Accepted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAccepted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACCEPTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAccepted() <em>Accepted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAccepted()
	 * @generated
	 * @ordered
	 */
	protected boolean accepted = ACCEPTED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMeta() <em>Meta</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeta()
	 * @generated
	 * @ordered
	 */
	protected EList<MetaInfo> meta;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProposalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.PROPOSAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Solution> getSols() {
		if (sols == null) {
			sols = new EObjectContainmentWithInverseEList<Solution>(Solution.class, this, HistoryPackage.PROPOSAL__SOLS, HistoryPackage.SOLUTION__PROPOSAL);
		}
		return sols;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Solution getDecision() {
		if (decision != null && decision.eIsProxy()) {
			InternalEObject oldDecision = (InternalEObject)decision;
			decision = (Solution)eResolveProxy(oldDecision);
			if (decision != oldDecision) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, HistoryPackage.PROPOSAL__DECISION, oldDecision, decision));
			}
		}
		return decision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Solution basicGetDecision() {
		return decision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDecision(Solution newDecision) {
		Solution oldDecision = decision;
		decision = newDecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HistoryPackage.PROPOSAL__DECISION, oldDecision, decision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Version getVersion() {
		if (eContainerFeatureID() != HistoryPackage.PROPOSAL__VERSION) return null;
		return (Version)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVersion(Version newVersion, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newVersion, HistoryPackage.PROPOSAL__VERSION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(Version newVersion) {
		if (newVersion != eInternalContainer() || (eContainerFeatureID() != HistoryPackage.PROPOSAL__VERSION && newVersion != null)) {
			if (EcoreUtil.isAncestor(this, newVersion))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newVersion != null)
				msgs = ((InternalEObject)newVersion).eInverseAdd(this, HistoryPackage.VERSION__PROPOSALS, Version.class, msgs);
			msgs = basicSetVersion(newVersion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HistoryPackage.PROPOSAL__VERSION, newVersion, newVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAccepted() {
		return accepted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccepted(boolean newAccepted) {
		boolean oldAccepted = accepted;
		accepted = newAccepted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HistoryPackage.PROPOSAL__ACCEPTED, oldAccepted, accepted));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaInfo> getMeta() {
		if (meta == null) {
			meta = new EObjectContainmentWithInverseEList<MetaInfo>(MetaInfo.class, this, HistoryPackage.PROPOSAL__META, HistoryPackage.META_INFO__PROPOSAL);
		}
		return meta;
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
			case HistoryPackage.PROPOSAL__SOLS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSols()).basicAdd(otherEnd, msgs);
			case HistoryPackage.PROPOSAL__VERSION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetVersion((Version)otherEnd, msgs);
			case HistoryPackage.PROPOSAL__META:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMeta()).basicAdd(otherEnd, msgs);
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
			case HistoryPackage.PROPOSAL__SOLS:
				return ((InternalEList<?>)getSols()).basicRemove(otherEnd, msgs);
			case HistoryPackage.PROPOSAL__VERSION:
				return basicSetVersion(null, msgs);
			case HistoryPackage.PROPOSAL__META:
				return ((InternalEList<?>)getMeta()).basicRemove(otherEnd, msgs);
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
			case HistoryPackage.PROPOSAL__VERSION:
				return eInternalContainer().eInverseRemove(this, HistoryPackage.VERSION__PROPOSALS, Version.class, msgs);
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
			case HistoryPackage.PROPOSAL__SOLS:
				return getSols();
			case HistoryPackage.PROPOSAL__DECISION:
				if (resolve) return getDecision();
				return basicGetDecision();
			case HistoryPackage.PROPOSAL__VERSION:
				return getVersion();
			case HistoryPackage.PROPOSAL__ACCEPTED:
				return isAccepted();
			case HistoryPackage.PROPOSAL__META:
				return getMeta();
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
			case HistoryPackage.PROPOSAL__SOLS:
				getSols().clear();
				getSols().addAll((Collection<? extends Solution>)newValue);
				return;
			case HistoryPackage.PROPOSAL__DECISION:
				setDecision((Solution)newValue);
				return;
			case HistoryPackage.PROPOSAL__VERSION:
				setVersion((Version)newValue);
				return;
			case HistoryPackage.PROPOSAL__ACCEPTED:
				setAccepted((Boolean)newValue);
				return;
			case HistoryPackage.PROPOSAL__META:
				getMeta().clear();
				getMeta().addAll((Collection<? extends MetaInfo>)newValue);
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
			case HistoryPackage.PROPOSAL__SOLS:
				getSols().clear();
				return;
			case HistoryPackage.PROPOSAL__DECISION:
				setDecision((Solution)null);
				return;
			case HistoryPackage.PROPOSAL__VERSION:
				setVersion((Version)null);
				return;
			case HistoryPackage.PROPOSAL__ACCEPTED:
				setAccepted(ACCEPTED_EDEFAULT);
				return;
			case HistoryPackage.PROPOSAL__META:
				getMeta().clear();
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
			case HistoryPackage.PROPOSAL__SOLS:
				return sols != null && !sols.isEmpty();
			case HistoryPackage.PROPOSAL__DECISION:
				return decision != null;
			case HistoryPackage.PROPOSAL__VERSION:
				return getVersion() != null;
			case HistoryPackage.PROPOSAL__ACCEPTED:
				return accepted != ACCEPTED_EDEFAULT;
			case HistoryPackage.PROPOSAL__META:
				return meta != null && !meta.isEmpty();
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
		result.append(" (accepted: ");
		result.append(accepted);
		result.append(')');
		return result.toString();
	}

} //ProposalImpl
