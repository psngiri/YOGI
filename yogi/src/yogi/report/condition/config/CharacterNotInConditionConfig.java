package yogi.report.condition.config;

import yogi.report.condition.CharacterNotInCondition;
import yogi.report.condition.Condition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringInValidator;

public class CharacterNotInConditionConfig extends ConditionConfig<Character> {

	private static final long serialVersionUID = 1L;

	public CharacterNotInConditionConfig() {
		super("NotIn",new StringInValidator());
	}

	@Override
	public Condition<Character> getCondition(String value) {
		return new CharacterNotInCondition(value.toUpperCase());
	}

}
