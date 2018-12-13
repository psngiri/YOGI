package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.StringNumberInConditionConfig;
import yogi.report.condition.config.StringNumberLikeConditionConfig;
import yogi.report.condition.config.StringNumberNotInConditionConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.StringNumberValidator;

public class StringNumberTableColumnConfig extends TableColumnConfig<String>{
	
	private static final long serialVersionUID = 7017778468116798752L;
	
	public StringNumberTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<String> formatter) {
		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		this.addCondition(new StringNumberInConditionConfig());
		this.addCondition(new StringNumberLikeConditionConfig());
		this.addCondition(new StringNumberNotInConditionConfig());
		this.addCondition(new IsNullConditionConfig());
		this.addCondition(new IsNotNullConditionConfig());
	}
	
	public StringNumberTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, new StringNumberValidator(), null);
	}
	
	public StringNumberTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, null);
	}
	
	public StringNumberTableColumnConfig(String name, int width, boolean readOnly, Validator validator, ColumnFieldType columnFieldType) {
		this(name, columnFieldType, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, null, validator, null);
	}
	
	public StringNumberTableColumnConfig(String name, int width, boolean readOnly, ColumnAlignment columnAlignment) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, columnAlignment, ColumnFilterType.STRING);
	}

	public StringNumberTableColumnConfig(String name, int width, boolean readOnly, ColumnFieldType columnFieldType) {
		this(name, columnFieldType, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING);
	}
	
	public StringNumberTableColumnConfig(String name, int width, boolean readOnly, Validator validator) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, null, validator, null);
	}
	
	public StringNumberTableColumnConfig(String name, int width, ColumnAlignment columnAlignment) {
		this(name, width, true, columnAlignment);
	}

	public StringNumberTableColumnConfig(String name, int width, boolean readOnly) {
		this(name, width, readOnly, ColumnFieldType.TEXTFIELD);
	}

	public StringNumberTableColumnConfig(String name, int width) {
		this(name, width, true);
	}
	
	public StringNumberTableColumnConfig(String name, int width, boolean readOnly, ColumnFieldType columnFieldType, Formatter<String> formatter) {
		this(name, columnFieldType, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, null, new StringNumberValidator(), formatter);
	}

	public StringNumberTableColumnConfig(String name, int width, boolean readOnly, ColumnFieldType columnFieldType, ColumnAlignment columnAlignment) {
		this(name, columnFieldType, width, readOnly, true, true, false, columnAlignment, ColumnFilterType.STRING);
	}

	public StringNumberTableColumnConfig(String name, int width, boolean readOnly, ColumnFieldType columnFieldType, ColumnAlignment columnAlignment, Validator validator) {
		this(name, columnFieldType, width, readOnly, true, true, false, columnAlignment, ColumnFilterType.STRING, null, validator, null);
	}

	public StringNumberTableColumnConfig(String name, int width, boolean readOnly, ColumnFieldType columnFieldType, Validator validator) {
		this(name, columnFieldType, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, null, validator, null);
	}

	public String scan(String value) {
		return value;
	}

	public int compare(Object obj1, Object obj2) {
		String str1 = (String)obj1;
		String str2 = (String)obj2;
		int rtnValue = str1.length()-str2.length();
		if(rtnValue != 0) return rtnValue;
		int length = Math.min(str1.length(), str2.length());
		for(int i =0; i < length; i ++){
			char c1 = str1.charAt(i);
			char c2 = str2.charAt(i);
			int d1, d2;
			if(Character.isDigit(c1)) d1 = c1 + 128; else d1 = c1;
			if(Character.isDigit(c2)) d2 = c2 + 128; else d2 = c2;			
			rtnValue = d1-d2;	
			if(rtnValue != 0) return rtnValue;			
		}
		return 0;
		}

}