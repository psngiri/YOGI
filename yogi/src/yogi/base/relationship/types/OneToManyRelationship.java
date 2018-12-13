package yogi.base.relationship.types;

import yogi.base.relationship.RelationshipObject;

public class OneToManyRelationship<F extends RelationshipObject, T> extends RelationshipType<F, T> {
    
	protected OneToManyRelationship(Class<F> from, Class<T> to, String name, boolean inverse, RelationshipType<T, F> otherRelationship, boolean inherited) {
		super(from, to, name, inverse, false, otherRelationship, inherited);
	}
}
