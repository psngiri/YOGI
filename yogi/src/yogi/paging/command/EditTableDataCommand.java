package yogi.paging.command;

import yogi.paging.EditCriteria;
import yogi.paging.TableData;
import yogi.paging.TableResponse;
import yogi.paging.changes.Changes;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class EditTableDataCommand<T> extends CommandAdapter<TableResponse> {
	private static final long serialVersionUID = 6876747226277961954L;
	
	private int tableDataSetId;
	private Changes changes;
	private EditCriteria editCriteria;

	public EditTableDataCommand(int tableDataSetId, Changes changes, EditCriteria editCriteria, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.changes = changes;
		this.editCriteria = editCriteria;
	}

	@Override
	public TableResponse execute() {
		TableData<?> tableData = PagingServerImpl.get().getTableData(tableDataSetId,getUserId());
		if(changes != null && changes.isChangesExisting()) {
			tableData.applyChanges(changes);
		}
		Changes editChanges = tableData.edit(editCriteria);
		tableData.applyChanges(editChanges);
		return new TableResponse(tableData.getCurrentRowCount(), tableData.getTableDataSetId());
	}

}
