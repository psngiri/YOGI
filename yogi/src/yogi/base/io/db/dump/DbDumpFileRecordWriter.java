package yogi.base.io.db.dump;

import yogi.base.io.FileWriter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DbRecordWriter;

public class DbDumpFileRecordWriter extends FileWriter<DbRecord> implements DbRecordWriter
{

	public DbDumpFileRecordWriter(String fileName) {
		super(fileName);
		this.setFormatter(new DumpDbRecordFormatter());
	}

}