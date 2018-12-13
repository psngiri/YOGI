package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.report.condition.config.ByteInConditionConfig;
import yogi.report.condition.config.ByteNotInConditionConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.IntegerValidator;

public class ByteTableColumnConfig extends TableColumnConfig<Byte> {

	private static final long serialVersionUID = -7325506333234943171L;

	public ByteTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<Byte> formatter) {
		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked,
				columnAlignment, columnFilterType, config, validator, formatter);
		addConditions();
	}
	
	public ByteTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked,
				columnAlignment, columnFilterType, config, new IntegerValidator(), null);
	}
	
	public ByteTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, null);
	}
	
	public Byte scan(String value) {
		return Byte.valueOf(value);
	}

	public int compare(Object obj1, Object obj2) {
		return ((Byte)obj1).compareTo((Byte)obj2);
	}

	protected void addConditions() {
		this.addCondition(new ByteInConditionConfig());
		this.addCondition(new ByteNotInConditionConfig());			
	}
}
