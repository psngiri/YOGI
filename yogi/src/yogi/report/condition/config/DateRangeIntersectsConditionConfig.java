package yogi.report.condition.config;

import yogi.period.date.range.DateRange;
import yogi.report.condition.Condition;
import yogi.report.condition.date.DateRangeIntersectsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateRangeValidator;

public class DateRangeIntersectsConditionConfig extends ConditionConfig<DateRange> {

	private static final long serialVersionUID = 1L;

	public DateRangeIntersectsConditionConfig() {
		super("Intersects",new DateRangeValidator());
	}

	@Override
	public Condition<DateRange> getCondition(String value) {
		return new DateRangeIntersectsCondition(value.toUpperCase());
	}

}
