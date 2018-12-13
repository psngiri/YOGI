package yogi.report.condition.config;

import yogi.base.util.PrecisionFloat;
import yogi.report.condition.Condition;
import yogi.report.condition.PrecisionFloatLessThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatValidator;

public class PrecisionFloatLessThanConditionConfig extends ConditionConfig<PrecisionFloat> {

	private static final long serialVersionUID = 1L;

	public PrecisionFloatLessThanConditionConfig() {
		super("LessThan",new FloatValidator());
	}

	@Override
	public Condition<PrecisionFloat> getCondition(String value) {
		return new PrecisionFloatLessThanCondition(value);
	}

}
