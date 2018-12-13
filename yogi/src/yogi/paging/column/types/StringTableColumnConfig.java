package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.StringInConditionConfig;
import yogi.report.condition.config.StringLikeConditionConfig;
import yogi.report.condition.config.StringNotInConditionConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.StringValidator;

public class StringTableColumnConfig extends TableColumnConfig<String>{

	private static final long serialVersionUID = 7017778468116798752L;
	
	public StringTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<String> formatter) {
		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		addConditions();
	}
	
	public StringTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, new StringValidator(), null);
	}
	
	public StringTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, null);
	}

	public StringTableColumnConfig(String name, int width, boolean readOnly, ColumnFieldType columnFieldType, ColumnAlignment columnAlignment) {
		this(name, columnFieldType, width, readOnly, true, true, false, columnAlignment, ColumnFilterType.STRING);
	}

	public StringTableColumnConfig(String name, int width, ColumnAlignment columnAlignment, Validator validator) {
		this(name, ColumnFieldType.TEXTFIELD, width, true, true, true, false, columnAlignment, ColumnFilterType.STRING, null, validator, null);
	}
	
	public StringTableColumnConfig(String name, int width, boolean readOnly, Validator validator, ColumnFieldType columnFieldType) {
		this(name, columnFieldType, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, null, validator, null);
	}
	
	public StringTableColumnConfig(String name, int width, boolean sortable, boolean filterable) {
		this(name, ColumnFieldType.TEXTFIELD, width, true, sortable, filterable, false, ColumnAlignment.CENTER, ColumnFilterType.STRING);
	}

	public StringTableColumnConfig(String name, int width, boolean readOnly, ColumnFieldType columnFieldType) {
		this(name, columnFieldType, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING);
	}
	
	public StringTableColumnConfig(String name, int width, boolean readOnly, Validator validator) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, null, validator, null);
	}
	
	public StringTableColumnConfig(String name, int width, boolean readOnly) {
		this(name, width, readOnly, ColumnFieldType.TEXTFIELD);
	}
	
	public StringTableColumnConfig(String name, int width) {
		this(name, width, true);
	}

	public String scan(String value) {
		return value;
	}

	public int compare(Object obj1, Object obj2) {
		return ((String)obj1).compareTo((String)obj2);
	}
	
	protected void addConditions() {
		this.addCondition(new StringInConditionConfig());
		this.addCondition(new StringLikeConditionConfig());
		this.addCondition(new StringNotInConditionConfig());
        this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
	}

}