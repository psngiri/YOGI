package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.timestamp.TimestampInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.TimestampInValidator;

public class TimestampInConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;
	
	public TimestampInConditionConfig(Formatter<Long> sqlFormatter) {
		super("In", new TimestampInValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new TimestampInCondition(value,sqlFormatter);
	}

}
