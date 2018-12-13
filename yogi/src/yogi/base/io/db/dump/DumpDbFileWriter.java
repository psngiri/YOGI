package yogi.base.io.db.dump;

import yogi.base.io.FileWriter;
import yogi.base.io.db.DbRecord;

public class DumpDbFileWriter extends FileWriter<DbRecord> {

	public DumpDbFileWriter(String fileName) {
		super(fileName, new DumpDbRecordFormatter());
	}

}
