package yogi.server.gui.record;

import yogi.server.gui.partition.Partition;
import yogi.server.gui.record.base.BaseRecordCreator;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.user.User;

/**
 * @author Vikram Vadavala
 *
 * @param <K>
 * @param <T>
 */
public abstract class RecordCreator<K extends Key, D extends RecordData, T extends Record<K, D>> extends BaseRecordCreator<K,T> {
	
	private String idName;
	private User user;
	private Partition partition;
	private String description;
	private String comments;
	private D data;
	
	public Partition getPartition() {
		return partition;
	}
	public void setPartition(Partition partition) {
		this.partition = partition;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName.trim();
	}
	@Override
	public String toString() {
		return getUser().getId()+"/"+idName+"/"+partition+"/"+super.toString();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public D getData() {
		return data;
	}
	public void setData(D data) {
		this.data = data;
	}
}