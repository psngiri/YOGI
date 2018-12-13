package yogi.base.io.db.dump;

import java.sql.SQLException;

import yogi.base.io.Formatter;
import yogi.base.io.db.DbRecord;

public class DumpDbRecordFormatter implements Formatter<DbRecord> {
	private static final String NULL = "";
	private int columnCount = -1;
	private String columnSeparator;


	public String getColumnSeparator() {
		if(columnSeparator == null){
			columnSeparator = DumpProperties.ColumnSeparator;
			columnSeparator = columnSeparator.replace("\\", NULL);
		}
		return columnSeparator;
	}

	public String format(DbRecord dbRecord) {
		int numberOfColumns = getColumnCount(dbRecord);
		if(numberOfColumns == 0) return NULL;
		String myColumnSeparator = getColumnSeparator();
		StringBuilder sb = new StringBuilder();
		try {
			for(int i = 1; i < numberOfColumns; i ++)
			{
				sb.append(getObject(dbRecord, i)).append(myColumnSeparator);
			}
			sb.append(getObject(dbRecord, numberOfColumns));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	protected Object getObject(DbRecord dbRecord, int i) throws SQLException {
		Object rtnVal = dbRecord.getObject(i);
		if(rtnVal.equals(NULL)||rtnVal == null) rtnVal = "null";
		return rtnVal;
	}

	private int getColumnCount(DbRecord dbRecord) {
		if(columnCount == -1)
		{
			try {
				columnCount = dbRecord.getColumnCount();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return columnCount;
	}

}
