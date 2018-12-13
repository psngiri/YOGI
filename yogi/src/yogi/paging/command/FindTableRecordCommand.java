package yogi.paging.command;

import yogi.paging.PagingCommandResponse;
import yogi.paging.TableData;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class FindTableRecordCommand<T> extends CommandAdapter<PagingCommandResponse> {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5001611914101519646L;
	
	private int tableDataSetId;
	private Changes changes;
	private int findFromRowIndex;
	private int columnIndex;
	private String columnValue;

	public FindTableRecordCommand(int tableDataSetId, Changes changes, int findFromRowIndex, int columnIndex, String columnValue, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
		this.findFromRowIndex = findFromRowIndex;
		this.columnIndex = columnIndex;
		this.columnValue = columnValue;
	}

	@Override
	public PagingCommandResponse execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);
		}
		int gotoIndex = 0;
		if(columnIndex != -1) {			
			gotoIndex = tableData.findRecord(findFromRowIndex, columnIndex, columnValue);
		}
		return new PagingCommandResponse(tableData.getCurrentRowCount(), tableData.getTableDataSetId(), gotoIndex); 
	}

}
