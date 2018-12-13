package yogi.paging.command;

import yogi.paging.TablePage;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class GetTablePageCommand<T> extends CommandAdapter<TablePage> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -56257700308878946L;
	private int tableDataSetId;
	private int startRow;
	private int endRow;

	public GetTablePageCommand(int tableDataSetId, int startRow, int endRow, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.startRow = startRow;
		this.endRow = endRow;
	}
	
	@Override
	public TablePage execute() {
		return PagingServerImpl.get().getTableData(tableDataSetId,getUserId()).getTablePage(startRow, endRow);
	}

}
