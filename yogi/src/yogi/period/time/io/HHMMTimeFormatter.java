package yogi.period.time.io;

import yogi.base.io.Formatter;
import yogi.period.time.Time;
import yogi.period.time.TimeUtils;

public class HHMMTimeFormatter implements Formatter<Time> {

	public String format(Time time) {
		short timeInMinutes = time.getTime();
		return format(timeInMinutes);
	}

	public String format(short timeInMinutes) {
		StringBuilder rtnValue = new StringBuilder();
		rtnValue.append(TimeUtils.getHoursComponent(timeInMinutes));
		rtnValue.append(TimeUtils.getMinutesComponent(timeInMinutes));
		return rtnValue.toString();
	}

}
