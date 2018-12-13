package examples.airport.io;

import java.util.Scanner;

import examples.airport.Airport;
import examples.airport.AirportCreator;


public class AirportScanner implements yogi.base.io.Scanner<Airport, String> {
    private AirportCreator creator = new AirportCreator();
    
	public AirportCreator scan(String record) {
		Scanner scanner = new Scanner(record);
		creator.setCode(scanner.next());
		scanner.close();
		return creator;
	}

}
