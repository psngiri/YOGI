package yogi.remote.client.app;

import yogi.base.app.Command;

public abstract class CommandAdapter<R> implements Command<R> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7104114408230210607L;
	String id;
	private String userId;
	
	public CommandAdapter(String userId) {
		super();
		this.userId = userId;
	}

	public String getID() {
		return id;
	}
	
	protected void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}

}
