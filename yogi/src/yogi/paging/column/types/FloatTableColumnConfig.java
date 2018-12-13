package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.paging.FloatAdjuster;
import yogi.paging.FloatAdjusterAssistant;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.paging.column.formatter.FloatFormatter;
import yogi.report.condition.config.FloatGreaterThanConditionConfig;
import yogi.report.condition.config.FloatGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.FloatInConditionConfig;
import yogi.report.condition.config.FloatLessThanConditionConfig;
import yogi.report.condition.config.FloatLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.FloatNotInConditionConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.FloatValidator;

public class FloatTableColumnConfig extends TableColumnConfig<Float> {	

	private static final long serialVersionUID = -5634310919990247153L;
	
	public FloatTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<Float> formatter) {
		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked,
				columnAlignment, columnFilterType, config, validator, formatter);
		this.addCondition(new FloatGreaterThanConditionConfig());
		this.addCondition(new FloatGreaterThanOrEqualsConditionConfig());
		this.addCondition(new FloatInConditionConfig());
		this.addCondition(new FloatLessThanConditionConfig());
		this.addCondition(new FloatLessThanOrEqualsConditionConfig());
		this.addCondition(new FloatNotInConditionConfig());					
	}
	
	public FloatTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, config, new FloatValidator(), new FloatFormatter(1, 2));		
	}
	
	public FloatTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, null);
	}
	
	public FloatTableColumnConfig(String name, int width) {
		this(name, width, true);
	}

	public FloatTableColumnConfig(String name, int width, boolean readOnly) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING);
	}
		
	public Float scan(String value) {
		return Float.valueOf(value);
	}

	public int compare(Object obj1, Object obj2) {
		return ((Float)obj1).compareTo((Float)obj2);
	}
	
	public String getModifiedColumnValue(Object obj, String value) {
		if(obj == null) return value;
		if(value.indexOf('+') >= 0 || value.indexOf('-') >= 0 || value.indexOf('P') >= 0 || value.indexOf('p') >= 0 || value.indexOf('%') >= 0 || value.indexOf('&') >= 0) {
			FloatAdjuster floatAdjuster = FloatAdjusterAssistant.get().getFloatAdjuster(value);
			Float modifiedValue = FloatAdjusterAssistant.get().applyFactor((Float)obj, floatAdjuster);
			return format(modifiedValue);
		} else {
			return value;
		}
	}
}
