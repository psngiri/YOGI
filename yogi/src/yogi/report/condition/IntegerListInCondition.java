package yogi.report.condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IntegerListInCondition extends BaseInCondition<List<Integer>> {

	public IntegerListInCondition(String value) {
		super(replaceBraces(value));
	}
	
	public IntegerListInCondition(String value, char separator) {
		super(replaceBraces(value), separator);
	}

	private static String replaceBraces(String value) {
		if(value != null && !value.isEmpty()) {
			StringBuilder builder = new StringBuilder(value);
			int index1 = value.indexOf(']');
			if(index1 != -1) builder.deleteCharAt(index1);
			int index2 = value.indexOf('[');
			if(index2 != -1) builder.deleteCharAt(index2);
			value = builder.toString();
		}
		return value;
	}
	
	@Override
	public List<Integer> scan(String token) {
		List<Integer> rtnVal = new ArrayList<Integer>();
		try {
			token = token.trim();
			if(token.isEmpty()) {
				return new ArrayList<Integer>();
			}
			String[] splits = token.split("\\^");			
			List<String> strList = Arrays.asList(splits);
			for(String str: strList) {
				str = str.trim();
				rtnVal.add(Integer.parseInt(str));				
			}			
		} catch(Exception e) {
			throw new RuntimeException("Input should be numbers seperated by ^ : " + token);
		}
		return rtnVal;
	}
	
}
