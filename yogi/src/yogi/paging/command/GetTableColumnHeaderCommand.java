package yogi.paging.command;

import yogi.paging.TableData;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;

public class GetTableColumnHeaderCommand<T> extends CommandAdapter<TableConfig> {	

	private static final long serialVersionUID = -1442554915129503158L;
	
	private int tableDataSetId;

	public GetTableColumnHeaderCommand(int tableDataSetId, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
	}

	@Override
	public TableConfig execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		return new TableConfig(tableData.getTableColumnConfigs(), tableData.getTableActionConfigs(), tableData.getDefaultFindColumnIndex(), tableData.isDefaultEditSelectAllValue(), tableData.getColumnDisplayNames());
	}

}
