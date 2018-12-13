package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.DoubleGreaterThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleValidator;

public class DoubleGreaterThanOrEqualsConditionConfig extends ConditionConfig<Double> {

	private static final long serialVersionUID = 1L;

	public DoubleGreaterThanOrEqualsConditionConfig() {
		super("GreaterThanOrEquals",new DoubleValidator());
	}

	@Override
	public Condition<Double> getCondition(String value) {
		return new DoubleGreaterThanOrEqualsCondition(value);
	}
}
