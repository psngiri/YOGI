package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.FloatGreaterThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatValidator;

public class FloatGreaterThanOrEqualsConditionConfig extends ConditionConfig<Float> {

	private static final long serialVersionUID = 1L;

	public FloatGreaterThanOrEqualsConditionConfig() {
		super("GreaterThanOrEquals",new FloatValidator());
	}

	@Override
	public Condition<Float> getCondition(String value) {
		return new FloatGreaterThanOrEqualsCondition(value);
	}

}
