package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.dateDb.DateDbGreaterThanCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateDbValidator;

public class DateDbGreaterThanConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;

	public DateDbGreaterThanConditionConfig(Formatter<Long> sqlFormatter) {
		super("GreaterThan", new DateDbValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new DateDbGreaterThanCondition(value,sqlFormatter);
	}

}
