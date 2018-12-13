package yogi.report.condition.config;

import yogi.base.util.FractionDouble;
import yogi.report.condition.Condition;
import yogi.report.condition.FractionDoubleIsNotBlankCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.NullValidator;

public class FractionDoubleIsNotBlankConditionConfig extends ConditionConfig<FractionDouble> {

	private static final long serialVersionUID = 1L;

	public FractionDoubleIsNotBlankConditionConfig() {
		super("Not Blank", new NullValidator());
	}

	@Override
	public Condition<FractionDouble> getCondition(String value) {
		return new FractionDoubleIsNotBlankCondition(value);
	}

}