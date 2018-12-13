package yogi.paging.server;

import java.util.List;

import yogi.paging.TableData;
import yogi.paging.TableDataMapper;

public interface PagingServer {
	<T, I> Integer submitData(List<T> data, TableDataMapper<T, I> mapper, int tableDataSetId, String userId);
	TableData<?> getTableData(int tableDataSetId, String userId);
	boolean closeTableData(int tableDataSetId, String userId);
	boolean closeTableDatas();
}
