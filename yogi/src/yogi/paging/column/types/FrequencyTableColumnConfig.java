package yogi.paging.column.types;

import yogi.base.io.Formatter;
import yogi.base.io.Scanner;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.period.frequency.Frequency;
import yogi.report.condition.config.FrequencyContainsConditionConfig;
import yogi.report.condition.config.FrequencyEqualsConditionConfig;
import yogi.report.condition.config.FrequencyNotContainsConditionConfig;
import yogi.report.condition.config.FrequencyNotEqualsConditionConfig;
import yogi.report.condition.frequency.FrequencyFormatter;
import yogi.report.condition.frequency.FrequencyScanner;
import yogi.report.server.config.Validator;
import yogi.report.server.config.validator.FrequencyValidator;

public class FrequencyTableColumnConfig extends TableColumnConfig<Frequency>{

	private static final long serialVersionUID = 7017778468116798752L;
	
	private Scanner<Frequency, String> scanner = new FrequencyScanner();
	
	public FrequencyTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType, 
			TypeConfig config, Validator validator, Formatter<Frequency> formatter) {
		
		super(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, validator, formatter);
		addConditions();		
	}
	
	public FrequencyTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment,
			ColumnFilterType columnFilterType, TypeConfig config) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, config, new FrequencyValidator(), new FrequencyFormatter());
	}
	
	public FrequencyTableColumnConfig(String name, ColumnFieldType columnFieldType,
			int width, boolean readOnly, boolean sortable, boolean filterable,
			boolean locked, ColumnAlignment columnAlignment, ColumnFilterType columnFilterType) {		
		this(name, columnFieldType, width, readOnly, sortable, filterable, locked, 
				columnAlignment, columnFilterType, null);
	}
	
	public FrequencyTableColumnConfig(String name, int width, Formatter<Frequency> formatter, boolean readOnly) {
		this(name, ColumnFieldType.TEXTFIELD, width, readOnly, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, null, new FrequencyValidator(), formatter);
	}
	
	public FrequencyTableColumnConfig(String name, int width, Formatter<Frequency> formatter, Scanner<Frequency,String> scanner) {
		this(name, width, formatter);
		this.scanner = scanner;		
	}
	
	public FrequencyTableColumnConfig(String name, int width, Formatter<Frequency> formatter) {
		this(name, width, formatter, false);
	}
	
	public FrequencyTableColumnConfig(String name, int width) {
		this(name, width, new FrequencyFormatter());
	}
	
	public Frequency scan(String value) {
		return scanner.scan(value).create();
	}

	public int compare(Object obj1, Object obj2) {
		return ((Frequency)obj1).compareTo((Frequency)obj2);
	}
	
	protected void addConditions() {
		this.addCondition(new FrequencyContainsConditionConfig());
		this.addCondition(new FrequencyEqualsConditionConfig());
		this.addCondition(new FrequencyNotContainsConditionConfig());
		this.addCondition(new FrequencyNotEqualsConditionConfig());
	}

}