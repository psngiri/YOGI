package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.report.condition.config.DateDbGreaterThanConditionConfig;
import yogi.report.condition.config.DateDbGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.DateDbInConditionConfig;
import yogi.report.condition.config.DateDbLessThanConditionConfig;
import yogi.report.condition.config.DateDbLessThanOrEqualsConditionConfig;
import yogi.report.function.max.config.MaxLongFunctionConfig;
import yogi.report.function.min.config.MinLongFunctionConfig;
import yogi.report.server.config.ColumnConfig;


public class DateDbColumnConfig<R> extends ColumnConfig<R, Long> {
	
	private static final long serialVersionUID = 1L;
	
	public DateDbColumnConfig(String columnName, Evaluator<R, Long> evaluator, Formatter<Long> formatter, Comparator<Long> comparator,TableColumnConfig<?> tableColumnConfig, boolean key, Formatter<Long> sqlFormatter) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new DateDbInConditionConfig(sqlFormatter));
		this.addCondition(new DateDbGreaterThanConditionConfig(sqlFormatter));
		this.addCondition(new DateDbGreaterThanOrEqualsConditionConfig(sqlFormatter));
		this.addCondition(new DateDbLessThanConditionConfig(sqlFormatter));
		this.addCondition(new DateDbLessThanOrEqualsConditionConfig(sqlFormatter));
		this.addFunction(new MinLongFunctionConfig());
		this.addFunction(new MaxLongFunctionConfig());
	}
	
	public DateDbColumnConfig(String columnName, Evaluator<R, Long> evaluator, Formatter<Long> formatter, TableColumnConfig<?> tableColumnConfig, boolean key, Formatter<Long> sqlFormatter) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key, sqlFormatter);
	}
	
}
