package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.dateDb.DateDbInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateDbInValidator;

public class DateDbInConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;
	
	public DateDbInConditionConfig(Formatter<Long> sqlFormatter) {
		super("In", new DateDbInValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new DateDbInCondition(value,sqlFormatter);
	}

}
