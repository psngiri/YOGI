package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.IntegerEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.IntegerValidator;

public class IntegerEqualsConditionConfig extends ConditionConfig<Integer> {

	private static final long serialVersionUID = 1L;

	public IntegerEqualsConditionConfig() {
		super("Equals",new IntegerValidator());
	}

	@Override
	public Condition<Integer> getCondition(String value) {
		return new IntegerEqualsCondition(value);
	}

}
