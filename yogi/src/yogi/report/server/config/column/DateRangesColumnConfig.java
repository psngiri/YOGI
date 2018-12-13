package yogi.report.server.config.column;


import java.util.Comparator;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.period.date.range.DateRange;
import yogi.report.condition.config.DateRangesContainsConditionConfig;
import yogi.report.condition.config.DateRangesIntersectsConditionConfig;
import yogi.report.condition.config.DateRangesNotInConditionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.function.dateranges.config.DateRangesAddFunctionConfig;
import yogi.report.function.dateranges.config.DateRangesIntersectionFunctionConfig;
import yogi.report.function.dateranges.config.DateRangesUnionFunctionConfig;
import yogi.report.server.config.ColumnConfig;


public class DateRangesColumnConfig<R> extends ColumnConfig<R, List<DateRange>> {

	private static final long serialVersionUID = -4754476392775423876L;

	public DateRangesColumnConfig(String columnName, Evaluator<R, List<DateRange>> evaluator, Formatter<List<DateRange>> formatter, Comparator<List<DateRange>> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new DateRangesContainsConditionConfig());
		this.addCondition(new DateRangesIntersectsConditionConfig());
		this.addCondition(new DateRangesNotInConditionConfig());
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
		this.addFunction(new DateRangesAddFunctionConfig());
		this.addFunction(new DateRangesIntersectionFunctionConfig());
		this.addFunction(new DateRangesUnionFunctionConfig());
	}
	
	public DateRangesColumnConfig(String columnName, Evaluator<R, List<DateRange>> evaluator, Formatter<List<DateRange>> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
}
