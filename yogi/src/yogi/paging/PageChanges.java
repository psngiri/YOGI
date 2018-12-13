package yogi.paging;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PageChanges implements Serializable {

	private static final long serialVersionUID = -7551095098251958209L;
	private Set<Integer> addedRows;
	private Map<Integer, Set<Integer>> modifiedCells;
	private boolean savePending;
	
	public PageChanges() {
		this.addedRows = new HashSet<Integer>();
		this.modifiedCells = new HashMap<Integer, Set<Integer>>();
		this.savePending = false;
	}

	public void setAddedRow(int index){
		addedRows.add(index);
	}

	public void setModifiedCell(int rowIndex, int columnIndex){
		Set<Integer> columns = modifiedCells.get(rowIndex);
		if(columns == null) {
			columns = new HashSet<Integer>();
			modifiedCells.put(rowIndex, columns);
		}
		columns.add(columnIndex);
	}

	public boolean isAddedRow(int rowIndex){
		return addedRows.contains(rowIndex);
	}

	public boolean isModifiedCell(int rowIndex, int columnIndex){
		Set<Integer> columns = modifiedCells.get(rowIndex);
		if(columns != null) {
			return modifiedCells.get(rowIndex).contains(columnIndex);
		}
		return false;
	}

	@SuppressWarnings("unused")
	public void removeAddedRow(int index){
		int i = 0;
		for (Iterator<Integer> iter = addedRows.iterator() ; iter.hasNext(); i++) {
			Integer rowNumber = iter.next();
			if (i == index) {
				iter.remove();
				break;
			} 
		}
	}

	public boolean getSavePending() {
		return savePending;
	}
	
	public void setSavePending(boolean savePending) {
		this.savePending = savePending;
	}

}
