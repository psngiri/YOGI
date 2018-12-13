package examples.retime.flight;

import yogi.base.relationship.DerivedRelationshipObjectImpl;

import examples.airport.Airport;
import examples.flight.Flight;


public class RetimeFlightImpl extends DerivedRelationshipObjectImpl<Flight, RetimeFlight> implements RetimeFlight {
	private int candidateId;
	protected RetimeFlightImpl(Flight flight, int candidateId) {
		super(flight);
	}

	public Flight getFlight() {
		return getParent();
	}

	public int getCandidateId() {
		return candidateId;
	}

	public Airport getDepartureAirport() {
		return getFlight().getDepartureAirport();
	}

	public Airport getArrivalAirport() {
		return getFlight().getArrivalAirport();
	}

	public String getName() {
		return getFlight().getName() + candidateId;
	}

}
