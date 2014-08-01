/**
 */
package fr.inria.atlanmod.collaboro.history;

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
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.inria.atlanmod.collaboro.history.HistoryFactory
 * @model kind="package"
 * @generated
 */
public interface HistoryPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "history";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://atlanmod.fr/collaboro/history";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "history";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	HistoryPackage eINSTANCE = fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.HistoryImpl <em>History</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getHistory()
	 * @generated
	 */
	int HISTORY = 0;

	/**
	 * The feature id for the '<em><b>Users</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY__USERS = 0;

	/**
	 * The feature id for the '<em><b>Histories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY__HISTORIES = 1;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY__LANGUAGE = 2;

	/**
	 * The number of structural features of the '<em>History</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>History</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.VersionHistoryImpl <em>Version History</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.VersionHistoryImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getVersionHistory()
	 * @generated
	 */
	int VERSION_HISTORY = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_HISTORY__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_HISTORY__VERSIONS = 1;

	/**
	 * The number of structural features of the '<em>Version History</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_HISTORY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Version History</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_HISTORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.IdElementImpl <em>Id Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.IdElementImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getIdElement()
	 * @generated
	 */
	int ID_ELEMENT = 2;

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
	 * The number of operations of the '<em>Id Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.UserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.UserImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getUser()
	 * @generated
	 */
	int USER = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__ID = ID_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Votes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__VOTES = ID_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Collaborations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__COLLABORATIONS = ID_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__EMAIL = ID_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Pasword</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__PASWORD = ID_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__FIRST_NAME = ID_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__LAST_NAME = ID_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = ID_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_OPERATION_COUNT = ID_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.VersionImpl <em>Version</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.VersionImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getVersion()
	 * @generated
	 */
	int VERSION = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION__ID = ID_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Proposals</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION__PROPOSALS = ID_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION__PREVIOUS = ID_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_FEATURE_COUNT = ID_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_OPERATION_COUNT = ID_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.VoteImpl <em>Vote</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.VoteImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getVote()
	 * @generated
	 */
	int VOTE = 5;

	/**
	 * The feature id for the '<em><b>Agreement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOTE__AGREEMENT = 0;

	/**
	 * The feature id for the '<em><b>User</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOTE__USER = 1;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOTE__COMMENT = 2;

	/**
	 * The feature id for the '<em><b>Collaboration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOTE__COLLABORATION = 3;

	/**
	 * The number of structural features of the '<em>Vote</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOTE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Vote</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.CollaborationImpl <em>Collaboration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.CollaborationImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getCollaboration()
	 * @generated
	 */
	int COLLABORATION = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLABORATION__ID = ID_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Rationale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLABORATION__RATIONALE = ID_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Proposed By</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLABORATION__PROPOSED_BY = ID_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLABORATION__COMMENTS = ID_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Votes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLABORATION__VOTES = ID_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Referred Elements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLABORATION__REFERRED_ELEMENTS = ID_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Collaboration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLABORATION_FEATURE_COUNT = ID_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Collaboration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLABORATION_OPERATION_COUNT = ID_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.ProposalImpl <em>Proposal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.ProposalImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getProposal()
	 * @generated
	 */
	int PROPOSAL = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__ID = COLLABORATION__ID;

	/**
	 * The feature id for the '<em><b>Rationale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__RATIONALE = COLLABORATION__RATIONALE;

	/**
	 * The feature id for the '<em><b>Proposed By</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__PROPOSED_BY = COLLABORATION__PROPOSED_BY;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__COMMENTS = COLLABORATION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Votes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__VOTES = COLLABORATION__VOTES;

	/**
	 * The feature id for the '<em><b>Referred Elements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__REFERRED_ELEMENTS = COLLABORATION__REFERRED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Sols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__SOLS = COLLABORATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Selected</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__SELECTED = COLLABORATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__VERSION = COLLABORATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Accepted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__ACCEPTED = COLLABORATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Meta</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__META = COLLABORATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Conflict With</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL__CONFLICT_WITH = COLLABORATION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Proposal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL_FEATURE_COUNT = COLLABORATION_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Proposal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPOSAL_OPERATION_COUNT = COLLABORATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.SolutionImpl <em>Solution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.SolutionImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getSolution()
	 * @generated
	 */
	int SOLUTION = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__ID = COLLABORATION__ID;

	/**
	 * The feature id for the '<em><b>Rationale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__RATIONALE = COLLABORATION__RATIONALE;

	/**
	 * The feature id for the '<em><b>Proposed By</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__PROPOSED_BY = COLLABORATION__PROPOSED_BY;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__COMMENTS = COLLABORATION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Votes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__VOTES = COLLABORATION__VOTES;

	/**
	 * The feature id for the '<em><b>Referred Elements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__REFERRED_ELEMENTS = COLLABORATION__REFERRED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CHANGES = COLLABORATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Proposal</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__PROPOSAL = COLLABORATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Changes Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CHANGES_TEXT = COLLABORATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Solution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION_FEATURE_COUNT = COLLABORATION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Solution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION_OPERATION_COUNT = COLLABORATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.CommentImpl <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.CommentImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getComment()
	 * @generated
	 */
	int COMMENT = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__ID = COLLABORATION__ID;

	/**
	 * The feature id for the '<em><b>Rationale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__RATIONALE = COLLABORATION__RATIONALE;

	/**
	 * The feature id for the '<em><b>Proposed By</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__PROPOSED_BY = COLLABORATION__PROPOSED_BY;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__COMMENTS = COLLABORATION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Votes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__VOTES = COLLABORATION__VOTES;

	/**
	 * The feature id for the '<em><b>Referred Elements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__REFERRED_ELEMENTS = COLLABORATION__REFERRED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Commented Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__COMMENTED_ELEMENT = COLLABORATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Included</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__INCLUDED = COLLABORATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = COLLABORATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_OPERATION_COUNT = COLLABORATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.ModelChangeImpl <em>Model Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.ModelChangeImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getModelChange()
	 * @generated
	 */
	int MODEL_CHANGE = 10;

	/**
	 * The feature id for the '<em><b>Solution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHANGE__SOLUTION = 0;

	/**
	 * The feature id for the '<em><b>Referred Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHANGE__REFERRED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHANGE__TARGET = 2;

	/**
	 * The number of structural features of the '<em>Model Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHANGE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Model Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHANGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.AddImpl <em>Add</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.AddImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getAdd()
	 * @generated
	 */
	int ADD = 11;

	/**
	 * The feature id for the '<em><b>Solution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD__SOLUTION = MODEL_CHANGE__SOLUTION;

	/**
	 * The feature id for the '<em><b>Referred Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD__REFERRED_ELEMENT = MODEL_CHANGE__REFERRED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD__TARGET = MODEL_CHANGE__TARGET;

	/**
	 * The number of structural features of the '<em>Add</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_FEATURE_COUNT = MODEL_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Add</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_OPERATION_COUNT = MODEL_CHANGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.UpdateImpl <em>Update</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.UpdateImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getUpdate()
	 * @generated
	 */
	int UPDATE = 12;

	/**
	 * The feature id for the '<em><b>Solution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE__SOLUTION = MODEL_CHANGE__SOLUTION;

	/**
	 * The feature id for the '<em><b>Referred Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE__REFERRED_ELEMENT = MODEL_CHANGE__REFERRED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE__TARGET = MODEL_CHANGE__TARGET;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE__SOURCE = MODEL_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Update</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_FEATURE_COUNT = MODEL_CHANGE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Update</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_OPERATION_COUNT = MODEL_CHANGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.DeleteImpl <em>Delete</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.DeleteImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getDelete()
	 * @generated
	 */
	int DELETE = 13;

	/**
	 * The feature id for the '<em><b>Solution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE__SOLUTION = MODEL_CHANGE__SOLUTION;

	/**
	 * The feature id for the '<em><b>Referred Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE__REFERRED_ELEMENT = MODEL_CHANGE__REFERRED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE__TARGET = MODEL_CHANGE__TARGET;

	/**
	 * The number of structural features of the '<em>Delete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_FEATURE_COUNT = MODEL_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Delete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_OPERATION_COUNT = MODEL_CHANGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.SyntaxElementImpl <em>Syntax Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.SyntaxElementImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getSyntaxElement()
	 * @generated
	 */
	int SYNTAX_ELEMENT = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_ELEMENT__ID = ID_ELEMENT__ID;

	/**
	 * The number of structural features of the '<em>Syntax Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_ELEMENT_FEATURE_COUNT = ID_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Syntax Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_ELEMENT_OPERATION_COUNT = ID_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.AbstractSyntaxElementImpl <em>Abstract Syntax Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.AbstractSyntaxElementImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getAbstractSyntaxElement()
	 * @generated
	 */
	int ABSTRACT_SYNTAX_ELEMENT = 15;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SYNTAX_ELEMENT__ID = SYNTAX_ELEMENT__ID;

	/**
	 * The number of structural features of the '<em>Abstract Syntax Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SYNTAX_ELEMENT_FEATURE_COUNT = SYNTAX_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Abstract Syntax Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SYNTAX_ELEMENT_OPERATION_COUNT = SYNTAX_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.ExistingAbstractSyntaxElementImpl <em>Existing Abstract Syntax Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.ExistingAbstractSyntaxElementImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getExistingAbstractSyntaxElement()
	 * @generated
	 */
	int EXISTING_ABSTRACT_SYNTAX_ELEMENT = 16;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTING_ABSTRACT_SYNTAX_ELEMENT__ID = ABSTRACT_SYNTAX_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT = ABSTRACT_SYNTAX_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Existing Abstract Syntax Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTING_ABSTRACT_SYNTAX_ELEMENT_FEATURE_COUNT = ABSTRACT_SYNTAX_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Existing Abstract Syntax Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXISTING_ABSTRACT_SYNTAX_ELEMENT_OPERATION_COUNT = ABSTRACT_SYNTAX_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.NewAbstractSyntaxElementImpl <em>New Abstract Syntax Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.NewAbstractSyntaxElementImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getNewAbstractSyntaxElement()
	 * @generated
	 */
	int NEW_ABSTRACT_SYNTAX_ELEMENT = 17;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_ABSTRACT_SYNTAX_ELEMENT__ID = ABSTRACT_SYNTAX_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT = ABSTRACT_SYNTAX_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>New Abstract Syntax Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_ABSTRACT_SYNTAX_ELEMENT_FEATURE_COUNT = ABSTRACT_SYNTAX_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>New Abstract Syntax Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_ABSTRACT_SYNTAX_ELEMENT_OPERATION_COUNT = ABSTRACT_SYNTAX_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.ConcreteSyntaxElementImpl <em>Concrete Syntax Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.ConcreteSyntaxElementImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getConcreteSyntaxElement()
	 * @generated
	 */
	int CONCRETE_SYNTAX_ELEMENT = 18;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_SYNTAX_ELEMENT__ID = SYNTAX_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_SYNTAX_ELEMENT__ELEMENT = SYNTAX_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Concrete Syntax Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_SYNTAX_ELEMENT_FEATURE_COUNT = SYNTAX_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Concrete Syntax Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_SYNTAX_ELEMENT_OPERATION_COUNT = SYNTAX_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.MetaInfoImpl <em>Meta Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.MetaInfoImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getMetaInfo()
	 * @generated
	 */
	int META_INFO = 19;

	/**
	 * The feature id for the '<em><b>Proposal</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int META_INFO__PROPOSAL = 0;

	/**
	 * The number of structural features of the '<em>Meta Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int META_INFO_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Meta Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int META_INFO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.PriorityImpl <em>Priority</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.PriorityImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getPriority()
	 * @generated
	 */
	int PRIORITY = 20;

	/**
	 * The feature id for the '<em><b>Proposal</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY__PROPOSAL = META_INFO__PROPOSAL;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY__VALUE = META_INFO_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Priority</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY_FEATURE_COUNT = META_INFO_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Priority</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORITY_OPERATION_COUNT = META_INFO_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.TagBasedImpl <em>Tag Based</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.TagBasedImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getTagBased()
	 * @generated
	 */
	int TAG_BASED = 21;

	/**
	 * The feature id for the '<em><b>Proposal</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_BASED__PROPOSAL = META_INFO__PROPOSAL;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_BASED__TAGS = META_INFO_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tag Based</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_BASED_FEATURE_COUNT = META_INFO_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tag Based</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_BASED_OPERATION_COUNT = META_INFO_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.impl.TagImpl
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 22;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Tag Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__TAG_COLLECTION = 1;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.atlanmod.collaboro.history.VersionHistoryType <em>Version History Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.atlanmod.collaboro.history.VersionHistoryType
	 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getVersionHistoryType()
	 * @generated
	 */
	int VERSION_HISTORY_TYPE = 23;


	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.History <em>History</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>History</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.History
	 * @generated
	 */
	EClass getHistory();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.history.History#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Users</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.History#getUsers()
	 * @see #getHistory()
	 * @generated
	 */
	EReference getHistory_Users();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.history.History#getHistories <em>Histories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Histories</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.History#getHistories()
	 * @see #getHistory()
	 * @generated
	 */
	EReference getHistory_Histories();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.History#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.History#getLanguage()
	 * @see #getHistory()
	 * @generated
	 */
	EAttribute getHistory_Language();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.VersionHistory <em>Version History</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Version History</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.VersionHistory
	 * @generated
	 */
	EClass getVersionHistory();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.VersionHistory#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.VersionHistory#getType()
	 * @see #getVersionHistory()
	 * @generated
	 */
	EAttribute getVersionHistory_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.history.VersionHistory#getVersions <em>Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Versions</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.VersionHistory#getVersions()
	 * @see #getVersionHistory()
	 * @generated
	 */
	EReference getVersionHistory_Versions();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.IdElement <em>Id Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Id Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.IdElement
	 * @generated
	 */
	EClass getIdElement();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.IdElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.IdElement#getId()
	 * @see #getIdElement()
	 * @generated
	 */
	EAttribute getIdElement_Id();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.User
	 * @generated
	 */
	EClass getUser();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.atlanmod.collaboro.history.User#getVotes <em>Votes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Votes</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.User#getVotes()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Votes();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.atlanmod.collaboro.history.User#getCollaborations <em>Collaborations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Collaborations</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.User#getCollaborations()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Collaborations();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.User#getEmail <em>Email</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Email</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.User#getEmail()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Email();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.User#getPasword <em>Pasword</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pasword</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.User#getPasword()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Pasword();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.User#getFirstName <em>First Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Name</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.User#getFirstName()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_FirstName();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.User#getLastName <em>Last Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Name</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.User#getLastName()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_LastName();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Version <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Version</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Version
	 * @generated
	 */
	EClass getVersion();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.history.Version#getProposals <em>Proposals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Proposals</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Version#getProposals()
	 * @see #getVersion()
	 * @generated
	 */
	EReference getVersion_Proposals();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.atlanmod.collaboro.history.Version#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Previous</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Version#getPrevious()
	 * @see #getVersion()
	 * @generated
	 */
	EReference getVersion_Previous();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Vote <em>Vote</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vote</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Vote
	 * @generated
	 */
	EClass getVote();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.Vote#isAgreement <em>Agreement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Agreement</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Vote#isAgreement()
	 * @see #getVote()
	 * @generated
	 */
	EAttribute getVote_Agreement();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.collaboro.history.Vote#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>User</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Vote#getUser()
	 * @see #getVote()
	 * @generated
	 */
	EReference getVote_User();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.collaboro.history.Vote#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Comment</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Vote#getComment()
	 * @see #getVote()
	 * @generated
	 */
	EReference getVote_Comment();

	/**
	 * Returns the meta object for the container reference '{@link fr.inria.atlanmod.collaboro.history.Vote#getCollaboration <em>Collaboration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Collaboration</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Vote#getCollaboration()
	 * @see #getVote()
	 * @generated
	 */
	EReference getVote_Collaboration();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Collaboration <em>Collaboration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collaboration</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Collaboration
	 * @generated
	 */
	EClass getCollaboration();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.Collaboration#getRationale <em>Rationale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rationale</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Collaboration#getRationale()
	 * @see #getCollaboration()
	 * @generated
	 */
	EAttribute getCollaboration_Rationale();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.collaboro.history.Collaboration#getProposedBy <em>Proposed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Proposed By</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Collaboration#getProposedBy()
	 * @see #getCollaboration()
	 * @generated
	 */
	EReference getCollaboration_ProposedBy();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.history.Collaboration#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Comments</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Collaboration#getComments()
	 * @see #getCollaboration()
	 * @generated
	 */
	EReference getCollaboration_Comments();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.history.Collaboration#getVotes <em>Votes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Votes</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Collaboration#getVotes()
	 * @see #getCollaboration()
	 * @generated
	 */
	EReference getCollaboration_Votes();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.Collaboration#getReferredElements <em>Referred Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referred Elements</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Collaboration#getReferredElements()
	 * @see #getCollaboration()
	 * @generated
	 */
	EAttribute getCollaboration_ReferredElements();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Proposal <em>Proposal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Proposal</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Proposal
	 * @generated
	 */
	EClass getProposal();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.history.Proposal#getSols <em>Sols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sols</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Proposal#getSols()
	 * @see #getProposal()
	 * @generated
	 */
	EReference getProposal_Sols();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.collaboro.history.Proposal#getSelected <em>Selected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Selected</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Proposal#getSelected()
	 * @see #getProposal()
	 * @generated
	 */
	EReference getProposal_Selected();

	/**
	 * Returns the meta object for the container reference '{@link fr.inria.atlanmod.collaboro.history.Proposal#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Version</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Proposal#getVersion()
	 * @see #getProposal()
	 * @generated
	 */
	EReference getProposal_Version();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.Proposal#isAccepted <em>Accepted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Accepted</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Proposal#isAccepted()
	 * @see #getProposal()
	 * @generated
	 */
	EAttribute getProposal_Accepted();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.history.Proposal#getMeta <em>Meta</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Proposal#getMeta()
	 * @see #getProposal()
	 * @generated
	 */
	EReference getProposal_Meta();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.atlanmod.collaboro.history.Proposal#getConflictWith <em>Conflict With</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Conflict With</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Proposal#getConflictWith()
	 * @see #getProposal()
	 * @generated
	 */
	EReference getProposal_ConflictWith();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Solution <em>Solution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Solution</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Solution
	 * @generated
	 */
	EClass getSolution();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.history.Solution#getChanges <em>Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Changes</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Solution#getChanges()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_Changes();

	/**
	 * Returns the meta object for the container reference '{@link fr.inria.atlanmod.collaboro.history.Solution#getProposal <em>Proposal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Proposal</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Solution#getProposal()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_Proposal();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.Solution#getChangesText <em>Changes Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Changes Text</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Solution#getChangesText()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_ChangesText();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Comment
	 * @generated
	 */
	EClass getComment();

	/**
	 * Returns the meta object for the container reference '{@link fr.inria.atlanmod.collaboro.history.Comment#getCommentedElement <em>Commented Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Commented Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Comment#getCommentedElement()
	 * @see #getComment()
	 * @generated
	 */
	EReference getComment_CommentedElement();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.Comment#isIncluded <em>Included</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Included</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Comment#isIncluded()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Included();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.ModelChange <em>Model Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Change</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.ModelChange
	 * @generated
	 */
	EClass getModelChange();

	/**
	 * Returns the meta object for the container reference '{@link fr.inria.atlanmod.collaboro.history.ModelChange#getSolution <em>Solution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Solution</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.ModelChange#getSolution()
	 * @see #getModelChange()
	 * @generated
	 */
	EReference getModelChange_Solution();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.atlanmod.collaboro.history.ModelChange#getReferredElement <em>Referred Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Referred Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.ModelChange#getReferredElement()
	 * @see #getModelChange()
	 * @generated
	 */
	EReference getModelChange_ReferredElement();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.atlanmod.collaboro.history.ModelChange#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.ModelChange#getTarget()
	 * @see #getModelChange()
	 * @generated
	 */
	EReference getModelChange_Target();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Add <em>Add</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Add
	 * @generated
	 */
	EClass getAdd();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Update <em>Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Update</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Update
	 * @generated
	 */
	EClass getUpdate();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.atlanmod.collaboro.history.Update#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Update#getSource()
	 * @see #getUpdate()
	 * @generated
	 */
	EReference getUpdate_Source();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Delete <em>Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delete</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Delete
	 * @generated
	 */
	EClass getDelete();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.SyntaxElement <em>Syntax Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Syntax Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.SyntaxElement
	 * @generated
	 */
	EClass getSyntaxElement();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.AbstractSyntaxElement <em>Abstract Syntax Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Syntax Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.AbstractSyntaxElement
	 * @generated
	 */
	EClass getAbstractSyntaxElement();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement <em>Existing Abstract Syntax Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Existing Abstract Syntax Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement
	 * @generated
	 */
	EClass getExistingAbstractSyntaxElement();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.ExistingAbstractSyntaxElement#getElement()
	 * @see #getExistingAbstractSyntaxElement()
	 * @generated
	 */
	EReference getExistingAbstractSyntaxElement_Element();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement <em>New Abstract Syntax Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Abstract Syntax Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement
	 * @generated
	 */
	EClass getNewAbstractSyntaxElement();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.NewAbstractSyntaxElement#getElement()
	 * @see #getNewAbstractSyntaxElement()
	 * @generated
	 */
	EReference getNewAbstractSyntaxElement_Element();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement <em>Concrete Syntax Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concrete Syntax Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement
	 * @generated
	 */
	EClass getConcreteSyntaxElement();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.ConcreteSyntaxElement#getElement()
	 * @see #getConcreteSyntaxElement()
	 * @generated
	 */
	EReference getConcreteSyntaxElement_Element();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.MetaInfo <em>Meta Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Meta Info</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.MetaInfo
	 * @generated
	 */
	EClass getMetaInfo();

	/**
	 * Returns the meta object for the container reference '{@link fr.inria.atlanmod.collaboro.history.MetaInfo#getProposal <em>Proposal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Proposal</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.MetaInfo#getProposal()
	 * @see #getMetaInfo()
	 * @generated
	 */
	EReference getMetaInfo_Proposal();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Priority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Priority</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Priority
	 * @generated
	 */
	EClass getPriority();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.Priority#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Priority#getValue()
	 * @see #getPriority()
	 * @generated
	 */
	EAttribute getPriority_Value();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.TagBased <em>Tag Based</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Based</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.TagBased
	 * @generated
	 */
	EClass getTagBased();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.atlanmod.collaboro.history.TagBased#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.TagBased#getTags()
	 * @see #getTagBased()
	 * @generated
	 */
	EReference getTagBased_Tags();

	/**
	 * Returns the meta object for class '{@link fr.inria.atlanmod.collaboro.history.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.atlanmod.collaboro.history.Tag#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Tag#getValue()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Value();

	/**
	 * Returns the meta object for the container reference '{@link fr.inria.atlanmod.collaboro.history.Tag#getTagCollection <em>Tag Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Tag Collection</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.Tag#getTagCollection()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_TagCollection();

	/**
	 * Returns the meta object for enum '{@link fr.inria.atlanmod.collaboro.history.VersionHistoryType <em>Version History Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Version History Type</em>'.
	 * @see fr.inria.atlanmod.collaboro.history.VersionHistoryType
	 * @generated
	 */
	EEnum getVersionHistoryType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	HistoryFactory getHistoryFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.HistoryImpl <em>History</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getHistory()
		 * @generated
		 */
		EClass HISTORY = eINSTANCE.getHistory();

		/**
		 * The meta object literal for the '<em><b>Users</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HISTORY__USERS = eINSTANCE.getHistory_Users();

		/**
		 * The meta object literal for the '<em><b>Histories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HISTORY__HISTORIES = eINSTANCE.getHistory_Histories();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY__LANGUAGE = eINSTANCE.getHistory_Language();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.VersionHistoryImpl <em>Version History</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.VersionHistoryImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getVersionHistory()
		 * @generated
		 */
		EClass VERSION_HISTORY = eINSTANCE.getVersionHistory();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERSION_HISTORY__TYPE = eINSTANCE.getVersionHistory_Type();

		/**
		 * The meta object literal for the '<em><b>Versions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERSION_HISTORY__VERSIONS = eINSTANCE.getVersionHistory_Versions();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.IdElementImpl <em>Id Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.IdElementImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getIdElement()
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
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.UserImpl <em>User</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.UserImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getUser()
		 * @generated
		 */
		EClass USER = eINSTANCE.getUser();

		/**
		 * The meta object literal for the '<em><b>Votes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__VOTES = eINSTANCE.getUser_Votes();

		/**
		 * The meta object literal for the '<em><b>Collaborations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__COLLABORATIONS = eINSTANCE.getUser_Collaborations();

		/**
		 * The meta object literal for the '<em><b>Email</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__EMAIL = eINSTANCE.getUser_Email();

		/**
		 * The meta object literal for the '<em><b>Pasword</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__PASWORD = eINSTANCE.getUser_Pasword();

		/**
		 * The meta object literal for the '<em><b>First Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__FIRST_NAME = eINSTANCE.getUser_FirstName();

		/**
		 * The meta object literal for the '<em><b>Last Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__LAST_NAME = eINSTANCE.getUser_LastName();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.VersionImpl <em>Version</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.VersionImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getVersion()
		 * @generated
		 */
		EClass VERSION = eINSTANCE.getVersion();

		/**
		 * The meta object literal for the '<em><b>Proposals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERSION__PROPOSALS = eINSTANCE.getVersion_Proposals();

		/**
		 * The meta object literal for the '<em><b>Previous</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERSION__PREVIOUS = eINSTANCE.getVersion_Previous();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.VoteImpl <em>Vote</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.VoteImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getVote()
		 * @generated
		 */
		EClass VOTE = eINSTANCE.getVote();

		/**
		 * The meta object literal for the '<em><b>Agreement</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOTE__AGREEMENT = eINSTANCE.getVote_Agreement();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VOTE__USER = eINSTANCE.getVote_User();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VOTE__COMMENT = eINSTANCE.getVote_Comment();

		/**
		 * The meta object literal for the '<em><b>Collaboration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VOTE__COLLABORATION = eINSTANCE.getVote_Collaboration();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.CollaborationImpl <em>Collaboration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.CollaborationImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getCollaboration()
		 * @generated
		 */
		EClass COLLABORATION = eINSTANCE.getCollaboration();

		/**
		 * The meta object literal for the '<em><b>Rationale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLABORATION__RATIONALE = eINSTANCE.getCollaboration_Rationale();

		/**
		 * The meta object literal for the '<em><b>Proposed By</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLABORATION__PROPOSED_BY = eINSTANCE.getCollaboration_ProposedBy();

		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLABORATION__COMMENTS = eINSTANCE.getCollaboration_Comments();

		/**
		 * The meta object literal for the '<em><b>Votes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLABORATION__VOTES = eINSTANCE.getCollaboration_Votes();

		/**
		 * The meta object literal for the '<em><b>Referred Elements</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLABORATION__REFERRED_ELEMENTS = eINSTANCE.getCollaboration_ReferredElements();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.ProposalImpl <em>Proposal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.ProposalImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getProposal()
		 * @generated
		 */
		EClass PROPOSAL = eINSTANCE.getProposal();

		/**
		 * The meta object literal for the '<em><b>Sols</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPOSAL__SOLS = eINSTANCE.getProposal_Sols();

		/**
		 * The meta object literal for the '<em><b>Selected</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPOSAL__SELECTED = eINSTANCE.getProposal_Selected();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPOSAL__VERSION = eINSTANCE.getProposal_Version();

		/**
		 * The meta object literal for the '<em><b>Accepted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPOSAL__ACCEPTED = eINSTANCE.getProposal_Accepted();

		/**
		 * The meta object literal for the '<em><b>Meta</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPOSAL__META = eINSTANCE.getProposal_Meta();

		/**
		 * The meta object literal for the '<em><b>Conflict With</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPOSAL__CONFLICT_WITH = eINSTANCE.getProposal_ConflictWith();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.SolutionImpl <em>Solution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.SolutionImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getSolution()
		 * @generated
		 */
		EClass SOLUTION = eINSTANCE.getSolution();

		/**
		 * The meta object literal for the '<em><b>Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__CHANGES = eINSTANCE.getSolution_Changes();

		/**
		 * The meta object literal for the '<em><b>Proposal</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__PROPOSAL = eINSTANCE.getSolution_Proposal();

		/**
		 * The meta object literal for the '<em><b>Changes Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__CHANGES_TEXT = eINSTANCE.getSolution_ChangesText();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.CommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.CommentImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();

		/**
		 * The meta object literal for the '<em><b>Commented Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMENT__COMMENTED_ELEMENT = eINSTANCE.getComment_CommentedElement();

		/**
		 * The meta object literal for the '<em><b>Included</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__INCLUDED = eINSTANCE.getComment_Included();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.ModelChangeImpl <em>Model Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.ModelChangeImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getModelChange()
		 * @generated
		 */
		EClass MODEL_CHANGE = eINSTANCE.getModelChange();

		/**
		 * The meta object literal for the '<em><b>Solution</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_CHANGE__SOLUTION = eINSTANCE.getModelChange_Solution();

		/**
		 * The meta object literal for the '<em><b>Referred Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_CHANGE__REFERRED_ELEMENT = eINSTANCE.getModelChange_ReferredElement();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_CHANGE__TARGET = eINSTANCE.getModelChange_Target();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.AddImpl <em>Add</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.AddImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getAdd()
		 * @generated
		 */
		EClass ADD = eINSTANCE.getAdd();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.UpdateImpl <em>Update</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.UpdateImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getUpdate()
		 * @generated
		 */
		EClass UPDATE = eINSTANCE.getUpdate();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UPDATE__SOURCE = eINSTANCE.getUpdate_Source();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.DeleteImpl <em>Delete</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.DeleteImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getDelete()
		 * @generated
		 */
		EClass DELETE = eINSTANCE.getDelete();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.SyntaxElementImpl <em>Syntax Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.SyntaxElementImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getSyntaxElement()
		 * @generated
		 */
		EClass SYNTAX_ELEMENT = eINSTANCE.getSyntaxElement();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.AbstractSyntaxElementImpl <em>Abstract Syntax Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.AbstractSyntaxElementImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getAbstractSyntaxElement()
		 * @generated
		 */
		EClass ABSTRACT_SYNTAX_ELEMENT = eINSTANCE.getAbstractSyntaxElement();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.ExistingAbstractSyntaxElementImpl <em>Existing Abstract Syntax Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.ExistingAbstractSyntaxElementImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getExistingAbstractSyntaxElement()
		 * @generated
		 */
		EClass EXISTING_ABSTRACT_SYNTAX_ELEMENT = eINSTANCE.getExistingAbstractSyntaxElement();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXISTING_ABSTRACT_SYNTAX_ELEMENT__ELEMENT = eINSTANCE.getExistingAbstractSyntaxElement_Element();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.NewAbstractSyntaxElementImpl <em>New Abstract Syntax Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.NewAbstractSyntaxElementImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getNewAbstractSyntaxElement()
		 * @generated
		 */
		EClass NEW_ABSTRACT_SYNTAX_ELEMENT = eINSTANCE.getNewAbstractSyntaxElement();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEW_ABSTRACT_SYNTAX_ELEMENT__ELEMENT = eINSTANCE.getNewAbstractSyntaxElement_Element();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.ConcreteSyntaxElementImpl <em>Concrete Syntax Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.ConcreteSyntaxElementImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getConcreteSyntaxElement()
		 * @generated
		 */
		EClass CONCRETE_SYNTAX_ELEMENT = eINSTANCE.getConcreteSyntaxElement();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCRETE_SYNTAX_ELEMENT__ELEMENT = eINSTANCE.getConcreteSyntaxElement_Element();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.MetaInfoImpl <em>Meta Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.MetaInfoImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getMetaInfo()
		 * @generated
		 */
		EClass META_INFO = eINSTANCE.getMetaInfo();

		/**
		 * The meta object literal for the '<em><b>Proposal</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference META_INFO__PROPOSAL = eINSTANCE.getMetaInfo_Proposal();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.PriorityImpl <em>Priority</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.PriorityImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getPriority()
		 * @generated
		 */
		EClass PRIORITY = eINSTANCE.getPriority();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIORITY__VALUE = eINSTANCE.getPriority_Value();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.TagBasedImpl <em>Tag Based</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.TagBasedImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getTagBased()
		 * @generated
		 */
		EClass TAG_BASED = eINSTANCE.getTagBased();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_BASED__TAGS = eINSTANCE.getTagBased_Tags();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.impl.TagImpl
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__VALUE = eINSTANCE.getTag_Value();

		/**
		 * The meta object literal for the '<em><b>Tag Collection</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__TAG_COLLECTION = eINSTANCE.getTag_TagCollection();

		/**
		 * The meta object literal for the '{@link fr.inria.atlanmod.collaboro.history.VersionHistoryType <em>Version History Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.atlanmod.collaboro.history.VersionHistoryType
		 * @see fr.inria.atlanmod.collaboro.history.impl.HistoryPackageImpl#getVersionHistoryType()
		 * @generated
		 */
		EEnum VERSION_HISTORY_TYPE = eINSTANCE.getVersionHistoryType();

	}

} //HistoryPackage
