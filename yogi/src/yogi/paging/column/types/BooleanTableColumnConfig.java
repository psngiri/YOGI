package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.report.condition.config.BooleanEqualsConditionConfig;
import yogi.report.condition.config.BooleanNotEqualsConditionConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.BooleanValidator;

public class BooleanTableColumnConfig extends TableColumnConfig<Boolean> {
	
	private static final long serialVersionUID = 171753039531060175L;
	
	public BooleanTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<Boolean> formatter) {
		
		super(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, config, validator, formatter);
		this.addCondition(new BooleanEqualsConditionConfig());
		this.addCondition(new BooleanNotEqualsConditionConfig());
	}
	
	public BooleanTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, config, new BooleanValidator(), null);
	}
	
	public BooleanTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, null);
	}
	
	public BooleanTableColumnConfig(String name, int width) {
		this(name, width, true);
	}
	
	public BooleanTableColumnConfig(String name, int width, boolean readOnly) {
		this(name, ColumnFieldType.CHECKBOXFIELD, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING);
	}	
	
	public Boolean scan(String value) {
		return Boolean.valueOf(value);
	}
	
	public int compare(Object obj1, Object obj2) {
		return ((Boolean)obj1).compareTo((Boolean)obj2);
	}
}
