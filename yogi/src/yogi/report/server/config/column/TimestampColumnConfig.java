package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.report.condition.config.TimestampGreaterThanConditionConfig;
import yogi.report.condition.config.TimestampGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.TimestampInConditionConfig;
import yogi.report.condition.config.TimestampLessThanConditionConfig;
import yogi.report.condition.config.TimestampLessThanOrEqualsConditionConfig;
import yogi.report.function.max.config.MaxLongFunctionConfig;
import yogi.report.function.min.config.MinLongFunctionConfig;
import yogi.report.server.config.ColumnConfig;

public class TimestampColumnConfig<R> extends ColumnConfig<R, Long> {
	
	private static final long serialVersionUID = 1L;
	
	public TimestampColumnConfig(String columnName, Evaluator<R, Long> evaluator, Formatter<Long> formatter, Comparator<Long> comparator,TableColumnConfig<?> tableColumnConfig, boolean key, Formatter<Long> sqlFormatter) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new TimestampInConditionConfig(sqlFormatter));
		this.addCondition(new TimestampGreaterThanConditionConfig(sqlFormatter));
		this.addCondition(new TimestampGreaterThanOrEqualsConditionConfig(sqlFormatter));
		this.addCondition(new TimestampLessThanConditionConfig(sqlFormatter));
		this.addCondition(new TimestampLessThanOrEqualsConditionConfig(sqlFormatter));
		this.addFunction(new MinLongFunctionConfig());
		this.addFunction(new MaxLongFunctionConfig());
	}
	
	public TimestampColumnConfig(String columnName, Evaluator<R, Long> evaluator, Formatter<Long> formatter, TableColumnConfig<?> tableColumnConfig, boolean key, Formatter<Long> sqlFormatter) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key, sqlFormatter);
	}
	
}
