package yogi.period.date;

import yogi.base.Creator;

class DateCreator implements Creator<Date> {
	private long numberOfDaysSinceEpoch;
	public Date create() {
		return new DateImpl(numberOfDaysSinceEpoch);
	}
	protected DateCreator(long numberOfDaysSinceEpoch) {
		super();
		this.numberOfDaysSinceEpoch = numberOfDaysSinceEpoch;
	}
	public long getNumberOfDaysSinceEpoch() {
		return numberOfDaysSinceEpoch;
	}
	public DateCreator setNumberOfDaysSinceEpoch(long numberOfDaysSinceEpoch) {
		this.numberOfDaysSinceEpoch = numberOfDaysSinceEpoch;
		return this;
	}
	public DateCreator() {
		super();
	}
	
}
