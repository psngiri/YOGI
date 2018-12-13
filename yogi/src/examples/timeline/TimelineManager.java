package examples.timeline;

import yogi.base.relationship.RelationshipManager;
import yogi.base.relationship.types.OneToOneInverseRelationship;
import yogi.base.relationship.types.OneToOneInverseToADirectRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;

import examples.airport.Airport;
import examples.flight.Flight;

public class TimelineManager extends RelationshipManager<Timeline>{
   private static TimelineManager timelineManager = new TimelineManager();
	static OneToOneInverseToADirectRelationship<Flight,Timeline> arrivingTimeline = RelationshipTypeFactory.get().createOneToOneInverseToADirectRelationship(Flight.class, Timeline.class, "Arriving");
	static OneToOneInverseToADirectRelationship<Flight,Timeline> departingTimeline = RelationshipTypeFactory.get().createOneToOneInverseToADirectRelationship(Flight.class, Timeline.class, "Departing");
	private static OneToOneInverseRelationship<Airport,Timeline> timelinesAirport = RelationshipTypeFactory.get().createOneToOneInverseRelationship(Airport.class, Timeline.class, "Timeline");
    

	protected TimelineManager() {
		super();
	}


	public static TimelineManager get() {
		return timelineManager;
	}

	protected  void buildRelationships(Timeline timeline) {
		buildRelationship(timeline.getAirport(), timeline, timelinesAirport);
	}
	
	protected void deleteRelationships(Timeline timeline) {
		deleteRelationship(timeline.getAirport(), timeline, timelinesAirport);
	}

	public Timeline getArrivingTimeline(Flight flight)
	{
		return getRelationship(flight, arrivingTimeline);
	}
	
	public Timeline getDepartingTimeline(Flight flight)
	{
		return getRelationship(flight, departingTimeline);
	}

	public Timeline getTimeline(Airport airport)
	{
		return getRelationship(airport, timelinesAirport);
	}
}
