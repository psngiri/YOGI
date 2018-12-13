package yogi.report.server.config.column;


import java.util.Comparator;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.period.time.range.TimeRange;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.TimeRangesContainsConditionConfig;
import yogi.report.condition.config.TimeRangesIntersectsConditionConfig;
import yogi.report.condition.config.TimeRangesNotInConditionConfig;
import yogi.report.function.timeranges.config.TimeRangesAddFunctionConfig;
import yogi.report.function.timeranges.config.TimeRangesIntersectionFunctionConfig;
import yogi.report.function.timeranges.config.TimeRangesUnionFunctionConfig;
import yogi.report.server.config.ColumnConfig;


public class TimeRangesColumnConfig<R> extends ColumnConfig<R, List<TimeRange>> {

	private static final long serialVersionUID = 352890804379684264L;

	public TimeRangesColumnConfig(String columnName, Evaluator<R, List<TimeRange>> evaluator, Formatter<List<TimeRange>> formatter, Comparator<List<TimeRange>> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new TimeRangesContainsConditionConfig());
		this.addCondition(new TimeRangesIntersectsConditionConfig());
		this.addCondition(new TimeRangesNotInConditionConfig());
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
		this.addFunction(new TimeRangesAddFunctionConfig());
		this.addFunction(new TimeRangesIntersectionFunctionConfig());
		this.addFunction(new TimeRangesUnionFunctionConfig());
	}
	
	public TimeRangesColumnConfig(String columnName, Evaluator<R, List<TimeRange>> evaluator, Formatter<List<TimeRange>> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
}
