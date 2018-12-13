package yogi.report.group;


public interface GroupItemComparator<T> {
	int compare(Group<T> group1, int indexInGroup1, Group<T> group2, int indexInGroup2);
}
