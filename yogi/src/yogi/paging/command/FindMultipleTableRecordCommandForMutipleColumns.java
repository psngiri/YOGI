package yogi.paging.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import yogi.paging.TableData;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;

public class FindMultipleTableRecordCommandForMutipleColumns extends CommandAdapter<List<Integer>> {	

	private static final long serialVersionUID = -7154268424850024674L;
	private int tableDataSetId;
	private Changes changes;
	private int findFromRowIndex;
	private List<Map<String, String>> columnViewIndexValueMaps;
	
	public FindMultipleTableRecordCommandForMutipleColumns(int tableDataSetId, Changes changes, int findFromRowIndex, List<Map<String,String>> columnViewIndexValueMaps, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
		this.findFromRowIndex = findFromRowIndex;		
		this.columnViewIndexValueMaps = columnViewIndexValueMaps;
	}

	@Override
	public List<Integer> execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);
		}
		List<Integer> gotoIndexes = new ArrayList<Integer>();
		for(Map<String,String> columnViewIndexValueMap : columnViewIndexValueMaps){
			if(columnViewIndexValueMap!=null && !columnViewIndexValueMap.isEmpty()) {			
				gotoIndexes.add(tableData.findRecord(findFromRowIndex, columnViewIndexValueMap));
			}
		}
		return gotoIndexes;
	}
	
}
