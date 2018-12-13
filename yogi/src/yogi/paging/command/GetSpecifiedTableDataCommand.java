package yogi.paging.command;

import java.util.List;

import yogi.paging.FilterCriteria;
import yogi.paging.TableData;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class GetSpecifiedTableDataCommand<T> extends CommandAdapter<List<String[]>> {	

	private static final long serialVersionUID = 3933958437264218094L;
	
	private int tableDataSetId;
	private FilterCriteria filterCriteria;
	private List<Integer> columnIndices;
	private List<String> columnNames;

	public GetSpecifiedTableDataCommand(int tableDataSetId, FilterCriteria filterCriteria, String userId, List<Integer> columnIndices) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.filterCriteria = filterCriteria;
		this.columnIndices = columnIndices;
	}
	
	public GetSpecifiedTableDataCommand(int tableDataSetId, FilterCriteria filterCriteria, List<String> columnNames, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.filterCriteria = filterCriteria;
		this.columnNames = columnNames;
	}

	@Override
	public List<String[]> execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());		
		List<Integer> filteredRowDataIndices = tableData.getFilteredRowDataIndices(filterCriteria);
		if(columnNames != null) {
			columnIndices = tableData.getColumnHeaderIndexes(columnNames);
		}
		return tableData.getSpecifiedTableData(filteredRowDataIndices, columnIndices);
	}

}
