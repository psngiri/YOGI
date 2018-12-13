package yogi.paging.column.types;

import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.period.date.io.DateDbScanner;
import yogi.report.condition.config.DateDbGreaterThanConditionConfig;
import yogi.report.condition.config.DateDbGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.DateDbInConditionConfig;
import yogi.report.condition.config.DateDbLessThanConditionConfig;
import yogi.report.condition.config.DateDbLessThanOrEqualsConditionConfig;
import yogi.report.condition.dateDb.DateDbFormatter;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.DateDbValidator;

public class DateDbTableColumnConfig extends TableColumnConfig<Long>{
	
	private static final long serialVersionUID = 6917675484936781973L;
	
	public static String defaultFormatPattern = "dMy, H:i";
	
	public DateDbTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, DateDbFormatter formatter, String formatPattern) {	
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		this.setFormatPattern(formatPattern);
		addConditions();
	}
	
	public DateDbTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, DateDbFormatter formatter) {	
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		this.setFormatPattern(defaultFormatPattern);
	}
	
	public DateDbTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, config, new DateDbValidator(), null);
	}
	
	public DateDbTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, null);
	}
	
	public DateDbTableColumnConfig(String name, int width) {		
		this(name, width, true, new DateDbFormatter());		
	}
	
	public DateDbTableColumnConfig(String name, int width, boolean readOnly) {		
		this(name, width, readOnly, new DateDbFormatter());
	}
	
	public DateDbTableColumnConfig(String name, int width, boolean readOnly, String formatPattern) {		
		this(name, width, readOnly, new DateDbFormatter(), formatPattern);
	}
	 
    public DateDbTableColumnConfig(String name, int width, DateDbFormatter formatter) {		
		this(name, width, true, formatter);
	}
    
    public DateDbTableColumnConfig(String name, int width, boolean readOnly, DateDbFormatter formatter) {		
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING,null, new DateDbValidator(), formatter);
	}

    public DateDbTableColumnConfig(String name, int width, boolean readOnly, DateDbFormatter formatter, String formatPattern) {		
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING,null, new DateDbValidator(), formatter, formatPattern);
	}
    
	public Long scan(String value) {
		return DateDbScanner.scan(value); 
	}
	
	public int compare(Object obj1, Object obj2) {
		return ((Long)obj1).compareTo((Long)obj2);
	}
	
	protected void addConditions() {
		DateDbFormatter formatter = new DateDbFormatter();
		this.addCondition(new DateDbInConditionConfig(formatter));
		this.addCondition(new DateDbGreaterThanConditionConfig(formatter));
		this.addCondition(new DateDbGreaterThanOrEqualsConditionConfig(formatter));
		this.addCondition(new DateDbLessThanConditionConfig(formatter));
		this.addCondition(new DateDbLessThanOrEqualsConditionConfig(formatter));
	}

}
