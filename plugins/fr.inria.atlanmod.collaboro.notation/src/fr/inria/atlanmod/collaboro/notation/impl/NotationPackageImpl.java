/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.inria.atlanmod.collaboro.notation.impl;

import fr.inria.atlanmod.collaboro.notation.AttributeValue;
import fr.inria.atlanmod.collaboro.notation.Color;
import fr.inria.atlanmod.collaboro.notation.Composite;
import fr.inria.atlanmod.collaboro.notation.Definition;
import fr.inria.atlanmod.collaboro.notation.Figure;
import fr.inria.atlanmod.collaboro.notation.GraphicalElement;
import fr.inria.atlanmod.collaboro.notation.IdElement;
import fr.inria.atlanmod.collaboro.notation.Image;
import fr.inria.atlanmod.collaboro.notation.Keyword;
import fr.inria.atlanmod.collaboro.notation.Label;
import fr.inria.atlanmod.collaboro.notation.Line;
import fr.inria.atlanmod.collaboro.notation.NotationElement;
import fr.inria.atlanmod.collaboro.notation.NotationFactory;
import fr.inria.atlanmod.collaboro.notation.NotationPackage;
import fr.inria.atlanmod.collaboro.notation.NotationType;
import fr.inria.atlanmod.collaboro.notation.Oval;
import fr.inria.atlanmod.collaboro.notation.Polygone;
import fr.inria.atlanmod.collaboro.notation.Rectangle;
import fr.inria.atlanmod.collaboro.notation.ReferenceValue;
import fr.inria.atlanmod.collaboro.notation.SyntaxOf;
import fr.inria.atlanmod.collaboro.notation.TextualElement;
import fr.inria.atlanmod.collaboro.notation.Token;
import fr.inria.atlanmod.collaboro.notation.Value;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NotationPackageImpl extends EPackageImpl implements NotationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass idElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass notationElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphicalElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass figureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rectangleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textualElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tokenEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass keywordEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass syntaxOfEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass definitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ovalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass polygoneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum colorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum notationTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see fr.inria.atlanmod.collaboro.notation.NotationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private NotationPackageImpl() {
		super(eNS_URI, NotationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link NotationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static NotationPackage init() {
		if (isInited) return (NotationPackage)EPackage.Registry.INSTANCE.getEPackage(NotationPackage.eNS_URI);

		// Obtain or create and register package
		NotationPackageImpl theNotationPackage = (NotationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof NotationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new NotationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theNotationPackage.createPackageContents();

		// Initialize created meta-data
		theNotationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theNotationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(NotationPackage.eNS_URI, theNotationPackage);
		return theNotationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIdElement() {
		return idElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdElement_Id() {
		return (EAttribute)idElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNotationElement() {
		return notationElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphicalElement() {
		return graphicalElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicalElement_X() {
		return (EAttribute)graphicalElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicalElement_Y() {
		return (EAttribute)graphicalElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicalElement_Height() {
		return (EAttribute)graphicalElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicalElement_Width() {
		return (EAttribute)graphicalElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicalElement_Fill() {
		return (EAttribute)graphicalElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicalElement_Stroke() {
		return (EAttribute)graphicalElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImage() {
		return imageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Path() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFigure() {
		return figureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRectangle() {
		return rectangleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLine() {
		return lineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabel() {
		return labelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLabel_Text() {
		return (EReference)labelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextualElement() {
		return textualElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextualElement_Fill() {
		return (EAttribute)textualElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getToken() {
		return tokenEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKeyword() {
		return keywordEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValue() {
		return valueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValue_Separator() {
		return (EAttribute)valueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getValue_Attribute() {
		return (EReference)valueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeValue() {
		return attributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceValue() {
		return referenceValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceValue_Reference() {
		return (EReference)referenceValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSyntaxOf() {
		return syntaxOfEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSyntaxOf_Reference() {
		return (EReference)syntaxOfEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSyntaxOf_Separator() {
		return (EReference)syntaxOfEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComposite() {
		return compositeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComposite_SubElements() {
		return (EReference)compositeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDefinition() {
		return definitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDefinition_Elements() {
		return (EReference)definitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDefinition_Type() {
		return (EAttribute)definitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOval() {
		return ovalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolygone() {
		return polygoneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPolygone_Lines() {
		return (EReference)polygoneEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getColor() {
		return colorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getNotationType() {
		return notationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotationFactory getNotationFactory() {
		return (NotationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		idElementEClass = createEClass(ID_ELEMENT);
		createEAttribute(idElementEClass, ID_ELEMENT__ID);

		notationElementEClass = createEClass(NOTATION_ELEMENT);

		graphicalElementEClass = createEClass(GRAPHICAL_ELEMENT);
		createEAttribute(graphicalElementEClass, GRAPHICAL_ELEMENT__X);
		createEAttribute(graphicalElementEClass, GRAPHICAL_ELEMENT__Y);
		createEAttribute(graphicalElementEClass, GRAPHICAL_ELEMENT__HEIGHT);
		createEAttribute(graphicalElementEClass, GRAPHICAL_ELEMENT__WIDTH);
		createEAttribute(graphicalElementEClass, GRAPHICAL_ELEMENT__FILL);
		createEAttribute(graphicalElementEClass, GRAPHICAL_ELEMENT__STROKE);

		imageEClass = createEClass(IMAGE);
		createEAttribute(imageEClass, IMAGE__PATH);

		figureEClass = createEClass(FIGURE);

		rectangleEClass = createEClass(RECTANGLE);

		lineEClass = createEClass(LINE);

		labelEClass = createEClass(LABEL);
		createEReference(labelEClass, LABEL__TEXT);

		textualElementEClass = createEClass(TEXTUAL_ELEMENT);
		createEAttribute(textualElementEClass, TEXTUAL_ELEMENT__FILL);

		tokenEClass = createEClass(TOKEN);

		keywordEClass = createEClass(KEYWORD);

		valueEClass = createEClass(VALUE);
		createEAttribute(valueEClass, VALUE__SEPARATOR);
		createEReference(valueEClass, VALUE__ATTRIBUTE);

		attributeValueEClass = createEClass(ATTRIBUTE_VALUE);

		referenceValueEClass = createEClass(REFERENCE_VALUE);
		createEReference(referenceValueEClass, REFERENCE_VALUE__REFERENCE);

		syntaxOfEClass = createEClass(SYNTAX_OF);
		createEReference(syntaxOfEClass, SYNTAX_OF__REFERENCE);
		createEReference(syntaxOfEClass, SYNTAX_OF__SEPARATOR);

		compositeEClass = createEClass(COMPOSITE);
		createEReference(compositeEClass, COMPOSITE__SUB_ELEMENTS);

		definitionEClass = createEClass(DEFINITION);
		createEReference(definitionEClass, DEFINITION__ELEMENTS);
		createEAttribute(definitionEClass, DEFINITION__TYPE);

		ovalEClass = createEClass(OVAL);

		polygoneEClass = createEClass(POLYGONE);
		createEReference(polygoneEClass, POLYGONE__LINES);

		// Create enums
		colorEEnum = createEEnum(COLOR);
		notationTypeEEnum = createEEnum(NOTATION_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		notationElementEClass.getESuperTypes().add(this.getIdElement());
		graphicalElementEClass.getESuperTypes().add(this.getNotationElement());
		imageEClass.getESuperTypes().add(this.getGraphicalElement());
		figureEClass.getESuperTypes().add(this.getGraphicalElement());
		rectangleEClass.getESuperTypes().add(this.getFigure());
		lineEClass.getESuperTypes().add(this.getGraphicalElement());
		labelEClass.getESuperTypes().add(this.getGraphicalElement());
		textualElementEClass.getESuperTypes().add(this.getNotationElement());
		tokenEClass.getESuperTypes().add(this.getTextualElement());
		keywordEClass.getESuperTypes().add(this.getTextualElement());
		valueEClass.getESuperTypes().add(this.getTextualElement());
		attributeValueEClass.getESuperTypes().add(this.getValue());
		referenceValueEClass.getESuperTypes().add(this.getValue());
		syntaxOfEClass.getESuperTypes().add(this.getNotationElement());
		compositeEClass.getESuperTypes().add(this.getNotationElement());
		ovalEClass.getESuperTypes().add(this.getFigure());
		polygoneEClass.getESuperTypes().add(this.getFigure());

		// Initialize classes and features; add operations and parameters
		initEClass(idElementEClass, IdElement.class, "IdElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIdElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, IdElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(notationElementEClass, NotationElement.class, "NotationElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(graphicalElementEClass, GraphicalElement.class, "GraphicalElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGraphicalElement_X(), ecorePackage.getEInt(), "x", null, 0, 1, GraphicalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphicalElement_Y(), ecorePackage.getEInt(), "y", null, 0, 1, GraphicalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphicalElement_Height(), ecorePackage.getEInt(), "height", null, 0, 1, GraphicalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphicalElement_Width(), ecorePackage.getEInt(), "width", null, 0, 1, GraphicalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphicalElement_Fill(), this.getColor(), "fill", "white", 0, 1, GraphicalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphicalElement_Stroke(), this.getColor(), "stroke", "black", 0, 1, GraphicalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(imageEClass, Image.class, "Image", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImage_Path(), ecorePackage.getEString(), "path", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(figureEClass, Figure.class, "Figure", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rectangleEClass, Rectangle.class, "Rectangle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(lineEClass, Line.class, "Line", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(labelEClass, Label.class, "Label", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLabel_Text(), this.getTextualElement(), null, "text", null, 1, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textualElementEClass, TextualElement.class, "TextualElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextualElement_Fill(), this.getColor(), "fill", "black", 0, 1, TextualElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tokenEClass, Token.class, "Token", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(keywordEClass, Keyword.class, "Keyword", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(valueEClass, Value.class, "Value", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValue_Separator(), ecorePackage.getEString(), "separator", null, 0, 1, Value.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValue_Attribute(), theEcorePackage.getEAttribute(), null, "attribute", null, 1, 1, Value.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeValueEClass, AttributeValue.class, "AttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(referenceValueEClass, ReferenceValue.class, "ReferenceValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferenceValue_Reference(), theEcorePackage.getEReference(), null, "reference", null, 1, 1, ReferenceValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(syntaxOfEClass, SyntaxOf.class, "SyntaxOf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSyntaxOf_Reference(), theEcorePackage.getEReference(), null, "reference", null, 1, 1, SyntaxOf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSyntaxOf_Separator(), this.getNotationElement(), null, "separator", null, 0, 1, SyntaxOf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositeEClass, Composite.class, "Composite", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComposite_SubElements(), this.getNotationElement(), null, "subElements", null, 0, -1, Composite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(definitionEClass, Definition.class, "Definition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDefinition_Elements(), this.getNotationElement(), null, "elements", null, 0, -1, Definition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefinition_Type(), this.getNotationType(), "type", null, 1, 1, Definition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ovalEClass, Oval.class, "Oval", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(polygoneEClass, Polygone.class, "Polygone", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPolygone_Lines(), this.getLine(), null, "lines", null, 0, -1, Polygone.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(colorEEnum, Color.class, "Color");
		addEEnumLiteral(colorEEnum, Color.BLACK);
		addEEnumLiteral(colorEEnum, Color.WHITE);
		addEEnumLiteral(colorEEnum, Color.RED);
		addEEnumLiteral(colorEEnum, Color.BLUE);
		addEEnumLiteral(colorEEnum, Color.GREEN);
		addEEnumLiteral(colorEEnum, Color.YELLOW);
		addEEnumLiteral(colorEEnum, Color.ORANGE);

		initEEnum(notationTypeEEnum, NotationType.class, "NotationType");
		addEEnumLiteral(notationTypeEEnum, NotationType.GRAPHICAL);
		addEEnumLiteral(notationTypeEEnum, NotationType.TEXTUAL);
		addEEnumLiteral(notationTypeEEnum, NotationType.MIXED);

		// Create resource
		createResource(eNS_URI);
	}

} //NotationPackageImpl
