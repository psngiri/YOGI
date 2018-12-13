package yogi.optimize.sm;

import yogi.base.relationship.RelationshipManager;

public class SmManager extends RelationshipManager<Sm> {
	private static SmManager itsInstance = new SmManager();

	protected SmManager() {
		super();
	}

	public static SmManager get() {
		return itsInstance;
	}

	@Override
	protected void buildRelationships(Sm sm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteRelationships(Sm sm) {
		// TODO Auto-generated method stub
		
	}
}
