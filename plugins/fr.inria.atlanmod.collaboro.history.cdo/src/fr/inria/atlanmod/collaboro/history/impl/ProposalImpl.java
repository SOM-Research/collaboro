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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Proposal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ProposalImpl#getSols <em>Sols</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ProposalImpl#getSelected <em>Selected</em>}</li>
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
	 * The default value of the '{@link #isAccepted() <em>Accepted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAccepted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACCEPTED_EDEFAULT = false;

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
	@SuppressWarnings("unchecked")
	public EList<Solution> getSols() {
		return (EList<Solution>)eDynamicGet(HistoryPackage.PROPOSAL__SOLS, HistoryPackage.Literals.PROPOSAL__SOLS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Solution getSelected() {
		return (Solution)eDynamicGet(HistoryPackage.PROPOSAL__SELECTED, HistoryPackage.Literals.PROPOSAL__SELECTED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Solution basicGetSelected() {
		return (Solution)eDynamicGet(HistoryPackage.PROPOSAL__SELECTED, HistoryPackage.Literals.PROPOSAL__SELECTED, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelected(Solution newSelected) {
		eDynamicSet(HistoryPackage.PROPOSAL__SELECTED, HistoryPackage.Literals.PROPOSAL__SELECTED, newSelected);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Version getVersion() {
		return (Version)eDynamicGet(HistoryPackage.PROPOSAL__VERSION, HistoryPackage.Literals.PROPOSAL__VERSION, true, true);
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
		eDynamicSet(HistoryPackage.PROPOSAL__VERSION, HistoryPackage.Literals.PROPOSAL__VERSION, newVersion);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAccepted() {
		return (Boolean)eDynamicGet(HistoryPackage.PROPOSAL__ACCEPTED, HistoryPackage.Literals.PROPOSAL__ACCEPTED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccepted(boolean newAccepted) {
		eDynamicSet(HistoryPackage.PROPOSAL__ACCEPTED, HistoryPackage.Literals.PROPOSAL__ACCEPTED, newAccepted);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<MetaInfo> getMeta() {
		return (EList<MetaInfo>)eDynamicGet(HistoryPackage.PROPOSAL__META, HistoryPackage.Literals.PROPOSAL__META, true, true);
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
			case HistoryPackage.PROPOSAL__SELECTED:
				if (resolve) return getSelected();
				return basicGetSelected();
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
			case HistoryPackage.PROPOSAL__SELECTED:
				setSelected((Solution)newValue);
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
			case HistoryPackage.PROPOSAL__SELECTED:
				setSelected((Solution)null);
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
				return !getSols().isEmpty();
			case HistoryPackage.PROPOSAL__SELECTED:
				return basicGetSelected() != null;
			case HistoryPackage.PROPOSAL__VERSION:
				return getVersion() != null;
			case HistoryPackage.PROPOSAL__ACCEPTED:
				return isAccepted() != ACCEPTED_EDEFAULT;
			case HistoryPackage.PROPOSAL__META:
				return !getMeta().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ProposalImpl
