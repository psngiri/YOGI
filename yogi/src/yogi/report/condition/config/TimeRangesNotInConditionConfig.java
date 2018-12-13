package yogi.report.condition.config;

import java.util.List;

import yogi.period.time.range.TimeRange;
import yogi.report.condition.Condition;
import yogi.report.condition.time.TimeRangesNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeRangesValidator;

public class TimeRangesNotInConditionConfig extends ConditionConfig<List<TimeRange>> {

	private static final long serialVersionUID = 1L;

	public TimeRangesNotInConditionConfig() {
		super("NotIn", new TimeRangesValidator());
	}

	@Override
	public Condition<List<TimeRange>> getCondition(String value) {
		return new TimeRangesNotInCondition(value);
	}

}
