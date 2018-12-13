package examples.flight.io;

import java.util.Scanner;

import examples.flight.Flight;
import examples.flight.FlightCreator;



public class FlightScanner implements yogi.base.io.Scanner<Flight, String> {
	private FlightCreator creator = new FlightCreator();
	
	public FlightCreator scan(String record) {
		Scanner scanner = new Scanner(record);
		creator.setDepartureAirportCode(scanner.next());
		creator.setArrivalAirportCode(scanner.next());
		scanner.close();
		return creator;
	}

}
