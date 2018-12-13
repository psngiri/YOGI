package examples.timeline;

import java.util.Collection;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.base.relationship.types.OneToManyDirectWithInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;

import examples.airport.Airport;
import examples.flight.Flight;

public class TimelineImpl extends RelationshipObjectImpl<Timeline> implements Timeline{
	private Airport airport;
	private static OneToManyDirectWithInverseRelationship<Timeline,Flight> arrivingFlights = RelationshipTypeFactory.get().createOneToManyDirectWithInverseRelationship(Timeline.class, Flight.class, "Arriving", TimelineManager.arrivingTimeline);
	private static OneToManyDirectWithInverseRelationship<Timeline,Flight> departingFlights = RelationshipTypeFactory.get().createOneToManyDirectWithInverseRelationship(Timeline.class, Flight.class, "Departing", TimelineManager.departingTimeline);
	
	
	protected TimelineImpl(Airport airport) {
		super();
		this.airport = airport;
	}

	public Airport getAirport() {
		return airport;
	}

	public ImmutableList<Flight> getArrivingFlights() {
		return getRelationship(arrivingFlights);
	}

	public ImmutableList<Flight> getDepartingFlights() {
		return getRelationship(departingFlights);
	}

	public void addArrivingFlight(Flight flight) {
		addToRelationship(arrivingFlights, flight);
	}

	public void addAllArrivingFlights(Collection<? extends Flight> flights) {
		addAllToRelationship(arrivingFlights, flights);
	}

	public void addDepartingFlight(Flight flight) {
		addToRelationship(departingFlights, flight);
	}

	public void addAllDepartingFlights(Collection<? extends Flight> flights) {
		addAllToRelationship(departingFlights, flights);
	}
	
	public String getName()
	{
		return airport.getName();
	}

	@Override
	public String toString() {
		return getName();
	}

}
