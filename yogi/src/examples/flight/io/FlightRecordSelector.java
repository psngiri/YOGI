package examples.flight.io;

import yogi.base.Selector;


public class FlightRecordSelector implements Selector<String> {

	public boolean select(String record) {
		if(record.trim().length() == 0) return false;
		return true;
	}

}
