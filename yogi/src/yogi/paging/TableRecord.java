package yogi.paging;

import java.io.Serializable;

public class TableRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7258514224571959762L;

	private String[] row;
	
	public TableRecord(String[] row) {
		super();
		this.row = row;
	}

	public String[] getRow() {
		return this.row;
	}
}
