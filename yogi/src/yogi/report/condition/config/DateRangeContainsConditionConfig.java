package yogi.report.condition.config;

import yogi.period.date.range.DateRange;
import yogi.report.condition.Condition;
import yogi.report.condition.date.DateRangeContainsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateRangeValidator;

public class DateRangeContainsConditionConfig extends ConditionConfig<DateRange> {

	private static final long serialVersionUID = 1L;

	public DateRangeContainsConditionConfig() {
		super("Contains",new DateRangeValidator());
	}

	@Override
	public Condition<DateRange> getCondition(String value) {
		return new DateRangeContainsCondition(value.toUpperCase());
	}

}
