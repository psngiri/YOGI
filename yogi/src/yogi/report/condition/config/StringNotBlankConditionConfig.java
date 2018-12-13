package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringNotBlankCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.NullValidator;

public class StringNotBlankConditionConfig extends ConditionConfig<String> {

	private static final long serialVersionUID = 1L;

	public StringNotBlankConditionConfig() {
		super("NotBlank", new NullValidator());
	}

	@Override
	public Condition<String> getCondition(String value) {
		return new StringNotBlankCondition(null);
	}

}