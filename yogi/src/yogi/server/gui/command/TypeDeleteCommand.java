package yogi.server.gui.command;

import java.util.LinkedHashMap;
import java.util.Map;

import yogi.base.Factory;
import yogi.remote.client.app.CommandAdapter;
import yogi.server.action.Action;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;


public abstract class TypeDeleteCommand<K extends Key, D extends RecordData, T extends Record<K, D>, C extends RecordCreator<K, D, T>> extends CommandAdapter<Map<String, String>> {

	private static final long serialVersionUID = -6849748273507429406L;
	private String[] names;
	private transient  LinkedHashMap<String, String> messages;
	protected String loginUserId;
	
	public TypeDeleteCommand(String[] names, String userId) {
		super(userId);
		this.names = names;
	}
	
	public String[] getName() {
		return names;
	}
	
	@Override
	public Map<String, String> execute() {
		User user = UserManager.get().getUser(getUserId());
		User loginUser = UserManager.get().getUser(loginUserId);
		String[] names = getName();
		for(String name: names){
			K key = getKeyManager().getKey(user, name);
			T latestRecord = getRecordManager().getLatestRecord(key);
			if(canRecordBeDeleted(key)){
				C creator = getCreator();
				creator.setKey(key);
				creator.setTimeStamp(System.currentTimeMillis());
				creator.setDescription("");
				creator.setComments("");
				creator.setData(latestRecord.getData());
				creator.setAction(Action.Cancel);
				creator.setModifiedByUser(loginUser);
				T object = getFactory().create(creator);
				if(object != null){
					 getMessages().put(name, "");	
				}else{
					 getMessages().put(name, String.format("Error deleting record with name %s and userId %s", name, getUserId()));
				}
				onRecordDeletion(object);
			}
		}
		return messages;
	}
	
	protected Map<String, String> getMessages() {
		if(messages == null) messages = new LinkedHashMap<String, String>();
		return messages;
	}

	protected boolean canRecordBeDeleted(K key){
		return true;
	}
	
	protected void onRecordDeletion(T record){}
	
	protected abstract C getCreator();
	public abstract KeyManager<K> getKeyManager();
	public abstract Factory<T> getFactory();
	public abstract RecordManager<K, D, T> getRecordManager();
}