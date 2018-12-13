package examples.retime.flight;

import yogi.base.relationship.RelationshipManager;

public class RetimeFlightManager extends RelationshipManager<RetimeFlight> {
	private static RetimeFlightManager itsInstance = new RetimeFlightManager();

	protected RetimeFlightManager() {
		super();
	}

	public static RetimeFlightManager get() {
		return itsInstance;
	}

	@Override
	protected void buildRelationships(RetimeFlight retimeFlight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteRelationships(RetimeFlight retimeFlight) {
		// TODO Auto-generated method stub
		
	}
}
