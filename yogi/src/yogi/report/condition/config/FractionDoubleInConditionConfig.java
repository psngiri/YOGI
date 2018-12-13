package yogi.report.condition.config;

import yogi.base.util.FractionDouble;
import yogi.report.condition.Condition;
import yogi.report.condition.FractionDoubleInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DoubleInValidator;

public class FractionDoubleInConditionConfig extends ConditionConfig<FractionDouble> {

	private static final long serialVersionUID = 1L;

	public FractionDoubleInConditionConfig() {
		super("In", new DoubleInValidator());
	}

	@Override
	public Condition<FractionDouble> getCondition(String value) {
		return new FractionDoubleInCondition(value);
	}

}
