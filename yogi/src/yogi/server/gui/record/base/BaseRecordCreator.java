package yogi.server.gui.record.base;

import yogi.base.Creator;
import yogi.base.relationship.RelationshipObject;
import yogi.server.action.Action;
import yogi.server.gui.user.User;

/**
 * @author Vikram Vadavala
 *
 * @param <K>
 * @param <T>
 */
public abstract class BaseRecordCreator<K extends RelationshipObject, T extends BaseRecord<K>> implements Creator<T> {
	
	private K key;
	private transient long timeStamp;
	private Action action;
	private User modifiedByUser;
	
	public long getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}
	
	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
		this.action = action;
	}
	
	public User getModifiedByUser() {
		return modifiedByUser;
	}
	
	public void setModifiedByUser(User modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	@Override
	public String toString() {
		return timeStamp+"/"+action+"/"+modifiedByUser;
	}

}