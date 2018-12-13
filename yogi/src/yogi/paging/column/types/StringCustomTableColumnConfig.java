package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.StringLikeConditionConfig;
import yogi.report.server.config.Validator;

public class StringCustomTableColumnConfig extends TableColumnConfig<String>{

	private static final long serialVersionUID = 7017778468116798752L;
	
	public StringCustomTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<String> formatter) {		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		addConditions();
	}
	
	public StringCustomTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config, Validator validator) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, null);
	}
	
	public StringCustomTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, Validator validator) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, null, validator);
	}

	public StringCustomTableColumnConfig(String name, int width, boolean readOnly, ColumnFieldType columnFieldType, ColumnAlignment columnAlignment, Validator validator) {
		this(name, columnFieldType, width, readOnly, true, true, false, columnAlignment, ColumnFilterType.STRING, validator);
	}

	public StringCustomTableColumnConfig(String name, int width, ColumnAlignment columnAlignment, Validator validator) {
		this(name, ColumnFieldType.TEXTFIELD, width, true, true, true, false, columnAlignment, ColumnFilterType.STRING, null, validator, null);
	}
	
	public StringCustomTableColumnConfig(String name, int width, boolean readOnly, Validator validator, ColumnFieldType columnFieldType) {
		this(name, columnFieldType, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, null, validator, null);
	}
	
	public StringCustomTableColumnConfig(String name, int width, boolean sortable, boolean filterable, Validator validator) {
		this(name, ColumnFieldType.TEXTFIELD, width, true, sortable, filterable, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, validator);
	}

	public StringCustomTableColumnConfig(String name, int width, boolean readOnly, ColumnFieldType columnFieldType, Validator validator) {
		this(name, columnFieldType, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, validator);
	}
	
	public StringCustomTableColumnConfig(String name, int width, boolean readOnly, Validator validator) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, null, validator, null);
	}
	
	public StringCustomTableColumnConfig(String name, int width, Validator validator) {
		this(name, width, true, validator);
	}

	public String scan(String value) {
		return value;
	}

	public int compare(Object obj1, Object obj2) {
		return ((String)obj1).compareTo((String)obj2);
	}
	
	protected void addConditions() {		
		this.addCondition(new StringLikeConditionConfig());		
        this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
	}
}