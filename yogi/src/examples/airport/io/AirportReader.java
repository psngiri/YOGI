package examples.airport.io;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.DefaultStringRecordReader;

import examples.airport.Airport;
import examples.airport.AirportFactory;
import examples.airport.AirportValidator;

public class AirportReader extends DefaultStringRecordReader<Airport> {
	public AirportReader(String fileName) {
		super(fileName);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<Airport, String>(new AirportValidator(), new AirportScanner(), AirportFactory.get(), new AirportRecordSelector()));
	}

	public AirportReader(Collection<String> airports) {
		super(airports);
		setup();
	}
}
