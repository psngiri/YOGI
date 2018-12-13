package yogi.paging.command;

import java.util.List;

import yogi.remote.client.app.CommandAdapter;

public abstract class BasePagingActionCommand<R> extends CommandAdapter<R> {

	private static final long serialVersionUID = -2878817163271858279L;

	public static final String PAGING_SERVER_NAME 	= "Paging"; 

	private int tableDataSetId;
	private List<Integer> rowViewIndices;
	private String loginUserId;	
		
	public BasePagingActionCommand(int tableDataSetId, List<Integer> rowViewIndices, String userId) {
		super(userId);
		this.tableDataSetId = tableDataSetId;
		this.rowViewIndices = rowViewIndices;
	}

	public BasePagingActionCommand(int tableDataSetId, List<Integer> rowViewIndices, String userId, String loginUserId) {
		super(userId);
		this.loginUserId = loginUserId;
		this.tableDataSetId = tableDataSetId;
		this.rowViewIndices = rowViewIndices;
	}
	
	public int getTableDataSetId() {
		return tableDataSetId;
	}

	public List<Integer> getRowViewIndices() {
		return rowViewIndices;
	}
	
	protected String getLoginUserId() {
		return loginUserId;
	}
	
}