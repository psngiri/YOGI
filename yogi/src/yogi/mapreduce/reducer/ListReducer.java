package yogi.mapreduce.reducer;

import java.util.ArrayList;
import java.util.List;

import yogi.mapreduce.Reducer;

public class ListReducer<T> implements Reducer<List<T>> {

	@Override
	public List<T> reduce(List<List<T>> results) {
		List<T> rtnValue = new ArrayList<T>();
		for(List<T> result: results){
			rtnValue.addAll(result);
		}
		return rtnValue;
	}

}
