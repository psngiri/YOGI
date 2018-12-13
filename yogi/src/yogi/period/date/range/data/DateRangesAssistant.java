package yogi.period.date.range.data;

import java.util.List;

import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.date.range.DateRange;

public abstract class DateRangesAssistant<T extends RelationshipObject> extends BaseDateRangesAssistant<T> {
	protected abstract OneToOneSimpleRelationship<T, DateRangesObject> getDateRangeedDataRelationship();
	
	public ImmutableList<DateRange> getDateRanges(T relationshipObject)
	{
		OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship = getDateRangeedDataRelationship();
		return getDateRanges(relationshipObject, dateRangeedDataRelationship);
	}

	public void addDateRanges(T relationshipObject, List<DateRange> dateRanges)
	{
		OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship = getDateRangeedDataRelationship();
		addDateRanges(relationshipObject, dateRanges, dateRangeedDataRelationship);
	}

	public void addDateRange(T relationshipObject, DateRange dateRange)
	{
		OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship = getDateRangeedDataRelationship();
		addDateRange(relationshipObject, dateRange, dateRangeedDataRelationship);
	}

	public void compressDateRanges(T relationshipObject)
	{
		compressDateRanges(relationshipObject, getDateRangeedDataRelationship());
	}

	public void deleteDateRanges(T relationshipObject, List<DateRange> dateRanges)
	{
		OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship = getDateRangeedDataRelationship();
		deleteDateRanges(relationshipObject, dateRanges, dateRangeedDataRelationship);
	}

	public void deleteDateRange(T relationshipObject, DateRange dateRange)
	{
		OneToOneSimpleRelationship<T, DateRangesObject> dateRangeedDataRelationship = getDateRangeedDataRelationship();
		deleteDateRange(relationshipObject, dateRange, dateRangeedDataRelationship);
	}
}
