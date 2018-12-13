package yogi.report.condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LongListInCondition extends BaseInCondition<List<Long>> {

	public LongListInCondition(String value) {
		super(replaceBraces(value));
	}
	
	public LongListInCondition(String value, char separator) {
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
	public List<Long> scan(String token) {
		List<Long> rtnVal = new ArrayList<Long>();
		try {
			token = token.trim();
			if(token.isEmpty()) {
				return new ArrayList<Long>();
			}
			String[] splits = token.split("\\^");			
			List<String> strList = Arrays.asList(splits);
			for(String str: strList) {
				str = str.trim();
				rtnVal.add(Long.parseLong(str));				
			}			
		} catch(Exception e) {
			throw new RuntimeException("Input should be numbers seperated by ^ : " + token);
		}
		return rtnVal;
	}
	
}
