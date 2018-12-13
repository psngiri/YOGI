package yogi.period.interval.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import yogi.period.interval.Interval;

public class IntervalsScanner  implements Serializable{
	
	private static final long serialVersionUID = 407023124383894744L;
	private IntervalScanner intervalScanner;
	public IntervalsScanner() {
		this(new IntervalScanner());
	}

	public IntervalsScanner(IntervalScanner intervalScanner) {
		super();
		this.intervalScanner = intervalScanner;
	}

	public List<Interval> scan(String record)
	{
		String trim = record.trim();
		if(trim.isEmpty()) return null;
		List<Interval> rtnValue = new ArrayList<Interval>();
		String[] split = trim.split(",");
		for(int i = 0; i < split.length; i++){
			rtnValue.add(intervalScanner.scan(split[i].trim()));
		}
		return rtnValue;
	}
}
