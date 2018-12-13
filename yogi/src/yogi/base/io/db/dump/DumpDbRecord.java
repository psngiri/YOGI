package yogi.base.io.db.dump;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import yogi.base.io.db.ObjectDbRecord;
import yogi.period.date.DateAssistant;
import yogi.property.MyBooleanParser;

public class DumpDbRecord extends ObjectDbRecord{
	public DumpDbRecord(String record) {
		super();
		Scanner scanner = new Scanner(record);
		scanner.useDelimiter(DumpProperties.ColumnSeparator);
		List<String> objects = new ArrayList<String>();
		while(scanner.hasNext())
		{
			String value = scanner.next();
			if(value.equals("null")) value = null;
			objects.add(value);
		}
		setObjects(objects.toArray());
	}

	public DumpDbRecord(Object[] objects) {
		super(objects);
	}

	public boolean getBoolean(int columnIndex) throws SQLException {
		String item = getString(columnIndex);
		if(item == null) return false;
		return MyBooleanParser.valueOf(item);
	}

	public byte getByte(int columnIndex) throws SQLException {
		String item = getString(columnIndex);
		if(item == null) return 0;
		return Byte.parseByte(item);
	}

	public short getShort(int columnIndex) throws SQLException {
		String item = getString(columnIndex);
		if(item == null) return 0;
		return Short.parseShort(item);
	}

	public int getInt(int columnIndex) throws SQLException {
		String item = getString(columnIndex);
		if(item == null) return 0;
		return Integer.parseInt(item);
	}

	public long getLong(int columnIndex) throws SQLException {
		String item = getString(columnIndex);
		if(item == null) return 0;
		return Long.parseLong(item);
	}

	public float getFloat(int columnIndex) throws SQLException {
		String item = getString(columnIndex);
		if(item == null) return 0;
		return Float.parseFloat(item);
	}

	public double getDouble(int columnIndex) throws SQLException {
		String item = getString(columnIndex);
		if(item == null) return 0;
		return Double.parseDouble(item);
	}

	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		String item = getString(columnIndex);
		if(item == null) return null;
		return new BigDecimal(new BigInteger(item), scale);
	}

	public byte[] getBytes(int columnIndex) throws SQLException {
		throw new RuntimeException("Not yet implemented");
	}

	public Date getDate(int columnIndex) throws SQLException {	
		return getSqlDate(DumpProperties.dateScanner.scan(getString(columnIndex)).create());
	}

	public Time getTime(int columnIndex) throws SQLException {
		throw new RuntimeException("Not yet implemented");
	}

	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		return new Timestamp(Long.valueOf(getString(columnIndex)));
	}

	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		throw new RuntimeException("Not yet implemented");
	}

	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		throw new RuntimeException("Not yet implemented");
	}

	public Reader getCharacterStream(int columnIndex) throws SQLException {
		throw new RuntimeException("Not yet implemented");
	}

	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		String item = getString(columnIndex);
		if(item == null) item = "0";
		return new BigDecimal(item);
	}
	
	private Date getSqlDate(yogi.period.date.Date date){
		Calendar calendar = DateAssistant.get().getCalendar(date);
		int year = calendar.get(Calendar.YEAR);
		String month = (calendar.get(Calendar.MONTH)+1)+"";
		if(month.length() == 1)
			month = "0"+month;
		String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
		if(day.length() == 1)
			day = "0"+day;
		return Date.valueOf(year+"-"+month+"-"+day);
		
	}
}
