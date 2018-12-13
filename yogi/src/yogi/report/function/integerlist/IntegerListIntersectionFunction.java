package yogi.report.function.integerlist;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import yogi.report.function.Function;

public class IntegerListIntersectionFunction implements Function<List<Integer>>{
	List<Integer> intersectList = new ArrayList<Integer>();
	
	public void reset() {
		intersectList = new ArrayList<Integer>();
	}

	public void process(List<Integer> object, int multiplier) {
		if(object == null) return;
		if(intersectList.isEmpty()) intersectList = object;
		else intersectList = getIntersection(intersectList, object);			
	}

	public List<Integer> getValue() {
		return intersectList;
	}
		
	private List<Integer> getIntersection(List<Integer> existingList, List<Integer> newList){
		List<Integer> rtnList = new ArrayList<Integer>();
		ListIterator<Integer> litr = existingList.listIterator();		
		while(litr.hasNext()){
			Integer key = litr.next();
			ListIterator<Integer> nlitr = newList.listIterator();
			while(nlitr.hasNext()) {
				Integer value = nlitr.next();
				if(key.compareTo(value) == 0) {
					rtnList.add(value);
				}					
			}
		}
		return rtnList;
	}
}
