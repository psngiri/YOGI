package yogi.report.condition.config;

import yogi.base.util.FractionDouble;
import yogi.report.condition.Condition;
import yogi.report.condition.FractionDoubleNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleInValidator;

public class FractionDoubleNotInConditionConfig extends ConditionConfig<FractionDouble> {

	private static final long serialVersionUID = 1L;

	public FractionDoubleNotInConditionConfig() {
		super("NotIn", new DoubleInValidator());
	}

	@Override
	public Condition<FractionDouble> getCondition(String value) {
		return new FractionDoubleNotInCondition(value);
	}

}
