package yogi.report.condition.config;

import java.util.List;

import yogi.period.date.range.DateRange;
import yogi.report.condition.Condition;
import yogi.report.condition.date.DateRangesNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateRangesValidator;

public class DateRangesNotInConditionConfig extends ConditionConfig<List<DateRange>> {

	private static final long serialVersionUID = 1L;

	public DateRangesNotInConditionConfig() {
		super("NotIn", new DateRangesValidator());
	}

	@Override
	public Condition<List<DateRange>> getCondition(String value) {
		return new DateRangesNotInCondition(value);
	}

}
