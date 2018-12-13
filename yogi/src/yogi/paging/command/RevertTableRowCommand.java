package yogi.paging.command;

import yogi.paging.TableData;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class RevertTableRowCommand<T> extends CommandAdapter<Boolean> {	
	
	private static final long serialVersionUID = 1163204803557786302L;
	
	private int tableDataSetId;
	private Changes changes;
	private int rowIndex;

	public RevertTableRowCommand(int tableDataSetId, Changes changes, int rowIndex, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
		this.rowIndex = rowIndex;
	}

	@Override
	public Boolean execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId, getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);
		}
		if(rowIndex >= tableData.getCurrentRowCount()) {
			throw new RuntimeException("RowIndex " + rowIndex + " doesnt exist for tableDataSetId : " + tableDataSetId);
		}
		return tableData.revertRowChanges(rowIndex);
	}

}
