package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.timeDb.TimeDbLessThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeDbValidator;

public class TimeDbLessThanOrEqualsConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;

	public TimeDbLessThanOrEqualsConditionConfig(Formatter<Long> sqlFormatter) {
		super("LessThanOrEquals", new TimeDbValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new TimeDbLessThanOrEqualsCondition(value,sqlFormatter);
	}

}
