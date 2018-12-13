package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.report.condition.config.LongGreaterThanConditionConfig;
import yogi.report.condition.config.LongGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.LongInConditionConfig;
import yogi.report.condition.config.LongLessThanConditionConfig;
import yogi.report.condition.config.LongLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.LongNotInConditionConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.LongValidator;

public class LongTableColumnConfig extends TableColumnConfig<Long> {

	private static final long serialVersionUID = 1095970627641931556L;
	
	public LongTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<Long> formatter) {
		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked,
				columnAlignment, columnFilterType, config, validator, formatter);
		addConditions();
	}
	
	public LongTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked,
				columnAlignment, columnFilterType, config, new LongValidator(), null);
	}
	
	public LongTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, null);
	}
	
	public LongTableColumnConfig(String name, int width, boolean readOnly, Formatter<Long> formatter) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING, null, new LongValidator(), formatter);
	}
	
	public LongTableColumnConfig(String name, int width, boolean readOnly, Validator validator, Formatter<Long> formatter) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING, null, validator, formatter);
	}
	
	public LongTableColumnConfig(String name, int width, Formatter<Long> formatter) {
		this(name, width, true, formatter);
	}

	public LongTableColumnConfig(String name, int width, boolean readOnly) {
		this(name, width, readOnly, null);		
	}

	public LongTableColumnConfig(String name, int width) {
		this(name, width, true);
	}
	
	public Long scan(String value) {
		if(value.trim().isEmpty()) return null;
		return Long.valueOf(value);	
	}

	public int compare(Object obj1, Object obj2) {
		if(obj1 == null && obj2 == null) return 0;
		if(obj1 == null) return -1;
		if(obj2 == null) return 1;
		return ((Long)obj1).compareTo((Long)obj2);
	}

	protected void addConditions() {
		this.addCondition(new LongGreaterThanConditionConfig());
		this.addCondition(new LongGreaterThanOrEqualsConditionConfig());
		this.addCondition(new LongInConditionConfig());
		this.addCondition(new LongLessThanConditionConfig());
		this.addCondition(new LongLessThanOrEqualsConditionConfig());
		this.addCondition(new LongNotInConditionConfig());				
	}
}
