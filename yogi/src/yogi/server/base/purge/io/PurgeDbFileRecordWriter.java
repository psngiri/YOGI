package yogi.server.base.purge.io;

import java.util.ArrayList;
import java.util.List;

import yogi.base.io.FileWriter;
import yogi.base.io.Formatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DbRecordWriter;

public class PurgeDbFileRecordWriter extends FileWriter<DbRecord> implements DbRecordWriter
{

	public PurgeDbFileRecordWriter(String fileName, String header) {
		super(fileName);
		this.setFormatter(new MyFormatter());
		List<String> headers = new ArrayList<String>(1);
		headers.add(header);
		this.setHeader(headers);
	}

	static class MyFormatter implements Formatter<DbRecord>{
		public String format(DbRecord record) {
			return record.toString();
		}
	}
}