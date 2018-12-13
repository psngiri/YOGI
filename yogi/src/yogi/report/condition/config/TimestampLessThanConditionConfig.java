package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.timestamp.TimestampLessThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimestampValidator;

public class TimestampLessThanConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;

	public TimestampLessThanConditionConfig(Formatter<Long> sqlFormatter) {
		super("LessThan", new TimestampValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new TimestampLessThanCondition(value,sqlFormatter);
	}

}
