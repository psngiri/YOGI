package yogi.report.function.list;

import java.util.ArrayList;
import java.util.List;

import yogi.report.function.Function;

public class ListIntersectionFunction<T> implements Function<List<T>>{
	private List<T> intersectList = new ArrayList<T>();
	
	public void reset() {
		intersectList = new ArrayList<T>();
	}

	public void process(List<T> object, int multiplier) {
		if(object == null) return;
		if(intersectList.isEmpty()) intersectList = object;
		else intersectList = getCommonData(intersectList, object);			
	}

	public List<T> getValue() {		
		return intersectList;
	}
		
	protected List<T> getCommonData(List<T> existingList, List<T> newList){
		existingList.retainAll(newList);
		return existingList;
	}
}
