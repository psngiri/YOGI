package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringNumberValidator;

public class StringNumberEqualsConditionConfig extends ConditionConfig<String> {

private static final long serialVersionUID = 1L;
	
	public StringNumberEqualsConditionConfig() {
		super("Equals", new StringNumberValidator());
	}

	@Override
	public Condition<String> getCondition(String value) {
		return new StringEqualsCondition(value.toUpperCase());
	}

}
