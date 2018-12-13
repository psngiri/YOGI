package yogi.paging.command;

import java.util.List;

import yogi.paging.TableDataMapper;
import yogi.paging.server.PagingServerImpl;
import yogi.remote.client.app.CommandAdapter;


public class SubmitTableDataCommand<T> extends CommandAdapter<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -56257700308878946L;
	private List<T> data;
	private TableDataMapper<T, ?> mapper;
	private int tableDataSetId = -1;

	public SubmitTableDataCommand(List<T> data, TableDataMapper<T, ?> mapper, int tableDataSetId, String userId) {
		super(userId);
		this.data = data;
		this.mapper = mapper;
		this.tableDataSetId = tableDataSetId;
	}


	@Override
	public Integer execute() {
		return PagingServerImpl.get().submitData(data, mapper, tableDataSetId,getUserId());
	}

}
