package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringNumberLikeCondition;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringNumberLikeValidator;

public class StringNumberLikeConditionConfig extends ConditionConfig<String> {

private static final long serialVersionUID = 1L;
	
	public StringNumberLikeConditionConfig() {
		super("Like", new StringNumberLikeValidator());
	}

	public StringNumberLikeConditionConfig(BaseValidator validator) {
		super("Like", validator);
	}
	
	@Override
	public Condition<String> getCondition(String value) {
		return new StringNumberLikeCondition(value);
	}

}
