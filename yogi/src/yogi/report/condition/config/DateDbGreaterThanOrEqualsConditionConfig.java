package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.dateDb.DateDbGreaterThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateDbValidator;

public class DateDbGreaterThanOrEqualsConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;

	public DateDbGreaterThanOrEqualsConditionConfig(Formatter<Long> sqlFormatter) {
		super("GreaterThanOrEquals", new DateDbValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new DateDbGreaterThanOrEqualsCondition(value,sqlFormatter);
	}

}
