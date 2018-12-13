package yogi.paging.command;

import java.util.List;

import yogi.paging.TableData;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;

public class AccumulateToTableDataCommand<T> extends CommandAdapter<Boolean> {
	private static final long serialVersionUID = -4494692505961957184L;

	private List<T> data;
	private int tableDataSetId = -1;

	public AccumulateToTableDataCommand(List<T> data, int tableDataSetId, String userId) {
		super(userId);
		this.data = data;
		this.tableDataSetId = tableDataSetId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean execute() {
		boolean accumulated = false;
		TableData<T> tableData = (TableData<T>) PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(!data.isEmpty()) {
			Changes changes = tableData.convertToChanges(data);
			tableData.applyChanges(changes);
			accumulated = true;
		}
		return accumulated;
	}

}
