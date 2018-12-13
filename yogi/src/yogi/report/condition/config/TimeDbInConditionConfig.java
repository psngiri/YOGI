package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.timeDb.TimeDbInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimeDbInValidator;

public class TimeDbInConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;

	public TimeDbInConditionConfig(Formatter<Long> sqlFormatter) {
		super("In", new TimeDbInValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new TimeDbInCondition(value,sqlFormatter);
	}

}
