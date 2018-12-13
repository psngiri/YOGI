package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.IsNullCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.NullValidator;

public class IsNullConditionConfig extends ConditionConfig<Object> {

	public static final String BLANK = "Blank";
	private static final long serialVersionUID = 1L;

	public IsNullConditionConfig() {
		super(BLANK, new NullValidator());
	}

	@Override
	public Condition<Object> getCondition(String value) {
		return new IsNullCondition<Object>(null);
	}

}