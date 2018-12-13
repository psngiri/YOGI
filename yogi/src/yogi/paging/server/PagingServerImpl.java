package yogi.paging.server;

import java.util.List;

import yogi.base.util.store.Element;
import yogi.base.util.store.secure.SecureListStore;
import yogi.paging.TableData;
import yogi.paging.TableDataMapper;

public class PagingServerImpl implements PagingServer {
	private static PagingServerImpl itsInstance = new PagingServerImpl(); 
	private SecureListStore<TableData<?>, String> tableDataSets = new SecureListStore<TableData<?>, String>();
	
	public static PagingServer get()
	{
		return itsInstance;
	}
	
	private synchronized <T> Integer createTableData(List<T> data, TableDataMapper<T, ?> mapper, int tableDataSetId, String userId) {
		if(tableDataSetId == -1){
			tableDataSetId = tableDataSets.generateKey();
		}
		try {
			tableDataSets.set(tableDataSetId, new TableData<T>(data, mapper, tableDataSetId), userId);
		} catch (Exception e) {
			if(tableDataSetId != -1){
				tableDataSetId = createTableData(data, mapper, -1, userId);
			} else {
				throw new RuntimeException(e);
			}
		}
		return tableDataSetId;
	}
	
	@Override
	public <T, I> Integer submitData(List<T> data, TableDataMapper<T, I> mapper, int tableDataSetId, String userId) {
		return createTableData(data, mapper, tableDataSetId, userId);
	}
		
	public TableData<?> getTableData(int tableDataSetId, String userId) {
		Element<Integer, TableData<?>> element = tableDataSets.get(tableDataSetId, userId);
		if(element == null) throw new RuntimeException(String.format("DataSet %s removed from cache rerun", tableDataSetId));
		return element.getValue();
	}
	@Override
	public boolean closeTableData(int tableDataSetId, String userId) {
		try{
			tableDataSets.set(tableDataSetId, null, userId);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean closeTableDatas() {
		try{
			tableDataSets.clear();
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	
}
