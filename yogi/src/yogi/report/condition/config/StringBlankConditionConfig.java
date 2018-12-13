package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringBlankCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.NullValidator;

public class StringBlankConditionConfig extends ConditionConfig<String> {

	private static final long serialVersionUID = 1L;

	public StringBlankConditionConfig() {
		super("Blank", new NullValidator());
	}

	@Override
	public Condition<String> getCondition(String value) {
		return new StringBlankCondition(null);
	}

}