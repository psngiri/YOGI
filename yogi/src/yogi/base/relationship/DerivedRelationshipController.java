package yogi.base.relationship;

import yogi.base.relationship.types.OneToManyRelationship;
import yogi.base.relationship.types.OneToOneRelationship;
import yogi.base.util.immutable.ImmutableList;

public class DerivedRelationshipController<F extends RelationshipObject> extends RelationshipController {
	private F parent;
	public DerivedRelationshipController(RelationshipObject relationshipObject, F parentRelationshipObject) {
		super(relationshipObject);
		this.parent = parentRelationshipObject;
	}

	@Override
	public <T> T getRelationship(OneToOneRelationship relationship) {
		return this.<T>getRelationship(relationship, relationship.isInherited());
	}

	@Override
	public <T> T getRelationship(OneToOneRelationship relationship, boolean inherit) {
		T returnValue = super.<T>getRelationship(relationship);
		if(returnValue == null && inherit)
		{
			returnValue = parent.getRelationshipController().<T>getRelationship(relationship);
		}
		return returnValue;
	}

	@Override
	public <T> ImmutableList<T> getRelationship(OneToManyRelationship relationship) {
		return getRelationship(relationship, relationship.isInherited());
	}

	@Override
	public <T> ImmutableList<T> getRelationship(OneToManyRelationship relationship, boolean inherit) {
		ImmutableList<T> returnValue = super.<T>getRelationship(relationship);
		if(inherit && returnValue.isEmpty())
		{
			returnValue = parent.getRelationshipController().<T>getRelationship(relationship);
		}
		return returnValue;
	}

	public F getParent()
	{
		return parent;
	}
	
	@SuppressWarnings("unchecked")
	public F getRoot()
	{
		return (F) parent.getRelationshipController().getRoot();
	}
}
