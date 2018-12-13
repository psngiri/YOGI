package yogi.base.io.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class ObjectDbRecord implements DbRecord{
	Object[] objects;

	public ObjectDbRecord(int count)
	{
		setObjects(new Object[count]);
	}
	
	public ObjectDbRecord(Object ... objects) {
		setObjects(objects);
	}
	
	public ObjectDbRecord(List<?> objects) {
		super();
		setObjects(objects.toArray());
	}
	
	public void clear()
	{
		for(int i = 0; i < objects.length; i++)
		{
			objects[i] = null;
		}
	}
	
	public void setObject(int index, Object object)
	{
		objects[index] = object;
	}
	
	protected void setObjects(Object[] objects) {
		this.objects = objects;
	}

	public String getString(int columnIndex) throws SQLException {
		return (String) objects[columnIndex-1];
	}

	public boolean getBoolean(int columnIndex) throws SQLException {
		return (Boolean) objects[columnIndex-1];
	}

	public byte getByte(int columnIndex) throws SQLException {
		return (Byte) objects[columnIndex-1];
	}

	public short getShort(int columnIndex) throws SQLException {
		return (Short) objects[columnIndex-1];
	}

	public int getInt(int columnIndex) throws SQLException {
		return (Integer) objects[columnIndex-1];
	}

	public long getLong(int columnIndex) throws SQLException {
		return (Long) objects[columnIndex-1];
	}

	public float getFloat(int columnIndex) throws SQLException {
		return (Float) objects[columnIndex-1];
	}

	public double getDouble(int columnIndex) throws SQLException {
		return (Double) objects[columnIndex-1];
	}

	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		return (BigDecimal) objects[columnIndex-1];
	}

	public byte[] getBytes(int columnIndex) throws SQLException {
		return (byte[]) objects[columnIndex-1];
	}

	public Date getDate(int columnIndex) throws SQLException {
		return (Date) objects[columnIndex-1];
	}

	public Time getTime(int columnIndex) throws SQLException {
		return (Time) objects[columnIndex-1];
	}

	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		return (Timestamp) objects[columnIndex-1];
	}

	public Object getObject(int columnIndex) throws SQLException {
		return objects[columnIndex-1];
	}

	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		return (BigDecimal) objects[columnIndex-1];
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
		return objects.length;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean rtnValue = super.equals(obj);
		if(rtnValue) return rtnValue;
		Object[] myObjects = ((ObjectDbRecord)obj).objects;
		if(objects.length != myObjects.length) return false;
		for(int i = 0; i < objects.length; i++){
			if(objects[i]==null && myObjects[i]==null) continue;
			else if(objects[i]==null && myObjects[i]!=null) return false;
			else if(!objects[i].equals(myObjects[i])) return false;
		}
		return true;
	}
	
	public boolean match(Object obj, int... columnsToIgnore) {
		if(columnsToIgnore.length==0) return equals(obj);
		boolean rtnValue = super.equals(obj);
		if(rtnValue) return rtnValue;
		Object[] myObjects = ((ObjectDbRecord)obj).objects;
		if(objects.length != myObjects.length) return false;
		Arrays.sort(columnsToIgnore);
		for(int i = 0; i < objects.length; i++){
			if(Arrays.binarySearch(columnsToIgnore, i)>=0) continue;
			if(objects[i]==null && myObjects[i]==null) continue;
			else if(objects[i]==null && myObjects[i]!=null) return false;
			else if(!objects[i].equals(myObjects[i])) return false;
		}
		return true;
	}
	
}
