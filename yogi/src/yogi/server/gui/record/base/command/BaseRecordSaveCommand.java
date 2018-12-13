package yogi.server.gui.record.base.command;

import java.util.ArrayList;
import java.util.List;

import yogi.base.relationship.RelationshipObject;
import yogi.base.validation.Validator;
import yogi.paging.changes.ChangeRecord;
import yogi.paging.command.SaveChangesPagingActionCommand;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.BaseRecordCreator;
import yogi.server.gui.record.base.BaseRecordFactory;
import yogi.server.gui.record.base.BaseRecordManager;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;

public abstract class BaseRecordSaveCommand<K extends RelationshipObject, T extends BaseRecord<K>> extends SaveChangesPagingActionCommand {

	private static final long serialVersionUID = -642369914383749805L;
	
	public static String SAVE_MODULE_NAME = "Save";
	
	public BaseRecordSaveCommand(int tableDataSetId, List<Integer> rowViewIndices,String userId) {
		super(tableDataSetId, rowViewIndices, userId);		
	}
	
	@Override
	public void execute(List<ChangeRecord> changesRecords) {
		Validator<T> validator = getValidator();
		BaseRecordFactory<K, T> factory = getFactory();
		List<BaseRecordCreator<K, T>> creators = new ArrayList<BaseRecordCreator<K, T>>();
		for(ChangeRecord changeRecord : changesRecords) {
			BaseRecordCreator<K, T> creator = getCreator(changeRecord);
			creators.add(creator);
		}
		List<Integer> errorIndices = factory.create(creators, validator, getLoginUser());
		if(!errorIndices.isEmpty()) {
			StringBuilder strBldr = new StringBuilder();
			for(Integer errorIndex : errorIndices) {
				strBldr.append("<BR>" + creators.get(errorIndex));
			}
			strBldr.append("<BR>");
			throw new RuntimeException("Save failed due to error in following records. Please correct and try again." + strBldr);
		}		
	}
		
	protected User getLoginUser() {
		String userId = this.getUserId();
		String loginUserId = this.getLoginUserId();
		if(loginUserId != null && !loginUserId.isEmpty()) userId = loginUserId; //superuser mode
		return UserManager.get().getUser(userId);	
	}

	private BaseRecordCreator<K, T> getCreator(ChangeRecord changeRecord) {
		BaseRecordCreator<K, T> creator = null;
		if(changeRecord.isDeleted()) { 
			creator = getDeleted(changeRecord);
		} else { 
			creator = getAddedOrModified(changeRecord);
			if(changeRecord.isAdded()){
				BaseRecordManager<K, T> manager = getManager();
				K key = creator.getKey();
				T latestRecord = manager.getLatestRecord(key);
				if(latestRecord != null) {
					throw new RuntimeException("Trying to over-write an existing record with duplicate one. Please remove and try again." + "<BR>" + key);
				}
			}
		}
		return creator;
	}
	
	protected abstract BaseRecordManager<K, T> getManager();
	
	protected abstract BaseRecordFactory<K, T> getFactory();

	protected abstract Validator<T> getValidator();

	protected abstract BaseRecordCreator<K, T> getAddedOrModified(ChangeRecord changeRecord);

	protected abstract BaseRecordCreator<K, T> getDeleted(ChangeRecord changeRecord);

}