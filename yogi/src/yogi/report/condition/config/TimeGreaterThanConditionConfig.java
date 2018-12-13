package yogi.report.condition.config;

import yogi.period.time.Time;
import yogi.report.condition.Condition;
import yogi.report.condition.time.TimeGreaterThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeValidator;

public class TimeGreaterThanConditionConfig extends ConditionConfig<Time> {

	private static final long serialVersionUID = 1L;

	public TimeGreaterThanConditionConfig() {
		super("GreaterThan", new TimeValidator());
	}

	@Override
	public Condition<Time> getCondition(String value) {
		return new TimeGreaterThanCondition(value);
	}

}
