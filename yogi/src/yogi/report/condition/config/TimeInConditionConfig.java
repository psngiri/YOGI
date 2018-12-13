package yogi.report.condition.config;

import yogi.period.time.Time;
import yogi.report.condition.Condition;
import yogi.report.condition.time.TimeInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeValidator;

public class TimeInConditionConfig extends ConditionConfig<Time> {

	private static final long serialVersionUID = 1L;

	public TimeInConditionConfig() {
		super("In", new TimeValidator());
	}

	@Override
	public Condition<Time> getCondition(String value) {
		return new TimeInCondition(value.toUpperCase());
	}

}
