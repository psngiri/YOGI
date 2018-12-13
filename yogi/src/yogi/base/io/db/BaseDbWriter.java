package yogi.base.io.db;

import java.util.Collection;
import java.util.logging.Logger;

import yogi.base.app.ApplicationProperties;
import yogi.base.io.db.dump.DbDumpFileRecordWriter;
import yogi.base.io.db.dump.DumpProperties;
import yogi.base.io.resource.Resource;
import yogi.base.io.resource.db.DbResource;
import yogi.base.util.logging.Logging;


public abstract class BaseDbWriter<T, F extends BaseDbFormatter> implements yogi.base.io.ObjectWriter<T> {
	private static Logger logger = Logging.getLogger(BaseDbWriter.class);
	private F formatter;
	private DbResource resource;
	private int numberOfRecordsWriten;
	private DbRecordWriter dbRecordWriter;
	private boolean append = false;
	private String dumpFileName;
	
	public BaseDbWriter(DbResource resource, F formatter, String dumpFileName) {
		super();
		this.formatter = formatter;
		this.resource = resource;
		this.setDumpFileName(dumpFileName);
	}
	
	public BaseDbWriter(DbResource resource, F formatter) {
		super();
		this.formatter = formatter;
		this.resource = resource;
	}

	public void setFormatter(F formatter) {
		this.formatter = formatter;
	}

	public F getFormatter() {
		if(formatter == null) throw new RuntimeException("No DbFormatter Set, please set the DbFormatter.");
		return formatter;
	}

	public int getNumberOfRecordsWriten() {
		return numberOfRecordsWriten;
	}

	public void write(Collection<T> objects) {
		for (T object : objects) {
			try {
				write(object);
			} catch (Exception e) {
				throw new RuntimeException(String.format("Could not write %1$s: %2$s to %$3s", object.getClass().getSimpleName(), object, resource.getName()), e);
			}
		}
	}

	protected void execute(T object, DbRecord record) {
		if(!dbRecordWriter.write(record)) 
		{
			throw new RuntimeException(String.format("Could not write %1$s: %2$s to %$3s", object.getClass().getSimpleName(), object, resource.getName()));
		}
		numberOfRecordsWriten++;
	}


	public boolean close() {
		boolean rtnValue = dbRecordWriter.close();
		dbRecordWriter = null;
		logger.info(String.format("Written %1$s records to %2$s using %3$s", numberOfRecordsWriten, resource.getName(), formatter.getClass().getSimpleName()));
		numberOfRecordsWriten = 0;
		return rtnValue;
	}

	public Resource getResource() {
		return resource;
	}

	public void setAppend(boolean append) {
		this.append = append;
		if(dbRecordWriter != null) dbRecordWriter.setAppend(append);
	}

	protected void setDbRecordWriter(DbRecordWriter dbRecordWriter) {
		this.dbRecordWriter = dbRecordWriter;
	}

	public String getDumpFileName() {
		return dumpFileName;
	}

	private void setDumpFileName(String dumpFileName) {
		this.dumpFileName = dumpFileName;
		if(dumpFileName != null){
			this.setAppend(true);
		}
	}

	public boolean open() {
		if(dbRecordWriter ==null){
			if(!DumpProperties.LoadToDb)
			{
				String myDumpFileName = dumpFileName;
				if(dumpFileName == null || !DumpProperties.AppendWriterDumpToCorrespondingReaderDump){
					myDumpFileName = ApplicationProperties.OutputLocation + DumpProperties.getDumpFileName(formatter.getClass());
				}
				dbRecordWriter = new DbDumpFileRecordWriter(myDumpFileName);
			}else
			{
				dbRecordWriter = new DbRecordWriterImpl(logger, resource, formatter);
			}
		}
		dbRecordWriter.setAppend(append);
		return dbRecordWriter.open();
	}

}

