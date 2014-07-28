/**
 */
package fr.inria.atlanmod.collaboro.history;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Tag#getValue <em>Value</em>}</li>
 *   <li>{@link fr.inria.atlanmod.collaboro.history.Tag#getTagCollection <em>Tag Collection</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getTag()
 * @model
 * @generated
 */
public interface Tag extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getTag_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Tag#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Tag Collection</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.collaboro.history.TagBased#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Collection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Collection</em>' container reference.
	 * @see #setTagCollection(TagBased)
	 * @see fr.inria.atlanmod.collaboro.history.HistoryPackage#getTag_TagCollection()
	 * @see fr.inria.atlanmod.collaboro.history.TagBased#getTags
	 * @model opposite="tags" transient="false"
	 * @generated
	 */
	TagBased getTagCollection();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.collaboro.history.Tag#getTagCollection <em>Tag Collection</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag Collection</em>' container reference.
	 * @see #getTagCollection()
	 * @generated
	 */
	void setTagCollection(TagBased value);

} // Tag
