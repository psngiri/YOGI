package yogi.report.condition.time;


import yogi.period.time.Time;
import yogi.period.time.io.HHColonMMTimeScanner;
import yogi.report.condition.BaseLessThanOrEqualsCondition;


public class TimeLessThanOrEqualsCondition extends BaseLessThanOrEqualsCondition<Time> {
	
	private static HHColonMMTimeScanner scanner = new HHColonMMTimeScanner();
	
	public TimeLessThanOrEqualsCondition(String value) {
		super(value);
	}

	@Override
	public Time scan(String value) {
		return scanner.scan(value).create();
	}

}
