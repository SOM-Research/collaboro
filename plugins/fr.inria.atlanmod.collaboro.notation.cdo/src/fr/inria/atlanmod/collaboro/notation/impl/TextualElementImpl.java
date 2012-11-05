/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation.impl;

import fr.inria.atlanmod.collaboro.notation.Color;
import fr.inria.atlanmod.collaboro.notation.NotationPackage;
import fr.inria.atlanmod.collaboro.notation.TextualElement;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Textual Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.TextualElementImpl#getFill <em>Fill</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TextualElementImpl extends NotationElementImpl implements TextualElement {
	/**
	 * The default value of the '{@link #getFill() <em>Fill</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFill()
	 * @generated
	 * @ordered
	 */
	protected static final Color FILL_EDEFAULT = Color.BLACK;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextualElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NotationPackage.Literals.TEXTUAL_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getFill() {
		return (Color)eDynamicGet(NotationPackage.TEXTUAL_ELEMENT__FILL, NotationPackage.Literals.TEXTUAL_ELEMENT__FILL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFill(Color newFill) {
		eDynamicSet(NotationPackage.TEXTUAL_ELEMENT__FILL, NotationPackage.Literals.TEXTUAL_ELEMENT__FILL, newFill);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NotationPackage.TEXTUAL_ELEMENT__FILL:
				return getFill();
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
			case NotationPackage.TEXTUAL_ELEMENT__FILL:
				setFill((Color)newValue);
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
			case NotationPackage.TEXTUAL_ELEMENT__FILL:
				setFill(FILL_EDEFAULT);
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
			case NotationPackage.TEXTUAL_ELEMENT__FILL:
				return getFill() != FILL_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //TextualElementImpl
