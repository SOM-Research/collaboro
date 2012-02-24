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
 * A representation of the model object '<em><b>Solution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Solution#getChanges <em>Changes</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Solution#getProposal <em>Proposal</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getSolution()
 * @model
 * @generated
 */
public interface Solution extends Collaboration {
	/**
	 * Returns the value of the '<em><b>Changes</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.history.ModelChange}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.ModelChange#getSolution <em>Solution</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changes</em>' containment reference list.
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getSolution_Changes()
	 * @see fr.inria.atlanmod.collaboro.history.ModelChange#getSolution
	 * @model opposite="solution" containment="true"
	 * @generated
	 */
	EList<ModelChange> getChanges();

	/**
	 * Returns the value of the '<em><b>Proposal</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Proposal#getSols <em>Sols</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proposal</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proposal</em>' container reference.
	 * @see #setProposal(Proposal)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getSolution_Proposal()
	 * @see fr.inria.atlanmod.collaboro.history.Proposal#getSols
	 * @model opposite="sols" transient="false"
	 * @generated
	 */
	Proposal getProposal();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Solution#getProposal <em>Proposal</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proposal</em>' container reference.
	 * @see #getProposal()
	 * @generated
	 */
	void setProposal(Proposal value);

} // Solution
