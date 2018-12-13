package yogi.server.base.purge.io;

import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbWriter;
import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.Purgeable;

public class PurgeDbFileWriter<T extends Purgeable> extends DbWriter<T> {
	
	private boolean writeToDbFile;
	private String fileName;
	private String header;

	public PurgeDbFileWriter(DbResource resource, DbFormatter<T> formatter,Class<?> klass, String PurgeDeleteDirectoryPath, boolean writeToDbFile) {
		super(resource, formatter);
		this.writeToDbFile=writeToDbFile;
		if(writeToDbFile){
			fileName = PurgeDeleteDirectoryPath + klass.getSimpleName()+ ".purge.dat";
			header = formatter.query();
			if (formatter.cleanUpQuery() != null) header = header + ";" + formatter.cleanUpQuery();
		}
	}

	@Override
	public boolean open() {
		if(writeToDbFile){
			PurgeDbFileRecordWriter dbRecordWriter = new PurgeDbFileRecordWriter(fileName, header);
			setDbRecordWriter(dbRecordWriter);
		}
		return super.open();
		
	}
	
}
