package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.timeDb.TimeDbGreaterThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeDbValidator;

public class TimeDbGreaterThanConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;

	public TimeDbGreaterThanConditionConfig(Formatter<Long> sqlFormatter) {
		super("GreaterThan", new TimeDbValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new TimeDbGreaterThanCondition(value,sqlFormatter);
	}

}
