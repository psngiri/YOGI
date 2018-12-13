package yogi.base.relationship;


public abstract class DerivedRelationshipObjectImpl<P extends RelationshipObject, F extends P> extends BaseRelationshipObjectImpl<F>  implements RelationshipObject {

	private transient DerivedRelationshipController<P> relationshipController = null;
	
	public DerivedRelationshipController<P> getRelationshipController() {
		return relationshipController;
	}
	
	protected DerivedRelationshipObjectImpl(P parent) {
		super();
		relationshipController = new DerivedRelationshipController<P>(this, parent);
	}

	protected P getParent() {
		return getRelationshipController().getParent();
	}

	protected P getRoot()
	{
		return getRelationshipController().getRoot();
	}
}
