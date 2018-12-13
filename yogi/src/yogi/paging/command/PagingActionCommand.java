package yogi.paging.command;

import java.util.List;

import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;

public abstract class PagingActionCommand<R> extends BasePagingActionCommand<R> {

	private static final long serialVersionUID = -2878817163271858279L;

	public PagingActionCommand(int tableDataSetId, List<Integer> rowViewIndices, String userId) {
		super(tableDataSetId, rowViewIndices, userId);
	}
	
	/**
	 * @param columnNames
	 * @return rows with Column Values(only return columns that match the columnNames passed in the Param)
	 */
	protected List<String[]> getColumnValues(List<String> columnNames) {
		List<Integer> rowViewIndices = getRowViewIndices();
		return getSelectedColumnValues(columnNames, rowViewIndices);			
	}

	protected List<String[]> getSelectedColumnValues(List<String> columnNames, List<Integer> rowViewIndices) {
		GetSelectionCommand selectionCommand = new GetSelectionCommand(getTableDataSetId(), rowViewIndices, columnNames, getUserId());
		try {
			return MultiServerCommandExecutor.get().execute(PAGING_SERVER_NAME, selectionCommand);
		} catch (CommandException e) {
			throw new RuntimeException("Exception occured while trying to get column details for selected row");			
		}
	}
		
	protected List<String[]> getColumnValuesForAllRows(List<String> columnNames) {
		return getSelectedColumnValues(columnNames, null);
	}
	
}