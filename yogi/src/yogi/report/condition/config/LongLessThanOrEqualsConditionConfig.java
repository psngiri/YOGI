package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.LongLessThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.LongValidator;

public class LongLessThanOrEqualsConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;

	public LongLessThanOrEqualsConditionConfig() {
		super("LessThanOrEquals",new LongValidator());
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new LongLessThanOrEqualsCondition(value);
	}

}
