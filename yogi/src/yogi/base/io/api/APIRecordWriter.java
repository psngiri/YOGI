package yogi.base.io.api;

import yogi.base.io.db.DbRecord;


public interface APIRecordWriter {
	boolean open();
	boolean close();
	boolean write(DbRecord record);
	void setAppend(boolean append);
}
