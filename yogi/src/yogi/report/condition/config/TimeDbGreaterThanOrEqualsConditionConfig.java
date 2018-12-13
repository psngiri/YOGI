package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.timeDb.TimeDbGreaterThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeDbValidator;

public class TimeDbGreaterThanOrEqualsConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;

	public TimeDbGreaterThanOrEqualsConditionConfig(Formatter<Long> sqlFormatter) {
		super("GreaterThanOrEquals", new TimeDbValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new TimeDbGreaterThanOrEqualsCondition(value,sqlFormatter);
	}

}
