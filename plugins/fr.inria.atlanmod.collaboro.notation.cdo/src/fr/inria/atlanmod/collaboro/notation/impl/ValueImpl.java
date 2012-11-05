/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation.impl;

import fr.inria.atlanmod.collaboro.notation.NotationPackage;
import fr.inria.atlanmod.collaboro.notation.Value;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.ValueImpl#getSeparator <em>Separator</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.ValueImpl#getAttribute <em>Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ValueImpl extends TextualElementImpl implements Value {
	/**
	 * The default value of the '{@link #getSeparator() <em>Separator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeparator()
	 * @generated
	 * @ordered
	 */
	protected static final String SEPARATOR_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NotationPackage.Literals.VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSeparator() {
		return (String)eDynamicGet(NotationPackage.VALUE__SEPARATOR, NotationPackage.Literals.VALUE__SEPARATOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeparator(String newSeparator) {
		eDynamicSet(NotationPackage.VALUE__SEPARATOR, NotationPackage.Literals.VALUE__SEPARATOR, newSeparator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute() {
		return (EAttribute)eDynamicGet(NotationPackage.VALUE__ATTRIBUTE, NotationPackage.Literals.VALUE__ATTRIBUTE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute basicGetAttribute() {
		return (EAttribute)eDynamicGet(NotationPackage.VALUE__ATTRIBUTE, NotationPackage.Literals.VALUE__ATTRIBUTE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttribute(EAttribute newAttribute) {
		eDynamicSet(NotationPackage.VALUE__ATTRIBUTE, NotationPackage.Literals.VALUE__ATTRIBUTE, newAttribute);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NotationPackage.VALUE__SEPARATOR:
				return getSeparator();
			case NotationPackage.VALUE__ATTRIBUTE:
				if (resolve) return getAttribute();
				return basicGetAttribute();
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
			case NotationPackage.VALUE__SEPARATOR:
				setSeparator((String)newValue);
				return;
			case NotationPackage.VALUE__ATTRIBUTE:
				setAttribute((EAttribute)newValue);
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
			case NotationPackage.VALUE__SEPARATOR:
				setSeparator(SEPARATOR_EDEFAULT);
				return;
			case NotationPackage.VALUE__ATTRIBUTE:
				setAttribute((EAttribute)null);
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
			case NotationPackage.VALUE__SEPARATOR:
				return SEPARATOR_EDEFAULT == null ? getSeparator() != null : !SEPARATOR_EDEFAULT.equals(getSeparator());
			case NotationPackage.VALUE__ATTRIBUTE:
				return basicGetAttribute() != null;
		}
		return super.eIsSet(featureID);
	}

} //ValueImpl
