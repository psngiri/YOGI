package yogi.report.column;

import yogi.report.group.Group;

public interface ColumnWorker<T> {
	Object getValue(Group<T> group, int index);
	Object getGroupValue(Group<T> group);
	int compare(T row1, T row2);
	int compareGroup(Group<T> group1, Group<T> group2);
	ColumnGroupWorker<T> getColumnGroupWorker();
	int getIndex();
	boolean isKey();
}
