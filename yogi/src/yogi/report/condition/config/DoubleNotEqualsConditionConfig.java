package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.DoubleNotEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleValidator;

public class DoubleNotEqualsConditionConfig extends ConditionConfig<Double> {

	private static final long serialVersionUID = 1L;

	public DoubleNotEqualsConditionConfig() {
		super("NotEquals",new DoubleValidator());
	}

	@Override
	public Condition<Double> getCondition(String value) {
		return new DoubleNotEqualsCondition(value);
	}
}
