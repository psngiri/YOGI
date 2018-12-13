package yogi.report.server.config.column;

import java.util.Comparator;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.IntervalTableColumnConfig;
import yogi.period.interval.Interval;
import yogi.period.interval.io.IntervalsScanner;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.function.interval.config.IntervalAddFunctionConfig;
import yogi.report.function.interval.config.IntervalIntersectionFunctionConfig;
import yogi.report.function.interval.config.IntervalUnionFunctionConfig;
import yogi.report.server.config.ColumnConfig;

public  class IntervalColumnConfig<R> extends ColumnConfig<R, List<Interval>> {

	private static final long serialVersionUID = 3468019700919515136L;

	public IntervalColumnConfig(String columnName, Evaluator<R, List<Interval>> evaluator, Formatter<List<Interval>> formatter, Comparator<List<Interval>> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
        this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
        this.addFunction(new IntervalAddFunctionConfig());
        this.addFunction(new IntervalIntersectionFunctionConfig());
        this.addFunction(new IntervalUnionFunctionConfig());
	}
	
	public IntervalColumnConfig(String columnName, Evaluator<R, List<Interval>> evaluator, Formatter<List<Interval>> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
	
	public IntervalColumnConfig(String columnName, Evaluator<R, List<Interval>> evaluator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, null, tableColumnConfig, key);
	}
	
	public IntervalColumnConfig(String columnName, Evaluator<R, List<Interval>>evaluator, Formatter<List<Interval>> formatter, IntervalsScanner scanner) {
		this(columnName, evaluator, formatter, new IntervalTableColumnConfig(columnName,120,formatter, scanner), false);
	}
	
	public IntervalColumnConfig(String columnName, Evaluator<R, List<Interval>> evaluator, String viewName, int width){
		this(columnName, evaluator,new IntervalTableColumnConfig(viewName, width, false),true);
	}
	
	public IntervalColumnConfig(String columnName, Evaluator<R, List<Interval>> evaluator){
		this(columnName, evaluator,columnName, 180);
	}
}