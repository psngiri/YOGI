package examples.retime.flight;

import yogi.base.Creator;

import examples.flight.Flight;

public class RetimeFlightCreator implements Creator<RetimeFlight> {
	private Flight flight;
	private int candidateId;

	public RetimeFlight create() {
		return new RetimeFlightImpl(flight, candidateId);
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
