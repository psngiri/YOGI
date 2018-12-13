package yogi.paging.command;

import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class CloseTableDataCommand extends CommandAdapter<Boolean> {

	private static final long serialVersionUID = -6849748273507429406L;
	int tableDataSetId;

	public CloseTableDataCommand(int tableDataSetId, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
	}
	
	public Boolean execute() {
		return  PagingServerImpl.get().closeTableData(tableDataSetId,getUserId());
	}


}
