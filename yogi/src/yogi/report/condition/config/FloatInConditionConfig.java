package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.FloatInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatInValidator;

public class FloatInConditionConfig extends ConditionConfig<Float> {

	private static final long serialVersionUID = 1L;

	public FloatInConditionConfig() {
		super("In", new FloatInValidator());
	}

	@Override
	public Condition<Float> getCondition(String value) {
		return new FloatInCondition(value);
	}

}
