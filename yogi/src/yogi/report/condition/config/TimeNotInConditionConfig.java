package yogi.report.condition.config;

import yogi.period.time.Time;
import yogi.report.condition.Condition;
import yogi.report.condition.time.TimeNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeValidator;

public class TimeNotInConditionConfig extends ConditionConfig<Time> {

	private static final long serialVersionUID = 1L;

	public TimeNotInConditionConfig() {
		super("NotIn",  new TimeValidator());
	}

	@Override
	public Condition<Time> getCondition(String value) {
		return new TimeNotInCondition(value.toUpperCase());
	}

}
