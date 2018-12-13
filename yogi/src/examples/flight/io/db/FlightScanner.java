package examples.flight.io.db;

import yogi.base.io.Scanner;
import yogi.base.io.db.DbRecord;

import examples.flight.Flight;
import examples.flight.FlightCreator;

public class FlightScanner implements Scanner<Flight, DbRecord> {
	private FlightCreator creator = new FlightCreator();

	public FlightCreator scan(DbRecord dbRecord) {
		return creator;
	}
}
