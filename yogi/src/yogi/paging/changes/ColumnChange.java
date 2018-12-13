package yogi.paging.changes;

import java.io.Serializable;

public class ColumnChange implements Serializable {

	private static final long serialVersionUID = 852644584387961164L;

	private int colIndex;
	private String oldValue;
	private String newValue;
	
	public ColumnChange(int colIndex, String oldValue, String newValue) {
		super();
		this.colIndex = colIndex;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public int getColIndex() {
		return colIndex;
	}

	public String getOldValue() {
		return oldValue;
	}

	public String getNewValue() {
		return newValue;
	}
	
}
