package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.IntegerLessThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.IntegerValidator;

public class IntegerLessThanOrEqualsConditionConfig extends ConditionConfig<Integer> {

	private static final long serialVersionUID = 1L;

	public IntegerLessThanOrEqualsConditionConfig() {
		super("LessThanOrEquals",new IntegerValidator());
	}

	@Override
	public Condition<Integer> getCondition(String value) {
		return new IntegerLessThanOrEqualsCondition(value);
	}

}
