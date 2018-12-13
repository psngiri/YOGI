package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.LongNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.LongInValidator;

public class LongNotInConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;

	public LongNotInConditionConfig() {
		super("NotIn", new LongInValidator());
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new LongNotInCondition(value);
	}

}
