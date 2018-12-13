package yogi.paging.command;

import yogi.paging.TableDataMapper;
import yogi.paging.changes.ChangeRecord;

public interface OutputDataMapper<T> {
	TableDataMapper<T, ?> getTableDataMapper();
	T populateOutputData(ChangeRecord changeRecord);
}
