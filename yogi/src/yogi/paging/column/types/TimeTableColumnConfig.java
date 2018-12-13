package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.base.io.Scanner;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.period.time.Time;
import yogi.report.condition.config.TimeGreaterThanConditionConfig;
import yogi.report.condition.config.TimeGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.TimeInConditionConfig;
import yogi.report.condition.config.TimeLessThanConditionConfig;
import yogi.report.condition.config.TimeLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.TimeNotInConditionConfig;
import yogi.report.condition.time.TimeFormatter;
import yogi.report.condition.time.TimeScanner;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.TimeValidator;

public class TimeTableColumnConfig extends TableColumnConfig<Time>{
	
	private static final long serialVersionUID = 6917675484936781973L;
	private Scanner<Time, String> timeScanner = new TimeScanner();
	
	public TimeTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<Time> formatter) {	
		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		addConditions();
	}
	
	public TimeTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, TimeFormatter formatter) {	
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
	}
	
	public TimeTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, config, new TimeValidator(), null);
	}
	
	public TimeTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, null);
	}
	
	public TimeTableColumnConfig(String name, int width){
		this(name, width, true);
	}
	
	public TimeTableColumnConfig(String name, int width, boolean readOnly){
		this(name, width, readOnly, new TimeFormatter(), new TimeScanner());
	}
    
	public TimeTableColumnConfig(String name, int width, boolean readOnly, Formatter<Time> formatter, Scanner<Time, String> scanner) {		
    	this(name, ColumnFieldType.UPPERCASETEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING,null, new TimeValidator(), formatter);
    	timeScanner = scanner;
    }

	public Time scan(String value) {
		return timeScanner.scan(value).create(); 
	}
	
	public int compare(Object obj1, Object obj2) {
		return ((Time)obj1).compareTo((Time)obj2);
	}
	
	protected void addConditions()
	{
		this.addCondition(new TimeInConditionConfig());
		this.addCondition(new TimeNotInConditionConfig());
		this.addCondition(new TimeGreaterThanConditionConfig());
		this.addCondition(new TimeGreaterThanOrEqualsConditionConfig());
		this.addCondition(new TimeLessThanConditionConfig());
		this.addCondition(new TimeLessThanOrEqualsConditionConfig());
	}

}
