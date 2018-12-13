package yogi.report.condition.time;

import java.io.Serializable;

import yogi.base.Creator;
import yogi.period.time.Time;
import yogi.period.time.io.HHColonMMTimeScanner;

public class TimeScanner implements yogi.base.io.Scanner<Time, String>, Serializable {

	private static final long serialVersionUID = 923453098957811259L;
	private static HHColonMMTimeScanner timeScanner = new HHColonMMTimeScanner();	

	@Override
	public Creator<Time> scan(String record) {
		return timeScanner.scan(record);
	}

}
