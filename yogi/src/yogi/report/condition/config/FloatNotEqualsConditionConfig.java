package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.FloatNotEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatValidator;

public class FloatNotEqualsConditionConfig extends ConditionConfig<Float> {

	private static final long serialVersionUID = 1L;

	public FloatNotEqualsConditionConfig() {
		super("NotEquals",new FloatValidator());
	}

	@Override
	public Condition<Float> getCondition(String value) {
		return new FloatNotEqualsCondition(value);
	}

}
