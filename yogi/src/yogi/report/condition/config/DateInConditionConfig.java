package yogi.report.condition.config;

import yogi.period.date.Date;
import yogi.report.condition.Condition;
import yogi.report.condition.date.DateInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateYYYYInValidator;

public class DateInConditionConfig extends ConditionConfig<Date> {

	private static final long serialVersionUID = 1L;

	public DateInConditionConfig() {
		super("In", new DateYYYYInValidator());
	}

	@Override
	public Condition<Date> getCondition(String value) {
		return new DateInCondition(value.toUpperCase());
	}

}
