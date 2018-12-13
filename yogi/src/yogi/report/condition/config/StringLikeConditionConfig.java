package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringLikeCondition;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringLikeValidator;

public class StringLikeConditionConfig extends ConditionConfig<String> {

private static final long serialVersionUID = 1L;
	
	public StringLikeConditionConfig() {		
		super("Like", new StringLikeValidator());		
	}

	public StringLikeConditionConfig(BaseValidator validator) {
		super("Like", validator);
	}
	
	@Override
	public Condition<String> getCondition(String value) {
		return new StringLikeCondition(value);
	}

}
