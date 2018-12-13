package yogi.report.condition.config;

import java.util.List;

import yogi.period.time.range.TimeRange;
import yogi.report.condition.Condition;
import yogi.report.condition.time.TimeRangesIntersectsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeRangesValidator;

public class TimeRangesIntersectsConditionConfig extends ConditionConfig<List<TimeRange>> {

	private static final long serialVersionUID = 1L;

	public TimeRangesIntersectsConditionConfig() {
		super("Intersects", new TimeRangesValidator());
	}

	@Override
	public Condition<List<TimeRange>> getCondition(String value) {
		return new TimeRangesIntersectsCondition(value);
	}

}
