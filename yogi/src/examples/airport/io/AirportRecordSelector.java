package examples.airport.io;

import yogi.base.Selector;

public class AirportRecordSelector implements Selector<String> {
	public boolean select(String record) {
		if(record.length() != 3) return false;
		return true;
	}
}
