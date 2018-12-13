package yogi.report.group;


public interface GroupMultiplierCalculator<T> {
	int getMultiplier(Group<T> group, int indexInGroup);
}
