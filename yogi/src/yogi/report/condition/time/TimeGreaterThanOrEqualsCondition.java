package yogi.report.condition.time;


import yogi.period.time.Time;
import yogi.period.time.io.HHColonMMTimeScanner;
import yogi.report.condition.BaseGreaterThanOrEqualsCondition;


public class TimeGreaterThanOrEqualsCondition extends BaseGreaterThanOrEqualsCondition<Time> {
	
	private static HHColonMMTimeScanner scanner = new HHColonMMTimeScanner();
	
	public TimeGreaterThanOrEqualsCondition(String value) {
		super(value);
	}

	@Override
	public Time scan(String value) {
		return scanner.scan(value).create();
	}

}
