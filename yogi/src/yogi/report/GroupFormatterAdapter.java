package yogi.report;

import yogi.base.io.Formatter;
import yogi.base.io.MultiLineFormatter;

public class GroupFormatterAdapter<T> implements GroupFormatter<T> {

	public MultiLineFormatter<T> getGroupObjectFormatter() {
		return null;
	}

	public Formatter<T> getGroupTotalFormatter() {
		return null;
	}

	public MultiLineFormatter<T> getGroupHeader() {
		return null;
	}

	public MultiLineFormatter<T> getGroupFooter() {
		return null;
	}

	public MultiLineFormatter<T> getGroupTotalHeader() {
		return null;
	}

	public MultiLineFormatter<T> getGroupTotalFooter() {
		return null;
	}

}
