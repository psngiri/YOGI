package yogi.period.date.range.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.date.range.DateRange;

public abstract class MultiDateRangesDataAssistant<T extends RelationshipObject, I extends MultiDateRangesData<O>, O> extends RelationshipAssistant<T> {	
	protected abstract OneToOneSimpleRelationship<T, I> getMultiDateRangeedDataRelationship();
	private boolean checkForDuplicateValuesOnSameDay = true;
	private DateRangesAssistant<? super T> dateRangesAssistantToPropagateTo = null;
	
	protected MultiDateRangesDataAssistant() {
		super();
	}

	protected MultiDateRangesDataAssistant(boolean checkForDuplicateValuesOnSameDay, DateRangesAssistant<? super T> dateRangesAssistantToPropagateTo) {
		super();
		this.checkForDuplicateValuesOnSameDay = checkForDuplicateValuesOnSameDay;
		this.dateRangesAssistantToPropagateTo = dateRangesAssistantToPropagateTo;
	}

	public I getData(T relationshipObject)
	{
		I dateRangeedData = getMultiDateRangeedData(relationshipObject);
		return dateRangeedData;
	}

	public Set<O> getValues(T relationshipObject){		
		I data = getData(relationshipObject);
		if(data == null)
			return new HashSet<O>();
		return data.getValues();
	}
	
	public ImmutableList<DateRange> getDateRanges(T relationshipObject, O value){
		return getData(relationshipObject).getDateRanges(value);
	}
	
	private void propagate(T relationshipObject, List<DateRange> dateRanges) {
		if(dateRangesAssistantToPropagateTo == null) return;
		dateRangesAssistantToPropagateTo.addDateRanges(relationshipObject, dateRanges);
	}
	
	private void propagate(T relationshipObject, DateRange dateRange) {
		if(dateRangesAssistantToPropagateTo == null) return;
		dateRangesAssistantToPropagateTo.addDateRange(relationshipObject, dateRange);
	}

	
	public void addDateRanges(T relationshipObject, O value, List<DateRange> dateRanges)
	{		
		propagate(relationshipObject, dateRanges);
		I dateRangeedData = getMultiDateRangeedDataCreateIfNecessary(relationshipObject);
		dateRangeedData.add(value, dateRanges);
		for (DateRange dateRange : dateRanges) {
			checkForDuplicateValuesOnSameDay(value, dateRange, dateRangeedData);	
		}		
	}


	public void addDateRange(T relationshipObject, O value, DateRange dateRange)
	{		
		propagate(relationshipObject, dateRange);
		I dateRangeedData = getMultiDateRangeedDataCreateIfNecessary(relationshipObject);
		dateRangeedData.add(value, dateRange);
		checkForDuplicateValuesOnSameDay(value, dateRange, dateRangeedData);
	}

	private void checkForDuplicateValuesOnSameDay(O value, DateRange dateRange, I dateRangeedData) {
		if(!checkForDuplicateValuesOnSameDay)
			return;
		dateRangeedData.checkForOverlap(value,dateRange);
	}

	private I getMultiDateRangeedDataCreateIfNecessary(T relationshipObject) {
		I multiDateRangeedData = getMultiDateRangeedData(relationshipObject);
		if(multiDateRangeedData == null)
		{
			synchronized(relationshipObject)
			{
				multiDateRangeedData = this.<I,OneToOneSimpleRelationship<T, I>>getRelationship(relationshipObject, getMultiDateRangeedDataRelationship());
				if(multiDateRangeedData == null)
				{
					multiDateRangeedData = createMultiDateRangeedData();
					this.setRelationship(relationshipObject, getMultiDateRangeedDataRelationship(), multiDateRangeedData);
				}
			}
		}
		return multiDateRangeedData;
	}

	private I getMultiDateRangeedData(T relationshipObject) {
		I dateRangeedData = this.<I,OneToOneSimpleRelationship<T, I>>getRelationship(relationshipObject, getMultiDateRangeedDataRelationship());
		return dateRangeedData;
	}
	
	protected abstract I createMultiDateRangeedData();

}
