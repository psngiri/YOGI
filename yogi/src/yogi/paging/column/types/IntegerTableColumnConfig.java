package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.report.condition.config.IntegerGreaterThanConditionConfig;
import yogi.report.condition.config.IntegerGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.IntegerInConditionConfig;
import yogi.report.condition.config.IntegerLessThanConditionConfig;
import yogi.report.condition.config.IntegerLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.IntegerNotInConditionConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.IntegerValidator;

public class IntegerTableColumnConfig extends TableColumnConfig<Integer> {

	private static final long serialVersionUID = 1095970627641931556L;
	
	public IntegerTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<Integer> formatter) {
		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked,
				columnAlignment, columnFilterType, config, validator, formatter);
		addConditions();
	}
	
	public IntegerTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked,
				columnAlignment, columnFilterType, config, new IntegerValidator(), null);
	}
	
	public IntegerTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, null);
	}
	
	public IntegerTableColumnConfig(String name, int width, boolean readOnly, Formatter<Integer> formatter) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING, null, new IntegerValidator(), formatter);
	}
	
	public IntegerTableColumnConfig(String name, int width, boolean readOnly, Validator validator, Formatter<Integer> formatter) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING, null, validator, formatter);
	}
	
	public IntegerTableColumnConfig(String name, int width, Formatter<Integer> formatter) {
		this(name, width, true, formatter);
	}

	public IntegerTableColumnConfig(String name, int width, boolean readOnly) {
		this(name, width, readOnly, null);		
	}

	public IntegerTableColumnConfig(String name, int width) {
		this(name, width, true);
	}
	
	public Integer scan(String value) {
		return Integer.valueOf(value);
	}

	public int compare(Object obj1, Object obj2) {
		if(obj1 == null && obj2 == null) return 0;
		if(obj1 == null) return -1;
		if(obj2 == null) return 1;
		return ((Integer)obj1).compareTo((Integer)obj2);
	}

	protected void addConditions() {
		this.addCondition(new IntegerGreaterThanConditionConfig());
		this.addCondition(new IntegerGreaterThanOrEqualsConditionConfig());
		this.addCondition(new IntegerInConditionConfig());
		this.addCondition(new IntegerLessThanConditionConfig());
		this.addCondition(new IntegerLessThanOrEqualsConditionConfig());
		this.addCondition(new IntegerNotInConditionConfig());				
	}
}
