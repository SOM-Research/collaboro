/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Existing Abstract Syntax Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.ExistingAbstractSyntaxElementImpl#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExistingAbstractSyntaxElementImpl extends AbstractSyntaxElementImpl implements ExistingAbstractSyntaxElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExistingAbstractSyntaxElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.EXISTING_ABSTRACT_SYNTAX_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EModelElement getElement() {
		return (EModelElement)eDynamicGet(HistoryPackage.EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, HistoryPackage.Literals.EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EModelElement basicGetElement() {
		return (EModelElement)eDynamicGet(HistoryPackage.EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, HistoryPackage.Literals.EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(EModelElement newElement) {
		eDynamicSet(HistoryPackage.EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, HistoryPackage.Literals.EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT, newElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HistoryPackage.EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT:
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
			case HistoryPackage.EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT:
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
			case HistoryPackage.EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT:
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
			case HistoryPackage.EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT:
				return basicGetElement() != null;
		}
		return super.eIsSet(featureID);
	}

} //ExistingAbstractSyntaxElementImpl
