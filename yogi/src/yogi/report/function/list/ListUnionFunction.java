package yogi.report.function.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yogi.report.function.Function;

public class ListUnionFunction<T> implements Function<List<T>> {
	private Set<T> unionList = new HashSet<T>();
	
	public void reset() {
		unionList = new HashSet<T>();
	}

	public void process(List<T> object, int multiplier) {
		if(object == null) return;
		unionList.addAll(object);
	}

	public List<T> getValue() {
		return new ArrayList<T>(unionList);
	}

}
