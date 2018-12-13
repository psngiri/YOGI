package yogi.report.condition.config;

import yogi.base.util.PrecisionFloat;
import yogi.report.condition.Condition;
import yogi.report.condition.PrecisionFloatNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatInValidator;

public class PrecisionFloatNotInConditionConfig extends ConditionConfig<PrecisionFloat> {

	private static final long serialVersionUID = 1L;

	public PrecisionFloatNotInConditionConfig() {
		super("NotIn", new FloatInValidator());
	}

	@Override
	public Condition<PrecisionFloat> getCondition(String value) {
		return new PrecisionFloatNotInCondition(value);
	}

}
