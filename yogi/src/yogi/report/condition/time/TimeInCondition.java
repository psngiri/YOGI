package yogi.report.condition.time;


import yogi.period.time.Time;
import yogi.period.time.io.HHColonMMTimeScanner;
import yogi.report.condition.BaseInCondition;


public class TimeInCondition extends BaseInCondition<Time> {
	
	private static HHColonMMTimeScanner scanner = new HHColonMMTimeScanner();
	
	public TimeInCondition(String value) {
		super(value);
	}

	public TimeInCondition(String value, char separator) {
		super(value, separator);
	}

	@Override
	public Time scan(String value) {
		return scanner.scan(value).create();
	}

}
