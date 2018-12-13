package yogi.report.condition;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.range.Range;

public abstract class BaseRangeInCondition<C extends Comparable<C>, R extends Range<C>> extends BaseInCondition<C> {

	List<R> rangeValues;
	
	public BaseRangeInCondition(String value) {
		this(value, ',');
	}
	
	public BaseRangeInCondition(String value, char separator) {
		super(value, separator);
		if(rangeValues == null) rangeValues = new ArrayList<R>();
	}
	
	protected void scanItem(String token) {
		int splitIndex = token.indexOf('-');
		if(token.substring(1).contains("-"))
		{
			if(token.charAt(0)=='-')
				splitIndex =(" " + token.substring(1)).indexOf('-');
			addRange(token,splitIndex);
		}
		else
		{
			super.scanItem(token);
		}
	}	
	protected void addRange(String token, int split)
	{
		String rangeOne = token.substring(0,split);
		String rangeTwo = token.substring(split+1,token.length());
		if(rangeValues == null) rangeValues = new ArrayList<R>();
		R range;
		C one = scan(rangeOne);
		C two = scan(rangeTwo);
		if(one.compareTo(two)<0)
			range = getRange(one, two);
		else
			range = getRange(two, one);
		rangeValues.add(range);	
	}

	protected abstract R getRange(C start, C end); 
	
	public abstract C scan(String token);

	public boolean satisfied(C data) {
		boolean rtnVal = super.satisfied(data);
		if(rtnVal) return rtnVal;
		for(R range : rangeValues) {
			if(range.contains(data)) 
				return true;
		}
		return false;
	}
	
	@Override
	public String getSqlValue(String columnName) {
		String sqlValue = super.getSqlValue(columnName);
		if(!rangeValues.isEmpty()){
			StringBuilder rtnvalue = new StringBuilder();
			if(!sqlValue.isEmpty())
				rtnvalue.append(sqlValue).append(" or ");
			int count=0;
			for(R value: rangeValues){
				if(count!=0)
					rtnvalue.append(" or ");
				count++;
				rtnvalue.append("(");
				rtnvalue.append(columnName);
				rtnvalue.append(" >= ");
				rtnvalue.append(this.getFormatter().format(value.getStart()));
				rtnvalue.append(" and ");
				rtnvalue.append(columnName);
				rtnvalue.append(" <= ");
				rtnvalue.append(this.getFormatter().format(value.getEnd()));	
				rtnvalue.append(")");
			}
			return rtnvalue.toString();
		}
		return sqlValue;
	}
	
	protected List<R> getRanges() {
		return rangeValues;
	}

}
