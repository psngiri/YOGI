package yogi.paging.command;

import yogi.paging.TableData;
import yogi.paging.TableResponse;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class DeleteAllTableDataCommand<T> extends CommandAdapter<TableResponse> {
	
	private static final long serialVersionUID = -1345502535868843811L;
	
	private int tableDataSetId;
	private Changes changes;
	
	public DeleteAllTableDataCommand(int tableDataSetId, Changes changes, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
	}

	@Override
	public TableResponse execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);
		}
		Changes deleteChanges = tableData.deleteAll();		
		tableData.applyChanges(deleteChanges);
		return new TableResponse(tableData.getCurrentRowCount(), tableData.getTableDataSetId());
	}

}
