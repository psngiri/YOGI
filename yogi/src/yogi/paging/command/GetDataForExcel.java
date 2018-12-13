package yogi.paging.command;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import yogi.paging.FilterCriteria;
import yogi.paging.TableData;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;

//Can be useful if string building is necessary at paging server level.
//This functionality is handled by Export Command and CsvCommandServlet in JafWebApp 
public class GetDataForExcel extends CommandAdapter<String> {	

	private static final long serialVersionUID = 3933958437264218094L;
	
	private int tableDataSetId;
	private FilterCriteria filterCriteria;
	private List<Integer> columnIndices;

	public GetDataForExcel(int tableDataSetId, FilterCriteria filterCriteria, List<Integer> columnIndices, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.filterCriteria = filterCriteria;
		this.columnIndices = columnIndices;
	}

	@Override
	public String execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());		
		List<Integer> filteredRowDataIndices = tableData.getFilteredRowDataIndices(filterCriteria);
		List<String[]> specifiedTableData = tableData.getSpecifiedTableData(filteredRowDataIndices, columnIndices);
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter);
		pw.print( getCommaDelimitedHeaderNames(PagingServerImpl.get().getTableData(tableDataSetId,getUserId()).getTableColumnConfigs()));
		pw.print("\n");
		int lastIndex = specifiedTableData.size() -1;
		for(int i = 0; i < lastIndex; i ++)
		{
			pw.print(convertToCommaDelimited(specifiedTableData.get(i)));
			pw.print("\n");
		}
		pw.print(convertToCommaDelimited(specifiedTableData.get(lastIndex)));
		
		return stringWriter.toString();
	}
	
	public String getCommaDelimitedHeaderNames(TableColumnConfig<?>[] columnHeader) {
		StringWriter headerNames = new StringWriter();
			if(columnHeader !=null){
				int lastIndex = columnHeader.length -1;
				for (int i = 0;  i < lastIndex; i++) {
					headerNames.append(getValue(columnHeader[i].getName()));
					headerNames.append(",");
				}
				headerNames.append(columnHeader[lastIndex].getName());
			}
			return headerNames.toString();
    }

	private String getValue(String value){
		if(value.contains(",")){
			return ","+value+",";
		}
		return value;
	}
	
	 public String convertToCommaDelimited(String[] list) {
	        StringWriter rtnValue = new StringWriter();
	        int lastIndex = list.length -1;
	        for (int i = 0; list != null && i < lastIndex; i++) {
	            rtnValue.append(getValue(list[i]));
	            rtnValue.append(",");
	        }
	        rtnValue.append(list[lastIndex]);
	        
	        return rtnValue.toString();
	    }

}
