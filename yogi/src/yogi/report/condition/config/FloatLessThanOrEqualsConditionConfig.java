package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.FloatLessThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatValidator;

public class FloatLessThanOrEqualsConditionConfig extends ConditionConfig<Float> {

	private static final long serialVersionUID = 1L;

	public FloatLessThanOrEqualsConditionConfig() {
		super("LessThanOrEquals",new FloatValidator());
	}

	@Override
	public Condition<Float> getCondition(String value) {
		return new FloatLessThanOrEqualsCondition(value);
	}

}
