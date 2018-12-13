package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.IsNotNullCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.NullValidator;

public class IsNotNullConditionConfig extends ConditionConfig<Object> {

	public static final String NOT_BLANK = "Not Blank";
	private static final long serialVersionUID = 1L;

	public IsNotNullConditionConfig() {
		super(NOT_BLANK, new NullValidator());
	}

	@Override
	public Condition<Object> getCondition(String value) {
		return new IsNotNullCondition<Object>(null);
	}

}