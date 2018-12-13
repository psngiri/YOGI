package yogi.paging.command;

import yogi.paging.PagingCommandResponse;
import yogi.paging.TableData;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class GotoTableRecordCommand<T> extends CommandAdapter<PagingCommandResponse> {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5001611914101519646L;
	
	private int tableDataSetId;
	private Changes changes;
	private int gotoRow;

	public GotoTableRecordCommand(int tableDataSetId, Changes changes, int gotoRow, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
		this.gotoRow = gotoRow;
	}

	@Override
	public PagingCommandResponse execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);
		}
		int gotoIndex = -1;
		if(gotoRow <= tableData.getCurrentRowCount()) {
			gotoIndex = gotoRow - 1;
		}
		return new PagingCommandResponse(tableData.getCurrentRowCount(), tableData.getTableDataSetId(), gotoIndex); 
	}

}
