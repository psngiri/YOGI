package yogi.paging.command;

import yogi.paging.PagingCommandResponse;
import yogi.paging.TableData;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class UndoTablePageCommand<T> extends CommandAdapter<PagingCommandResponse> {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1470164167263075615L;
	
	private int tableDataSetId;
	private Changes changes;
	
	public UndoTablePageCommand(int tableDataSetId, Changes changes, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
	}

	@Override
	public PagingCommandResponse execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);
		}
		int gotoIndex = tableData.undo();
		return new PagingCommandResponse(tableData.getCurrentRowCount(), tableData.getTableDataSetId(), gotoIndex);
	}
}
