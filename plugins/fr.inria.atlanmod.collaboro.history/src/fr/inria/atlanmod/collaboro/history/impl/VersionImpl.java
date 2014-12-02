/**
 */
package fr.inria.atlanmod.collaboro.history.impl;

import fr.inria.atlanmod.collaboro.history.HistoryPackage;
import fr.inria.atlanmod.collaboro.history.Proposal;
import fr.inria.atlanmod.collaboro.history.Version;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Version</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.VersionImpl#getProposals <em>Proposals</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.VersionImpl#getPrevious <em>Previous</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.impl.VersionImpl#isRecommended <em>Recommended</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VersionImpl extends IdElementImpl implements Version {
	/**
	 * The cached value of the '{@link #getProposals() <em>Proposals</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProposals()
	 * @generated
	 * @ordered
	 */
	protected EList<Proposal> proposals;

	/**
	 * The cached value of the '{@link #getPrevious() <em>Previous</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrevious()
	 * @generated
	 * @ordered
	 */
	protected EList<Version> previous;

	/**
	 * The default value of the '{@link #isRecommended() <em>Recommended</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecommended()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RECOMMENDED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRecommended() <em>Recommended</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecommended()
	 * @generated
	 * @ordered
	 */
	protected boolean recommended = RECOMMENDED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VersionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HistoryPackage.Literals.VERSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Proposal> getProposals() {
		if (proposals == null) {
			proposals = new EObjectContainmentWithInverseEList<Proposal>(Proposal.class, this, HistoryPackage.VERSION__PROPOSALS, HistoryPackage.PROPOSAL__VERSION);
		}
		return proposals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Version> getPrevious() {
		if (previous == null) {
			previous = new EObjectResolvingEList<Version>(Version.class, this, HistoryPackage.VERSION__PREVIOUS);
		}
		return previous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRecommended() {
		return recommended;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecommended(boolean newRecommended) {
		boolean oldRecommended = recommended;
		recommended = newRecommended;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HistoryPackage.VERSION__RECOMMENDED, oldRecommended, recommended));
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
			case HistoryPackage.VERSION__PROPOSALS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProposals()).basicAdd(otherEnd, msgs);
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
			case HistoryPackage.VERSION__PROPOSALS:
				return ((InternalEList<?>)getProposals()).basicRemove(otherEnd, msgs);
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
			case HistoryPackage.VERSION__PROPOSALS:
				return getProposals();
			case HistoryPackage.VERSION__PREVIOUS:
				return getPrevious();
			case HistoryPackage.VERSION__RECOMMENDED:
				return isRecommended();
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
			case HistoryPackage.VERSION__PROPOSALS:
				getProposals().clear();
				getProposals().addAll((Collection<? extends Proposal>)newValue);
				return;
			case HistoryPackage.VERSION__PREVIOUS:
				getPrevious().clear();
				getPrevious().addAll((Collection<? extends Version>)newValue);
				return;
			case HistoryPackage.VERSION__RECOMMENDED:
				setRecommended((Boolean)newValue);
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
			case HistoryPackage.VERSION__PROPOSALS:
				getProposals().clear();
				return;
			case HistoryPackage.VERSION__PREVIOUS:
				getPrevious().clear();
				return;
			case HistoryPackage.VERSION__RECOMMENDED:
				setRecommended(RECOMMENDED_EDEFAULT);
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
			case HistoryPackage.VERSION__PROPOSALS:
				return proposals != null && !proposals.isEmpty();
			case HistoryPackage.VERSION__PREVIOUS:
				return previous != null && !previous.isEmpty();
			case HistoryPackage.VERSION__RECOMMENDED:
				return recommended != RECOMMENDED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (recommended: ");
		result.append(recommended);
		result.append(')');
		return result.toString();
	}

} //VersionImpl
