package yogi.paging.column;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import yogi.base.io.Formatter;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.Validator;

public abstract class TableColumnConfig<C> implements Serializable, Cloneable  {	
	
	private static final long serialVersionUID = -4637243837655741371L;
	
	private String name;
	private String fieldType;
	private int width;
	private boolean readOnly;
	private boolean sortable;
	private boolean filterable;
	private boolean locked;
	private String alignment;
	private String filterType;
	private Validator validator;
	private TypeConfig config;
	private boolean hidden;	
	private String formatPattern;
	private Formatter<? super C> formatter;
	private transient List<ConditionConfig<? super C>> conditions;	
	private Map<String, ConditionConfig<? super C>> conditionsMap;

	public abstract C scan(String value);
	public abstract int compare(Object obj1, Object obj2);
	
	public TableColumnConfig(String name, ColumnFieldType columnFieldType, int width, 
			boolean readOnly, boolean sortable, boolean filterable, boolean locked, ColumnAlignment columnAlignment, 
			ColumnFilterType columnFilterType, TypeConfig config, Validator validator, Formatter<C> formatter) {			
		super();
		this.name = name;
		this.fieldType = columnFieldType.getDescription();
		this.width = width;
		this.readOnly = readOnly;
		this.sortable = sortable;
		this.filterable = filterable;
		this.locked = locked;
		this.alignment = columnAlignment.getDescription();
		this.filterType = columnFilterType.getDescription();
		this.config = config;		
		this.validator = validator;
		this.formatter = formatter;
		this.conditionsMap = new LinkedHashMap<String, ConditionConfig<? super C>>();		
	}	
	
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public boolean isHidden() {
		return hidden;
	}
	
	public String getFormatPattern() {
		return formatPattern;
	}
	
	public void setFormatPattern(String formatPattern) {
		this.formatPattern = formatPattern;
	}
	
	public void addCondition(ConditionConfig<? super C> condition) {
		conditionsMap.put(condition.getName(), condition);
	}
	
	public List<ConditionConfig<? super C>> getConditions() {
		if(conditions == null) {
			conditions = new ArrayList<ConditionConfig<? super C>>();
			for(Map.Entry<String, ConditionConfig<? super C>> entry: conditionsMap.entrySet()) {
				conditions.add(entry.getValue());
			}
		}
		return conditions;
	}

	public ConditionConfig<? super C> getCondition(String conditionName) {
		conditionName = conditionName.trim();
		if(conditionName.trim().isEmpty()) {
			return getConditionsMap().values().iterator().next();
		}
		return getConditionsMap().get(conditionName);
	}

	private Map<String, ConditionConfig<? super C>> getConditionsMap() {
		return conditionsMap;
	}

	public String getName() {
		return name;
	}
	
	public String getFieldType() {
		return fieldType;
	}

	public int getWidth() {
		return width;
	}
	
	public boolean isReadOnly() {
		return readOnly;
	}

	public boolean isSortable() {
		return sortable;
	}

	public boolean isFilterable() {
		return filterable;
	}

	public String getAlignment() {
		return alignment;
	}

	public String getFilterType() {
		return filterType;
	}

	public Validator getValidator() {
		return validator;
	}

	public TypeConfig getConfig() {
		return config;
	}

	public boolean isLocked() {
		return locked;
	}

	public Formatter<? super C> getFormatter() {
		return formatter;
	}	
	
	@SuppressWarnings("unchecked")
	public String format(Object obj) {
		if(obj == null) return "";
		if(formatter == null) return obj.toString();
		String format = formatter.format((C) obj);
		if(format == null) return "";
		return format;
	}

	public String getModifiedColumnValue(Object obj, String newValue) {
		return newValue;
	}
	
	public void setName(String string) {
		this.name = string;
	}
	
	 public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }
	
}
