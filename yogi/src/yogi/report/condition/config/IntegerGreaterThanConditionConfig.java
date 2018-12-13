package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.IntegerGreaterThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.IntegerValidator;

public class IntegerGreaterThanConditionConfig extends ConditionConfig<Integer> {

	private static final long serialVersionUID = 1L;

	public IntegerGreaterThanConditionConfig() {
		super("GreaterThan",new IntegerValidator());
	}

	@Override
	public Condition<Integer> getCondition(String value) {
		return new IntegerGreaterThanCondition(value);
	}

}
