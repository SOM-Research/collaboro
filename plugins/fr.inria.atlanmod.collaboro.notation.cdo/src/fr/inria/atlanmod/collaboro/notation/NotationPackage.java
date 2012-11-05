/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.inria.atlanmod.collaboro.notation.NotationFactory
 * @model kind="package"
 * @generated
 */
public interface NotationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "notation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://atlanmod.fr/collaboro/notation";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "notation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NotationPackage eINSTANCE = fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.IdElementImpl <em>Id Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.IdElementImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getIdElement()
	 * @generated
	 */
	int ID_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_ELEMENT__ID = 0;

	/**
	 * The number of structural features of the '<em>Id Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.NotationElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationElementImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getNotationElement()
	 * @generated
	 */
	int NOTATION_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTATION_ELEMENT__ID = ID_ELEMENT__ID;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTATION_ELEMENT_FEATURE_COUNT = ID_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.GraphicalElementImpl <em>Graphical Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.GraphicalElementImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getGraphicalElement()
	 * @generated
	 */
	int GRAPHICAL_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_ELEMENT__ID = NOTATION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_ELEMENT__X = NOTATION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_ELEMENT__Y = NOTATION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_ELEMENT__HEIGHT = NOTATION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_ELEMENT__WIDTH = NOTATION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_ELEMENT__FILL = NOTATION_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Stroke</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_ELEMENT__STROKE = NOTATION_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Graphical Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_ELEMENT_FEATURE_COUNT = NOTATION_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.FigureImpl <em>Figure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.FigureImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getFigure()
	 * @generated
	 */
	int FIGURE = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE__ID = GRAPHICAL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE__X = GRAPHICAL_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE__Y = GRAPHICAL_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE__HEIGHT = GRAPHICAL_ELEMENT__HEIGHT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE__WIDTH = GRAPHICAL_ELEMENT__WIDTH;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE__FILL = GRAPHICAL_ELEMENT__FILL;

	/**
	 * The feature id for the '<em><b>Stroke</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE__STROKE = GRAPHICAL_ELEMENT__STROKE;

	/**
	 * The number of structural features of the '<em>Figure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIGURE_FEATURE_COUNT = GRAPHICAL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.RectangleImpl <em>Rectangle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.RectangleImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getRectangle()
	 * @generated
	 */
	int RECTANGLE = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__ID = FIGURE__ID;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__X = FIGURE__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__Y = FIGURE__Y;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__HEIGHT = FIGURE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__WIDTH = FIGURE__WIDTH;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__FILL = FIGURE__FILL;

	/**
	 * The feature id for the '<em><b>Stroke</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__STROKE = FIGURE__STROKE;

	/**
	 * The number of structural features of the '<em>Rectangle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE_FEATURE_COUNT = FIGURE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.LineImpl <em>Line</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.LineImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getLine()
	 * @generated
	 */
	int LINE = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__ID = GRAPHICAL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__X = GRAPHICAL_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__Y = GRAPHICAL_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__HEIGHT = GRAPHICAL_ELEMENT__HEIGHT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__WIDTH = GRAPHICAL_ELEMENT__WIDTH;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__FILL = GRAPHICAL_ELEMENT__FILL;

	/**
	 * The feature id for the '<em><b>Stroke</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__STROKE = GRAPHICAL_ELEMENT__STROKE;

	/**
	 * The number of structural features of the '<em>Line</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_FEATURE_COUNT = GRAPHICAL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.LabelImpl <em>Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.LabelImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getLabel()
	 * @generated
	 */
	int LABEL = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ID = GRAPHICAL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__X = GRAPHICAL_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__Y = GRAPHICAL_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__HEIGHT = GRAPHICAL_ELEMENT__HEIGHT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__WIDTH = GRAPHICAL_ELEMENT__WIDTH;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__FILL = GRAPHICAL_ELEMENT__FILL;

	/**
	 * The feature id for the '<em><b>Stroke</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__STROKE = GRAPHICAL_ELEMENT__STROKE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TEXT = GRAPHICAL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = GRAPHICAL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.TextualElementImpl <em>Textual Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.TextualElementImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getTextualElement()
	 * @generated
	 */
	int TEXTUAL_ELEMENT = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTUAL_ELEMENT__ID = NOTATION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTUAL_ELEMENT__FILL = NOTATION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Textual Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTUAL_ELEMENT_FEATURE_COUNT = NOTATION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.TokenImpl <em>Token</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.TokenImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getToken()
	 * @generated
	 */
	int TOKEN = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN__ID = TEXTUAL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN__FILL = TEXTUAL_ELEMENT__FILL;

	/**
	 * The number of structural features of the '<em>Token</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN_FEATURE_COUNT = TEXTUAL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.KeywordImpl <em>Keyword</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.KeywordImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getKeyword()
	 * @generated
	 */
	int KEYWORD = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD__ID = TEXTUAL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD__FILL = TEXTUAL_ELEMENT__FILL;

	/**
	 * The number of structural features of the '<em>Keyword</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD_FEATURE_COUNT = TEXTUAL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.ValueImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__ID = TEXTUAL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__FILL = TEXTUAL_ELEMENT__FILL;

	/**
	 * The feature id for the '<em><b>Separator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__SEPARATOR = TEXTUAL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__ATTRIBUTE = TEXTUAL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = TEXTUAL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.AttributeValueImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getAttributeValue()
	 * @generated
	 */
	int ATTRIBUTE_VALUE = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VALUE__ID = VALUE__ID;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VALUE__FILL = VALUE__FILL;

	/**
	 * The feature id for the '<em><b>Separator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VALUE__SEPARATOR = VALUE__SEPARATOR;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VALUE__ATTRIBUTE = VALUE__ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.ReferenceValueImpl <em>Reference Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.ReferenceValueImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getReferenceValue()
	 * @generated
	 */
	int REFERENCE_VALUE = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE__ID = VALUE__ID;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE__FILL = VALUE__FILL;

	/**
	 * The feature id for the '<em><b>Separator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE__SEPARATOR = VALUE__SEPARATOR;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE__ATTRIBUTE = VALUE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE__REFERENCE = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.SyntaxOfImpl <em>Syntax Of</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.SyntaxOfImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getSyntaxOf()
	 * @generated
	 */
	int SYNTAX_OF = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_OF__ID = NOTATION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_OF__REFERENCE = NOTATION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Separator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_OF__SEPARATOR = NOTATION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Syntax Of</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_OF_FEATURE_COUNT = NOTATION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.CompositeImpl <em>Composite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.CompositeImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getComposite()
	 * @generated
	 */
	int COMPOSITE = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__ID = NOTATION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sub Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__SUB_ELEMENTS = NOTATION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Composite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_FEATURE_COUNT = NOTATION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.impl.DefinitionImpl <em>Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.impl.DefinitionImpl
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getDefinition()
	 * @generated
	 */
	int DEFINITION = 15;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__ELEMENTS = 0;

	/**
	 * The number of structural features of the '<em>Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION_FEATURE_COUNT = 1;


	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.notation.Color <em>Color</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.notation.Color
	 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getColor()
	 * @generated
	 */
	int COLOR = 16;


	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.IdElement <em>Id Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Id Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.IdElement
	 * @generated
	 */
	EClass getIdElement();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.notation.IdElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.IdElement#getId()
	 * @see #getIdElement()
	 * @generated
	 */
	EAttribute getIdElement_Id();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.NotationElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.NotationElement
	 * @generated
	 */
	EClass getNotationElement();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement <em>Graphical Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphical Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.GraphicalElement
	 * @generated
	 */
	EClass getGraphicalElement();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.GraphicalElement#getX()
	 * @see #getGraphicalElement()
	 * @generated
	 */
	EAttribute getGraphicalElement_X();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.GraphicalElement#getY()
	 * @see #getGraphicalElement()
	 * @generated
	 */
	EAttribute getGraphicalElement_Y();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.GraphicalElement#getHeight()
	 * @see #getGraphicalElement()
	 * @generated
	 */
	EAttribute getGraphicalElement_Height();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.GraphicalElement#getWidth()
	 * @see #getGraphicalElement()
	 * @generated
	 */
	EAttribute getGraphicalElement_Width();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getFill <em>Fill</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.GraphicalElement#getFill()
	 * @see #getGraphicalElement()
	 * @generated
	 */
	EAttribute getGraphicalElement_Fill();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.notation.GraphicalElement#getStroke <em>Stroke</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stroke</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.GraphicalElement#getStroke()
	 * @see #getGraphicalElement()
	 * @generated
	 */
	EAttribute getGraphicalElement_Stroke();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.Figure <em>Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Figure</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Figure
	 * @generated
	 */
	EClass getFigure();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.Rectangle <em>Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rectangle</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Rectangle
	 * @generated
	 */
	EClass getRectangle();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.Line <em>Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Line</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Line
	 * @generated
	 */
	EClass getLine();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Label
	 * @generated
	 */
	EClass getLabel();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.atlanmod.collaboro.notation.Label#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Text</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Label#getText()
	 * @see #getLabel()
	 * @generated
	 */
	EReference getLabel_Text();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.TextualElement <em>Textual Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Textual Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.TextualElement
	 * @generated
	 */
	EClass getTextualElement();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.notation.TextualElement#getFill <em>Fill</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.TextualElement#getFill()
	 * @see #getTextualElement()
	 * @generated
	 */
	EAttribute getTextualElement_Fill();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.Token <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Token</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Token
	 * @generated
	 */
	EClass getToken();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.Keyword <em>Keyword</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Keyword</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Keyword
	 * @generated
	 */
	EClass getKeyword();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.notation.Value#getSeparator <em>Separator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Separator</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Value#getSeparator()
	 * @see #getValue()
	 * @generated
	 */
	EAttribute getValue_Separator();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.collaboro.notation.Value#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Attribute</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Value#getAttribute()
	 * @see #getValue()
	 * @generated
	 */
	EReference getValue_Attribute();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.AttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Value</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.AttributeValue
	 * @generated
	 */
	EClass getAttributeValue();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.ReferenceValue <em>Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Value</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.ReferenceValue
	 * @generated
	 */
	EClass getReferenceValue();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.collaboro.notation.ReferenceValue#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.ReferenceValue#getReference()
	 * @see #getReferenceValue()
	 * @generated
	 */
	EReference getReferenceValue_Reference();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.SyntaxOf <em>Syntax Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Syntax Of</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.SyntaxOf
	 * @generated
	 */
	EClass getSyntaxOf();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.collaboro.notation.SyntaxOf#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.SyntaxOf#getReference()
	 * @see #getSyntaxOf()
	 * @generated
	 */
	EReference getSyntaxOf_Reference();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.collaboro.notation.SyntaxOf#getSeparator <em>Separator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Separator</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.SyntaxOf#getSeparator()
	 * @see #getSyntaxOf()
	 * @generated
	 */
	EReference getSyntaxOf_Separator();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.Composite <em>Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Composite
	 * @generated
	 */
	EClass getComposite();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.notation.Composite#getSubElements <em>Sub Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Elements</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Composite#getSubElements()
	 * @see #getComposite()
	 * @generated
	 */
	EReference getComposite_SubElements();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.notation.Definition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Definition</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Definition
	 * @generated
	 */
	EClass getDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.notation.Definition#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Definition#getElements()
	 * @see #getDefinition()
	 * @generated
	 */
	EReference getDefinition_Elements();

	/**
	 * Returns the meta object for enum '{@link fr.inria.atlanmod.collaboro.notation.Color <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Color</em>'.
	 * @see fr.inria.atlanmod.collaboro.notation.Color
	 * @generated
	 */
	EEnum getColor();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NotationFactory getNotationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.IdElementImpl <em>Id Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.IdElementImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getIdElement()
		 * @generated
		 */
		EClass ID_ELEMENT = eINSTANCE.getIdElement();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ID_ELEMENT__ID = eINSTANCE.getIdElement_Id();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.NotationElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationElementImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getNotationElement()
		 * @generated
		 */
		EClass NOTATION_ELEMENT = eINSTANCE.getNotationElement();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.GraphicalElementImpl <em>Graphical Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.GraphicalElementImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getGraphicalElement()
		 * @generated
		 */
		EClass GRAPHICAL_ELEMENT = eINSTANCE.getGraphicalElement();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICAL_ELEMENT__X = eINSTANCE.getGraphicalElement_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICAL_ELEMENT__Y = eINSTANCE.getGraphicalElement_Y();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICAL_ELEMENT__HEIGHT = eINSTANCE.getGraphicalElement_Height();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICAL_ELEMENT__WIDTH = eINSTANCE.getGraphicalElement_Width();

		/**
		 * The meta object literal for the '<em><b>Fill</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICAL_ELEMENT__FILL = eINSTANCE.getGraphicalElement_Fill();

		/**
		 * The meta object literal for the '<em><b>Stroke</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICAL_ELEMENT__STROKE = eINSTANCE.getGraphicalElement_Stroke();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.FigureImpl <em>Figure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.FigureImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getFigure()
		 * @generated
		 */
		EClass FIGURE = eINSTANCE.getFigure();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.RectangleImpl <em>Rectangle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.RectangleImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getRectangle()
		 * @generated
		 */
		EClass RECTANGLE = eINSTANCE.getRectangle();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.LineImpl <em>Line</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.LineImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getLine()
		 * @generated
		 */
		EClass LINE = eINSTANCE.getLine();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.LabelImpl <em>Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.LabelImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getLabel()
		 * @generated
		 */
		EClass LABEL = eINSTANCE.getLabel();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABEL__TEXT = eINSTANCE.getLabel_Text();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.TextualElementImpl <em>Textual Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.TextualElementImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getTextualElement()
		 * @generated
		 */
		EClass TEXTUAL_ELEMENT = eINSTANCE.getTextualElement();

		/**
		 * The meta object literal for the '<em><b>Fill</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXTUAL_ELEMENT__FILL = eINSTANCE.getTextualElement_Fill();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.TokenImpl <em>Token</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.TokenImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getToken()
		 * @generated
		 */
		EClass TOKEN = eINSTANCE.getToken();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.KeywordImpl <em>Keyword</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.KeywordImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getKeyword()
		 * @generated
		 */
		EClass KEYWORD = eINSTANCE.getKeyword();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.ValueImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '<em><b>Separator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE__SEPARATOR = eINSTANCE.getValue_Separator();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE__ATTRIBUTE = eINSTANCE.getValue_Attribute();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.AttributeValueImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getAttributeValue()
		 * @generated
		 */
		EClass ATTRIBUTE_VALUE = eINSTANCE.getAttributeValue();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.ReferenceValueImpl <em>Reference Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.ReferenceValueImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getReferenceValue()
		 * @generated
		 */
		EClass REFERENCE_VALUE = eINSTANCE.getReferenceValue();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_VALUE__REFERENCE = eINSTANCE.getReferenceValue_Reference();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.SyntaxOfImpl <em>Syntax Of</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.SyntaxOfImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getSyntaxOf()
		 * @generated
		 */
		EClass SYNTAX_OF = eINSTANCE.getSyntaxOf();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNTAX_OF__REFERENCE = eINSTANCE.getSyntaxOf_Reference();

		/**
		 * The meta object literal for the '<em><b>Separator</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNTAX_OF__SEPARATOR = eINSTANCE.getSyntaxOf_Separator();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.CompositeImpl <em>Composite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.CompositeImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getComposite()
		 * @generated
		 */
		EClass COMPOSITE = eINSTANCE.getComposite();

		/**
		 * The meta object literal for the '<em><b>Sub Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE__SUB_ELEMENTS = eINSTANCE.getComposite_SubElements();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.impl.DefinitionImpl <em>Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.impl.DefinitionImpl
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getDefinition()
		 * @generated
		 */
		EClass DEFINITION = eINSTANCE.getDefinition();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFINITION__ELEMENTS = eINSTANCE.getDefinition_Elements();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.notation.Color <em>Color</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.notation.Color
		 * @see fr.inria.atlanmod.collaboro.notation.impl.NotationPackageImpl#getColor()
		 * @generated
		 */
		EEnum COLOR = eINSTANCE.getColor();

	}

} //NotationPackage
