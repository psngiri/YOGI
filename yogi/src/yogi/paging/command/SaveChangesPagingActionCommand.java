package yogi.paging.command;

import java.util.Comparator;
import java.util.List;

import yogi.base.util.collections.Duplicates;
import yogi.base.util.collections.IndexItem;
import yogi.paging.changes.ChangeRecord;
import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsAssistant;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;

public abstract class SaveChangesPagingActionCommand extends ChangesPagingActionCommand<Integer> {

	private static final long serialVersionUID = -642369914383749805L;
	
	public static String SAVE_MODULE_NAME = "Save";
	
	public SaveChangesPagingActionCommand(int tableDataSetId, List<Integer> rowViewIndices,String userId) {
		super(tableDataSetId, rowViewIndices, userId);		
	}
	
	@Override
	public Integer execute() {
		String appName = getAppName();
		if(appName != null){
			if(!ApplicationPermissionsAssistant.get().isAuthorized(appName, SAVE_MODULE_NAME, getUser())) {
				throw new RuntimeException(getUserId() + " is not authorized to perform this save operation.");
			}
		}
		List<String> columnNames = getColumnNames();
		List<ChangeRecord> changesRecords = getTablePageChanges(columnNames);
		Comparator<ChangeRecord> comparator = getComparator();
		if(comparator != null){
			List<List<IndexItem<ChangeRecord>>> checkDuplicates = Duplicates.checkDuplicates(changesRecords, comparator);
			if(!checkDuplicates.isEmpty()) {
				StringBuilder message = new StringBuilder();
				message.append("<BR>");
				for(List<IndexItem<ChangeRecord>> duplicate : checkDuplicates){
					ChangeRecord item = duplicate.get(0).getItem();
					message.append(item.getValue(0) + " / " + item.getValue(1) + " / " + item.getValue(2));				
					message.append("<BR>");
				}
				throw new RuntimeException("Trying to save with duplicate records. Please remove and try again." + message);
			}
		}
		execute(changesRecords);
		Integer rtnVal = -1;
		AdjustSavePointerCommand adjustSavePointerCommand = new AdjustSavePointerCommand(getTableDataSetId(), getUserId());
		try {
			rtnVal = MultiServerCommandExecutor.get().execute(PAGING_SERVER_NAME, adjustSavePointerCommand);
		} catch (CommandException e) {
			throw new RuntimeException("Exception occured while trying to adjust save pointer", e);			
		}
		return rtnVal;
	}

	protected User getUser() {
		return UserManager.get().getUser(getUserId());		
	}
		
	protected String getAppName(){
		return null;
	}
	
	protected Comparator<ChangeRecord> getComparator(){
		return null;
	}

	protected abstract void execute(List<ChangeRecord> changesRecords);

	protected abstract List<String> getColumnNames();

	@Override
	protected boolean isRowIndicesNeeded() {
		return false;
	}
	
}