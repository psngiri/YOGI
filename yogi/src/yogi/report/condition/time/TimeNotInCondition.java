package yogi.report.condition.time;

import yogi.period.time.Time;


public class TimeNotInCondition extends TimeInCondition {
	
	public TimeNotInCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Time data) {
		return !super.satisfied(data);
	}
}
