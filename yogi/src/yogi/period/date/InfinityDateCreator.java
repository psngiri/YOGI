package yogi.period.date;

import yogi.base.Creator;


public final class InfinityDateCreator implements Creator<Date> {
	public static final InfinityDateCreator creator = new InfinityDateCreator();
	public Date create() {
		return DateManager.INFINITY;
	}
	InfinityDateCreator() {
		super();
	}
}
