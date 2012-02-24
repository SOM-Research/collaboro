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
 * A representation of the model object '<em><b>Version</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Version#getProposals <em>Proposals</em>}</li>
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

} // Version
