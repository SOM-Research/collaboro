/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.Collaboration;
import fr.inria.atlanmod.collaboro.history.Comment;
import fr.inria.atlanmod.collaboro.history.HistoryPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.CommentImpl#getCommentedElement <em>Commented Element</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.CommentImpl#isIncluded <em>Included</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommentImpl extends CollaborationImpl implements Comment {
	/**
	 * The default value of the '{@link #isIncluded() <em>Included</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIncluded()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INCLUDED_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.COMMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collaboration getCommentedElement() {
		return (Collaboration)eDynamicGet(HistoryPackage.COMMENT__COMMENTED_ELEMENT, HistoryPackage.Literals.COMMENT__COMMENTED_ELEMENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCommentedElement(Collaboration newCommentedElement, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCommentedElement, HistoryPackage.COMMENT__COMMENTED_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCommentedElement(Collaboration newCommentedElement) {
		eDynamicSet(HistoryPackage.COMMENT__COMMENTED_ELEMENT, HistoryPackage.Literals.COMMENT__COMMENTED_ELEMENT, newCommentedElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIncluded() {
		return (Boolean)eDynamicGet(HistoryPackage.COMMENT__INCLUDED, HistoryPackage.Literals.COMMENT__INCLUDED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncluded(boolean newIncluded) {
		eDynamicSet(HistoryPackage.COMMENT__INCLUDED, HistoryPackage.Literals.COMMENT__INCLUDED, newIncluded);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HistoryPackage.COMMENT__COMMENTED_ELEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCommentedElement((Collaboration)otherEnd, msgs);
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
			case HistoryPackage.COMMENT__COMMENTED_ELEMENT:
				return basicSetCommentedElement(null, msgs);
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
			case HistoryPackage.COMMENT__COMMENTED_ELEMENT:
				return eInternalContainer().eInverseRemove(this, HistoryPackage.COLLABORATION__COMMENTS, Collaboration.class, msgs);
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
			case HistoryPackage.COMMENT__COMMENTED_ELEMENT:
				return getCommentedElement();
			case HistoryPackage.COMMENT__INCLUDED:
				return isIncluded();
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
			case HistoryPackage.COMMENT__COMMENTED_ELEMENT:
				setCommentedElement((Collaboration)newValue);
				return;
			case HistoryPackage.COMMENT__INCLUDED:
				setIncluded((Boolean)newValue);
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
			case HistoryPackage.COMMENT__COMMENTED_ELEMENT:
				setCommentedElement((Collaboration)null);
				return;
			case HistoryPackage.COMMENT__INCLUDED:
				setIncluded(INCLUDED_EDEFAULT);
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
			case HistoryPackage.COMMENT__COMMENTED_ELEMENT:
				return getCommentedElement() != null;
			case HistoryPackage.COMMENT__INCLUDED:
				return isIncluded() != INCLUDED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //CommentImpl
