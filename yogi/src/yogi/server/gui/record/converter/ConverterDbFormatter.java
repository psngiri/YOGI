package yogi.server.gui.record.converter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import yogi.base.io.db.DbFormatterAdapter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.base.util.logging.Logging;

public 	class ConverterDbFormatter extends DbFormatterAdapter<DbRecord> {
	
	public static String TableName = "SetTableNameProperty";
	public static String Types = "";
	
	private static Map<String, Converter> converters = new HashMap<String, Converter>();
	
	private static Logger logger = Logging.getLogger(ConverterDbFormatter.class);
	
	///   SCOPE:com.ngp.scope.ScopeConverter,ACTION:com.ngp.action.actionConverter
	public static void setConverters(String value)
	{
		converters.clear();
		Types = "";
		String[] splits = value.split(",");
		for(String split: splits)
		{
			String[] splits2 = split.split(":");
			if(splits2.length < 2) continue;
			try {
				Class<?> c = Class.forName(splits2[1]);
				Converter converter = (Converter) c.newInstance();
				converters.put(splits2[0], converter);
				if(!Types.isEmpty())Types = Types+",";
				Types = Types + "'" + splits2[0] + "'";
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public ConverterDbFormatter() {
		super(6);
	}

	protected static Map<String, Converter> getConverters() {
		return converters;
	}
	public String query() {		
		QueryReader queryReader = new QueryReader(new ClassPathResource("updateQuery.txt", this.getClass()));
		queryReader.addVariable("TableName",TableName);
		return queryReader.read();
	}

	public String cleanUpQuery() {
		return null;
	}

	public void putConverter(String type, Converter converter) {
		converters.put(type, converter);
	}
	
	@Override
	public DbRecord format(DbRecord object) {
		logger.info("Record read from DB : " + object);
		ObjectDbRecord objectDbRecord = getObjectDbRecord();
		try {
			objectDbRecord.setObject(1, object.getObject(1));
			objectDbRecord.setObject(2, object.getObject(2));
			objectDbRecord.setObject(3, object.getObject(3));
			
			String type = object.getString(4);
			objectDbRecord.setObject(4, type);
			objectDbRecord.setObject(5, object.getObject(5));

			Converter myConverter = converters.get(type);
			if(myConverter == null) return null;
			objectDbRecord.setObject(0, myConverter.convert(object.getString(6)));			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		logger.info("Record to write into DB : " + objectDbRecord);
		return objectDbRecord;
	}

}