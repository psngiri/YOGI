package yogi.base.relationship.types;

import yogi.base.relationship.RelationshipObject;


public class OneToOneInverseToADirectRelationship<F extends RelationshipObject, T extends RelationshipObject> extends OneToOneInverseRelationship<F,T> {

	OneToOneInverseToADirectRelationship(Class<F> from, Class<T> to, String name, boolean inherited) {
		super(from, to, name, inherited);
	}

}
