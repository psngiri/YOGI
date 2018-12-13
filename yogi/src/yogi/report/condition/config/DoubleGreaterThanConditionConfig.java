package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.DoubleGreaterThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleValidator;

public class DoubleGreaterThanConditionConfig extends ConditionConfig<Double> {

	private static final long serialVersionUID = 1L;

	public DoubleGreaterThanConditionConfig() {
		super("GreaterThan",new DoubleValidator());
	}

	@Override
	public Condition<Double> getCondition(String value) {
		return new DoubleGreaterThanCondition(value);
	}
}
