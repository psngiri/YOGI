package yogi.paging.command;

import java.util.List;

import yogi.paging.changes.ChangeRecord;
import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;

public abstract class ChangesPagingActionCommand<R> extends PagingActionCommand<R> {

	private static final long serialVersionUID = -2878817163271858279L;
	
	public ChangesPagingActionCommand(int tableDataSetId, List<Integer> rowViewIndices,String userId) {
		super(tableDataSetId, rowViewIndices, userId);
	}

	protected List<ChangeRecord> getTablePageChanges(List<String> columnNames) {
		List<ChangeRecord> changeList;
		try {			    	
		    changeList = (List<ChangeRecord>) MultiServerCommandExecutor.get().execute(PAGING_SERVER_NAME, new GetTablePageChangesCommand(getTableDataSetId(), columnNames, getUserId(), isRowIndicesNeeded()));
		} catch (CommandException e) {
			throw new RuntimeException(e);
		}		
		return changeList;
	}

	protected abstract boolean isRowIndicesNeeded();

}