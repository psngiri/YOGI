package yogi.server.gui.record.key;

import yogi.base.Creator;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.user.User;

public abstract class KeyCreator<K extends Key> implements Creator<K> {
	private User user;
	private String idName;
	private Partition partition;
	private long createDate;
	
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
		this.idName = idName;
	}

	public long getCreateDate() {
		return createDate;
	}
	public Partition getPartition() {
		return partition;
	}
	public void setPartition(Partition partition) {
		this.partition = partition;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return user+"/"+idName+"/"+createDate;
	}
	

	
}
