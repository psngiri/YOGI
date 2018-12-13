package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.DoubleLessThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleValidator;

public class DoubleLessThanOrEqualsConditionConfig extends ConditionConfig<Double> {

	private static final long serialVersionUID = 1L;

	public DoubleLessThanOrEqualsConditionConfig() {
		super("LessThanOrEquals",new DoubleValidator());
	}

	@Override
	public Condition<Double> getCondition(String value) {
		return new DoubleLessThanOrEqualsCondition(value);
	}
}
