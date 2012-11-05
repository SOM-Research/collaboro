/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.ModelChange;
import fr.inria.atlanmod.collaboro.history.Solution;
import fr.inria.atlanmod.collaboro.history.SyntaxElement;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ModelChangeImpl#getSolution <em>Solution</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ModelChangeImpl#getReferredElement <em>Referred Element</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ModelChangeImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ModelChangeImpl extends CDOObjectImpl implements ModelChange {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.MODEL_CHANGE;
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
	public Solution getSolution() {
		return (Solution)eDynamicGet(HistoryPackage.MODEL_CHANGE__SOLUTION, HistoryPackage.Literals.MODEL_CHANGE__SOLUTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSolution(Solution newSolution, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSolution, HistoryPackage.MODEL_CHANGE__SOLUTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSolution(Solution newSolution) {
		eDynamicSet(HistoryPackage.MODEL_CHANGE__SOLUTION, HistoryPackage.Literals.MODEL_CHANGE__SOLUTION, newSolution);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SyntaxElement getReferredElement() {
		return (SyntaxElement)eDynamicGet(HistoryPackage.MODEL_CHANGE__REFERRED_ELEMENT, HistoryPackage.Literals.MODEL_CHANGE__REFERRED_ELEMENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferredElement(SyntaxElement newReferredElement, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newReferredElement, HistoryPackage.MODEL_CHANGE__REFERRED_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferredElement(SyntaxElement newReferredElement) {
		eDynamicSet(HistoryPackage.MODEL_CHANGE__REFERRED_ELEMENT, HistoryPackage.Literals.MODEL_CHANGE__REFERRED_ELEMENT, newReferredElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SyntaxElement getTarget() {
		return (SyntaxElement)eDynamicGet(HistoryPackage.MODEL_CHANGE__TARGET, HistoryPackage.Literals.MODEL_CHANGE__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(SyntaxElement newTarget, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newTarget, HistoryPackage.MODEL_CHANGE__TARGET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(SyntaxElement newTarget) {
		eDynamicSet(HistoryPackage.MODEL_CHANGE__TARGET, HistoryPackage.Literals.MODEL_CHANGE__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HistoryPackage.MODEL_CHANGE__SOLUTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSolution((Solution)otherEnd, msgs);
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
			case HistoryPackage.MODEL_CHANGE__SOLUTION:
				return basicSetSolution(null, msgs);
			case HistoryPackage.MODEL_CHANGE__REFERRED_ELEMENT:
				return basicSetReferredElement(null, msgs);
			case HistoryPackage.MODEL_CHANGE__TARGET:
				return basicSetTarget(null, msgs);
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
			case HistoryPackage.MODEL_CHANGE__SOLUTION:
				return eInternalContainer().eInverseRemove(this, HistoryPackage.SOLUTION__CHANGES, Solution.class, msgs);
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
			case HistoryPackage.MODEL_CHANGE__SOLUTION:
				return getSolution();
			case HistoryPackage.MODEL_CHANGE__REFERRED_ELEMENT:
				return getReferredElement();
			case HistoryPackage.MODEL_CHANGE__TARGET:
				return getTarget();
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
			case HistoryPackage.MODEL_CHANGE__SOLUTION:
				setSolution((Solution)newValue);
				return;
			case HistoryPackage.MODEL_CHANGE__REFERRED_ELEMENT:
				setReferredElement((SyntaxElement)newValue);
				return;
			case HistoryPackage.MODEL_CHANGE__TARGET:
				setTarget((SyntaxElement)newValue);
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
			case HistoryPackage.MODEL_CHANGE__SOLUTION:
				setSolution((Solution)null);
				return;
			case HistoryPackage.MODEL_CHANGE__REFERRED_ELEMENT:
				setReferredElement((SyntaxElement)null);
				return;
			case HistoryPackage.MODEL_CHANGE__TARGET:
				setTarget((SyntaxElement)null);
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
			case HistoryPackage.MODEL_CHANGE__SOLUTION:
				return getSolution() != null;
			case HistoryPackage.MODEL_CHANGE__REFERRED_ELEMENT:
				return getReferredElement() != null;
			case HistoryPackage.MODEL_CHANGE__TARGET:
				return getTarget() != null;
		}
		return super.eIsSet(featureID);
	}

} //ModelChangeImpl
