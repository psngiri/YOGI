package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.LongInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.LongInValidator;

public class LongInConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;

	public LongInConditionConfig() {
		super("In", new LongInValidator());
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new LongInCondition(value);
	}

}
