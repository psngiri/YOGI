package yogi.period.date.range.data;

import java.util.List;

import yogi.base.indexing.Indexer;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.date.range.DateRange;

public abstract class DateRangesDataAssistant<T extends RelationshipObject, O extends Enum<O>> extends BaseDateRangesAssistant<T> {
	private Indexer<O, OneToOneSimpleRelationship<T, DateRangesObject>> relationships = new Indexer<O, OneToOneSimpleRelationship<T, DateRangesObject>>();
	protected abstract Class<T> getRelationshipObjectClass();
	
	private OneToOneSimpleRelationship<T, DateRangesObject> getDateRangeedDataRelationship(O data)
	{
		OneToOneSimpleRelationship<T, DateRangesObject> oneToOneSimpleRelationship = relationships.get(data);
		if(oneToOneSimpleRelationship == null)
		{
			synchronized(relationships){
				oneToOneSimpleRelationship = createRelationship(data);
			}
		}
		return oneToOneSimpleRelationship;
	}

	private OneToOneSimpleRelationship<T, DateRangesObject> createRelationship(O data) {
		OneToOneSimpleRelationship<T, DateRangesObject> oneToOneSimpleRelationship = relationships.get(data);
		if(oneToOneSimpleRelationship == null)
		{
			oneToOneSimpleRelationship = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(getRelationshipObjectClass(), DateRangesObject.class, "" +data.name() +"DateRangeedData");
			relationships.index(data, oneToOneSimpleRelationship);
		}
		return oneToOneSimpleRelationship;
	}

	public void addDateRange(T relationshipObject, O data, DateRange dateRange) {
		this.getDateRangesAssistant().addDateRange(relationshipObject, dateRange);
		super.addDateRange(relationshipObject, dateRange, getDateRangeedDataRelationship(data));
	}

	public void addDateRanges(T relationshipObject, O data, List<DateRange> dateRanges) {
		this.getDateRangesAssistant().addDateRanges(relationshipObject, dateRanges);
		super.addDateRanges(relationshipObject, dateRanges, getDateRangeedDataRelationship(data));
	}

	public ImmutableList<DateRange> getDateRanges(T relationshipObject, O data) {
		return super.getDateRanges(relationshipObject, getDateRangeedDataRelationship(data));
	}
	
	public void compressDateRanges(T relationshipObject, O data)
	{
		compressDateRanges(relationshipObject, getDateRangeedDataRelationship(data));
	}

	public void deleteDateRanges(T relationshipObject, List<DateRange> dateRanges, O data)
	{
		this.getDateRangesAssistant().deleteDateRanges(relationshipObject, dateRanges);
		deleteDateRanges(relationshipObject, dateRanges, getDateRangeedDataRelationship(data));
	}

	public void deleteDateRange(T relationshipObject, DateRange dateRange, O data)
	{
		this.getDateRangesAssistant().deleteDateRange(relationshipObject, dateRange);
		deleteDateRange(relationshipObject, dateRange, getDateRangeedDataRelationship(data));
	}
	protected abstract DateRangesAssistant<T> getDateRangesAssistant();
}
