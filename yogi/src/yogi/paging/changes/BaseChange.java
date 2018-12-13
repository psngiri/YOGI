package yogi.paging.changes;

import java.io.Serializable;

import yogi.paging.ChangeType;

public class BaseChange implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1376128674690882177L;
	
	private ChangeType type;

	public BaseChange(ChangeType type) {
		super();
		this.type = type;
	}

	public ChangeType getType() {
		return type;
	}
	
}