package yogi.report;

import yogi.base.io.Formatter;
import yogi.base.io.MultiLineFormatter;

public interface GroupFormatter<T>{
	  MultiLineFormatter<T> getGroupObjectFormatter();
	  Formatter<T> getGroupTotalFormatter();
	  MultiLineFormatter<T> getGroupHeader();
	  MultiLineFormatter<T> getGroupFooter();
	  MultiLineFormatter<T> getGroupTotalHeader();
	  MultiLineFormatter<T> getGroupTotalFooter();
}
