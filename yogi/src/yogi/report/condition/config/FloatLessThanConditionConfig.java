package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.FloatLessThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatValidator;

public class FloatLessThanConditionConfig extends ConditionConfig<Float> {

	private static final long serialVersionUID = 1L;

	public FloatLessThanConditionConfig() {
		super("LessThan",new FloatValidator());
	}

	@Override
	public Condition<Float> getCondition(String value) {
		return new FloatLessThanCondition(value);
	}

}
