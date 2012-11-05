/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation.impl;

import fr.inria.atlanmod.collaboro.notation.Color;
import fr.inria.atlanmod.collaboro.notation.GraphicalElement;
import fr.inria.atlanmod.collaboro.notation.NotationPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graphical Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.GraphicalElementImpl#getX <em>X</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.GraphicalElementImpl#getY <em>Y</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.GraphicalElementImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.GraphicalElementImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.GraphicalElementImpl#getFill <em>Fill</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.notation.impl.GraphicalElementImpl#getStroke <em>Stroke</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GraphicalElementImpl extends NotationElementImpl implements GraphicalElement {
	/**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected static final int X_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected static final int Y_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int HEIGHT_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int WIDTH_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getFill() <em>Fill</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFill()
	 * @generated
	 * @ordered
	 */
	protected static final Color FILL_EDEFAULT = Color.BLACK;

	/**
	 * The default value of the '{@link #getStroke() <em>Stroke</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStroke()
	 * @generated
	 * @ordered
	 */
	protected static final Color STROKE_EDEFAULT = Color.BLACK;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphicalElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NotationPackage.Literals.GRAPHICAL_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getX() {
		return (Integer)eDynamicGet(NotationPackage.GRAPHICAL_ELEMENT__X, NotationPackage.Literals.GRAPHICAL_ELEMENT__X, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setX(int newX) {
		eDynamicSet(NotationPackage.GRAPHICAL_ELEMENT__X, NotationPackage.Literals.GRAPHICAL_ELEMENT__X, newX);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getY() {
		return (Integer)eDynamicGet(NotationPackage.GRAPHICAL_ELEMENT__Y, NotationPackage.Literals.GRAPHICAL_ELEMENT__Y, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setY(int newY) {
		eDynamicSet(NotationPackage.GRAPHICAL_ELEMENT__Y, NotationPackage.Literals.GRAPHICAL_ELEMENT__Y, newY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHeight() {
		return (Integer)eDynamicGet(NotationPackage.GRAPHICAL_ELEMENT__HEIGHT, NotationPackage.Literals.GRAPHICAL_ELEMENT__HEIGHT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeight(int newHeight) {
		eDynamicSet(NotationPackage.GRAPHICAL_ELEMENT__HEIGHT, NotationPackage.Literals.GRAPHICAL_ELEMENT__HEIGHT, newHeight);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWidth() {
		return (Integer)eDynamicGet(NotationPackage.GRAPHICAL_ELEMENT__WIDTH, NotationPackage.Literals.GRAPHICAL_ELEMENT__WIDTH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(int newWidth) {
		eDynamicSet(NotationPackage.GRAPHICAL_ELEMENT__WIDTH, NotationPackage.Literals.GRAPHICAL_ELEMENT__WIDTH, newWidth);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getFill() {
		return (Color)eDynamicGet(NotationPackage.GRAPHICAL_ELEMENT__FILL, NotationPackage.Literals.GRAPHICAL_ELEMENT__FILL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFill(Color newFill) {
		eDynamicSet(NotationPackage.GRAPHICAL_ELEMENT__FILL, NotationPackage.Literals.GRAPHICAL_ELEMENT__FILL, newFill);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getStroke() {
		return (Color)eDynamicGet(NotationPackage.GRAPHICAL_ELEMENT__STROKE, NotationPackage.Literals.GRAPHICAL_ELEMENT__STROKE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStroke(Color newStroke) {
		eDynamicSet(NotationPackage.GRAPHICAL_ELEMENT__STROKE, NotationPackage.Literals.GRAPHICAL_ELEMENT__STROKE, newStroke);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NotationPackage.GRAPHICAL_ELEMENT__X:
				return getX();
			case NotationPackage.GRAPHICAL_ELEMENT__Y:
				return getY();
			case NotationPackage.GRAPHICAL_ELEMENT__HEIGHT:
				return getHeight();
			case NotationPackage.GRAPHICAL_ELEMENT__WIDTH:
				return getWidth();
			case NotationPackage.GRAPHICAL_ELEMENT__FILL:
				return getFill();
			case NotationPackage.GRAPHICAL_ELEMENT__STROKE:
				return getStroke();
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
			case NotationPackage.GRAPHICAL_ELEMENT__X:
				setX((Integer)newValue);
				return;
			case NotationPackage.GRAPHICAL_ELEMENT__Y:
				setY((Integer)newValue);
				return;
			case NotationPackage.GRAPHICAL_ELEMENT__HEIGHT:
				setHeight((Integer)newValue);
				return;
			case NotationPackage.GRAPHICAL_ELEMENT__WIDTH:
				setWidth((Integer)newValue);
				return;
			case NotationPackage.GRAPHICAL_ELEMENT__FILL:
				setFill((Color)newValue);
				return;
			case NotationPackage.GRAPHICAL_ELEMENT__STROKE:
				setStroke((Color)newValue);
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
			case NotationPackage.GRAPHICAL_ELEMENT__X:
				setX(X_EDEFAULT);
				return;
			case NotationPackage.GRAPHICAL_ELEMENT__Y:
				setY(Y_EDEFAULT);
				return;
			case NotationPackage.GRAPHICAL_ELEMENT__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case NotationPackage.GRAPHICAL_ELEMENT__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case NotationPackage.GRAPHICAL_ELEMENT__FILL:
				setFill(FILL_EDEFAULT);
				return;
			case NotationPackage.GRAPHICAL_ELEMENT__STROKE:
				setStroke(STROKE_EDEFAULT);
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
			case NotationPackage.GRAPHICAL_ELEMENT__X:
				return getX() != X_EDEFAULT;
			case NotationPackage.GRAPHICAL_ELEMENT__Y:
				return getY() != Y_EDEFAULT;
			case NotationPackage.GRAPHICAL_ELEMENT__HEIGHT:
				return getHeight() != HEIGHT_EDEFAULT;
			case NotationPackage.GRAPHICAL_ELEMENT__WIDTH:
				return getWidth() != WIDTH_EDEFAULT;
			case NotationPackage.GRAPHICAL_ELEMENT__FILL:
				return getFill() != FILL_EDEFAULT;
			case NotationPackage.GRAPHICAL_ELEMENT__STROKE:
				return getStroke() != STROKE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //GraphicalElementImpl
