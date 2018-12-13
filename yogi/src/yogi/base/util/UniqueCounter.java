package yogi.base.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class UniqueCounter<T>{
	Map<T, Integer> map = new HashMap<T, Integer>();
	
	public Integer put(T object) {
		Integer value = map.get(object);
		if(value == null) value = 0;
		value = value + 1;
		return map.put(object, value);
	}

	public Set<Entry<T, Integer>> entrySet() {
		return map.entrySet();
	}
	
}
