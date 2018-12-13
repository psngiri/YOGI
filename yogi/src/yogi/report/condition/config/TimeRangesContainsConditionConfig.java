package yogi.report.condition.config;

import java.util.List;

import yogi.period.time.range.TimeRange;
import yogi.report.condition.Condition;
import yogi.report.condition.time.TimeRangesContainsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeRangesValidator;

public class TimeRangesContainsConditionConfig extends ConditionConfig<List<TimeRange>> {

	private static final long serialVersionUID = 1L;

	public TimeRangesContainsConditionConfig() {
		super("Contains", new TimeRangesValidator());
	}
	
	@Override
	public Condition<List<TimeRange>> getCondition(String value) {
		return new TimeRangesContainsCondition(value);
	}

}
