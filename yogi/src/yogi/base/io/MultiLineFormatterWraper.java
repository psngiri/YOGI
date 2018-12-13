package yogi.base.io;

import java.util.List;

import yogi.base.Util;

public class MultiLineFormatterWraper<T> implements Formatter<T> {
	MultiLineFormatter<T> multiFormatter;
	
	
	public MultiLineFormatterWraper(MultiLineFormatter<T> multiFormatter) {
		super();
		this.multiFormatter = multiFormatter;
	}


	public String format(T object) {
		List<String> lines = multiFormatter.format(object);
		return Util.convert(lines);
	}


}
