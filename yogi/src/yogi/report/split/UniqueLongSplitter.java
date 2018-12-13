package yogi.report.split;

import java.util.Set;

public class UniqueLongSplitter extends UniqueSplitter {

	public UniqueLongSplitter() {
		super();
	}

	public UniqueLongSplitter(String regex) {
		super(regex);
	}

	@Override
	protected boolean check(String item) {
		try {
			
			String trim = item.trim();
			if(trim.isEmpty())
				return false;
			return !(Long.parseLong(trim)==0);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	protected Comparable<?>[] enchance(Set<String> set) {
		Long[] rtnValue = new Long[set.size()];
		int i=0;
		for(String item: set)
		{
			rtnValue[i] =Long.parseLong(item);
			i++;
		}
		return rtnValue;
	}

}
