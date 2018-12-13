package yogi.base.relationship;

public abstract class RelationshipObjectWrapper<T extends RelationshipObject> implements RelationshipObject{
	private T relationshipObject;
	
	public RelationshipObjectWrapper(T relationshipObject) {
		super();
		this.relationshipObject = relationshipObject;
	}

	protected void setRelationshipObject(T relationshipObject) {
		this.relationshipObject = relationshipObject;
	}

	public T getRelationshipObject() {
		return relationshipObject;
	}

	@Override
	public RelationshipController getRelationshipController() {
		return relationshipObject.getRelationshipController();
	}

}
