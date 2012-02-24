/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.history;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Meta Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.MetaInfo#getProposal <em>Proposal</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getMetaInfo()
 * @model abstract="true"
 * @generated
 */
public interface MetaInfo extends EObject {

	/**
	 * Returns the value of the '<em><b>Proposal</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.Proposal#getMeta <em>Meta</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proposal</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proposal</em>' container reference.
	 * @see #setProposal(Proposal)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getMetaInfo_Proposal()
	 * @see fr.inria.atlanmod.collaboro.history.Proposal#getMeta
	 * @model opposite="meta" transient="false"
	 * @generated
	 */
	Proposal getProposal();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.MetaInfo#getProposal <em>Proposal</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proposal</em>' container reference.
	 * @see #getProposal()
	 * @generated
	 */
	void setProposal(Proposal value);
} // MetaInfo
