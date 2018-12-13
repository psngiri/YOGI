package yogi.server.gui.report.converter;

import java.sql.SQLException;
import java.util.logging.Logger;

import yogi.base.io.db.DbFormatterAdapter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.base.util.logging.Logging;
import yogi.server.gui.record.converter.Converter;

public 	class ReportConverterDbFormatter extends DbFormatterAdapter<DbRecord> {
	
	public static String TableName = "SetTableNameProperty";
	
	private static Converter converter = null;
	
	private static Logger logger = Logging.getLogger(ReportConverterDbFormatter.class);
	
	///   yogi.server.gui.report.io.db.converter.ReportQueriesConverter
	public static void setConverters(String value) {
		try {
			Class<?> c = Class.forName(value);
			converter = (Converter) c.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public ReportConverterDbFormatter() {
		super(5);
	}

	protected static Converter getConverter() {
		return converter;
	}
	
	public String query() {		
		QueryReader queryReader = new QueryReader(new ClassPathResource("updateQuery.txt", this.getClass()));
		queryReader.addVariable("TableName",TableName);
		return queryReader.read();
	}

	public String cleanUpQuery() {
		return null;
	}
	
	@Override
	public DbRecord format(DbRecord object) {
		logger.info("Record read from DB : " + object);
		ObjectDbRecord objectDbRecord = getObjectDbRecord();
		try {
			objectDbRecord.setObject(1, object.getObject(1));
			objectDbRecord.setObject(2, object.getObject(2));
			objectDbRecord.setObject(3, object.getObject(3));
			objectDbRecord.setObject(4, object.getObject(4));			
			if(converter == null) return null;
			objectDbRecord.setObject(0, converter.convert(object.getString(5)));			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		logger.info("Record to write into DB : " + objectDbRecord);
		return objectDbRecord;
	}

}