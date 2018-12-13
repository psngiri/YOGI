package yogi.paging.command;

import yogi.base.app.Executor;
import yogi.remote.client.app.CommandAdapter;


public class ClearTablePagesCommand extends CommandAdapter<Boolean> {
		
	private static final long serialVersionUID = -965089600674463069L;
	
	private int[] tableDataSetIds;
	
	
	public ClearTablePagesCommand(int[] tableDataSetIds,String userId) {
		super(userId);
		this.tableDataSetIds = tableDataSetIds;
	}


	@Override
	public Boolean execute() {
		for(int tableDataSetId:tableDataSetIds){
			if(tableDataSetId<0) continue;
			Executor.get().execute(new ClearTablePageCommand(tableDataSetId,getUserId()));
		}
		return true;
	}

}
