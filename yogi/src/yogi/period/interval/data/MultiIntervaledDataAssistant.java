package yogi.period.interval.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;

public abstract class MultiIntervaledDataAssistant<T extends RelationshipObject, I extends MultiIntervaledData<O>, O> extends RelationshipAssistant<T> {	
	protected abstract OneToOneSimpleRelationship<T, I> getMultiIntervaledDataRelationship();
	private boolean checkForDuplicateValuesOnSameDay = true;
	private IntervalsAssistant<? super T> intervalsAssistantToPropagateTo = null;
	
	protected MultiIntervaledDataAssistant() {
		super();
	}

	protected MultiIntervaledDataAssistant(boolean checkForDuplicateValuesOnSameDay, IntervalsAssistant<? super T> intervalsAssistantToPropagateTo) {
		super();
		this.checkForDuplicateValuesOnSameDay = checkForDuplicateValuesOnSameDay;
		this.intervalsAssistantToPropagateTo = intervalsAssistantToPropagateTo;
	}

	public I getData(T relationshipObject)
	{
		I intervaledData = getMultiIntervaledData(relationshipObject);
		return intervaledData;
	}

	public Set<O> getValues(T relationshipObject){		
		I data = getData(relationshipObject);
		if(data == null)
			return new HashSet<O>();
		return data.getValues();
	}
	
	public ImmutableList<Interval> getIntervals(T relationshipObject, O value){
		return getData(relationshipObject).getIntervals(value);
	}
	
	private void propagate(T relationshipObject, List<Interval> intervals) {
		if(intervalsAssistantToPropagateTo == null) return;
		intervalsAssistantToPropagateTo.addIntervals(relationshipObject, intervals);
	}
	
	private void propagate(T relationshipObject, Interval interval) {
		if(intervalsAssistantToPropagateTo == null) return;
		intervalsAssistantToPropagateTo.addInterval(relationshipObject, interval);
	}

	
	public void addIntervals(T relationshipObject, O value, List<Interval> intervals)
	{		
		propagate(relationshipObject, intervals);
		I intervaledData = getMultiIntervaledDataCreateIfNecessary(relationshipObject);
		intervaledData.add(value, intervals);
		for (Interval interval : intervals) {
			checkForDuplicateValuesOnSameDay(value, interval, intervaledData);	
		}		
	}


	public void addInterval(T relationshipObject, O value, Interval interval)
	{		
		propagate(relationshipObject, interval);
		I intervaledData = getMultiIntervaledDataCreateIfNecessary(relationshipObject);
		intervaledData.add(value, interval);
		checkForDuplicateValuesOnSameDay(value, interval, intervaledData);
	}

	private void checkForDuplicateValuesOnSameDay(O value, Interval interval, I intervaledData) {
		if(!checkForDuplicateValuesOnSameDay)
			return;
		intervaledData.checkForOverlap(value,interval);
	}

	private I getMultiIntervaledDataCreateIfNecessary(T relationshipObject) {
		I multiIntervaledData = getMultiIntervaledData(relationshipObject);
		if(multiIntervaledData == null)
		{
			synchronized(relationshipObject)
			{
				multiIntervaledData = this.<I,OneToOneSimpleRelationship<T, I>>getRelationship(relationshipObject, getMultiIntervaledDataRelationship());
				if(multiIntervaledData == null)
				{
					multiIntervaledData = createMultiIntervaledData();
					this.setRelationship(relationshipObject, getMultiIntervaledDataRelationship(), multiIntervaledData);
				}
			}
		}
		return multiIntervaledData;
	}

	private I getMultiIntervaledData(T relationshipObject) {
		I intervaledData = this.<I,OneToOneSimpleRelationship<T, I>>getRelationship(relationshipObject, getMultiIntervaledDataRelationship());
		return intervaledData;
	}
	
	protected abstract I createMultiIntervaledData();

}
