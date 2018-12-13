package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.IntegerNotEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.IntegerValidator;

public class IntegerNotEqualsConditionConfig extends ConditionConfig<Integer> {

	private static final long serialVersionUID = 1L;

	public IntegerNotEqualsConditionConfig() {
		super("NotEquals",new IntegerValidator());
	}

	@Override
	public Condition<Integer> getCondition(String value) {
		return new IntegerNotEqualsCondition(value);
	}

}
