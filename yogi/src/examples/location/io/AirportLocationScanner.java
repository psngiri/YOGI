package examples.location.io;

import java.util.Scanner;

import examples.airport.Airport;
import examples.airport.AirportCreator;
import examples.location.AirportLocation;
import examples.location.AirportLocationCreator;
import static examples.airport.AirportFactory.get;

public class AirportLocationScanner implements yogi.base.io.Scanner<AirportLocation, String> {
    private AirportCreator creator = new AirportCreator();
    private AirportLocationCreator locationCreator = new AirportLocationCreator();
    
	public AirportLocationCreator scan(String record) {
		Scanner scanner = new Scanner(record);
		creator.setCode(scanner.next());
		Airport airport = get().create(creator);
		locationCreator.setAirport(airport);
		locationCreator.setLattitude(scanner.nextFloat());
		locationCreator.setLongitude(scanner.nextFloat());
		scanner.close();
		return locationCreator;
	}

}
