package yogi.base.io.api;

import java.util.Collection;
import java.util.logging.Logger;

import yogi.base.app.ApplicationProperties;
import yogi.base.io.db.BaseDbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DbRecordWriter;
import yogi.base.io.db.dump.DbDumpFileRecordWriter;
import yogi.base.io.db.dump.DumpProperties;
import yogi.base.io.resource.Resource;
import yogi.base.util.logging.Logging;


public abstract class BaseAPIWriter<T, F extends BaseDbFormatter> implements yogi.base.io.ObjectWriter<T> {
	private static Logger logger = Logging.getLogger(BaseAPIWriter.class);
	private F formatter;
	private APIResource resource;
	private int numberOfRecordsWriten;
	private DbRecordWriter dbRecordWriter;
	private boolean append = false;
	
	public BaseAPIWriter(APIResource resource, F formatter) {
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
			write(object);
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
		logger.info(String.format("Written %1$s records from %2$s", numberOfRecordsWriten, resource.getName()));
		return rtnValue;
	}

	public Resource getResource() {
		return resource;
	}

	public void setAppend(boolean append) {
		this.append = append;
		if(dbRecordWriter != null) dbRecordWriter.setAppend(append);
	}

	public boolean open() {
		if(!DumpProperties.LoadToDb)
		{
			dbRecordWriter = new DbDumpFileRecordWriter(ApplicationProperties.OutputLocation + DumpProperties.getDumpFileName(formatter.getClass()));
		}else
		{
			dbRecordWriter = new APIRecordWriterImpl(logger, resource);
		}
		dbRecordWriter.setAppend(append);
		return dbRecordWriter.open();
	}

}

