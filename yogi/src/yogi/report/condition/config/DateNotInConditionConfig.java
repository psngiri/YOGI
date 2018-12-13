package yogi.report.condition.config;

import yogi.period.date.Date;
import yogi.report.condition.Condition;
import yogi.report.condition.date.DateNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateYYYYInValidator;

public class DateNotInConditionConfig extends ConditionConfig<Date> {

	private static final long serialVersionUID = 1L;

	public DateNotInConditionConfig() {
		super("NotIn",  new DateYYYYInValidator());
	}

	@Override
	public Condition<Date> getCondition(String value) {
		return new DateNotInCondition(value.toUpperCase());
	}

}
