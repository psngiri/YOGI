package yogi.paging.launch.command;

import java.util.List;

import yogi.paging.command.PagingActionCommand;
import yogi.paging.launch.LaunchParameters;

public abstract class LaunchAppCommand extends PagingActionCommand<LaunchParameters> {

	private static final long serialVersionUID = 1L;

	public LaunchAppCommand(int tableDataSetId, List<Integer> rowViewIndices,String userId) {
		super(tableDataSetId, rowViewIndices,userId);
	}

}