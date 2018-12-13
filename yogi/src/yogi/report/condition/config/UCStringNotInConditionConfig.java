package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringNotInCondition;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringInValidator;

public class UCStringNotInConditionConfig extends ConditionConfig<String> {

	private static final long serialVersionUID = 1L;

	public UCStringNotInConditionConfig() {
		super("NotIn",new StringInValidator());
	}

	public UCStringNotInConditionConfig(BaseValidator validator) {
		super("NotIn", validator);
	}
	
	@Override
	public Condition<String> getCondition(String value) {
		return new StringNotInCondition(value.toUpperCase());
	}
}
