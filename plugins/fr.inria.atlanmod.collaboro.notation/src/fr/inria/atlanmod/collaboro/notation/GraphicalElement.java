/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graphical Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getX <em>X</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getY <em>Y</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getHeight <em>Height</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getWidth <em>Width</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getFill <em>Fill</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getStroke <em>Stroke</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getGraphicalElement()
 * @model abstract="true"
 * @generated
 */
public interface GraphicalElement extends NotationElement {
	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(int)
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getGraphicalElement_X()
	 * @model
	 * @generated
	 */
	int getX();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(int value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(int)
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getGraphicalElement_Y()
	 * @model
	 * @generated
	 */
	int getY();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(int value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(int)
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getGraphicalElement_Height()
	 * @model
	 * @generated
	 */
	int getHeight();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(int value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(int)
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getGraphicalElement_Width()
	 * @model
	 * @generated
	 */
	int getWidth();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(int value);

	/**
	 * Returns the value of the '<em><b>Fill</b></em>' attribute.
	 * The default value is <code>"white"</code>.
	 * The literals are from the enumeration {@link fr.inria.atlanmod.collaboro.notation.Color}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fill</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill</em>' attribute.
	 * @see fr.inria.atlanmod.collaboro.notation.Color
	 * @see #setFill(Color)
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getGraphicalElement_Fill()
	 * @model default="white"
	 * @generated
	 */
	Color getFill();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getFill <em>Fill</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill</em>' attribute.
	 * @see fr.inria.atlanmod.collaboro.notation.Color
	 * @see #getFill()
	 * @generated
	 */
	void setFill(Color value);

	/**
	 * Returns the value of the '<em><b>Stroke</b></em>' attribute.
	 * The default value is <code>"black"</code>.
	 * The literals are from the enumeration {@link fr.inria.atlanmod.collaboro.notation.Color}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stroke</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stroke</em>' attribute.
	 * @see fr.inria.atlanmod.collaboro.notation.Color
	 * @see #setStroke(Color)
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#getGraphicalElement_Stroke()
	 * @model default="black"
	 * @generated
	 */
	Color getStroke();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getStroke <em>Stroke</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stroke</em>' attribute.
	 * @see fr.inria.atlanmod.collaboro.notation.Color
	 * @see #getStroke()
	 * @generated
	 */
	void setStroke(Color value);

} // GraphicalElement
