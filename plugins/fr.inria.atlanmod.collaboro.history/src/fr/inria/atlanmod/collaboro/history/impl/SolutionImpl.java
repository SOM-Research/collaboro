/**
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Solution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.SolutionImpl#getChanges <em>Changes</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.SolutionImpl#getProposal <em>Proposal</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.SolutionImpl#getChangesText <em>Changes Text</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SolutionImpl extends CollaborationImpl implements Solution {
	/**
	 * The cached value of the '{@link #getChanges() <em>Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelChange> changes;

	/**
	 * The default value of the '{@link #getChangesText() <em>Changes Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangesText()
	 * @generated
	 * @ordered
	 */
	protected static final String CHANGES_TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChangesText() <em>Changes Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangesText()
	 * @generated
	 * @ordered
	 */
	protected String changesText = CHANGES_TEXT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SolutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.SOLUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelChange> getChanges() {
		if (changes == null) {
			changes = new EObjectContainmentWithInverseEList<ModelChange>(ModelChange.class, this, HistoryPackage.SOLUTION__CHANGES, HistoryPackage.MODEL_CHANGE__SOLUTION);
		}
		return changes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Proposal getProposal() {
		if (eContainerFeatureID() != HistoryPackage.SOLUTION__PROPOSAL) return null;
		return (Proposal)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProposal(Proposal newProposal, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newProposal, HistoryPackage.SOLUTION__PROPOSAL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProposal(Proposal newProposal) {
		if (newProposal != eInternalContainer() || (eContainerFeatureID() != HistoryPackage.SOLUTION__PROPOSAL && newProposal != null)) {
			if (EcoreUtil.isAncestor(this, newProposal))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newProposal != null)
				msgs = ((InternalEObject)newProposal).eInverseAdd(this, HistoryPackage.PROPOSAL__SOLS, Proposal.class, msgs);
			msgs = basicSetProposal(newProposal, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HistoryPackage.SOLUTION__PROPOSAL, newProposal, newProposal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChangesText() {
		return changesText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangesText(String newChangesText) {
		String oldChangesText = changesText;
		changesText = newChangesText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HistoryPackage.SOLUTION__CHANGES_TEXT, oldChangesText, changesText));
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
			case HistoryPackage.SOLUTION__CHANGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChanges()).basicAdd(otherEnd, msgs);
			case HistoryPackage.SOLUTION__PROPOSAL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetProposal((Proposal)otherEnd, msgs);
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
			case HistoryPackage.SOLUTION__CHANGES:
				return ((InternalEList<?>)getChanges()).basicRemove(otherEnd, msgs);
			case HistoryPackage.SOLUTION__PROPOSAL:
				return basicSetProposal(null, msgs);
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
			case HistoryPackage.SOLUTION__PROPOSAL:
				return eInternalContainer().eInverseRemove(this, HistoryPackage.PROPOSAL__SOLS, Proposal.class, msgs);
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
			case HistoryPackage.SOLUTION__CHANGES:
				return getChanges();
			case HistoryPackage.SOLUTION__PROPOSAL:
				return getProposal();
			case HistoryPackage.SOLUTION__CHANGES_TEXT:
				return getChangesText();
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
			case HistoryPackage.SOLUTION__CHANGES:
				getChanges().clear();
				getChanges().addAll((Collection<? extends ModelChange>)newValue);
				return;
			case HistoryPackage.SOLUTION__PROPOSAL:
				setProposal((Proposal)newValue);
				return;
			case HistoryPackage.SOLUTION__CHANGES_TEXT:
				setChangesText((String)newValue);
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
			case HistoryPackage.SOLUTION__CHANGES:
				getChanges().clear();
				return;
			case HistoryPackage.SOLUTION__PROPOSAL:
				setProposal((Proposal)null);
				return;
			case HistoryPackage.SOLUTION__CHANGES_TEXT:
				setChangesText(CHANGES_TEXT_EDEFAULT);
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
			case HistoryPackage.SOLUTION__CHANGES:
				return changes != null && !changes.isEmpty();
			case HistoryPackage.SOLUTION__PROPOSAL:
				return getProposal() != null;
			case HistoryPackage.SOLUTION__CHANGES_TEXT:
				return CHANGES_TEXT_EDEFAULT == null ? changesText != null : !CHANGES_TEXT_EDEFAULT.equals(changesText);
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
		result.append(" (changesText: ");
		result.append(changesText);
		result.append(')');
		return result.toString();
	}

} //SolutionImpl
