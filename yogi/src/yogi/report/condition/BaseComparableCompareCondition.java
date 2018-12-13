package yogi.report.condition;



public abstract class BaseComparableCompareCondition<C extends Comparable<C>> extends ConditionBaseImpl<C> {
	private C object;

	public BaseComparableCompareCondition(String value) {
		super(value);
		object = scan(value);
	}

	public abstract C scan(String value);
	
	public int compare(C data) {
		if(data == null) return -1;
		return data.compareTo(object);
	}

	@Override
	public String getSqlValue(String columnName) {
		return super.getSqlValue(columnName);
	}

}
