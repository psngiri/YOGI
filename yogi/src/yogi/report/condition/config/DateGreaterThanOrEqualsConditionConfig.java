package yogi.report.condition.config;

import yogi.period.date.Date;
import yogi.report.condition.Condition;
import yogi.report.condition.date.DateGreaterThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateYYYYValidator;

public class DateGreaterThanOrEqualsConditionConfig extends ConditionConfig<Date> {

	private static final long serialVersionUID = 1L;

	public DateGreaterThanOrEqualsConditionConfig() {
		super("GreaterThanOrEquals", new DateYYYYValidator());
	}

	@Override
	public Condition<Date> getCondition(String value) {
		return new DateGreaterThanOrEqualsCondition(value);
	}

}
