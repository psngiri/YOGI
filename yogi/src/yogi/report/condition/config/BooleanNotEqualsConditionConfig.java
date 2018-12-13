package yogi.report.condition.config;

import yogi.report.condition.BooleanNotEqualsCondition;
import yogi.report.condition.Condition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.BooleanValidator;

public class BooleanNotEqualsConditionConfig extends ConditionConfig<Boolean> {

	private static final long serialVersionUID = 1L;

	public BooleanNotEqualsConditionConfig() {
		super("NotIn", new BooleanValidator());
	}

	@Override
	public Condition<Boolean> getCondition(String value) {
		return new BooleanNotEqualsCondition(value.toUpperCase());
	}

}
