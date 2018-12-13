package yogi.server.gui.record.command;

import java.util.List;

import yogi.base.Creator;
import yogi.base.util.JsonAssistant;
import yogi.server.action.ActionAssistant;
import yogi.server.gui.partition.PartitionManager;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.record.base.command.BaseRecordClusterCommand;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.user.UserManager;

public abstract class RecordClusterCommand<K extends Key, D extends RecordData, T extends Record<K, D>> extends BaseRecordClusterCommand<K, T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private String name;
	private String partition;
	private long createDate;
	private long timestamp;
	private String description;
	private String comments;
	private int action;
	private String reportData;
	private String modifiedByUserId;
	
	public RecordClusterCommand(T report) {
		super(report);
		userId = report.getUserId();
		name = report.getIdName();
		partition = report.getPartition().getPartitionCode();
		createDate = report.getCreateDate();
		timestamp = report.getTimeStamp();
		description = report.getDescription();
		comments = report.getComments();
		action = report.getAction().getValue();
		reportData = JsonAssistant.get().toJson(report.getData());
		modifiedByUserId = report.getModifiedByUser().getId();
	}

	public RecordClusterCommand(List<T> objects) {
		super(objects);
	}

	@Override
	protected void process(T object) {
		
	}

	@Override
	protected List<Creator<T>> getCreators() {
		return null;
	}

	@Override
	protected Creator<T> getCreator() {
		RecordCreator<K, D, T> creator = getRecordCreator();
		K key = getKeyManager().getKey(UserManager.get().getUser(userId), name, PartitionManager.get().getPartition(partition), createDate, true);
		creator.setKey(key);
		creator.setTimeStamp(timestamp);
		creator.setDescription(description);
		creator.setComments(comments);
		creator.setAction(ActionAssistant.get().getAction(action));
		D data = JsonAssistant.get().fromJson(reportData, getRecordDataClass());
		creator.setData(data);
		creator.setModifiedByUser(UserManager.get().getUser(modifiedByUserId));
		return creator;
	}

	protected abstract KeyManager<K> getKeyManager();

	protected abstract Class<D> getRecordDataClass();

	protected abstract RecordCreator<K, D, T> getRecordCreator();

}
