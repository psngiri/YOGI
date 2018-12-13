package examples.location.inheritance.io;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.DefaultStringRecordReader;

import examples.location.inheritance.AirportLocation;
import examples.location.inheritance.AirportLocationFactory;
import examples.location.inheritance.AirportLocationValidator;

public class AirportLocationReader extends DefaultStringRecordReader<AirportLocation> {
	public static boolean RUN = true;
	public AirportLocationReader(String fileName) {
		super(fileName);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<AirportLocation, String>(new AirportLocationValidator(), new AirportLocationScanner(), AirportLocationFactory.get(), null));
	}

	public AirportLocationReader(Collection<String> airportLocations) {
		super(airportLocations);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
