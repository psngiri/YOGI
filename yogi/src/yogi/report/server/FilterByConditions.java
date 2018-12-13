package yogi.report.server;

import java.util.List;

import yogi.base.indexing.ManyIndexer;
import yogi.report.condition.Condition;

public class FilterByConditions {
	
	private boolean defaultValue;
	private ManyIndexer<String, Condition<Object>> filters = new ManyIndexer<String, Condition<Object>>();
	
	public FilterByConditions() {
		this(true);
	}
	
	public FilterByConditions(boolean defaultValue) {
		super();
		this.defaultValue = defaultValue;
	}
	
	public int getIndexColumnCount(){
		return filters.keySet().size();
	}
	
	public void set(String key, List<Condition<Object>> conditions) 
	{
		filters.index(key, conditions);
	}
	
	public boolean filter(Object value, String key) {	
		if(filters.isEmpty()) return defaultValue;
		List<Condition<Object>> conditions = filters.get(key);
		if(conditions == null || conditions.isEmpty()) return defaultValue;
		for(Condition<Object> condition: conditions)
        {
            if(condition.satisfied(value)) return true;
        }
        return false;
	}

}

