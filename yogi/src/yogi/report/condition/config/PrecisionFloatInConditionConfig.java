package yogi.report.condition.config;

import yogi.base.util.PrecisionFloat;
import yogi.report.condition.Condition;
import yogi.report.condition.PrecisionFloatInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FloatInValidator;

public class PrecisionFloatInConditionConfig extends ConditionConfig<PrecisionFloat> {

	private static final long serialVersionUID = 1L;

	public PrecisionFloatInConditionConfig() {
		super("In", new FloatInValidator());
	}

	@Override
	public Condition<PrecisionFloat> getCondition(String value) {
		return new PrecisionFloatInCondition(value);
	}

}