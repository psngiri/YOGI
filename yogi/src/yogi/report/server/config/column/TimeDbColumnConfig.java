package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.TimeDbGreaterThanConditionConfig;
import yogi.report.condition.config.TimeDbGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.TimeDbInConditionConfig;
import yogi.report.condition.config.TimeDbLessThanConditionConfig;
import yogi.report.condition.config.TimeDbLessThanOrEqualsConditionConfig;
import yogi.report.server.config.ColumnConfig;


public class TimeDbColumnConfig<R> extends ColumnConfig<R, Long> {
	
	private static final long serialVersionUID = 1L;

	public TimeDbColumnConfig(String columnName, Evaluator<R, Long> evaluator, Formatter<Long> formatter, Comparator<Long> comparator,TableColumnConfig<?> tableColumnConfig, boolean key,Formatter<Long> sqlFormatter) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new TimeDbInConditionConfig(sqlFormatter));
		this.addCondition(new TimeDbGreaterThanConditionConfig(sqlFormatter));
		this.addCondition(new TimeDbGreaterThanOrEqualsConditionConfig(sqlFormatter));
		this.addCondition(new TimeDbLessThanConditionConfig(sqlFormatter));
		this.addCondition(new TimeDbLessThanOrEqualsConditionConfig(sqlFormatter));
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
	}
	
	public TimeDbColumnConfig(String columnName, Evaluator<R, Long> evaluator, Formatter<Long> formatter, TableColumnConfig<?> tableColumnConfig, boolean key,Formatter<Long> sqlFormatter) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key, sqlFormatter);
	}

}
