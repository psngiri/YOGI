package yogi.report.server;

import java.util.List;

import yogi.base.Selector;
import yogi.report.condition.Condition;

public abstract class FilterSelector<T> implements Selector<T> {
	
	private FilterByConditions filters;	
	private boolean defaultValue;
	private int count = 0;
	private int maxCount = Integer.MAX_VALUE;
	
	protected FilterSelector() {
		this(true);
	}
	
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	protected FilterSelector(boolean defaultValue) {
		super();
		this.defaultValue = defaultValue;
		this.filters = new FilterByConditions(defaultValue);
	}
	
	public boolean isDefaultValue() {
		return defaultValue;
	}

	public void set(String key, List<Condition<Object>> conditions) {
		filters.set(key, conditions);
	}	

	protected boolean filter(Object value, String key) {
		boolean rtnValue = filters.filter(value, key);
		if(rtnValue){
			count++;
			if(count > maxCount) throw new RuntimeException(key + "(s) cannot exceed more than " + maxCount);
		}		
		return rtnValue;
	}
	
	public int getIndexColumnCount() {
		return filters.getIndexColumnCount();
	}
}
