package yogi.paging.command;

import yogi.paging.FilterCriteria;
import yogi.paging.TableData;
import yogi.paging.TableResponse;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class FilterTablePageCommand<T> extends CommandAdapter<TableResponse> {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6876747226277961954L;
	
	private int tableDataSetId;
	private Changes changes;
	private boolean clearFilteredData;
	private FilterCriteria filterCriteria;

	public FilterTablePageCommand(int tableDataSetId, Changes changes, boolean clearFilteredData, FilterCriteria filterCriteria, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
		this.clearFilteredData = clearFilteredData;
		this.filterCriteria = filterCriteria;		
	}

	@Override
	public TableResponse execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);
		}
		if(clearFilteredData) tableData.clearFilter();
		tableData.filter(filterCriteria.getFilterData());
		return new TableResponse(tableData.getCurrentRowCount(), tableData.getTableDataSetId());
	}

}
