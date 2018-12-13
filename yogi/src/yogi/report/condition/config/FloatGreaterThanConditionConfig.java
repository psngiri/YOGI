package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.FloatGreaterThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatValidator;

public class FloatGreaterThanConditionConfig extends ConditionConfig<Float> {

	private static final long serialVersionUID = 1L;

	public FloatGreaterThanConditionConfig() {
		super("GreaterThan",new FloatValidator());
	}

	@Override
	public Condition<Float> getCondition(String value) {
		return new FloatGreaterThanCondition(value);
	}

}
