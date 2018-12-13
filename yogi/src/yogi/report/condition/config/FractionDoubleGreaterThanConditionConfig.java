package yogi.report.condition.config;

import yogi.base.util.FractionDouble;
import yogi.report.condition.Condition;
import yogi.report.condition.FractionDoubleGreaterThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleValidator;

public class FractionDoubleGreaterThanConditionConfig extends ConditionConfig<FractionDouble> {

	private static final long serialVersionUID = 1L;

	public FractionDoubleGreaterThanConditionConfig() {
		super("GreaterThan", new DoubleValidator());
	}

	@Override
	public Condition<FractionDouble> getCondition(String value) {
		return new FractionDoubleGreaterThanCondition(value);
	}

}
