package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.LongNotEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.LongValidator;

public class LongNotEqualsConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;

	public LongNotEqualsConditionConfig() {
		super("NotEquals",new LongValidator());
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new LongNotEqualsCondition(value);
	}

}
