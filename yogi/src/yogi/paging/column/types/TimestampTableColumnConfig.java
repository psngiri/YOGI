package yogi.paging.column.types;

import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.period.date.io.TimestampScanner;
import yogi.period.time.Time;
import yogi.report.condition.config.TimestampGreaterThanConditionConfig;
import yogi.report.condition.config.TimestampGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.TimestampInConditionConfig;
import yogi.report.condition.config.TimestampLessThanConditionConfig;
import yogi.report.condition.config.TimestampLessThanOrEqualsConditionConfig;
import yogi.report.condition.timestamp.TimestampFormatter;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.TimestampValidator;

public class TimestampTableColumnConfig extends TableColumnConfig<Long>{
	
	private static final long serialVersionUID = 6917675484936781973L;
	
	public static String defaultFormatPattern = "d-M-y H:i:s";
	
	public TimestampTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, TimestampFormatter formatter, String formatPattern) {	
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		this.setFormatPattern(formatPattern);
		addConditions();
	}
	
	public TimestampTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, TimestampFormatter formatter) {	
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		this.setFormatPattern(defaultFormatPattern);
	}
	
	public TimestampTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, config, new TimestampValidator(), null);
	}
	
	public TimestampTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, null);
	}
	
	public TimestampTableColumnConfig(String name, int width) {		
		this(name, width, true, new TimestampFormatter());		
	}
	
	public TimestampTableColumnConfig(String name, int width, boolean readOnly) {		
		this(name, width, readOnly, new TimestampFormatter());
	}
	
	public TimestampTableColumnConfig(String name, int width, boolean readOnly, String formatPattern) {		
		this(name, width, readOnly, new TimestampFormatter(), formatPattern);
	}
	 
    public TimestampTableColumnConfig(String name, int width, TimestampFormatter formatter) {		
		this(name, width, true, formatter);
	}
    
    public TimestampTableColumnConfig(String name, int width, boolean readOnly, TimestampFormatter formatter) {		
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING,null, new TimestampValidator(), formatter);
	}

    public TimestampTableColumnConfig(String name, int width, boolean readOnly, TimestampFormatter formatter, String formatPattern) {		
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING,null, new TimestampValidator(), formatter, formatPattern);
	}
    
	public Long scan(String value) {
		return TimestampScanner.scan(value); 
	}
	
	public int compare(Object obj1, Object obj2) {
		return ((Time)obj1).compareTo((Time)obj2);
	}
	
	protected void addConditions()
	{
		TimestampFormatter formatter = new TimestampFormatter();
		this.addCondition(new TimestampInConditionConfig(formatter));
		this.addCondition(new TimestampGreaterThanConditionConfig(formatter));
		this.addCondition(new TimestampGreaterThanOrEqualsConditionConfig(formatter));
		this.addCondition(new TimestampLessThanConditionConfig(formatter));
		this.addCondition(new TimestampLessThanOrEqualsConditionConfig(formatter));
	}

}
