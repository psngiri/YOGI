package examples.server.fare;

import yogi.server.action.Action;
import yogi.server.base.record.IRecord;

import examples.server.fare.farekey.FareKey;
import examples.server.sub.Sub;

public interface Fare extends IRecord<FareKey, Sub> {
	Action getAction();
	int getFareAmount();
}
