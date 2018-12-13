package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringNotInCondition;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringInValidator;

public class StringNotInConditionConfig extends ConditionConfig<String> {

	private static final long serialVersionUID = 1L;

	public StringNotInConditionConfig() {
		super("NotIn",new StringInValidator());
	}

	public StringNotInConditionConfig(BaseValidator validator) {
		super("NotIn", validator);
	}
	
	@Override
	public Condition<String> getCondition(String value) {
		return new StringNotInCondition(value);
	}

}
