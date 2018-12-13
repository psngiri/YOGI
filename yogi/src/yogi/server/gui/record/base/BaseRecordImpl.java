package yogi.server.gui.record.base;

import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.RelationshipObjectImpl;
import yogi.server.action.Action;
import yogi.server.gui.user.User;



public abstract class BaseRecordImpl<K extends RelationshipObject, T extends BaseRecord<K>> extends RelationshipObjectImpl<T> implements BaseRecord<K>{
	
	private transient K key;
	private transient Action action;
	private transient long timeStamp;
	private transient User modifiedByUser;
	
	protected BaseRecordImpl(K key, Action action, long timeStamp, User modifiedByUser) {
		super();
		this.key = key;
		this.action=action;
		this.timeStamp = timeStamp;
		this.modifiedByUser = modifiedByUser;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public Action getAction() {
		return action;
	}

	@Override
	public long getTimeStamp() {
		return timeStamp;
	}
	
	@Override
	public User getModifiedByUser() {
		return modifiedByUser;
	}

	@Override
	public String getName() {
		return key.getName()+"/"+timeStamp;
	}
	
	public String toString() {
		return key+"/"+timeStamp;
	}
	
}
