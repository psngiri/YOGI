package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.LongLessThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.LongValidator;

public class LongLessThanConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;

	public LongLessThanConditionConfig() {
		super("LessThan",new LongValidator());
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new LongLessThanCondition(value);
	}

}
