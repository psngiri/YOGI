package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.LongGreaterThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.LongValidator;

public class LongGreaterThanOrEqualsConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;

	public LongGreaterThanOrEqualsConditionConfig() {
		super("GreaterThanOrEquals",new LongValidator());
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new LongGreaterThanOrEqualsCondition(value);
	}

}
