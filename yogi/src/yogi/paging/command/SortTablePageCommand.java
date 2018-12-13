package yogi.paging.command;

import yogi.paging.SortCriteria;
import yogi.paging.TableData;
import yogi.paging.TableResponse;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class SortTablePageCommand<T> extends CommandAdapter<TableResponse> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6156312402326958104L;
	
	private int tableDataSetId;
	private Changes changes;
	private SortCriteria sortCriteria;
	
	public SortTablePageCommand(int tableDataSetId, Changes changes, SortCriteria sortCriteria, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
		this.sortCriteria = sortCriteria;
	}

	@Override
	public TableResponse execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);			
		}
		tableData.sort(sortCriteria.getSortData());
		return new TableResponse(tableData.getCurrentRowCount(), tableData.getTableDataSetId());
	}
}
