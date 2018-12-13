package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.DateRangeTableColumnConfig;
import yogi.period.date.range.DateRange;
import yogi.report.condition.config.DateRangeContainsConditionConfig;
import yogi.report.condition.config.DateRangeIntersectsConditionConfig;
import yogi.report.condition.config.DateRangeNotInConditionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.date.DefaultDateRangeFormatter;
import yogi.report.server.config.ColumnConfig;

public class DateRangeColumnConfig<R> extends ColumnConfig<R, DateRange> {

	private static final long serialVersionUID = 3880802262217859205L;
	
	public static String Name = "DateRange";
	public static String ViewName = "DateRange";
	public static int Width = 120;
	
	public DateRangeColumnConfig(String columnName, Evaluator<R, DateRange> evaluator, Formatter<DateRange> formatter, Comparator<DateRange> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new DateRangeContainsConditionConfig());
		this.addCondition(new DateRangeIntersectsConditionConfig());
		this.addCondition(new DateRangeNotInConditionConfig());
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
	}
	
	public DateRangeColumnConfig(String columnName, Evaluator<R, DateRange> evaluator, Formatter<DateRange> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
	
	public DateRangeColumnConfig(Evaluator<R,  DateRange> evaluator) {
		this(Name, evaluator, new DefaultDateRangeFormatter(), new DateRangeTableColumnConfig(ViewName,Width), false);		
	}
	
}
