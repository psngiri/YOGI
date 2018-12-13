package yogi.server.gui.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.partition.PartitionAssistant;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;


public abstract class TypeManagerCommand<K extends Key, D extends RecordData, T extends Record<K, D>> extends CommandAdapter<List<ManagerRecord<K, D, T>>> {
	
	private static final long serialVersionUID = -6849748273507429406L;
	
	private String selectedUserId;
	
	public TypeManagerCommand(String selectedUserId) {
		super(selectedUserId);
		this.selectedUserId = selectedUserId;
	}

	public String getSelectedUserId() {
		return selectedUserId;
	}
	
	@Override
	public List<ManagerRecord<K, D, T>> execute() {
		
		User user = UserManager.get().getUser(getSelectedUserId());
		Partition partition = PartitionAssistant.get().getUserPartition(UserManager.get().getUser(getUserId())).getPartition();
		ImmutableList<K> keys = getKeyManager().getKeys(user, partition);
		List<ManagerRecord<K, D, T>> rtnValue = new ArrayList<ManagerRecord<K, D, T>>(keys.size());
		for(K key: keys)
		{
			T latestRecord = getRecordManager().getLatestRecord(key);			
			if(latestRecord != null && isAuthorized(UserManager.get().getUser(getUserId()), latestRecord)){
				rtnValue.add(new ManagerRecord<K, D, T>(latestRecord));
			}
		}
		Collections.sort(rtnValue, new Comparator<ManagerRecord<K, D, T>>(){

			@Override
			public int compare(ManagerRecord<K, D, T> o1, ManagerRecord<K, D, T> o2) {
				return o1.getIdName().toLowerCase().compareTo(o2.getIdName().toLowerCase());
			}
			
		});
		return rtnValue;
	}
	
	protected boolean isAuthorized(User user, T latestRecord) {
		return true;
	}
	public abstract KeyManager<K> getKeyManager();
	public abstract RecordManager<K, D, T> getRecordManager();
	
	
}