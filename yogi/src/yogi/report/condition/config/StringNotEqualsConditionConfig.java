package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringNotEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringValidator;

public class StringNotEqualsConditionConfig extends ConditionConfig<String> {

	private static final long serialVersionUID = 1L;
	
	public StringNotEqualsConditionConfig() {
		super("NotEquals",new StringValidator());
	}

	@Override
	public Condition<String> getCondition(String value) {
		return new StringNotEqualsCondition(value.toUpperCase());
	}

}
