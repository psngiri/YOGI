package yogi.paging.command;

import java.util.ArrayList;
import java.util.List;

import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;

public abstract class PagingCellActionCommand<R> extends PagingActionCommand<R> {
	
	private static final long serialVersionUID = -5127671504940681953L;

	private int columnIndex;
	
	public PagingCellActionCommand(int tableDataSetId, List<Integer> rowViewIndices, Integer columnIndex, String userId) {
		super(tableDataSetId, rowViewIndices, userId);
	}
	
	/**
	 * @return selected cell value
	 */
	protected String getSelectedCellValue() {
		String rtnVal = null;
		List<Integer> columnIndices = new ArrayList<Integer>();
		columnIndices.add(this.getColumnIndex());
		GetSelectionCommand selectionCommand = new GetSelectionCommand(getTableDataSetId(), getRowViewIndices(), getUserId(), columnIndices);
		List<String[]> selectedValues;
		try {
			selectedValues = MultiServerCommandExecutor.get().execute(PAGING_SERVER_NAME, selectionCommand);
		} catch (CommandException e) {
			throw new RuntimeException("Exception occured while trying to get column details for selected row");			
		}
		if(selectedValues.size() == 1) {
			String[] selectedRow = selectedValues.get(0);
			rtnVal = selectedRow[0];
		}
		return rtnVal;
	}

	public Integer getColumnIndex() {
		return this.columnIndex;
	}
		
}