package examples.retime.flight;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;


public class RetimeFlightAssistant extends RelationshipAssistant<RetimeFlight>{
	private static RetimeFlightAssistant retimeFlightAssistant = new RetimeFlightAssistant();
	private static OneToOneSimpleRelationship<RetimeFlight, Integer> retimePenalty = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(RetimeFlight.class, Integer.class, "RetimePenalty");
	public static RetimeFlightAssistant get()
	{
		return retimeFlightAssistant;
	}
	
	public Integer getRetimePenalty(RetimeFlight retimeFlight) {
		Integer penalty = getRelationship(retimeFlight, retimePenalty);
		return penalty;
	}
	
	public void setRetimePenalty(RetimeFlight retimeFlight, Integer penalty)
	{
		setRelationship(retimeFlight, retimePenalty, penalty);
	}
}
