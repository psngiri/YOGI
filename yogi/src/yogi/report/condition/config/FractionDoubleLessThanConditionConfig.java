package yogi.report.condition.config;

import yogi.base.util.FractionDouble;
import yogi.report.condition.Condition;
import yogi.report.condition.FractionDoubleLessThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleValidator;

public class FractionDoubleLessThanConditionConfig extends ConditionConfig<FractionDouble> {

	private static final long serialVersionUID = 1L;

	public FractionDoubleLessThanConditionConfig() {
		super("LessThan", new DoubleValidator());
	}

	@Override
	public Condition<FractionDouble> getCondition(String value) {
		return new FractionDoubleLessThanCondition(value);
	}

}
