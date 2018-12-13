package yogi.paging;

import java.io.Serializable;

public class TableResponse implements Serializable {

    	private static final long serialVersionUID = -405119026954691526L;
    
	private Integer tableDataSetId;
	private int tableSize;
		
	public TableResponse(int tableSize, Integer tableDataSetId) {
		this.tableDataSetId = tableDataSetId;
		this.tableSize = tableSize;
	}
	
	public int getTableSize() {
		return tableSize;
	}
	
	public Integer getTableDataSetId() {
		return tableDataSetId;
	}
}
