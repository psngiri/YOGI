package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.timestamp.TimestampLessThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimestampValidator;

public class TimestampLessThanOrEqualsConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;

	public TimestampLessThanOrEqualsConditionConfig(Formatter<Long> sqlFormatter) {
		super("LessThanOrEquals", new TimestampValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new TimestampLessThanOrEqualsCondition(value,sqlFormatter);
	}

}
