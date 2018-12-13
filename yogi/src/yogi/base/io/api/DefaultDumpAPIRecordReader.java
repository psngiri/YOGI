package yogi.base.io.api;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Logger;

import yogi.base.app.ApplicationProperties;
import yogi.base.io.RecordReader;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.dump.DumpDbFileRecordReader;
import yogi.base.io.db.dump.DumpDbFileWriter;
import yogi.base.io.db.dump.DumpProperties;
import yogi.base.io.resource.Resource;
import yogi.base.util.logging.Logging;

public class DefaultDumpAPIRecordReader<T> implements RecordReader<DbRecord> {
	private static Logger logger = Logging.getLogger(DefaultDumpAPIRecordReader.class);

	private RecordReader<DbRecord> recordReader;
	private DefaultAPIRecordReader<T> reader;
	private APIResource resource;
	private static HashSet<String> exceptionReaders = new HashSet<String>();
	
	public static void setExceptionReaders(String value)
	{
		Scanner scanner = new Scanner(value);
		scanner.useDelimiter(",");
		exceptionReaders.clear();
		while(scanner.hasNext())
		{
			exceptionReaders.add(scanner.next().trim());
		}
	}
	
	private boolean isValidExceptionReader(String dumpFileName)
	{
		String name = reader.getClass().getName();
		if(exceptionReaders.contains(name))
		{
			File file = new File(dumpFileName);
			if(file.exists())
			{
				logger.info("Reader Exception reading data from : " + dumpFileName);
				return true;
			}else
			{
				logger.warning("Reader Exception could not read data from : " + dumpFileName);
			}
		}
		return false;
	}
	
	public DefaultDumpAPIRecordReader(APIResource resource, DefaultAPIRecordReader<T> reader) {
		super();
		this.reader = reader;
		this.resource = resource;
	}
	private String getDumpFileName() {
		return ApplicationProperties.InputDataLocation + DumpProperties.getDumpFileName(reader.getClass());
	}

	private RecordReader<DbRecord> getRecordReader() {
		return recordReader;
	}

	public boolean open() {
		String dumpFileName = getDumpFileName();
		if(DumpProperties.ReadFromDbDump || isValidExceptionReader(dumpFileName))
		{
			recordReader = new DumpDbFileRecordReader(dumpFileName);
		}else
		{
			recordReader = new MyDumpDbRecordReader(resource, reader, dumpFileName);
		}
		return getRecordReader().open();
	}

	public boolean close() {
		return getRecordReader().close();
	}

	public boolean hasNext() {
		return getRecordReader().hasNext();
	}

	public DbRecord next() {
		return getRecordReader().next();
	}

	public Resource getResource() {
		return getRecordReader().getResource();
	}

	class MyDumpDbRecordReader extends APIRecordReader
	{
		private DumpDbFileWriter dumpWriter;
		public MyDumpDbRecordReader(APIResource resource, DefaultAPIRecordReader<T> reader, String dumpFileName) {
			super(resource);
			if(DumpProperties.WriteToDbDump)
			{
				dumpWriter = new DumpDbFileWriter(dumpFileName);
			}
		}

		public boolean close() {
			if(getDumpWriter() != null) getDumpWriter().close();
			return super.close();
		}

		private DumpDbFileWriter getDumpWriter() {
			return dumpWriter;
		}
		@Override
		public boolean hasNext() {
			return super.hasNext();
		}
		@Override
		public DbRecord next() {
			return super.next();
		}
		@Override
		public boolean open() {
			if(getDumpWriter() != null) getDumpWriter().open();
			return super.open();
		}

		@Override
		protected void logDbRecord(DbRecord myRecord) {
			super.logDbRecord(myRecord);
			if(getDumpWriter() != null) getDumpWriter().write(myRecord);
		}
		
	}
}
