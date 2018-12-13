package yogi.paging.command;

import java.util.List;

import yogi.paging.FilterCriteria;
import yogi.paging.TableData;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class GetSpecifiedTableDataCountCommand<T> extends CommandAdapter<Integer> {	

	private static final long serialVersionUID = 3933958437264218094L;
	
	private int tableDataSetId;
	private FilterCriteria filterCriteria;

	public GetSpecifiedTableDataCountCommand(int tableDataSetId, FilterCriteria filterCriteria, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.filterCriteria = filterCriteria;
	}

	@Override
	public Integer execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());		
		List<Integer> filteredRowDataIndices = tableData.getFilteredRowDataIndices(filterCriteria);
		return filteredRowDataIndices.size();
	}

}
