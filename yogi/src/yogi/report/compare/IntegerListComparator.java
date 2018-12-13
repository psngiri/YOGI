package yogi.report.compare;

import java.util.Comparator;
import java.util.List;

public class IntegerListComparator implements Comparator<List<Integer>> {
	
	public int compare(List<Integer> obj1, List<Integer> obj2) {
		
		if(obj1.isEmpty() && obj2.isEmpty()) return 0;
		if(obj1.isEmpty()) return -1;
		if(obj2.isEmpty()) return 1;
		
		int size1 = obj1.size();
		int size2 = obj2.size();
		int min = Math.min(size1, size2);
		int i = 0;
		for(; i < min; i++) {
			int rtnVal = obj1.get(i).compareTo(obj2.get(i));
			if(rtnVal != 0) return rtnVal;
		}
		if(size1 != size2) {
			if(i == size1) return -1;
			if(i == size2) return 1;
		}
		return 0;
	}

}
