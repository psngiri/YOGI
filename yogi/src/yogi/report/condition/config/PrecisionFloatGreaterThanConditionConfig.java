package yogi.report.condition.config;

import yogi.base.util.PrecisionFloat;
import yogi.report.condition.Condition;
import yogi.report.condition.PrecisionFloatGreaterThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatValidator;

public class PrecisionFloatGreaterThanConditionConfig extends ConditionConfig<PrecisionFloat> {

	private static final long serialVersionUID = 1L;

	public PrecisionFloatGreaterThanConditionConfig() {
		super("GreaterThan",new FloatValidator());
	}

	@Override
	public Condition<PrecisionFloat> getCondition(String value) {
		return new PrecisionFloatGreaterThanCondition(value);
	}

}
