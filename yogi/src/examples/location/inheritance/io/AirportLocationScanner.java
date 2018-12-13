package examples.location.inheritance.io;

import java.util.Scanner;

import examples.location.inheritance.AirportLocation;
import examples.location.inheritance.AirportLocationCreator;

public class AirportLocationScanner implements yogi.base.io.Scanner<AirportLocation, String> {
    private AirportLocationCreator locationCreator = new AirportLocationCreator();
    
	public AirportLocationCreator scan(String record) {
		Scanner scanner = new Scanner(record);
		locationCreator.setCode(scanner.next());
		locationCreator.setLattitude(scanner.nextFloat());
		locationCreator.setLongitude(scanner.nextFloat());
		scanner.close();
		return locationCreator;
	}

}
