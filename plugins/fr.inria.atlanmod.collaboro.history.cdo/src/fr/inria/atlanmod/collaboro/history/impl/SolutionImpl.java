/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Solution;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

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
 * </ul>
 * </p>
 *
 * @generated
 */
public class SolutionImpl extends CollaborationImpl implements Solution {
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
	@SuppressWarnings("unchecked")
	public EList<ModelChange> getChanges() {
		return (EList<ModelChange>)eDynamicGet(HistoryPackage.SOLUTION__CHANGES, HistoryPackage.Literals.SOLUTION__CHANGES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Proposal getProposal() {
		return (Proposal)eDynamicGet(HistoryPackage.SOLUTION__PROPOSAL, HistoryPackage.Literals.SOLUTION__PROPOSAL, true, true);
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
		eDynamicSet(HistoryPackage.SOLUTION__PROPOSAL, HistoryPackage.Literals.SOLUTION__PROPOSAL, newProposal);
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
				return !getChanges().isEmpty();
			case HistoryPackage.SOLUTION__PROPOSAL:
				return getProposal() != null;
		}
		return super.eIsSet(featureID);
	}

} //SolutionImpl
