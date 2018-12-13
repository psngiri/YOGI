package yogi.base.relationship.types;

import yogi.base.relationship.RelationshipObject;

public class OneToManyDirectWithInverseRelationship<F extends RelationshipObject, T extends RelationshipObject> extends OneToManyRelationship<F, T> {

	OneToManyDirectWithInverseRelationship(Class<F> from, Class<T> to, String name, OneToManyInverseToADirectRelationship<T,F> inverseRelationship, boolean inherited) {
		super(from, to, name, false, inverseRelationship, inherited);
		inverseRelationship.setOtherRelationship(this);
	}
	
	OneToManyDirectWithInverseRelationship(Class<F> from, Class<T> to, String name, OneToOneInverseToADirectRelationship<T,F> inverseRelationship, boolean inherited) {
		super(from, to, name, false, inverseRelationship, inherited);
		inverseRelationship.setOtherRelationship(this);
	}
	
}
