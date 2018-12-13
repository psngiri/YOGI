package yogi.base.relationship.types;

import yogi.base.relationship.RelationshipObject;

public class OneToManyInverseRelationship<F extends RelationshipObject, T> extends OneToManyRelationship<F, T> {
    
	OneToManyInverseRelationship(Class<F> from, Class<T> to, String name, boolean inherited) {
		super(from, to, name, true, null, inherited);
	}
}
