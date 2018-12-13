package yogi.server.gui.command;

import yogi.base.Factory;
import yogi.base.util.immutable.ImmutableList;
import yogi.base.validation.ObjectValidator;
import yogi.remote.client.app.CommandAdapter;
import yogi.server.action.Action;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.record.key.global.GlobalAssistant;
import yogi.server.gui.user.UserManager;


public abstract class TypeSaveCommand<K extends Key, D extends RecordData, T extends Record<K, D>, C extends RecordCreator<K, D, T>> extends CommandAdapter<Boolean> {

	private static final long serialVersionUID = -6849748273507429406L;
	
	private String idName;
	private boolean global;
	private String description;
	private String comments;
	protected D data;
	protected boolean flag;
	protected String loginUserId;
		
	public TypeSaveCommand(String idName, String userId,boolean global,String description,String comments,D recordData) {
		super(userId);
		this.idName = idName;
		this.global = global;
		this.description = description;
		this.comments = comments;
		this.data = recordData;
	}
	public String getIdName() {
		return idName!=null ? idName.trim() : null;
	}
	public boolean isGlobal() {
		return global;
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
	
	@Override
	public Boolean execute() {
		
		K key = getKeyManager().getKey(UserManager.get().getUser(getSaveUserId()), getIdName());
        if(!validate(key)) return false;
    	create(key); 
        return true;
	}
	
	protected boolean validate(K key)
	{
		if(key == null || flag) return true;
		if (getRecordManager().getLatestRecord(key) != null) {
			return false;
		} else {
			return true;
		}
	}
	
	protected String getSaveUserId() {
		return getUserId();
	}
	
	protected Boolean create(K key) {
		boolean newKey=false;
  	    if(key==null) {
  	    	key = getKeyManager().getKey(getSaveUserId(), getIdName());
  	    	newKey=true;
  	    }
		return create(key, newKey);
	}
	
	protected Boolean create(K key, boolean newKey) {
		if(isGlobal()) GlobalAssistant.get().setGlobal(key);
		C creator = getCreator();
		creator.setKey(key);
		long currentTimeMillis = System.currentTimeMillis();
		creator.setTimeStamp(currentTimeMillis);
		creator.setDescription(description);
		creator.setComments(comments);
		creator.setData(data);
		if(newKey){
			creator.setAction(Action.Add);
		}else{
			creator.setAction(Action.Update);
		}
		creator.setModifiedByUser(UserManager.get().getUser(loginUserId));
		setChildKeys(creator);
		T record = null;
		synchronized(getFactory()){
			ImmutableList<T> records = this.getRecordManager().getRecords(key);
			if(!records.isEmpty() && records.get(records.size()-1).getTimeStamp() == currentTimeMillis) {
				throw new RuntimeException("Multiple saves happening at the same time for the key:" + key);
			}
			record = getFactory().create(creator, getValidator());
		}
		return record != null;
	}
	
	
	public void setChildKeys(C recordCreator){}
	protected abstract C getCreator();
	public abstract ObjectValidator<? super T> getValidator();
	public abstract KeyManager<K> getKeyManager();
	public abstract RecordManager<K, D, T> getRecordManager();
	public abstract Factory<T> getFactory();
	
}