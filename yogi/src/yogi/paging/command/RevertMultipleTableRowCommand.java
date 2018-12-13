package yogi.paging.command;

import java.util.List;

import yogi.paging.TableData;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class RevertMultipleTableRowCommand<T> extends CommandAdapter<Boolean> {	
	
	private static final long serialVersionUID = 1163204803557786302L;
	
	private int tableDataSetId;
	private Changes changes;
	private List<Integer> rowIndices;

	public RevertMultipleTableRowCommand(int tableDataSetId, Changes changes, List<Integer> rowIndices, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
		this.rowIndices = rowIndices;
	}

	@Override
	public Boolean execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId, getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);
		}
		for(Integer rowIndex : rowIndices) {
			if(rowIndex >= tableData.getCurrentRowCount()) {
				throw new RuntimeException("RowIndex " + rowIndex + " doesnt exist for tableDataSetId : " + tableDataSetId);
			}
		}
		for(Integer rowIndex : rowIndices) {
			tableData.revertRowChanges(rowIndex);
		}
		return true;
	}

}
