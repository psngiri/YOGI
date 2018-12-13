package yogi.paging.command;

import yogi.paging.TableData;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class AdjustSavePointerCommand extends CommandAdapter<Integer> {
		
	private static final long serialVersionUID = -965089600674463069L;
	
	private int tableDataSetId;
	
	public AdjustSavePointerCommand(int tableDataSetId, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
	}

	@Override
	public Integer execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId, getUserId());
		return tableData.adjustSavePointer();		
	}

}
