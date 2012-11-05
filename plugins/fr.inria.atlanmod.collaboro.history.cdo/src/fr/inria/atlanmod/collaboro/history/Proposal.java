/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Proposal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Proposal#getSols <em>Sols</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Proposal#getSelected <em>Selected</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Proposal#getVersion <em>Version</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Proposal#isAccepted <em>Accepted</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Proposal#getMeta <em>Meta</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getProposal()
 * @model
 * @generated
 */
public interface Proposal extends Collaboration {
	/**
	 * Returns the value of the '<em><b>Sols</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.history.Solution}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Solution#getProposal <em>Proposal</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sols</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sols</em>' containment reference list.
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getProposal_Sols()
	 * @see fr.inria.atlanmod.collaboro.history.Solution#getProposal
	 * @model opposite="proposal" containment="true"
	 * @generated
	 */
	EList<Solution> getSols();

	/**
	 * Returns the value of the '<em><b>Selected</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selected</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selected</em>' reference.
	 * @see #setSelected(Solution)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getProposal_Selected()
	 * @model
	 * @generated
	 */
	Solution getSelected();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Proposal#getSelected <em>Selected</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selected</em>' reference.
	 * @see #getSelected()
	 * @generated
	 */
	void setSelected(Solution value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Version#getProposals <em>Proposals</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' container reference.
	 * @see #setVersion(Version)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getProposal_Version()
	 * @see fr.inria.atlanmod.collaboro.history.Version#getProposals
	 * @model opposite="proposals" transient="false"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Proposal#getVersion <em>Version</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' container reference.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(Version value);

	/**
	 * Returns the value of the '<em><b>Accepted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accepted</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accepted</em>' attribute.
	 * @see #setAccepted(boolean)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getProposal_Accepted()
	 * @model
	 * @generated
	 */
	boolean isAccepted();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Proposal#isAccepted <em>Accepted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accepted</em>' attribute.
	 * @see #isAccepted()
	 * @generated
	 */
	void setAccepted(boolean value);

	/**
	 * Returns the value of the '<em><b>Meta</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.history.MetaInfo}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.MetaInfo#getProposal <em>Proposal</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Meta</em>' containment reference list.
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getProposal_Meta()
	 * @see fr.inria.atlanmod.collaboro.history.MetaInfo#getProposal
	 * @model opposite="proposal" containment="true"
	 * @generated
	 */
	EList<MetaInfo> getMeta();

} // Proposal
