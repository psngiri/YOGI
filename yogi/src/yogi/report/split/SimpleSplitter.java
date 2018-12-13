package yogi.report.split;

public class SimpleSplitter implements Splitter {

	private String regex = "/";

	public SimpleSplitter() {
		super();
	}

	public SimpleSplitter(String regex) {
		super();
		this.regex = regex;
	}

	@Override
	public Comparable<?>[] split(Object value) {
		String myValue = value.toString();
		return myValue.substring(1, myValue.length()-1).split(regex);
	}

}
