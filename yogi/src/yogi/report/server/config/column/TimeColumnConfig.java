package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.TimeTableColumnConfig;
import yogi.period.time.Time;
import yogi.report.compare.function.average.config.LongAverageCompareFunctionConfig;
import yogi.report.compare.function.diff.config.LongDiffCompareFunctionConfig;
import yogi.report.compare.function.diff.config.TimeDiffCompareFunctionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.TimeGreaterThanConditionConfig;
import yogi.report.condition.config.TimeGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.TimeInConditionConfig;
import yogi.report.condition.config.TimeLessThanConditionConfig;
import yogi.report.condition.config.TimeLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.TimeNotInConditionConfig;
import yogi.report.server.config.ColumnConfig;


public class TimeColumnConfig<R> extends ColumnConfig<R, Time> {
	
	private static final long serialVersionUID = 6054707400303559604L;
	
	public TimeColumnConfig(String columnName, Evaluator<R, Time> evaluator, Formatter<Time> formatter, Comparator<Time> comparator,TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new TimeInConditionConfig());
		this.addCondition(new TimeNotInConditionConfig());
		this.addCondition(new TimeGreaterThanConditionConfig());
		this.addCondition(new TimeGreaterThanOrEqualsConditionConfig());
		this.addCondition(new TimeLessThanConditionConfig());
		this.addCondition(new TimeLessThanOrEqualsConditionConfig());
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
		this.addCompareFunction(new TimeDiffCompareFunctionConfig());
	}
	
	public TimeColumnConfig(String columnName, Evaluator<R, Time> evaluator, Formatter<Time> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
	
	public TimeColumnConfig(String columnName, Evaluator<R, Time> evaluator, Formatter<Time> formatter, int width) {
		this(columnName,evaluator,formatter, new TimeTableColumnConfig(columnName, width, false), false);
	}
	
	public TimeColumnConfig(String columnName, Evaluator<R, Time> evaluator, Formatter<Time> formatter) {
		this(columnName,evaluator,formatter, 120);
	}
}
