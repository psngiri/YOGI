package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.DoubleLessThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleValidator;

public class DoubleLessThanConditionConfig extends ConditionConfig<Double> {

	private static final long serialVersionUID = 1L;

	public DoubleLessThanConditionConfig() {
		super("LessThan",new DoubleValidator());
	}

	@Override
	public Condition<Double> getCondition(String value) {
		return new DoubleLessThanCondition(value);
	}
}
