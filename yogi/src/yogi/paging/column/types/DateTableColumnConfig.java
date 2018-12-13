package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.base.io.Scanner;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.period.date.Date;
import yogi.report.condition.config.DateGreaterThanConditionConfig;
import yogi.report.condition.config.DateGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.DateInConditionConfig;
import yogi.report.condition.config.DateLessThanConditionConfig;
import yogi.report.condition.config.DateLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.DateNotInConditionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.date.DateYYYYFormatter;
import yogi.report.condition.date.DateYYYYScanner;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.DateYYYYValidator;



public class DateTableColumnConfig extends TableColumnConfig<Date>{
	
	private static final long serialVersionUID = 6737006034156895621L;
	private Scanner<Date, String> dateScanner = new DateYYYYScanner();
	
	public DateTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<Date> formatter) {
		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		this.addCondition(new DateInConditionConfig());
		this.addCondition(new DateNotInConditionConfig());		
	}
	
	public DateTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, config, new DateYYYYValidator(), new DateYYYYFormatter());
	}
	
	public DateTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, null);
	}
	
	public DateTableColumnConfig(String name, int width){
		this(name, width, true);
	}
	
	public DateTableColumnConfig(String name, int width, boolean readOnly){
		this(name, width, readOnly, new DateYYYYFormatter(), new DateYYYYScanner());
	}
    
    public DateTableColumnConfig(String name, int width, boolean readOnly, Formatter<Date> formatter, Scanner<Date, String> scanner) {		
    	this(name, ColumnFieldType.UPPERCASETEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING,null, new DateYYYYValidator(), formatter);
    	dateScanner = scanner;
    }

	public Date scan(String value) {
		return dateScanner.scan(value).create();
	}
	
	public int compare(Object obj1, Object obj2) {
		return ((Date)obj1).compareTo((Date)obj2);
	}
	
	protected void addConditions() {
		this.addCondition(new DateInConditionConfig());
		this.addCondition(new DateNotInConditionConfig());
		this.addCondition(new DateGreaterThanConditionConfig());
		this.addCondition(new DateGreaterThanOrEqualsConditionConfig());
		this.addCondition(new DateLessThanConditionConfig());
		this.addCondition(new DateLessThanOrEqualsConditionConfig());
		this.addCondition(new IsNullConditionConfig());
	    this.addCondition(new IsNotNullConditionConfig());
	}

}
