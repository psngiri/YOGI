package yogi.server.gui.record.key;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.user.User;


public abstract class KeyImpl<K extends Key> extends RelationshipObjectImpl<K> implements Key{
	
	private transient User user;
	private String idName;
	private String userId;
	private transient Partition partition;
	private transient long createDate;
	
	protected KeyImpl(User user, String idName, long createDate, Partition partition) {
		super();
		this.user = user;
		this.userId = user.getId();
		this.idName = idName;
		this.createDate = createDate;
		this.partition = partition;
	}	
		
	public String getIdName() {
		return idName;
	}

	public User getUser() {
		return user;
	}

	public long getCreateDate() {
		return createDate;
	}

	public String getName() {
		return user.getName()+"/"+idName+"/"+createDate;
	}

	@Override
	public String toString() {
		return user+"/"+idName+"/"+createDate;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	public Partition getPartition() {
		return partition;
	}

	
}
