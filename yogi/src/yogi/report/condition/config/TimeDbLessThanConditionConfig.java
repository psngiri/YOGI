package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.timeDb.TimeDbLessThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeDbValidator;

public class TimeDbLessThanConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;

	public TimeDbLessThanConditionConfig(Formatter<Long> sqlFormatter) {
		super("LessThan", new TimeDbValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new TimeDbLessThanCondition(value,sqlFormatter);
	}

}
