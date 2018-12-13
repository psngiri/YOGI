package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.FloatNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatInValidator;

public class FloatNotInConditionConfig extends ConditionConfig<Float> {

	private static final long serialVersionUID = 1L;

	public FloatNotInConditionConfig() {
		super("NotIn", new FloatInValidator());
	}

	@Override
	public Condition<Float> getCondition(String value) {
		return new FloatNotInCondition(value);
	}

}
