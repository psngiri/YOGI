package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.FloatEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatValidator;

public class FloatEqualsConditionConfig extends ConditionConfig<Float> {

	private static final long serialVersionUID = 1L;

	public FloatEqualsConditionConfig() {
		super("Equals",new FloatValidator());
	}

	@Override
	public Condition<Float> getCondition(String value) {
		return new FloatEqualsCondition(value);
	}

}
