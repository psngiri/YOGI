package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.LongEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.LongValidator;

public class LongEqualsConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;

	public LongEqualsConditionConfig() {
		super("Equals",new LongValidator());
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new LongEqualsCondition(value);
	}

}
