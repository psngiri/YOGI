package yogi.report.condition.timestamp;

import java.util.HashSet;
import java.util.List;

import yogi.base.io.Formatter;
import yogi.base.util.range.Range;
import yogi.period.date.io.TimestampScanner;
import yogi.report.condition.LongInCondition;

public class TimestampInCondition extends LongInCondition{
	
	private Formatter<Long> sqlFormatter;
	
	public TimestampInCondition(String value, Formatter<Long> sqlFormatter) {
		super(value);
		this.sqlFormatter = sqlFormatter;
	}

	public TimestampInCondition(String value, char separator) {
		super(value, separator);
	}

	public Long scan(String token) {
		return TimestampScanner.scan(token);
	}
	
	public boolean satisfied(Long data) {
		return super.satisfied(data);
	}
	
	@Override
	protected void scanItem(String token) {
		if(token.contains("-"))
			super.scanItem(token);
		else
			values.add(TimestampScanner.scan(token));
	}
	
	@Override
	public String getSqlValue(String columnName) {
		StringBuilder sb = new StringBuilder();
		HashSet<Long> vals = getValues();
		List<Range<Long>> rangeValues = getRanges();
		if((vals.size() + rangeValues.size())>1)
			sb.append("( ");
		for(Long value: vals){	
			sb.append("(");
			sb.append(columnName);
			sb.append(" = '");
			sb.append(sqlFormatter.format(value));
			sb.append("')");
			sb.append(" or ");
		}
		if(!vals.isEmpty()) {
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
		}
		if(vals.size()>0 && rangeValues.size()>0){
			sb.append(" or ");
			sb.append("( ");
		}
		for(Range<Long> range: rangeValues){
			sb.append("(");
			sb.append(columnName);
			sb.append(" >= '");
			sb.append(sqlFormatter.format(range.getStart()));
			sb.append("' and ");
			sb.append(columnName);
			sb.append(" <= '");
			sb.append(sqlFormatter.format(range.getEnd()));
			sb.append("')");
			sb.append(" or ");
		}
		if(!rangeValues.isEmpty()) {
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
		}
		if(vals.size()>0 && rangeValues.size()>0)
			sb.append(" )");
		if((vals.size() + rangeValues.size())>1)
			sb.append(" )");
		return sb.toString();
	}
	
}
