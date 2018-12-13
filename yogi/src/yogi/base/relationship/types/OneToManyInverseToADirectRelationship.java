package yogi.base.relationship.types;

import yogi.base.relationship.RelationshipObject;

public class OneToManyInverseToADirectRelationship<F extends RelationshipObject, T extends RelationshipObject> extends OneToManyInverseRelationship<F, T> {
    
	OneToManyInverseToADirectRelationship(Class<F> from, Class<T> to, String name, boolean inherited) {
		super(from, to, name, inherited);
	}
}
