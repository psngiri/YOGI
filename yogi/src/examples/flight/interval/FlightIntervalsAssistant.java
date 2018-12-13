package examples.flight.interval;

import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.period.interval.data.IntervalsAssistant;
import yogi.period.interval.data.IntervalsObject;

import examples.flight.Flight;


public class FlightIntervalsAssistant extends IntervalsAssistant<Flight>{
	private static FlightIntervalsAssistant itsInstance = new FlightIntervalsAssistant();
	private static OneToOneSimpleRelationship<Flight, IntervalsObject> flightIntervals = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(Flight.class, IntervalsObject.class, "Intervals");
	public static FlightIntervalsAssistant get()
	{
		return itsInstance;
	}
	@Override
	protected OneToOneSimpleRelationship<Flight, IntervalsObject> getIntervaledDataRelationship() {
		return flightIntervals;
	}
	
}
