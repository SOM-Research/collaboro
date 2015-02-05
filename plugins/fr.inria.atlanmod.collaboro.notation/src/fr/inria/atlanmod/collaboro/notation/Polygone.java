/**
 */
package fr.inria.atlanmod.collaboro.notation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Polygone</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.Polygone#getLines <em>Lines</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getPolygone()
 * @model
 * @generated
 */
public interface Polygone extends Figure {
	/**
	 * Returns the value of the '<em><b>Lines</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.collaboro.notation.Line}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lines</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lines</em>' containment reference list.
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getPolygone_Lines()
	 * @model containment="true"
	 * @generated
	 */
	EList<Line> getLines();

} // Polygone
