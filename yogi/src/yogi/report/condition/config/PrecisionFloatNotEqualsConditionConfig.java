package yogi.report.condition.config;

import yogi.base.util.PrecisionFloat;
import yogi.report.condition.Condition;
import yogi.report.condition.PrecisionFloatNotEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatValidator;

public class PrecisionFloatNotEqualsConditionConfig extends ConditionConfig<PrecisionFloat> {

	private static final long serialVersionUID = 1L;

	public PrecisionFloatNotEqualsConditionConfig() {
		super("Not Equals",new FloatValidator());
	}

	@Override
	public Condition<PrecisionFloat> getCondition(String value) {
		return new PrecisionFloatNotEqualsCondition(value);
	}

}
