package yogi.paging.command;

import java.util.Map;

import yogi.paging.PagingCommandResponse;
import yogi.paging.TableData;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;

public class FindTableRecordCommandForMutipleColumns<T> extends CommandAdapter<PagingCommandResponse> {	

	private static final long serialVersionUID = -2565757033797649087L;
	private int tableDataSetId;
	private Changes changes;
	private int findFromRowIndex;
	private Map<String, String> columnViewIndexValueMap;
	
	public FindTableRecordCommandForMutipleColumns(int tableDataSetId, Changes changes, int findFromRowIndex, Map<String,String> columnViewIndexValueMap, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
		this.findFromRowIndex = findFromRowIndex;		
		this.columnViewIndexValueMap = columnViewIndexValueMap;
	}

	@Override
	public PagingCommandResponse execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);
		}
		int gotoIndex = 0;
		if(columnViewIndexValueMap!=null && !columnViewIndexValueMap.isEmpty()) {			
			gotoIndex = tableData.findRecord(findFromRowIndex, columnViewIndexValueMap);
		}
		return new PagingCommandResponse(tableData.getCurrentRowCount(), tableData.getTableDataSetId(), gotoIndex); 
	}
	
}
