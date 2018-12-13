package yogi.report.condition;

import java.util.Comparator;


public abstract class BaseCompareCondition<C> extends ConditionBaseImpl<C> {
	private C object;
	private Comparator<C> comparator;

	public BaseCompareCondition(String value, Comparator<C> comparator) {
		super(value);
	}

	public abstract C scan(String value);
	
	public int compare(C data) {
		if(data == null) return -1;
		
		return comparator.compare(data, object);
	}

}
