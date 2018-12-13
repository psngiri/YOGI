package yogi.report.server.command;

import java.util.HashMap;

import yogi.base.util.Pair;
import yogi.paging.BaseTableDataMapper;
import yogi.report.server.Column;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.report.server.ReportTableData;

public class ReportDataMapper extends BaseTableDataMapper<ReportData, Pair<Query, Long>>{
	
	private static final long serialVersionUID = -5015241115948351778L;
	
	public ReportDataMapper(ReportTableData reportTableData,Pair<Query, Long> queryTimestamp) {
		super(reportTableData.getTableColumnConfigs(), reportTableData.getTableActionConfigs(), reportTableData.getDefaultFindColumnName(), reportTableData.isSelectAll(), queryTimestamp);
	}

	@Override
	public Object getColumnValue(int columnHeaderIndex, ReportData rowData) {
		Object value = rowData.getValue(columnHeaderIndex);
		if(value == null) return null;
		return convertColumnValue(columnHeaderIndex, value.toString());
	}

	@Override
	public HashMap<String, String> getColumnDisplayNames() {
		if(columnDisplayNames == null){
			columnDisplayNames = new HashMap<String, String>();
			Pair<Query, Long> queryTimestamp = this.getInput();
			for(Column column: queryTimestamp.getFirst().getSelectedColumns()) {
				String name = column.getName();
				String displayName = column.getDisplayName();
				if(!name.equals(displayName)){
					columnDisplayNames.put(name, displayName);
				}
			}
		}
		return columnDisplayNames;
	}
	
}