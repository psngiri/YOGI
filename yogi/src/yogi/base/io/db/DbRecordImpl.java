package yogi.base.io.db;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class DbRecordImpl implements DbRecord{
	private ResultSet resultSet;

	protected DbRecordImpl(ResultSet resultSet) {
		super();
		this.resultSet = resultSet;
	}

	public String getString(int columnIndex) throws SQLException {
		return resultSet.getString(columnIndex);
	}

	public boolean getBoolean(int columnIndex) throws SQLException {
		return resultSet.getBoolean(columnIndex);
	}

	public byte getByte(int columnIndex) throws SQLException {
		return resultSet.getByte(columnIndex);
	}

	public short getShort(int columnIndex) throws SQLException {
		return resultSet.getShort(columnIndex);
	}

	public int getInt(int columnIndex) throws SQLException {
		return resultSet.getInt(columnIndex);
	}

	public long getLong(int columnIndex) throws SQLException {
		return resultSet.getLong(columnIndex);
	}

	public float getFloat(int columnIndex) throws SQLException {
		return resultSet.getFloat(columnIndex);
	}

	public double getDouble(int columnIndex) throws SQLException {
		return resultSet.getDouble(columnIndex);
	}

	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		return resultSet.getBigDecimal(columnIndex);
	}

	public byte[] getBytes(int columnIndex) throws SQLException {
		return resultSet.getBytes(columnIndex);
	}

	public Date getDate(int columnIndex) throws SQLException {
		return resultSet.getDate(columnIndex);
	}

	public Time getTime(int columnIndex) throws SQLException {
		return resultSet.getTime(columnIndex);
	}

	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		return resultSet.getTimestamp(columnIndex);
	}

	public Object getObject(int columnIndex) throws SQLException {
		Object object = resultSet.getObject(columnIndex);
		if (object instanceof Clob) {
			return getString(columnIndex);
		}
		return object;
	}

	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		return resultSet.getBigDecimal(columnIndex);
	}

	@Override
	public String toString() {
		try {
			StringBuilder sb = new StringBuilder();
			int columnCount = getColumnCount();
			for(int i = 1; i <= columnCount; i ++)
			{
				sb.append(getObject(i));
				sb.append(',');
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
			return sb.toString();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int getColumnCount() throws SQLException {
		return resultSet.getMetaData().getColumnCount();
	}
}
