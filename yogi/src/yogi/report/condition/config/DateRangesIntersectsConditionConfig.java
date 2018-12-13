package yogi.report.condition.config;

import java.util.List;

import yogi.period.date.range.DateRange;
import yogi.report.condition.Condition;
import yogi.report.condition.date.DateRangesIntersectsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateRangesValidator;

public class DateRangesIntersectsConditionConfig extends ConditionConfig<List<DateRange>> {

	private static final long serialVersionUID = 1L;

	public DateRangesIntersectsConditionConfig() {
		super("Intersects", new DateRangesValidator());
	}

	@Override
	public Condition<List<DateRange>> getCondition(String value) {
		return new DateRangesIntersectsCondition(value);
	}

}
