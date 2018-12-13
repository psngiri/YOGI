package yogi.paging.command;

import java.util.ArrayList;
import java.util.List;

import yogi.paging.TableData;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class GetSelectionCommand extends CommandAdapter<List<String[]>> {	

	private static final long serialVersionUID = 3933958437264218094L;
	
	private int tableDataSetId;
	private List<Integer> rowViewIndices;
	private List<Integer> columnIndices;
	private List<String> columnNames;

	public GetSelectionCommand(int tableDataSetId, List<Integer> rowViewIndices, String userId, List<Integer> columnIndices) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.rowViewIndices = rowViewIndices;
		this.columnIndices = columnIndices;
	}
	
	public GetSelectionCommand(int tableDataSetId, List<Integer> rowViewIndices, List<String> columnNames, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.rowViewIndices = rowViewIndices;
		this.columnNames = columnNames;
	}

	@Override
	public List<String[]> execute() {
		List<String[]> rtnValue  = new ArrayList<String[]>();
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());	
		if(columnNames != null){
			columnIndices = tableData.getColumnHeaderIndexes(columnNames);
		} 
		List<Integer> rowDataView = tableData.getRowDataView();
		if(rowViewIndices == null || rowViewIndices.isEmpty()){
			rtnValue = tableData.getSpecifiedTableData(rowDataView, columnIndices);
		} else {
			List<Integer> rowDataIndicies = new ArrayList<Integer>(rowViewIndices.size());
			for(int i = 0; i < rowViewIndices.size(); i++){
				rowDataIndicies.add(rowDataView.get(rowViewIndices.get(i)));
			} 
			rtnValue = tableData.getSpecifiedTableData(rowDataIndicies, columnIndices);
		}
		return rtnValue;
	}

}