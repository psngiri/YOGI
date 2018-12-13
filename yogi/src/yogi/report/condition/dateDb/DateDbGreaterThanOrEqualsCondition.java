package yogi.report.condition.dateDb;

import yogi.base.io.Formatter;
import yogi.period.date.io.DateDbScanner;
import yogi.report.condition.LongGreaterThanOrEqualsCondition;

public class DateDbGreaterThanOrEqualsCondition extends LongGreaterThanOrEqualsCondition {
	private Formatter<Long> sqlFormatter;
	
	public DateDbGreaterThanOrEqualsCondition(String value,Formatter<Long> sqlFormatter) {
		super(value);
		this.sqlFormatter = sqlFormatter;
	}
	@Override
	public boolean satisfied(Long data) {
		if(data==null)return false;
		return compare(data)>=0;
	}

	@Override
	public Long scan(String value) {
		return DateDbScanner.scan(value);
	}
	
	@Override
	public String getSqlValue(String columnName) {
		StringBuilder sb = new StringBuilder();
		sb.append(' ');
		sb.append(columnName);
		sb.append(' ');
		sb.append(getSqlCondition());
		sb.append(" '");
		sb.append(sqlFormatter.format(scan(getValue())));
		sb.append("' ");
		return sb.toString();
	}
}
