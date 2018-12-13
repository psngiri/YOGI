package yogi.paging;

import java.io.Serializable;

public class EditConfig implements Serializable {	
	private static final long serialVersionUID = -8512747192053274906L;
	
	private int columnIndex;
	private String value;
	
	public EditConfig(int columnIndex,  String value) {
		super();
		this.columnIndex = columnIndex;
		this.value = value;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public String getValue() {
		return value;
	}
}
