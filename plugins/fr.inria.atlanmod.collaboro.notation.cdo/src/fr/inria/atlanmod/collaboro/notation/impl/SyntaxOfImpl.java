/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation.impl;

import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.notation.NotationPackage;
import fr.inria.atlanmod.collaboro.notation.SyntaxOf;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Syntax Of</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.SyntaxOfImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.SyntaxOfImpl#getSeparator <em>Separator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SyntaxOfImpl extends NotationElementImpl implements SyntaxOf {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SyntaxOfImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NotationPackage.Literals.SYNTAX_OF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReference() {
		return (EReference)eDynamicGet(NotationPackage.SYNTAX_OF__REFERENCE, NotationPackage.Literals.SYNTAX_OF__REFERENCE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference basicGetReference() {
		return (EReference)eDynamicGet(NotationPackage.SYNTAX_OF__REFERENCE, NotationPackage.Literals.SYNTAX_OF__REFERENCE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReference(EReference newReference) {
		eDynamicSet(NotationPackage.SYNTAX_OF__REFERENCE, NotationPackage.Literals.SYNTAX_OF__REFERENCE, newReference);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotationElement getSeparator() {
		return (NotationElement)eDynamicGet(NotationPackage.SYNTAX_OF__SEPARATOR, NotationPackage.Literals.SYNTAX_OF__SEPARATOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotationElement basicGetSeparator() {
		return (NotationElement)eDynamicGet(NotationPackage.SYNTAX_OF__SEPARATOR, NotationPackage.Literals.SYNTAX_OF__SEPARATOR, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeparator(NotationElement newSeparator) {
		eDynamicSet(NotationPackage.SYNTAX_OF__SEPARATOR, NotationPackage.Literals.SYNTAX_OF__SEPARATOR, newSeparator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NotationPackage.SYNTAX_OF__REFERENCE:
				if (resolve) return getReference();
				return basicGetReference();
			case NotationPackage.SYNTAX_OF__SEPARATOR:
				if (resolve) return getSeparator();
				return basicGetSeparator();
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
			case NotationPackage.SYNTAX_OF__REFERENCE:
				setReference((EReference)newValue);
				return;
			case NotationPackage.SYNTAX_OF__SEPARATOR:
				setSeparator((NotationElement)newValue);
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
			case NotationPackage.SYNTAX_OF__REFERENCE:
				setReference((EReference)null);
				return;
			case NotationPackage.SYNTAX_OF__SEPARATOR:
				setSeparator((NotationElement)null);
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
			case NotationPackage.SYNTAX_OF__REFERENCE:
				return basicGetReference() != null;
			case NotationPackage.SYNTAX_OF__SEPARATOR:
				return basicGetSeparator() != null;
		}
		return super.eIsSet(featureID);
	}

} //SyntaxOfImpl
