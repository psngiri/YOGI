package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringNotEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringNumberValidator;

public class StringNumberNotEqualsConditionConfig extends ConditionConfig<String> {

	private static final long serialVersionUID = 1L;
	
	public StringNumberNotEqualsConditionConfig() {
		super("NotEquals", new StringNumberValidator());
	}

	@Override
	public Condition<String> getCondition(String value) {
		return new StringNotEqualsCondition(value.toUpperCase());
	}

}
