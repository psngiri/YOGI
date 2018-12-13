package yogi.report.group;


public interface GroupProcessor<T> {
	void reset();
	void processObject(Group<T> group, int indexInGroup, int multiplier);
	Object[] getValues(Group<T> group);
}
