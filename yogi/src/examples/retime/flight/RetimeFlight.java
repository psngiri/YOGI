package examples.retime.flight;

import examples.flight.Flight;

public interface RetimeFlight extends Flight {
	Flight getFlight();
	int getCandidateId();
}
