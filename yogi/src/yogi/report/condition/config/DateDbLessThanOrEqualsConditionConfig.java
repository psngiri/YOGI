package yogi.report.condition.config;

import yogi.base.io.Formatter;
import yogi.report.condition.Condition;
import yogi.report.condition.dateDb.DateDbLessThanOrEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.DateDbValidator;

public class DateDbLessThanOrEqualsConditionConfig extends ConditionConfig<Long> {

	private static final long serialVersionUID = 1L;
	private Formatter<Long> sqlFormatter;

	public DateDbLessThanOrEqualsConditionConfig(Formatter<Long> sqlFormatter) {
		super("LessThanOrEquals", new DateDbValidator());
		this.sqlFormatter = sqlFormatter;
	}

	@Override
	public Condition<Long> getCondition(String value) {
		return new DateDbLessThanOrEqualsCondition(value,sqlFormatter);
	}

}
