package yogi.report.condition.date;

import yogi.period.date.Date;


public class DateNotInCondition extends DateInCondition {

	public DateNotInCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Date data) {
		return !super.satisfied(data);
	}
	
}
