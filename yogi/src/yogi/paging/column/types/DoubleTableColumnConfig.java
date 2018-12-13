package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.paging.column.formatter.DoubleFormatter;
import yogi.report.condition.config.DoubleGreaterThanConditionConfig;
import yogi.report.condition.config.DoubleGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.DoubleInConditionConfig;
import yogi.report.condition.config.DoubleLessThanConditionConfig;
import yogi.report.condition.config.DoubleLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.DoubleNotInConditionConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.DoubleValidator;

public class DoubleTableColumnConfig extends TableColumnConfig<Double> {	

	private static final long serialVersionUID = 2261291939676080348L;

	public DoubleTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType,
			TypeConfig config, Validator validator, Formatter<Double> formatter) {		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, columnAlignment, columnFilterType, config, validator, formatter);
		this.addCondition(new DoubleGreaterThanConditionConfig());
		this.addCondition(new DoubleGreaterThanOrEqualsConditionConfig());
		this.addCondition(new DoubleInConditionConfig());		
		this.addCondition(new DoubleLessThanConditionConfig());
		this.addCondition(new DoubleLessThanOrEqualsConditionConfig());
		this.addCondition(new DoubleNotInConditionConfig());		
	}

	public DoubleTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, columnAlignment, columnFilterType, config, new DoubleValidator(), new DoubleFormatter(1, 2));
	}
	
	public DoubleTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, columnAlignment, columnFilterType, null);
	}
		
	public DoubleTableColumnConfig(String name, int width, Formatter<Double> formatter, boolean readOnly) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING, null, new DoubleValidator(), formatter);
	}
		
	public DoubleTableColumnConfig(String name, int width, boolean readOnly) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING);		
	}
	
	public DoubleTableColumnConfig(String name, int width, Formatter<Double> formatter) {
		this(name, width, formatter, true);
	}
	
	public DoubleTableColumnConfig(String name, int width) {
		this(name, width, true);
	}
	
	public Double scan(String value) {
		return Double.valueOf(value);
	}

	public int compare(Object obj1, Object obj2) {
		return ((Double)obj1).compareTo((Double)obj2);
	}

}
