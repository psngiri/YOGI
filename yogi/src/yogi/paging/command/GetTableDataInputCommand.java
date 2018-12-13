package yogi.paging.command;

import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;

public class GetTableDataInputCommand<I> extends CommandAdapter<I> {	

	private static final long serialVersionUID = -1442554915129503158L;
	
	private int tableDataSetId;

	public GetTableDataInputCommand(int tableDataSetId, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public I execute() {
		return (I) PagingServerImpl.get().getTableData(tableDataSetId,getUserId()).getTableDataMapper().getInput();
	}

}
