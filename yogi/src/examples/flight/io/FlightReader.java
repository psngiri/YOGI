package examples.flight.io;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.DefaultStringRecordReader;

import examples.flight.Flight;
import examples.flight.FlightFactory;
import examples.flight.FlightValidator;

public class FlightReader extends DefaultStringRecordReader<Flight> {
	public static boolean RUN = true;
	public FlightReader(String fileName) {
		super(fileName);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<Flight, String>(new FlightValidator(), new FlightScanner(), FlightFactory.get(), new FlightRecordSelector()));
	}

	public FlightReader(Collection<String> flights) {
		super(flights);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
