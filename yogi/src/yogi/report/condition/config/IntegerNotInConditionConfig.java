package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.IntegerNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.IntegerInValidator;

public class IntegerNotInConditionConfig extends ConditionConfig<Integer> {

	private static final long serialVersionUID = 1L;

	public IntegerNotInConditionConfig() {
		super("NotIn", new IntegerInValidator());
	}

	@Override
	public Condition<Integer> getCondition(String value) {
		return new IntegerNotInCondition(value);
	}

}
