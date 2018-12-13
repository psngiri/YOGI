package yogi.report.condition.config;

import yogi.report.condition.CharacterInCondition;
import yogi.report.condition.Condition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringInValidator;

public class CharacterInConditionConfig extends ConditionConfig<Character> {

	private static final long serialVersionUID = 1L;

	public CharacterInConditionConfig() {
		super("In",new StringInValidator());
	}

	@Override
	public Condition<Character> getCondition(String value) {
		return new CharacterInCondition(value.toUpperCase());
	}

}
