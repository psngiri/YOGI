package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.DoubleNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleInValidator;

public class DoubleNotInConditionConfig extends ConditionConfig<Double> {

	private static final long serialVersionUID = 1L;

	public DoubleNotInConditionConfig() {
		super("NotIn", new DoubleInValidator());
	}

	@Override
	public Condition<Double> getCondition(String value) {
		return new DoubleNotInCondition(value);
	}

}
