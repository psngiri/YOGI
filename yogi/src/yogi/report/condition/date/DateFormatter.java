package yogi.report.condition.date;

import java.io.Serializable;

import yogi.base.io.Formatter;
import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.io.DDMMMYYDateFormatter;

public class DateFormatter implements Formatter<Date>, Serializable {

	private static final long serialVersionUID = 923453098957811259L;
	private static DDMMMYYDateFormatter formater = new DDMMMYYDateFormatter();
	
	@Override
	public String format(Date date) {
		if(date == DateManager.INFINITY||date == DateManager.EARLIEST_DATE||date==DateManager.UNKNOWN_DATE) return "00XXX00";
		return formater.format(date);
	}

}
