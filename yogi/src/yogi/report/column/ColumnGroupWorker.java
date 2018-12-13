package yogi.report.column;

import yogi.report.group.Group;

interface ColumnGroupWorker<T> {
	int getIndex();
	void setIndex(int index);
	void reset();
	void processObject(Group<T> group, int indexInGroup, int multiplier);
	boolean isKey();
}
