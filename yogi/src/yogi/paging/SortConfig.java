package yogi.paging;

import java.io.Serializable;

public class SortConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5083151561179055565L;
	
	private int columnIndex;
	private boolean ascendingOrder;
	
	public SortConfig(int columnIndex, boolean ascendingOrder) {
		super();
		this.columnIndex = columnIndex;
		this.ascendingOrder = ascendingOrder;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public boolean getAscendingOrder() {
		return ascendingOrder;
	}
}
