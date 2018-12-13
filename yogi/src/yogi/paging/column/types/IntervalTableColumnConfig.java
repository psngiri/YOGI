package yogi.paging.column.types;

import java.util.List;

import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.period.date.io.DDMMMYYYYDateFormatter;
import yogi.period.date.range.io.DateRangeFormatter;
import yogi.period.date.range.io.DateRangeScanner;
import yogi.period.frequency.io.MondayToSundayOneToSevenFrequencyFormatter;
import yogi.period.interval.Interval;
import yogi.period.interval.io.IntervalScanner;
import yogi.period.interval.io.IntervalsFormatter;
import yogi.period.interval.io.IntervalsScanner;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.frequency.FrequencyScanner;
import yogi.report.function.interval.config.IntervalAddFunctionConfig;
import yogi.report.function.interval.config.IntervalIntersectionFunctionConfig;
import yogi.report.function.interval.config.IntervalUnionFunctionConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.IntervalValidator;

public class IntervalTableColumnConfig extends TableColumnConfig<List<Interval>> {	

	private static final long serialVersionUID = 2261291939676080348L;
	IntervalsScanner scanner = new IntervalsScanner(new IntervalScanner(new DateRangeScanner(), new FrequencyScanner(), ' '));

	public IntervalTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType,
			TypeConfig config, Validator validator, Formatter<List<Interval>> formatter) {		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, columnAlignment, columnFilterType, config, validator, formatter);		
		addConditions();
	}

	public IntervalTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, columnAlignment, columnFilterType, config, new IntervalValidator(), new IntervalsFormatter(new DateRangeFormatter(new DDMMMYYYYDateFormatter(), ' '),new MondayToSundayOneToSevenFrequencyFormatter(' '),' '));
	}
	
	public IntervalTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, columnAlignment, columnFilterType, null);
	}	
		
	public IntervalTableColumnConfig(String name, int width, Formatter<List<Interval>> formatter, boolean readOnly) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.LEFT, ColumnFilterType.STRING, null, new IntervalValidator(), formatter);
	}
	
	public IntervalTableColumnConfig(String name, int width, Formatter<List<Interval>> formatter, IntervalsScanner scanner) {
		this(name, width, formatter);
		this.scanner = scanner;		
	}
		
	public IntervalTableColumnConfig(String name, int width, boolean readOnly) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.LEFT, ColumnFilterType.STRING);		
	}
	
	public IntervalTableColumnConfig(String name, int width, Formatter<List<Interval>>formatter) {
		this(name, width, formatter, true);
	}
	
	public IntervalTableColumnConfig(String name, int width) {
		this(name, width, true);
	}
	
	public List<Interval> scan(String value) {
		return scanner.scan(value);
	}

	public int compare(Object obj1, Object obj2) {
		return ((Interval)obj1).compareTo((Interval)obj2);
	}
	
	protected void addConditions() {
	    this.addCondition(new IsNullConditionConfig());
	    this.addCondition(new IsNotNullConditionConfig());
	}

}
