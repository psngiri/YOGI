package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringValidator;

public class StringEqualsConditionConfig extends ConditionConfig<String> {

private static final long serialVersionUID = 1L;
	
	public StringEqualsConditionConfig() {
		super("Equals",new StringValidator());
	}

	@Override
	public Condition<String> getCondition(String value) {
		return new StringEqualsCondition(value.toUpperCase());
	}

}
