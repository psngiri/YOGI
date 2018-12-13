package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.IntegerInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.IntegerInValidator;

public class IntegerInConditionConfig extends ConditionConfig<Integer> {

	private static final long serialVersionUID = 1L;

	public IntegerInConditionConfig() {
		super("In", new IntegerInValidator());
	}

	@Override
	public Condition<Integer> getCondition(String value) {
		return new IntegerInCondition(value);
	}

}
