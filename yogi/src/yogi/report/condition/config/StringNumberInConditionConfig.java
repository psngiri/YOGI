package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringInCondition;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringNumberInValidator;

public class StringNumberInConditionConfig extends ConditionConfig<String> {

	private static final long serialVersionUID = 1L;

	public StringNumberInConditionConfig() {
		super("In", new StringNumberInValidator());
	}

	public StringNumberInConditionConfig(BaseValidator validator) {
		super("In", validator);
	}
	
	@Override
	public Condition<String> getCondition(String value) {
		return new StringInCondition(value.toUpperCase());
	}

}
