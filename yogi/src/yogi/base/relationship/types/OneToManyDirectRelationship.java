package yogi.base.relationship.types;

import yogi.base.relationship.RelationshipObject;

public class OneToManyDirectRelationship<F extends RelationshipObject, T> extends OneToManyRelationship<F, T> {
    
	OneToManyDirectRelationship(Class<F> from, Class<T> to, String name, boolean inherited) {
		super(from, to, name, false, null, inherited);
	}
}
