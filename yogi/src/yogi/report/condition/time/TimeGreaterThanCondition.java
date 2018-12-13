package yogi.report.condition.time;


import yogi.period.time.Time;
import yogi.period.time.io.HHColonMMTimeScanner;
import yogi.report.condition.BaseGreaterThanCondition;


public class TimeGreaterThanCondition extends BaseGreaterThanCondition<Time> {
	
	private static HHColonMMTimeScanner scanner = new HHColonMMTimeScanner();
	
	public TimeGreaterThanCondition(String value) {
		super(value);
	}

	@Override
	public Time scan(String value) {
		return scanner.scan(value).create();
	}

}
