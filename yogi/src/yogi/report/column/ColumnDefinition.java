package yogi.report.column;

import yogi.report.split.Splitter;

public interface ColumnDefinition<T> {
	String[] getHeading();
	String getName();
	int getWidth();
	ColumnWorker<T> getColumnWorker();
	Class<?> getType();
	String format(Object object);
	int getIndex();
	boolean isKey();
	Splitter getSplitter();
}
