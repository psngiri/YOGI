package yogi.optimize.expr;


import java.util.List;

import yogi.base.Filter;
import yogi.base.ObjectNotFoundException;
import yogi.base.Selector;
import yogi.base.app.ErrorReporter;
import yogi.base.relationship.RelationshipManager;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;

public class ConstraintManager extends RelationshipManager<Constraint> {
	private static ConstraintManager itsInstance = new ConstraintManager();
	private static OneToManyInverseRelationship<RelationshipObject, Constraint> constarintsRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(RelationshipObject.class, Constraint.class, "Constraints");
	
	public static ConstraintManager get() {
		return itsInstance;
	}

	protected ConstraintManager() {
		super();
	}

	@Override
	protected void buildRelationships(Constraint constraint) {
		super.buildRelationship(constraint.getConstraintObject(), constraint, constarintsRel);
	}

	@Override
	protected void deleteRelationships(Constraint constraint) {
		super.deleteRelationship(constraint.getConstraintObject(), constraint, constarintsRel);
	}
	
	public ImmutableList<Constraint> getConstraints(RelationshipObject object)
	{
		return super.getRelationship(object, constarintsRel);
	}

	public Constraint getConstraint(RelationshipObject object, String prefix, String suffix) throws ObjectNotFoundException
	{
		List<Constraint> filter = Filter.filter(getConstraints(object), new ConstraintSelector(prefix, suffix));
		if(filter.size() > 1) ErrorReporter.get().error("Unique Constraint violated for Constraints", filter);
		else if(filter.isEmpty()){
			throw new ObjectNotFoundException(String.format("No Constraints found for the Object: %s with prefix: %s, and suffix: %s", object.getName(), prefix, suffix));
		}
		return filter.get(0);
	}
	
	static class ConstraintSelector implements Selector<Constraint>
	{
		private String prefix;
		private String suffix;
		
		public ConstraintSelector(String prefix, String suffix) {
			super();
			this.prefix = prefix;
			this.suffix = suffix;
		}

		public boolean select(Constraint item) {
			return item.getPrefix().equals(prefix) && item.getSuffix().equals(suffix);
		}
		
	}
}
