package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.LongGreaterThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.LongValidator;

public class LongGreaterThanConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;

	public LongGreaterThanConditionConfig() {
		super("GreaterThan",new LongValidator());
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new LongGreaterThanCondition(value);
	}

}
