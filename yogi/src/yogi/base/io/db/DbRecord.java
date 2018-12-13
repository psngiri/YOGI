package yogi.base.io.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public interface DbRecord{
	String getString(int columnIndex) throws SQLException;
	boolean getBoolean(int columnIndex) throws SQLException;
	byte getByte(int columnIndex) throws SQLException;
	short getShort(int columnIndex) throws SQLException;
	int getInt(int columnIndex) throws SQLException;
	long getLong(int columnIndex) throws SQLException;
	float getFloat(int columnIndex) throws SQLException;
	double getDouble(int columnIndex) throws SQLException;
	BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException;
	byte[] getBytes(int columnIndex) throws SQLException;
	Date getDate(int columnIndex) throws SQLException;
	Time getTime(int columnIndex) throws SQLException;
	Timestamp getTimestamp(int columnIndex) throws SQLException;
	Object getObject(int columnIndex) throws SQLException;
	BigDecimal getBigDecimal(int columnIndex) throws SQLException;
	int getColumnCount() throws SQLException;
}
