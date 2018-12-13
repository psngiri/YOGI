package yogi.server.gui.record;

import yogi.server.action.Action;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyImpl;
import yogi.server.gui.user.User;



public abstract class RecordImpl<K extends Key, D extends RecordData,T extends Record<K, D>> extends KeyImpl<T> implements Record<K, D>{
	private transient K key;
	private transient long timeStamp;
	private String description;
	private String comments;
	private D data;
	private transient Action action;
	private transient User modifiedByUser;
	
	protected RecordImpl(K key, long timeStamp, String description, String comments, D data, Action action, User modifiedByUser) {
		super(key.getUser(), key.getIdName(), key.getCreateDate(),key.getPartition());
		this.key = key;
		this.timeStamp = timeStamp;
		this.description = description;
		this.comments = comments;
		this.data = data;
		this.action=action;
		this.modifiedByUser = modifiedByUser;
	}

	@Override
	public K getKey() {
		return key;
	}

	public long getTimeStamp() {
		return timeStamp;
	}
	
	@Override
	public String getName() {
		return key.getName()+"/"+timeStamp;
	}
	
	@Override
	public String toString() {
		return key+"/"+timeStamp;
	}

	public String getDescription() {
		return description;
	}
	public String getComments() {
		return comments;
	}

	public D getData() {
		return data;
	}

	public Action getAction() {
		return action;
	}

	@Override
	public User getModifiedByUser() {
		return modifiedByUser;
	}
	
}
