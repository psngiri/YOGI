package yogi.report.split;

import java.util.HashSet;
import java.util.Set;

public class UniqueSplitter implements Splitter {

	private String regex = "/";
	private Set<String> ignoreValues = new HashSet<String>();
	public UniqueSplitter() {
		super();
	}

	public UniqueSplitter(String regex) {
		super();
		this.regex = regex;
	}
	
	public void add(String ignoreValue)
	{
		ignoreValues.add(ignoreValue);
	}

	@Override
	public Comparable<?>[] split(Object value) {
		String myValue = value.toString();
		String[] split = myValue.substring(1, myValue.length()-1).split(regex);
		Set<String> set = new HashSet<String>(split.length);
		for(String item: split){
			boolean check = check(item);
			if(check){
				set.add(item);
			}
		}
		return enchance(set);
	}

	protected boolean check(String item) {
		String trim = item.trim();
		boolean rtnValue = !trim.isEmpty();
		if(!rtnValue){
			return rtnValue;
		}
		return !ignoreValues.contains(trim);
	}

	protected Comparable<?>[] enchance(Set<String> set) {
		String[] rtnValue = new String[set.size()];
		return set.toArray(rtnValue);
	}

}
