package yogi.base.relationship.types;

import yogi.base.SynchronizedFactory;
import yogi.base.app.ErrorReporter;
import yogi.base.relationship.RelationshipObject;


public class RelationshipTypeFactory extends SynchronizedFactory<RelationshipType> {
	private static RelationshipTypeFactory relationshipFactory = new RelationshipTypeFactory();
	public static RelationshipTypeFactory get()
	{
		return relationshipFactory;
	}
	
	protected RelationshipTypeFactory() {
		super(RelationshipTypeManager.get());
	}
	
	@Override
	protected void clearAll() {
	}

	@Override
	public void delete(RelationshipType relationship) {
		ErrorReporter.get().error("RelationshipType once created cant be deleted", relationship);
	}

	public <F extends RelationshipObject, T> OneToManyDirectRelationship<F, T> createOneToManyDirectRelationship(Class<F> from, Class<T> to, String name)
	{
		return createOneToManyDirectRelationship(from, to, name, false);
	}
	
	public <F extends RelationshipObject, T extends RelationshipObject> OneToManyDirectWithInverseRelationship<F, T> createOneToManyDirectWithInverseRelationship(Class<F> from, Class<T> to, String name, OneToManyInverseToADirectRelationship<T,F> inverseRelationship)
	{
		return createOneToManyDirectWithInverseRelationship(from, to, name, inverseRelationship, false);
	}

	public <F extends RelationshipObject, T extends RelationshipObject> OneToManyDirectWithInverseRelationship<F, T> createOneToManyDirectWithInverseRelationship(Class<F> from, Class<T> to, String name, OneToOneInverseToADirectRelationship<T,F> inverseRelationship)
	{
		return createOneToManyDirectWithInverseRelationship(from, to, name, inverseRelationship, false);
	}

	public <F extends RelationshipObject, T> OneToManyInverseRelationship<F, T> createOneToManyInverseRelationship(Class<F> from, Class<T> to, String name)
	{
		return createOneToManyInverseRelationship(from, to, name, false);
	}

	public <F extends RelationshipObject, T extends RelationshipObject> OneToManyInverseToADirectRelationship<F, T> createOneToManyInverseToADirectRelationship(Class<F> from, Class<T> to, String name)
	{
		return createOneToManyInverseToADirectRelationship(from, to, name, false);
	}

	public <F extends RelationshipObject, T> OneToOneInverseRelationship<F, T> createOneToOneInverseRelationship(Class<F> from, Class<T> to, String name)
	{
		return createOneToOneInverseRelationship(from, to, name, false);
	}

	public <F extends RelationshipObject, T extends RelationshipObject> OneToOneInverseToADirectRelationship<F, T> createOneToOneInverseToADirectRelationship(Class<F> from, Class<T> to, String name)
	{
		return createOneToOneInverseToADirectRelationship(from, to, name, false);
	}

	public <F extends RelationshipObject, T> OneToOneSimpleRelationship<F, T> createOneToOneSimpleRelationship(Class<F> from, Class<T> to, String name)
	{
		return createOneToOneSimpleRelationship(from, to, name, true);
	}

	public <F extends RelationshipObject, T> OneToManyDirectRelationship<F, T> createOneToManyDirectRelationship(Class<F> from, Class<T> to, String name, boolean inherited)
	{
		OneToManyDirectRelationship<F, T> oneToManyDirectRelationship = new OneToManyDirectRelationship<F, T>(from, to, name, inherited);
		this.add(oneToManyDirectRelationship);
		return oneToManyDirectRelationship;
	}
	
	public <F extends RelationshipObject, T extends RelationshipObject> OneToManyDirectWithInverseRelationship<F, T> createOneToManyDirectWithInverseRelationship(Class<F> from, Class<T> to, String name, OneToManyInverseToADirectRelationship<T,F> inverseRelationship, boolean inherited)
	{
		OneToManyDirectWithInverseRelationship<F, T> oneToManyDirectWithInverseRelationship = new OneToManyDirectWithInverseRelationship<F, T>(from, to, name, inverseRelationship, inherited);
		this.add(oneToManyDirectWithInverseRelationship);
		return oneToManyDirectWithInverseRelationship;
	}

	public <F extends RelationshipObject, T extends RelationshipObject> OneToManyDirectWithInverseRelationship<F, T> createOneToManyDirectWithInverseRelationship(Class<F> from, Class<T> to, String name, OneToOneInverseToADirectRelationship<T,F> inverseRelationship, boolean inherited)
	{
		OneToManyDirectWithInverseRelationship<F, T> oneToManyDirectWithInverseRelationship = new OneToManyDirectWithInverseRelationship<F, T>(from, to, name, inverseRelationship, inherited);
		this.add(oneToManyDirectWithInverseRelationship);
		return oneToManyDirectWithInverseRelationship;
	}

	public <F extends RelationshipObject, T> OneToManyInverseRelationship<F, T> createOneToManyInverseRelationship(Class<F> from, Class<T> to, String name, boolean inherited)
	{
		OneToManyInverseRelationship<F, T> oneToManyInverseRelationship = new OneToManyInverseRelationship<F, T>(from, to, name, inherited);
		this.add(oneToManyInverseRelationship);
		return oneToManyInverseRelationship;
	}

	public <F extends RelationshipObject, T extends RelationshipObject> OneToManyInverseToADirectRelationship<F, T> createOneToManyInverseToADirectRelationship(Class<F> from, Class<T> to, String name, boolean inherited)
	{
		OneToManyInverseToADirectRelationship<F, T> oneToManyInverseToADirectRelationship = new OneToManyInverseToADirectRelationship<F, T>(from, to, name, inherited);
		this.add(oneToManyInverseToADirectRelationship);
		return oneToManyInverseToADirectRelationship;
	}

	public <F extends RelationshipObject, T> OneToOneInverseRelationship<F, T> createOneToOneInverseRelationship(Class<F> from, Class<T> to, String name, boolean inherited)
	{
		OneToOneInverseRelationship<F, T> oneToOneInverseRelationship = new OneToOneInverseRelationship<F, T>(from, to, name, inherited);
		this.add(oneToOneInverseRelationship);
		return oneToOneInverseRelationship;
	}

	public <F extends RelationshipObject, T extends RelationshipObject> OneToOneInverseToADirectRelationship<F, T> createOneToOneInverseToADirectRelationship(Class<F> from, Class<T> to, String name, boolean inherited)
	{
		OneToOneInverseToADirectRelationship<F, T> oneToOneInverseToADirectRelationship = new OneToOneInverseToADirectRelationship<F, T>(from, to, name, inherited);
		this.add(oneToOneInverseToADirectRelationship);
		return oneToOneInverseToADirectRelationship;
	}

	public <F extends RelationshipObject, T> OneToOneSimpleRelationship<F, T> createOneToOneSimpleRelationship(Class<F> from, Class<T> to, String name, boolean inherited)
	{
		OneToOneSimpleRelationship<F, T> oneToOneSimpleRelationship = new OneToOneSimpleRelationship<F, T>(from, to, name, inherited);
		this.add(oneToOneSimpleRelationship);
		return oneToOneSimpleRelationship;
	}

}
