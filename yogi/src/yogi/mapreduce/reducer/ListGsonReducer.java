package yogi.mapreduce.reducer;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.JsonAssistant;
import yogi.mapreduce.Reducer;

public class ListGsonReducer implements Reducer<String> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String reduce(List<String> results) {
		List rtnValue = new ArrayList();
		for(String result: results){
			ArrayList fromJson = JsonAssistant.get().fromJson(result, ArrayList.class);
			rtnValue.addAll(fromJson);
		}
		return JsonAssistant.get().toJson(rtnValue);
	}

}
