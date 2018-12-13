package yogi.period.interval.data;

import java.util.List;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;

public abstract class BaseIntervalsAssistant<T extends RelationshipObject> extends RelationshipAssistant<T> {
	
	protected ImmutableList<Interval> getIntervals(T relationshipObject, OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship) {
		IntervalsObject intervalsObject = getIntervalsObject(relationshipObject, intervaledDataRelationship);
		if(intervalsObject == null)
		{
			return Interval.EMPTY_IMMUTABLE_LIST;
		}
		return intervalsObject.getIntervals();
	}

	protected IntervalsObject getIntervalsObject(T relationshipObject,	OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship) {
		return this.<IntervalsObject,OneToOneSimpleRelationship<T, IntervalsObject>>getRelationship(relationshipObject, intervaledDataRelationship, true);
	}

	protected void addIntervals(T relationshipObject, List<Interval> intervals, OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship) {
		IntervalsObject intervalsObject = getIntervalsObjectCreateIfNecessary(relationshipObject, intervaledDataRelationship);
		intervalsObject.add(intervals);
	}

	protected void addInterval(T relationshipObject, Interval interval, OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship) {
		IntervalsObject intervalsObject = getIntervalsObjectCreateIfNecessary(relationshipObject, intervaledDataRelationship);
		intervalsObject.add(interval);
	}

	private IntervalsObject getIntervalsObjectCreateIfNecessary(T relationshipObject, OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship) {
		IntervalsObject intervalsObject = this.<IntervalsObject,OneToOneSimpleRelationship<T, IntervalsObject>>getRelationship(relationshipObject, intervaledDataRelationship, false);
		if(intervalsObject == null)
		{
			synchronized(relationshipObject)
			{
				intervalsObject = this.<IntervalsObject,OneToOneSimpleRelationship<T, IntervalsObject>>getRelationship(relationshipObject, intervaledDataRelationship, false);
				if(intervalsObject == null)
				{
					intervalsObject = new IntervalsObject();
					this.setRelationship(relationshipObject, intervaledDataRelationship, intervalsObject);
				}
			}
		}
		return intervalsObject;
	}
	
	public void compressIntervals(T relationshipObject, OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship)
	{
		IntervalsObject intervalsObject = this.<IntervalsObject,OneToOneSimpleRelationship<T, IntervalsObject>>getRelationship(relationshipObject, intervaledDataRelationship);
		intervalsObject.compress();
	}
	
	public void orderlyCompressIntervals(T relationshipObject, OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship)
	{
		IntervalsObject intervalsObject = this.<IntervalsObject,OneToOneSimpleRelationship<T, IntervalsObject>>getRelationship(relationshipObject, intervaledDataRelationship);
		intervalsObject.orderlyCompress();
	}
	
	protected void deleteIntervals(T relationshipObject, List<Interval> intervals, OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship) {
		IntervalsObject intervalsObject = this.<IntervalsObject,OneToOneSimpleRelationship<T, IntervalsObject>>getRelationship(relationshipObject, intervaledDataRelationship, false);
		if(intervalsObject != null)
		{
			intervalsObject.delete(intervals);
		}else
		{
			ImmutableList<Interval> myIntervals = this.getIntervals(relationshipObject, intervaledDataRelationship);
			List<Interval> newIntervals = Intervals.subtract(myIntervals, intervals);
			addIntervals(relationshipObject, newIntervals, intervaledDataRelationship);
		}
	}

	protected void deleteInterval(T relationshipObject, Interval interval, OneToOneSimpleRelationship<T, IntervalsObject> intervaledDataRelationship) {
		IntervalsObject intervalsObject = this.<IntervalsObject,OneToOneSimpleRelationship<T, IntervalsObject>>getRelationship(relationshipObject, intervaledDataRelationship, false);
		if(intervalsObject != null)
		{
			intervalsObject.delete(interval);
		}else
		{
			ImmutableList<Interval> myIntervals = this.getIntervals(relationshipObject, intervaledDataRelationship);
			List<Interval> newIntervals = Intervals.subtract(myIntervals, interval);
			addIntervals(relationshipObject, newIntervals, intervaledDataRelationship);
		}
	}
}
