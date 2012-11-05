/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>New Abstract Syntax Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.NewAbstractSyntaxElementImpl#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NewAbstractSyntaxElementImpl extends AbstractSyntaxElementImpl implements NewAbstractSyntaxElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NewAbstractSyntaxElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.NEW_ABSTRACT_SYNTAX_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EModelElement getElement() {
		return (EModelElement)eDynamicGet(HistoryPackage.NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, HistoryPackage.Literals.NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElement(EModelElement newElement, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newElement, HistoryPackage.NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(EModelElement newElement) {
		eDynamicSet(HistoryPackage.NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, HistoryPackage.Literals.NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, newElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HistoryPackage.NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT:
				return basicSetElement(null, msgs);
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
			case HistoryPackage.NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT:
				return getElement();
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
			case HistoryPackage.NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT:
				setElement((EModelElement)newValue);
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
			case HistoryPackage.NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT:
				setElement((EModelElement)null);
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
			case HistoryPackage.NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT:
				return getElement() != null;
		}
		return super.eIsSet(featureID);
	}

} //NewAbstractSyntaxElementImpl
