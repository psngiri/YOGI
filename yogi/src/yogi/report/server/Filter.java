package yogi.report.server;

import java.io.Serializable;

public class Filter implements Serializable{
	private static final long serialVersionUID = 1L;
	private String columnName;
	private String condition;
	private String value;
	private String alias;
	
	public String getColumnName() {
		return columnName;
	}
	public Filter(String columnName, String condition, String value, String alias) {
		super();
		this.columnName = columnName;
		this.condition = condition;
		this.value = value;
		this.alias = alias;
	}
	
	public String getCondition() {
		return condition;
	}
	public String getValue() {
		return value;
	}
	public String getAlias() {
		return alias;
	}
	public boolean isValid(){
		if(columnName==null || columnName.equals("") ||
				condition==null|| condition.equals("") ||
				value==null || value.equals("") ) return false;
		return true;
	}
	@Override
	public String toString() {
		return "Filter [columnName=" + columnName + ", condition=" + condition
				+ ", value=" + value + ", alias=" + alias + "]";
	}
	
}
