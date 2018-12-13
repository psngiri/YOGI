package yogi.base.util.loader;

import java.util.List;

import yogi.base.io.FileRecordReader;
import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;

public 	class QueryDataLoaderDbFormatter implements DbFormatter<String> {
	
	private String query;
	private String cleanUpQuery;
	private ObjectDbRecord dbRecord;
	private FileRecordReader fileRecordReader;

	public QueryDataLoaderDbFormatter(FileRecordReader fileRecordReader) {
		super();
		this.fileRecordReader = fileRecordReader;
	}

	public QueryDataLoaderDbFormatter(String query, String cleanUpQuery){
		super();
		this.query = query;
		this.cleanUpQuery = cleanUpQuery;
	}
	
	public String query() {		
		if(query == null){
			List<String> header = fileRecordReader.getHeader();
			String headerString = header.get(0);
			String[] split = headerString.split(";");
			query = split[0];
			if(split.length > 1){
				cleanUpQuery = split[1];
			}
		}

		return query;
	}

	public String cleanUpQuery() {
 		return cleanUpQuery;	
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setCleanUpQuery(String cleanUpQuery) {
		this.cleanUpQuery = cleanUpQuery;
	}

	public void setDbRecord(ObjectDbRecord dbRecord) {
		this.dbRecord = dbRecord;
	}

	@Override
	public DbRecord format(String record) {
		String[] split = record.split(",");
		if(dbRecord == null) dbRecord = new ObjectDbRecord(split.length);
		dbRecord.clear();
		for(int i = 0; i < split.length; i ++){
			dbRecord.setObject(i, split[i]);
		}
		return dbRecord;
	}
	
}