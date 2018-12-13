package yogi.report.condition.timeDb;


import java.util.HashSet;
import java.util.List;

import yogi.base.io.Formatter;
import yogi.base.util.range.Range;
import yogi.period.time.io.TimeDbScanner;
import yogi.report.condition.LongInCondition;


public class TimeDbInCondition extends LongInCondition {
	private Formatter<Long> sqlFormatter;
	
	public TimeDbInCondition(String value,Formatter<Long> sqlFormatter) {
		super(value);
		this.sqlFormatter = sqlFormatter;	
	}

	public TimeDbInCondition(String value, char separator) {
		super(value, separator);
	}

	@Override
	public Long scan(String value) {
		return TimeDbScanner.scan(value);
	}
	
	@Override
	public boolean satisfied(Long data) {
		return super.satisfied(data);
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
