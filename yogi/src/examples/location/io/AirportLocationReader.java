package examples.location.io;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.DefaultStringRecordReader;

import examples.location.AirportLocation;
import examples.location.AirportLocationFactory;
import examples.location.AirportLocationValidator;

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
