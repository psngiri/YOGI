package yogi.report.condition.time;


import yogi.period.time.Time;
import yogi.period.time.io.HHColonMMTimeScanner;
import yogi.report.condition.BaseLessThanCondition;


public class TimeLessThanCondition extends BaseLessThanCondition<Time> {
	
	private static HHColonMMTimeScanner scanner = new HHColonMMTimeScanner();
	
	public TimeLessThanCondition(String value) {
		super(value);
	}

	@Override
	public Time scan(String value) {
		return scanner.scan(value).create();
	}

}
