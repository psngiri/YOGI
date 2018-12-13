package yogi.report.server;

import java.util.ArrayList;
import java.util.List;

import yogi.paging.TableActionConfig;
import yogi.paging.column.TableColumnConfig;

public class ReportTableData extends ReportDataRows{
	
	private static final long serialVersionUID = 1L;
	private int queryId;
	private List<String> header = new ArrayList<String>();
	private List<TableColumnConfig<?>> tableColumnConfigs;
	private List<TableActionConfig> tableActionConfigs;
	private String defaultFindColumnName;
	private boolean selectAll;

	public ReportTableData(int queryId,
			List<String> header, List<ReportData> rows, List<TableColumnConfig<?>> tableColumnConfigs, List<TableActionConfig> tableActionConfigs, String defaultFindColumnName, boolean selectAll) {
		super(rows);
		this.queryId = queryId;
		this.header = header;
		this.tableColumnConfigs = tableColumnConfigs;
		this.tableActionConfigs = tableActionConfigs;
		this.defaultFindColumnName = defaultFindColumnName;
		this.selectAll = selectAll;
	}

	public List<String> getHeader() {
		if(header == null) return new ArrayList<String>();
		return header;
	}

	public List<TableColumnConfig<?>> getTableColumnConfigs() {
		return tableColumnConfigs;
	}
	
	public int getQueryId() {
		return queryId;
	}

	public List<TableActionConfig> getTableActionConfigs() {
		return tableActionConfigs;
	}

	public String getDefaultFindColumnName() {
		return defaultFindColumnName;
	}

	public boolean isSelectAll() {
		return selectAll;
	}
	
}
