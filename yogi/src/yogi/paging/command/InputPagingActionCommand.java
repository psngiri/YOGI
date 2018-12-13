package yogi.paging.command;

import java.util.List;

import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;

public abstract class InputPagingActionCommand<T, I> extends PagingActionCommand<T> {

	private static final long serialVersionUID = -2878817163271858279L;
		
	public InputPagingActionCommand(int tableDataSetId, List<Integer> rowViewIndices,String userId) {
		super(tableDataSetId, rowViewIndices, userId);
	}

	/**
	 * @param TableDataSetId, UserId
	 * @return Query
	 */
	protected I getInput() {
		GetTableDataInputCommand<I> tableDataInputCommand = new GetTableDataInputCommand<I>(getTableDataSetId(), getUserId());
		try {
			return MultiServerCommandExecutor.get().execute(PAGING_SERVER_NAME, tableDataInputCommand);
		} catch (CommandException e) {
			throw new RuntimeException("Exception occured while trying to get Input Query details");			
		}			
	}
	
}