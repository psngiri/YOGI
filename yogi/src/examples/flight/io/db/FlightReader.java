package examples.flight.io.db;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;

import examples.flight.Flight;
import examples.flight.FlightFactory;
import examples.flight.FlightValidator;

public class FlightReader extends DefaultDbRecordReader<Flight> {
	public static boolean RUN = true;
	public FlightReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<Flight, DbRecord>(new FlightValidator(), new FlightScanner(), FlightFactory.get(), new FlightRecordSelector()));
	}

	public FlightReader(Collection<DbRecord> flights) {
		super(flights);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
