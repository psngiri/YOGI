package yogi.paging.column.types;


import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.period.date.io.DDMMMYYDateScanner;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.io.DateRangeFormatter;
import yogi.period.date.range.io.DateRangeScanner;
import yogi.report.condition.config.DateRangeContainsConditionConfig;
import yogi.report.condition.config.DateRangeIntersectsConditionConfig;
import yogi.report.condition.config.DateRangeNotInConditionConfig;
import yogi.report.condition.date.DateFormatter;
import yogi.report.server.config.Validator;

public class DateRangeTableColumnConfig extends TableColumnConfig<DateRange>{

	private static final long serialVersionUID = -664491411557302013L;
	private static DateRangeScanner scanner = new DateRangeScanner(new DDMMMYYDateScanner(), '-');
	
	public DateRangeTableColumnConfig(String name,ColumnFieldType columnFieldType, int width, boolean readOnly,
			boolean sortable, boolean filterable, boolean locked,
			ColumnAlignment columnAlignment, ColumnFilterType columnFilterType,
			TypeConfig config, Validator validator) {
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked,
				columnAlignment, columnFilterType, config, validator, new DateRangeFormatter(new DateFormatter(), '-'));
		addConditions();
	}
	
	public DateRangeTableColumnConfig(String name, int width){
		this(name, width, true);
	}
	
	public DateRangeTableColumnConfig(String name, int width, boolean readOnly){
		this(name, ColumnFieldType.UPPERCASETEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, null, null);
	}
	
	public DateRange scan(String value) {
		return scanner.scan(value);
	}

	public int compare(Object obj1, Object obj2) {
		return ((DateRange)obj1).compareTo((DateRange)obj2);
	}
	
	public void  addConditions(){
		this.addCondition(new DateRangeIntersectsConditionConfig());
		this.addCondition(new DateRangeNotInConditionConfig());
		this.addCondition(new DateRangeContainsConditionConfig());
	}

}
