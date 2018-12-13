package yogi.period.interval.data;

import java.util.List;

import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;

public abstract class IntervalsAssistant<T extends RelationshipObject> extends BaseIntervalsAssistant<T> {
	protected abstract OneToOneSimpleRelationship<T, IntervalsObject> getIntervaledDataRelationship();
	
	public ImmutableList<Interval> getIntervals(T relationshipObject)
	{
		OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship = getIntervaledDataRelationship();
		return getIntervals(relationshipObject, intervaledDataRelationship);
	}

	public IntervalsObject getIntervalsObject(T relationshipObject)
	{
		OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship = getIntervaledDataRelationship();
		return getIntervalsObject(relationshipObject, intervaledDataRelationship);
	}
	
	public void addIntervals(T relationshipObject, List<Interval> intervals)
	{
		OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship = getIntervaledDataRelationship();
		addIntervals(relationshipObject, intervals, intervaledDataRelationship);
	}

	public void addInterval(T relationshipObject, Interval interval)
	{
		OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship = getIntervaledDataRelationship();
		addInterval(relationshipObject, interval, intervaledDataRelationship);
	}
	public void compressIntervals(T relationshipObject)
	{
		compressIntervals(relationshipObject, getIntervaledDataRelationship());
	}
	public void orderlyCompressIntervals(T relationshipObject)
	{
		orderlyCompressIntervals(relationshipObject, getIntervaledDataRelationship());
	}
	public void deleteIntervals(T relationshipObject, List<Interval> intervals)
	{
		OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship = getIntervaledDataRelationship();
		deleteIntervals(relationshipObject, intervals, intervaledDataRelationship);
	}

	public void deleteInterval(T relationshipObject, Interval interval)
	{
		OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship = getIntervaledDataRelationship();
		deleteInterval(relationshipObject, interval, intervaledDataRelationship);
	}
}
