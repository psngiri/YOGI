package yogi.paging.changes;

import java.io.Serializable;
import java.util.List;

import yogi.paging.ChangeType;

public class Change implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1376128674690882177L;
	
	private ChangeType type;
	private int rowIndex;
	private int columnIndex;
	private String oldValue;
	private String newValue;
	private String[] row;
	private int rowDataIndex;
	private int columnHeaderIndex;
	private boolean groupUndoAction;
	private boolean groupRedoAction;
	private Integer pointerIndex;
	private Integer oldPointerIndex;
	private List<Integer> oldRowViewList;
	private List<Integer> newRowViewList;
	
	public Change(ChangeType type, List<Integer> oldRowViewList, List<Integer> newRowViewList) {
		super();
		this.type = type;
		this.oldRowViewList = oldRowViewList;
		this.newRowViewList = newRowViewList;
		this.rowIndex = 0;
		this.rowDataIndex = -1;
	}

	public Change(ChangeType type, int rowIndex, int columnIndex, String oldValue, String newValue, String[] row, boolean groupUndoAction, boolean groupRedoAction) {
		super();
		this.type = type;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.row = row;
		this.groupUndoAction = groupUndoAction;
		this.groupRedoAction = groupRedoAction;
	}

	public ChangeType getChangeType() {
		return type;
	}

	public void setChangeType(ChangeType type) {
		this.type = type;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String[] getRow() {
		return row;
	}
	
	public void setRow(String[] row) {
		this.row = row;
	}
	
	public int getRowDataIndex() {
		return rowDataIndex;
	}

	public void setRowDataIndex(int rowDataIndex) {
		this.rowDataIndex = rowDataIndex;
	}

	public int getColumnHeaderIndex() {
		return columnHeaderIndex;
	}

	public void setColumnHeaderIndex(int columnHeaderIndex) {
		this.columnHeaderIndex = columnHeaderIndex;
	}

	public boolean isGroupUndoAction() {
		return groupUndoAction;
	}

	public boolean isGroupRedoAction() {
		return groupRedoAction;
	}
	
	public void setGroupUndoAction(boolean groupUndoAction) {
		this.groupUndoAction = groupUndoAction;
	}

	public void setGroupRedoAction(boolean groupRedoAction) {
		this.groupRedoAction = groupRedoAction;
	}

	public Integer getPointerIndex() {
		return this.pointerIndex;
	}
	
	public void setPointerIndex(Integer pointerIndex) {
		this.pointerIndex = pointerIndex;
	}

	public Integer getOldPointerIndex() {
		return this.oldPointerIndex;
	}

	public void setOldPointerIndex(Integer oldPointerIndex) {
		this.oldPointerIndex = oldPointerIndex;
	}

	public List<Integer> getOldRowViewList() {
		return oldRowViewList;
	}
	
	public List<Integer> getNewRowViewList() {
		return newRowViewList;
	}
	
}