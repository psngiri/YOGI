package yogi.base.relationship;

import java.util.Collection;

import yogi.base.relationship.types.OneToManyDirectRelationship;
import yogi.base.relationship.types.OneToManyDirectWithInverseRelationship;
import yogi.base.util.immutable.ImmutableList;

public abstract class BaseRelationshipObjectImpl<F extends RelationshipObject> implements RelationshipObject {
	protected BaseRelationshipObjectImpl() {
		super();
	}

	public <T extends RelationshipObject, R extends OneToManyDirectWithInverseRelationship<? super F,? super T>>  ImmutableList<T> getRelationship(R oneToManyDirectWithInverseRelationship) {
		return getRelationshipController().getRelationship(oneToManyDirectWithInverseRelationship);
	}

	public <T extends RelationshipObject, R extends OneToManyDirectWithInverseRelationship<? super F,? super T>>  ImmutableList<T> getRelationship(R oneToManyDirectWithInverseRelationship, boolean inherited) {
		return getRelationshipController().getRelationship(oneToManyDirectWithInverseRelationship, inherited);
	}

	public <T extends RelationshipObject, R extends OneToManyDirectWithInverseRelationship<? super F,? super T>> void addToRelationship(R oneToManyDirectWithInverseRelationship, T value) {
		getRelationshipController().addToRelationship(oneToManyDirectWithInverseRelationship, value);
	}

	public <T extends RelationshipObject, R extends OneToManyDirectWithInverseRelationship<? super F,? super T>> void addAllToRelationship(R oneToManyDirectWithInverseRelationship, Collection<? extends T> items) {
		getRelationshipController().addAllToRelationship(oneToManyDirectWithInverseRelationship, items);
	}

	public <T extends RelationshipObject, R extends OneToManyDirectWithInverseRelationship<? super F,? super T>> void removeFromRelationship(R oneToManyDirectWithInverseRelationship, T value) {
		getRelationshipController().removeFromRelationship(oneToManyDirectWithInverseRelationship, value);
	}

	public <T extends RelationshipObject, R extends OneToManyDirectWithInverseRelationship<? super F,? super T>> void removeAllFromRelationship(R oneToManyDirectWithInverseRelationship, Collection<? extends T> items) {
		getRelationshipController().removeAllFromRelationship(oneToManyDirectWithInverseRelationship, items);
	}

	public <T, R extends OneToManyDirectRelationship<? super F,? super T>> ImmutableList<T> getRelationship(R oneToManyDirectRelationship) {
		return getRelationshipController().getRelationship(oneToManyDirectRelationship);
	}

	public <T, R extends OneToManyDirectRelationship<? super F,? super T>> ImmutableList<T> getRelationship(R oneToManyDirectRelationship, boolean inherited) {
		return getRelationshipController().getRelationship(oneToManyDirectRelationship, inherited);
	}

	public <T, R extends OneToManyDirectRelationship<? super F,? super T>> void addToRelationship(R oneToManyDirectRelationship, T value) {
		getRelationshipController().addToRelationship(oneToManyDirectRelationship, value);
	}

	public <T, R extends OneToManyDirectRelationship<? super F,? super T>> void addAllToRelationship(R oneToManyDirectRelationship, Collection<? extends T> items) {
		getRelationshipController().addAllToRelationship(oneToManyDirectRelationship, items);
	}

	public <T, R extends OneToManyDirectRelationship<? super F,? super T>> void removeFromRelationship(R oneToManyDirectRelationship, T value) {
		getRelationshipController().removeFromRelationship(oneToManyDirectRelationship, value);
	}

	public <T, R extends OneToManyDirectRelationship<? super F,? super T>> void removeAllFromRelationship(R oneToManyDirectRelationship, Collection<? extends T> items) {
		getRelationshipController().removeAllFromRelationship(oneToManyDirectRelationship, items);
	}
}
