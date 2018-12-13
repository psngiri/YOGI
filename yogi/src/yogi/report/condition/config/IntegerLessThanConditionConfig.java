package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.IntegerLessThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.IntegerValidator;

public class IntegerLessThanConditionConfig extends ConditionConfig<Integer> {

	private static final long serialVersionUID = 1L;

	public IntegerLessThanConditionConfig() {
		super("LessThan",new IntegerValidator());
	}

	@Override
	public Condition<Integer> getCondition(String value) {
		return new IntegerLessThanCondition(value);
	}

}
