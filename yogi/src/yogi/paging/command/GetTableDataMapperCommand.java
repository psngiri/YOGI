package yogi.paging.command;

import yogi.paging.TableDataMapper;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;

public class GetTableDataMapperCommand extends CommandAdapter<TableDataMapper<?, ?>> {	

	private static final long serialVersionUID = -1442554915129503158L;
	
	private int tableDataSetId;

	public GetTableDataMapperCommand(int tableDataSetId, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
	}

	@Override
	public TableDataMapper<?, ?> execute() {
		return PagingServerImpl.get().getTableData(tableDataSetId,getUserId()).getTableDataMapper();
	}

}
