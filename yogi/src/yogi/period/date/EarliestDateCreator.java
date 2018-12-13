package yogi.period.date;

import yogi.base.Creator;


public final class EarliestDateCreator implements Creator<Date> {
	
	public static final EarliestDateCreator creator = new EarliestDateCreator();
	
	public Date create() {
		return DateManager.EARLIEST_DATE;
	}
	
	EarliestDateCreator() {
		super();
	}
}
