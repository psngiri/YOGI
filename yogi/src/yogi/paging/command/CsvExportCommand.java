package yogi.paging.command;

import java.util.List;

import yogi.paging.TableData;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class CsvExportCommand extends CommandAdapter<List<String[]>> {	

	private static final long serialVersionUID = 3933958437264218094L;
	
	private int tableDataSetId;
	private List<Integer> columnIndices;

	public CsvExportCommand(int tableDataSetId, List<Integer> columnIndices, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.columnIndices = columnIndices;
	}

	@Override
	public List<String[]> execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());		
		List<String[]> specifiedTableData = tableData.getSpecifiedTableData(tableData.getRowDataView(), columnIndices);
		TableColumnConfig<?>[] columnHeader = tableData.getTableColumnConfigs();		
		String headerNames[];
		if(columnIndices == null || columnIndices.isEmpty()) {
			headerNames = getCommaDelimitedHeaderNames(columnHeader);
		} else {
			headerNames = getCommaDelimitedHeaderNames(columnHeader, columnIndices);
		}
		specifiedTableData.add(0, headerNames);
		return specifiedTableData;
	}
	
	private String[] getCommaDelimitedHeaderNames(TableColumnConfig<?>[] columnHeader) {
		String[] rtnValue = new String[columnHeader.length];
		if(columnHeader != null) {
			for (int i = 0;  i < columnHeader.length; i++) {
				rtnValue[i] = columnHeader[i].getName();
			}
		}
		return rtnValue;
    }
	
	private String[] getCommaDelimitedHeaderNames(TableColumnConfig<?>[] columnHeader, List<Integer> columnIndices) {
		String[] rtnValue = new String[columnIndices.size()];
		if(columnHeader != null) {
			int i = 0;
			for (Integer columnIndex : columnIndices) {
				rtnValue[i++] = columnHeader[columnIndex].getName();
			}
		}
		return rtnValue;
    }

}
