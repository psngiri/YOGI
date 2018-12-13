package yogi.period.date.range.data;

import java.util.List;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.DateRanges;

public abstract class BaseDateRangesAssistant<T extends RelationshipObject> extends RelationshipAssistant<T> {
	
	protected ImmutableList<DateRange> getDateRanges(T relationshipObject, OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship) {
		DateRangesObject dateRangesObject = this.<DateRangesObject,OneToOneSimpleRelationship<T, DateRangesObject>>getRelationship(relationshipObject, dateRangeedDataRelationship, true);
		if(dateRangesObject == null)
		{
			return DateRanges.EMPTY_IMMUTABLE_LIST;
		}
		return dateRangesObject.getDateRanges();
	}

	protected void addDateRanges(T relationshipObject, List<DateRange> dateRanges, OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship) {
		DateRangesObject dateRangesObject = getDateRangesObjectCreateIfNecessary(relationshipObject, dateRangeedDataRelationship);
		dateRangesObject.add(dateRanges);
	}

	protected void addDateRange(T relationshipObject, DateRange dateRange, OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship) {
		DateRangesObject dateRangesObject = getDateRangesObjectCreateIfNecessary(relationshipObject, dateRangeedDataRelationship);
		dateRangesObject.add(dateRange);
	}

	private DateRangesObject getDateRangesObjectCreateIfNecessary(T relationshipObject, OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship) {
		DateRangesObject dateRangesObject = this.<DateRangesObject,OneToOneSimpleRelationship<T, DateRangesObject>>getRelationship(relationshipObject, dateRangeedDataRelationship, false);
		if(dateRangesObject == null)
		{
			synchronized(relationshipObject)
			{
				dateRangesObject = this.<DateRangesObject,OneToOneSimpleRelationship<T, DateRangesObject>>getRelationship(relationshipObject, dateRangeedDataRelationship, false);
				if(dateRangesObject == null)
				{
					dateRangesObject = new DateRangesObject();
					this.setRelationship(relationshipObject, dateRangeedDataRelationship, dateRangesObject);
				}
			}
		}
		return dateRangesObject;
	}
	
	public void compressDateRanges(T relationshipObject, OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship)
	{
		DateRangesObject dateRangesObject = this.<DateRangesObject,OneToOneSimpleRelationship<T, DateRangesObject>>getRelationship(relationshipObject, dateRangeedDataRelationship);
		dateRangesObject.compress();
	}
		
	protected void deleteDateRanges(T relationshipObject, List<DateRange> dateRanges, OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship) {
		DateRangesObject dateRangesObject = this.<DateRangesObject,OneToOneSimpleRelationship<T, DateRangesObject>>getRelationship(relationshipObject, dateRangeedDataRelationship, false);
		if(dateRangesObject != null)
		{
			dateRangesObject.delete(dateRanges);
		}else
		{
			ImmutableList<DateRange> myDateRanges = this.getDateRanges(relationshipObject, dateRangeedDataRelationship);
			List<DateRange> newDateRanges = DateRanges.subtract(myDateRanges, dateRanges);
			addDateRanges(relationshipObject, newDateRanges, dateRangeedDataRelationship);
		}
	}

	protected void deleteDateRange(T relationshipObject, DateRange dateRange, OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship) {
		DateRangesObject dateRangesObject = this.<DateRangesObject,OneToOneSimpleRelationship<T, DateRangesObject>>getRelationship(relationshipObject, dateRangeedDataRelationship, false);
		if(dateRangesObject != null)
		{
			dateRangesObject.delete(dateRange);
		}else
		{
			ImmutableList<DateRange> myDateRanges = this.getDateRanges(relationshipObject, dateRangeedDataRelationship);
			List<DateRange> newDateRanges = DateRanges.subtract(myDateRanges, dateRange);
			addDateRanges(relationshipObject, newDateRanges, dateRangeedDataRelationship);
		}
	}
}
