package yogi.paging;

import java.io.Serializable;

public class FilterConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6219825728586636592L;
	
	private int columnIndex;
	private String value;
	private String conditionName;
	
	public FilterConfig(int columnIndex, String conditionName, String value) {
		super();
		this.columnIndex = columnIndex;
		this.conditionName = conditionName;
		this.value = value;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public String getConditionName() {
		return conditionName;
	}
	
	public String getValue() {
		return value;
	}
}
