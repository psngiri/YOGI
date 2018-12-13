package yogi.paging.column.types;

import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.period.time.io.TimeDbScanner;
import yogi.report.condition.config.TimeDbGreaterThanConditionConfig;
import yogi.report.condition.config.TimeDbGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.TimeDbInConditionConfig;
import yogi.report.condition.config.TimeDbLessThanConditionConfig;
import yogi.report.condition.config.TimeDbLessThanOrEqualsConditionConfig;
import yogi.report.condition.timeDb.TimeDbFormatter;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.TimeDbValidator;

public class TimeDbTableColumnConfig extends TableColumnConfig<Long>{
	
	private static final long serialVersionUID = 6917675484936781973L;
	
	public static String defaultFormatPattern = "d-M-y H:i:s";
	
	public TimeDbTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, TimeDbFormatter formatter, String formatPattern) {	
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		this.setFormatPattern(formatPattern);
		addConditions();
	}
	
	public TimeDbTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, TimeDbFormatter formatter) {	
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		this.setFormatPattern(defaultFormatPattern);
	}
	
	public TimeDbTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, config, new TimeDbValidator(), null);
	}
	
	public TimeDbTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, null);
	}
	
	public TimeDbTableColumnConfig(String name, int width) {		
		this(name, width, true, new TimeDbFormatter());		
	}
	
	public TimeDbTableColumnConfig(String name, int width, boolean readOnly) {		
		this(name, width, readOnly, new TimeDbFormatter());
	}
	
	public TimeDbTableColumnConfig(String name, int width, boolean readOnly, String formatPattern) {		
		this(name, width, readOnly, new TimeDbFormatter(), formatPattern);
	}
	 
    public TimeDbTableColumnConfig(String name, int width, TimeDbFormatter formatter) {		
		this(name, width, true, formatter);
	}
    
    public TimeDbTableColumnConfig(String name, int width, boolean readOnly, TimeDbFormatter formatter) {		
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING,null, new TimeDbValidator(), formatter);
	}

    public TimeDbTableColumnConfig(String name, int width, boolean readOnly, TimeDbFormatter formatter, String formatPattern) {		
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING,null, new TimeDbValidator(), formatter, formatPattern);
	}
    
	public Long scan(String value) {
		return TimeDbScanner.scan(value); 
	}
	
	public int compare(Object obj1, Object obj2) {
		return ((Long)obj1).compareTo((Long)obj2);
	}
	
	protected void addConditions() {
		TimeDbFormatter formatter = new TimeDbFormatter();
		this.addCondition(new TimeDbInConditionConfig(formatter));
		this.addCondition(new TimeDbGreaterThanConditionConfig(formatter));
		this.addCondition(new TimeDbGreaterThanOrEqualsConditionConfig(formatter));
		this.addCondition(new TimeDbLessThanConditionConfig(formatter));
		this.addCondition(new TimeDbLessThanOrEqualsConditionConfig(formatter));
	}

}
