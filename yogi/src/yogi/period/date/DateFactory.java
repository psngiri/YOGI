package yogi.period.date;

import yogi.base.Factory;
import yogi.base.Manager;

class DateFactory extends Factory<Date> {
	private static DateFactory dateFactory = new DateFactory(DateManager.get());
	
	static DateFactory get() {
		return dateFactory;
	}

	public DateFactory(Manager<Date> manager) {
		super(manager);
	}

	@Override
	public void clear() {
	}

	@Override
	protected void clearAll() {
	}

	@Override
	public void delete(Date object) {
	}

}
