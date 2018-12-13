package yogi.report.dated.group;

import yogi.report.group.Group;
import yogi.report.group.GroupEvaluator;

public abstract class DatedGroupEvaluator<T, C> implements GroupEvaluator<T, C> {

	public C evaluate(Group<T> group, int indexInGroup) {
		if(group instanceof DatedGroup)
		{
			return evaluate((DatedGroup<T>)group, indexInGroup);
		}
		return evaluate(group.getItem(indexInGroup));
	}
	
	public abstract C evaluate(DatedGroup<T> group, int indexInGroup);
}
