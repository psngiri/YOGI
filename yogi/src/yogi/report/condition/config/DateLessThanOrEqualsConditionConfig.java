package yogi.report.condition.config;

import yogi.period.date.Date;
import yogi.report.condition.Condition;
import yogi.report.condition.date.DateLessThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateYYYYValidator;

public class DateLessThanOrEqualsConditionConfig extends ConditionConfig<Date> {

	private static final long serialVersionUID = 1L;

	public DateLessThanOrEqualsConditionConfig() {
		super("LessThanOrEquals", new DateYYYYValidator());
	}

	@Override
	public Condition<Date> getCondition(String value) {
		return new DateLessThanOrEqualsCondition(value);
	}

}
