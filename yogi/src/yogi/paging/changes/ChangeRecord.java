package yogi.paging.changes;

import java.io.Serializable;

public class ChangeRecord implements Serializable {

	private static final long serialVersionUID = -3425228262240716239L;
	private String[] oldRow;
	private String[] newRow;
	private int rowViewIndex;
	private int rowDataIndex;
	
	public ChangeRecord(String[] oldRow, String[] newRow) {
		super();
		this.oldRow = oldRow;
		this.newRow = newRow;
		this.rowViewIndex = -1;
		this.rowDataIndex = -1;
	}
	
	public ChangeRecord(String[] oldRow, String[] newRow, int rowViewIndex, int rowDataIndex) {
		super();
		this.oldRow = oldRow;
		this.newRow = newRow;
		this.rowViewIndex = rowViewIndex;
		this.rowDataIndex = rowDataIndex;
	}
	
	public String getValue(int index){
		String value = getNewValue(index);
		if(value.isEmpty()){
			value = getOldValue(index);
		}
		return value;
	}
	
	public String getAddedValue(int index){
		String value = getOldValue(index);
		if(this.isAdded()){
			value = getNewValue(index);
		} 
		return value;
	}
	
	public boolean isModified(int index)
	{
		return getOldValue(index)!=getNewValue(index);
	}
	
	public String getOldValue(int index){
		if(oldRow == null) return "";
		return oldRow[index];
	}

	public String getNewValue(int index){
		if(newRow == null) return "";
		return newRow[index];
	}

	public boolean isDeleted() {
		if(newRow == null) return true;
		return false;
	}
	
	public boolean isAdded() {
		if(oldRow == null) return true;
		return false;
	}

	public String[] getOldRow() {
		return oldRow;
	}

	public String[] getNewRow() {
		return newRow;
	}
	
	public int getRowViewIndex() {
		return rowViewIndex;
	}

	public int getRowDataIndex() {
		return rowDataIndex;
	}

}
