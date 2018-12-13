package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringLikeCondition;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringLikeValidator;

public class UCStringLikeConditionConfig extends ConditionConfig<String> {

private static final long serialVersionUID = 1L;
	
	public UCStringLikeConditionConfig() {		
		super("Like", new StringLikeValidator());		
	}

	public UCStringLikeConditionConfig(BaseValidator validator) {
		super("Like", validator);
	}
	
	@Override
	public Condition<String> getCondition(String value) {
		return new StringLikeCondition(value.toUpperCase());
	}
	
}
