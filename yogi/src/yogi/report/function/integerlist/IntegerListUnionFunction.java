package yogi.report.function.integerlist;

import java.util.ArrayList;
import java.util.List;

import yogi.report.function.Function;

public class IntegerListUnionFunction implements Function<List<Integer>> {
	List<Integer> unionList = new ArrayList<Integer>();
	
	public void reset() {
		unionList = new ArrayList<Integer>();
	}

	public void process(List<Integer> object, int multiplier) {
		if(object == null) return;
		for(Integer obj: object)
		{
			unionList.add(obj);
		}
	}

	public List<Integer> getValue() {
		return unionList;
	}

}
