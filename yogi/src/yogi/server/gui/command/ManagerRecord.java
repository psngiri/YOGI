package yogi.server.gui.command;

import yogi.server.gui.record.Record;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.global.GlobalAssistant;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoManager;

public class ManagerRecord<K extends Key, D extends RecordData, T extends Record<K, D>> {
	private String idName;
	private String description;
	private String comments;
	private long createdDate; //numberof days since epoch
	private long timestamp;///time in miillis since epoch
	private boolean global;
	private String userName;
	private String modifiedByUserName;
	
	public ManagerRecord(T record) {
		super();
		idName = record.getKey().getIdName();
		description = record.getDescription();
		comments = record.getComments();
		createdDate = record.getKey().getCreateDate();
		timestamp = record.getTimeStamp();
		global = GlobalAssistant.get().isGlobal(record.getKey());
		UserInfo userInfo = UserInfoManager.get().getUserInfo(record.getKey().getUser());
		userName = userInfo.getUserName() + "("+ userInfo.getUser().getId()+ ")";
		modifiedByUserName = "";
		User modifiedByUser = record.getModifiedByUser();
		if(modifiedByUser != null && modifiedByUser != UserManager.BLANK && modifiedByUser != record.getUser()) {
			UserInfo modifiedByUserInfo = UserInfoManager.get().getUserInfo(modifiedByUser);
			modifiedByUserName = modifiedByUserInfo.getLastName() + ", " + modifiedByUserInfo.getFirstName() + "("+ modifiedByUserInfo.getUser().getId()+ ")";
		}
	}

	public String getIdName() {
		return idName;
	}

	public String getDescription() {
		return description;
	}
	
	public String getComments() {
		return comments;
	}
	
	public long getCreatedDate() {
		return createdDate;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public boolean isGlobal() {
		return global;
	}

	public String getUserName() {
		return userName;
	}

	public String getModifiedByUserName() {
		return modifiedByUserName;
	}
	
}
