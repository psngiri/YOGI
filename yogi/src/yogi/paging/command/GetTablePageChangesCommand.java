package yogi.paging.command;

import java.util.List;

import yogi.paging.TableData;
import yogi.paging.changes.ChangeRecord;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class GetTablePageChangesCommand extends CommandAdapter<List<ChangeRecord>> {
	
	private static final long serialVersionUID = -56257700308878946L;
	
	private int tableDataSetId;
	private List<Integer> columnIndexes;
	private List<String> columnNames;
	private boolean isRowIndicesNeeded;
	
	public GetTablePageChangesCommand(int tableDataSetId, String userId, List<Integer> columnIndexes) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.columnIndexes = columnIndexes;
		this.isRowIndicesNeeded = false;
	}
	
	public GetTablePageChangesCommand(int tableDataSetId, List<String> columnNames, String userId) {
		this(tableDataSetId, columnNames, userId, false);
	}

	public GetTablePageChangesCommand(int tableDataSetId, List<String> columnNames, String userId, boolean isRowIndicesNeeded) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.columnNames = columnNames;
		this.isRowIndicesNeeded = isRowIndicesNeeded;
	}

	@Override
	public List<ChangeRecord> execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(columnNames != null){
			columnIndexes = tableData.getColumnHeaderIndexes(columnNames);
		}
		return tableData.getChanges(columnIndexes, isRowIndicesNeeded);	
	}

}