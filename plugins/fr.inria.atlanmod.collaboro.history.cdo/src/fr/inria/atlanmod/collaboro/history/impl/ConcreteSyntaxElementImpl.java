/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;

import fr.inria.atlanmod.collaboro.notation.NotationElement;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concrete Syntax Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ConcreteSyntaxElementImpl#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConcreteSyntaxElementImpl extends SyntaxElementImpl implements ConcreteSyntaxElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConcreteSyntaxElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.CONCRETE_SYNTAX_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotationElement getElement() {
		return (NotationElement)eDynamicGet(HistoryPackage.CONCRETE_SYNTAX_ELEMENT__ELEMENT, HistoryPackage.Literals.CONCRETE_SYNTAX_ELEMENT__ELEMENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotationElement basicGetElement() {
		return (NotationElement)eDynamicGet(HistoryPackage.CONCRETE_SYNTAX_ELEMENT__ELEMENT, HistoryPackage.Literals.CONCRETE_SYNTAX_ELEMENT__ELEMENT, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(NotationElement newElement) {
		eDynamicSet(HistoryPackage.CONCRETE_SYNTAX_ELEMENT__ELEMENT, HistoryPackage.Literals.CONCRETE_SYNTAX_ELEMENT__ELEMENT, newElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HistoryPackage.CONCRETE_SYNTAX_ELEMENT__ELEMENT:
				if (resolve) return getElement();
				return basicGetElement();
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
			case HistoryPackage.CONCRETE_SYNTAX_ELEMENT__ELEMENT:
				setElement((NotationElement)newValue);
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
			case HistoryPackage.CONCRETE_SYNTAX_ELEMENT__ELEMENT:
				setElement((NotationElement)null);
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
			case HistoryPackage.CONCRETE_SYNTAX_ELEMENT__ELEMENT:
				return basicGetElement() != null;
		}
		return super.eIsSet(featureID);
	}

} //ConcreteSyntaxElementImpl
