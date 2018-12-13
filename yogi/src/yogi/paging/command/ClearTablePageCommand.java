package yogi.paging.command;

import yogi.paging.TableData;
import yogi.paging.TableResponse;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class ClearTablePageCommand extends CommandAdapter<TableResponse> {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -965089600674463069L;
	
	private int tableDataSetId;
	
	public ClearTablePageCommand(int tableDataSetId,String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
	}

	@Override
	public TableResponse execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		tableData.clear();
		return new TableResponse(tableData.getCurrentRowCount(), tableData.getTableDataSetId());
	}

}
