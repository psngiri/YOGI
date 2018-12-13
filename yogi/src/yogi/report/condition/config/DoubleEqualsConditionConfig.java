package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.DoubleEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleValidator;

public class DoubleEqualsConditionConfig extends ConditionConfig<Double> {

	private static final long serialVersionUID = 1L;

	public DoubleEqualsConditionConfig() {
		super("Equals",new DoubleValidator());
	}

	@Override
	public Condition<Double> getCondition(String value) {
		return new DoubleEqualsCondition(value);
	}
}
