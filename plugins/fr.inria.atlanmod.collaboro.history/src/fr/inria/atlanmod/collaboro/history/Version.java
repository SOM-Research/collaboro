/**
 */
package fr.inria.atlanmod.collaboro.history;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Version</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Version#getProposals <em>Proposals</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Version#getPrevious <em>Previous</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Version#isRecommended <em>Recommended</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getVersion()
 * @model
 * @generated
 */
public interface Version extends IdElement {
	/**
	 * Returns the value of the '<em><b>Proposals</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.history.Proposal}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Proposal#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proposals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proposals</em>' containment reference list.
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getVersion_Proposals()
	 * @see fr.inria.atlanmod.collaboro.history.Proposal#getVersion
	 * @model opposite="version" containment="true"
	 * @generated
	 */
	EList<Proposal> getProposals();

	/**
	 * Returns the value of the '<em><b>Previous</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.history.Version}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous</em>' reference list.
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getVersion_Previous()
	 * @model
	 * @generated
	 */
	EList<Version> getPrevious();

	/**
	 * Returns the value of the '<em><b>Recommended</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recommended</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recommended</em>' attribute.
	 * @see #setRecommended(boolean)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getVersion_Recommended()
	 * @model
	 * @generated
	 */
	boolean isRecommended();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Version#isRecommended <em>Recommended</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recommended</em>' attribute.
	 * @see #isRecommended()
	 * @generated
	 */
	void setRecommended(boolean value);

} // Version
