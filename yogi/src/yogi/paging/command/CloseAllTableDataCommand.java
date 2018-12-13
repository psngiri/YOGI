package yogi.paging.command;

import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class CloseAllTableDataCommand extends CommandAdapter<Boolean> {

	public CloseAllTableDataCommand(String userId) {
		super(userId);
	}

	private static final long serialVersionUID = -6849748273507429406L;
	
	public Boolean execute() {
		return  PagingServerImpl.get().closeTableDatas();
	}
}