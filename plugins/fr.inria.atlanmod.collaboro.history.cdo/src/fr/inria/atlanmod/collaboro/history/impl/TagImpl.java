/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.Tag;
import fr.inria.atlanmod.collaboro.history.TagBased;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.TagImpl#getValue <em>Value</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.TagImpl#getTagCollection <em>Tag Collection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagImpl extends CDOObjectImpl implements Tag {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TagImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.TAG;
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
	public String getValue() {
		return (String)eDynamicGet(HistoryPackage.TAG__VALUE, HistoryPackage.Literals.TAG__VALUE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		eDynamicSet(HistoryPackage.TAG__VALUE, HistoryPackage.Literals.TAG__VALUE, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagBased getTagCollection() {
		return (TagBased)eDynamicGet(HistoryPackage.TAG__TAG_COLLECTION, HistoryPackage.Literals.TAG__TAG_COLLECTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTagCollection(TagBased newTagCollection, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTagCollection, HistoryPackage.TAG__TAG_COLLECTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagCollection(TagBased newTagCollection) {
		eDynamicSet(HistoryPackage.TAG__TAG_COLLECTION, HistoryPackage.Literals.TAG__TAG_COLLECTION, newTagCollection);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HistoryPackage.TAG__TAG_COLLECTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTagCollection((TagBased)otherEnd, msgs);
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
			case HistoryPackage.TAG__TAG_COLLECTION:
				return basicSetTagCollection(null, msgs);
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
			case HistoryPackage.TAG__TAG_COLLECTION:
				return eInternalContainer().eInverseRemove(this, HistoryPackage.TAG_BASED__TAGS, TagBased.class, msgs);
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
			case HistoryPackage.TAG__VALUE:
				return getValue();
			case HistoryPackage.TAG__TAG_COLLECTION:
				return getTagCollection();
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
			case HistoryPackage.TAG__VALUE:
				setValue((String)newValue);
				return;
			case HistoryPackage.TAG__TAG_COLLECTION:
				setTagCollection((TagBased)newValue);
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
			case HistoryPackage.TAG__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case HistoryPackage.TAG__TAG_COLLECTION:
				setTagCollection((TagBased)null);
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
			case HistoryPackage.TAG__VALUE:
				return VALUE_EDEFAULT == null ? getValue() != null : !VALUE_EDEFAULT.equals(getValue());
			case HistoryPackage.TAG__TAG_COLLECTION:
				return getTagCollection() != null;
		}
		return super.eIsSet(featureID);
	}

} //TagImpl
