package yogi.server.gui.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.partition.PartitionAssistant;
import yogi.server.gui.partition.PartitionManager;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.user.UserManager;


public abstract class TypeGetCommand<K extends Key, D extends RecordData, T extends Record<K, D>> extends CommandAdapter<T> {

	private static final long serialVersionUID = -6849748273507429406L;
	private String name;
	protected String selectedUserId;
	protected String partitionCode;
		
	public TypeGetCommand(String name, String selectedUserId) {
		super(selectedUserId);
		this.name = name;
		this.selectedUserId = selectedUserId;
	}

	public String getName() {
		return name;
	}

	public String getSelectedUserId() {
		return selectedUserId;
	}

	@Override
	public T execute() {
		
		Partition partition = PartitionAssistant.get().getUserPartition(UserManager.get().getUser(getUserId())).getPartition();
		if(partitionCode != null){
			partition = PartitionManager.get().getPartition(partitionCode);
		}
		K key = getKeyManager().getKey(UserManager.get().getUser(getSelectedUserId()), getName(), partition);
		return getRecordManager().getLatestRecord(key);
	}
	public abstract KeyManager<K> getKeyManager();
	public abstract RecordManager<K, D, T> getRecordManager();
}