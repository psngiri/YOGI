package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.DoubleInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleInValidator;

public class DoubleInConditionConfig extends ConditionConfig<Double> {

	private static final long serialVersionUID = 1L;

	public DoubleInConditionConfig() {
		super("In", new DoubleInValidator());
	}

	@Override
	public Condition<Double> getCondition(String value) {
		return new DoubleInCondition(value);
	}

}
