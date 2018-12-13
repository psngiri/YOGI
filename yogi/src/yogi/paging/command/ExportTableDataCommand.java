package yogi.paging.command;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import yogi.paging.TableData;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class ExportTableDataCommand<T> extends CommandAdapter<String> {	

	private static final long serialVersionUID = 3933958437264218094L;
	
	private int tableDataSetId;
	private Character seperator;
	private List<Integer> columnIndices;

	public ExportTableDataCommand(int tableDataSetId, char seperator, List<Integer> columnIndices, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.seperator = seperator;
		this.columnIndices = columnIndices;
	}

	@Override
	public String execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());	
		List<String[]> data = tableData.getSpecifiedTableData(tableData.getRowDataView(), columnIndices);
		String[] header = tableData.getColumnHeaderNames();
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		if(seperator == null) {
			seperator = ',';		
		}
		pw.println(getLineData(header, seperator));
		for(String[] row : data) {
			pw.println(getLineData(row, seperator));
		}
		return sw.getBuffer().toString();
	}

	private StringBuilder getLineData(String[] data, char seperator) {
		StringBuilder bldr = new StringBuilder();
		for(String str : data) {
			if(str.contains(",")){
				bldr.append("\"" + str + "\"");
			} else {
				bldr.append(str);
			}
			bldr.append(seperator);			
		}
		bldr.delete(bldr.length()-1, bldr.length());
		return bldr;
	}
}
