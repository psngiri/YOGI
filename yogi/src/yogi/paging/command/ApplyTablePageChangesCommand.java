package yogi.paging.command;

import yogi.paging.TableData;
import yogi.paging.TableResponse;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;

public class ApplyTablePageChangesCommand<T> extends CommandAdapter<TableResponse> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1394139795566508606L;
	
	private int tableDataSetId;
	private Changes changes;

	public ApplyTablePageChangesCommand(int tableDataSetId, Changes changes,String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
	}

	@Override
	public TableResponse execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		tableData.applyChanges(changes);
		return new TableResponse(tableData.getCurrentRowCount(), tableData.getTableDataSetId());
	}
}
