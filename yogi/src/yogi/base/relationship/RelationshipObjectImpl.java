package yogi.base.relationship;


public abstract class RelationshipObjectImpl<F extends RelationshipObject> extends BaseRelationshipObjectImpl<F> implements RelationshipObject {
	protected transient RelationshipController relationshipController;

	public RelationshipController getRelationshipController() {
		if(relationshipController ==  null){
			synchronized (this) {
				if(relationshipController ==  null){
					this.relationshipController = new RelationshipController(this);
				}
			}
		}
		return relationshipController;
	}
	
	protected RelationshipObjectImpl() {
		super();
	}

}
