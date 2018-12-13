package yogi.report.condition.time;

import java.io.Serializable;

import yogi.base.io.Formatter;
import yogi.period.time.Time;
import yogi.period.time.io.HHColonMMTimeFormatter;

public class TimeFormatter implements Formatter<Time>, Serializable {

	private static final long serialVersionUID = 1L;
	private static HHColonMMTimeFormatter formater = new HHColonMMTimeFormatter();
	
	@Override
	public String format(Time time) {
		return formater.format(time);
	}

}