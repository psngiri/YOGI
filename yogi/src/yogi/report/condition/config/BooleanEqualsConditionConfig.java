package yogi.report.condition.config;

import yogi.report.condition.BooleanEqualsCondition;
import yogi.report.condition.Condition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.BooleanValidator;

public class BooleanEqualsConditionConfig extends ConditionConfig<Boolean> {

	private static final long serialVersionUID = 1L;

	public BooleanEqualsConditionConfig() {
		super("In", new BooleanValidator());
	}

	@Override
	public Condition<Boolean> getCondition(String value) {
		return new BooleanEqualsCondition(value.toUpperCase());
	}

}
