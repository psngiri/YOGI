package yogi.report.condition.config;

import yogi.base.util.FractionDouble;
import yogi.report.condition.Condition;
import yogi.report.condition.FractionDoubleIsBlankCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.NullValidator;

public class FractionDoubleIsBlankConditionConfig extends ConditionConfig<FractionDouble> {

	private static final long serialVersionUID = 1L;

	public FractionDoubleIsBlankConditionConfig() {
		super("Blank", new NullValidator());
	}

	@Override
	public Condition<FractionDouble> getCondition(String value) {
		return new FractionDoubleIsBlankCondition(value);
	}

}