package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.base.util.PrecisionFloat;
import yogi.paging.FloatAdjuster;
import yogi.paging.FloatAdjusterAssistant;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.report.condition.config.PrecisionFloatGreaterThanConditionConfig;
import yogi.report.condition.config.PrecisionFloatGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.PrecisionFloatInConditionConfig;
import yogi.report.condition.config.PrecisionFloatLessThanConditionConfig;
import yogi.report.condition.config.PrecisionFloatLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.PrecisionFloatNotInConditionConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.FloatValidator;

public class PrecisionFloatTableColumnConfig extends TableColumnConfig<PrecisionFloat> {	

	private static final long serialVersionUID = -5634310919990247153L;
	protected boolean isRoundingNeeded;
	
	public PrecisionFloatTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<PrecisionFloat> formatter) {
		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked,
				columnAlignment, columnFilterType, config, validator, formatter);
		this.addCondition(new PrecisionFloatGreaterThanConditionConfig());
		this.addCondition(new PrecisionFloatGreaterThanOrEqualsConditionConfig());
		this.addCondition(new PrecisionFloatInConditionConfig());
		this.addCondition(new PrecisionFloatLessThanConditionConfig());
		this.addCondition(new PrecisionFloatLessThanOrEqualsConditionConfig());
		this.addCondition(new PrecisionFloatNotInConditionConfig());					
	}
	
	public PrecisionFloatTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {	
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, config, new FloatValidator(), null);		
	}
	
	public PrecisionFloatTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable,
				locked, columnAlignment, columnFilterType, null);
	}
		
	public PrecisionFloatTableColumnConfig(String name, int width) {
		this(name, width, true);
	}

	public PrecisionFloatTableColumnConfig(String name, int width, boolean readOnly) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.RIGHT, ColumnFilterType.STRING);
	}
	
	public boolean isRoundingNeeded() {
		return isRoundingNeeded;
	}

	public void setRoundingNeeded(boolean isRoundingNeeded) {
		this.isRoundingNeeded = isRoundingNeeded;
	}

	public PrecisionFloat scan(String value) {
		int precision = 0;
		if(value.indexOf(".") != -1)  {
			precision = value.length() - value.indexOf(".") - 1;
		}
		return new PrecisionFloat(Float.valueOf(value), precision);
	}

	public int compare(Object obj1, Object obj2) {	
		Float value1 = ((PrecisionFloat)obj1).getValue();
		Float value2 = ((PrecisionFloat)obj2).getValue();
		return value1.compareTo(value2);		
	}
	
	public String getModifiedColumnValue(Object obj, String value) {
		if(obj == null) return value;
		if(value.indexOf('+') >= 0 || value.indexOf('-') >= 0 || value.indexOf('P') >= 0 || value.indexOf('p') >= 0 || value.indexOf('%') >= 0 || value.indexOf('&') >= 0) {
			FloatAdjuster floatAdjuster = FloatAdjusterAssistant.get().getFloatAdjuster(value);
			PrecisionFloat currentObj = (PrecisionFloat) obj;			
			float floatValue = FloatAdjusterAssistant.get().applyFactor(currentObj.getValue(), floatAdjuster);			
			if(isRoundingNeeded) floatValue = Math.round(floatValue);
			else if(currentObj.getValue() % 1 == 0) floatValue = (float) Math.round(floatValue);
			PrecisionFloat modifiedValue = new PrecisionFloat(floatValue, currentObj.getDecimal());
			return format(modifiedValue);
		} else {
			return value;
		}
	}	
}
