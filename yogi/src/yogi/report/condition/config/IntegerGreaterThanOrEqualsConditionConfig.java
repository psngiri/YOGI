package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.IntegerGreaterThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.IntegerValidator;

public class IntegerGreaterThanOrEqualsConditionConfig extends ConditionConfig<Integer> {

	private static final long serialVersionUID = 1L;

	public IntegerGreaterThanOrEqualsConditionConfig() {
		super("GreaterThanOrEquals",new IntegerValidator());
	}

	@Override
	public Condition<Integer> getCondition(String value) {
		return new IntegerGreaterThanOrEqualsCondition(value);
	}

}
