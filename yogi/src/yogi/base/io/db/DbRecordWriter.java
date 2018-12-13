package yogi.base.io.db;


public interface DbRecordWriter {
	boolean open();
	boolean close();
	boolean write(DbRecord record);
	void setAppend(boolean append);
}
