package yogi.period.interval.data;

import java.util.List;

import yogi.base.indexing.Indexer;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;

public abstract class IntervaledDataAssistant<T extends RelationshipObject, O extends Enum<O>> extends BaseIntervalsAssistant<T> {
	private Indexer<O, OneToOneSimpleRelationship<T, IntervalsObject>> relationships = new Indexer<O, OneToOneSimpleRelationship<T, IntervalsObject>>();
	protected abstract Class<T> getRelationshipObjectClass();
	
	private OneToOneSimpleRelationship<T, IntervalsObject> getIntervaledDataRelationship(O data)
	{
		OneToOneSimpleRelationship<T, IntervalsObject> oneToOneSimpleRelationship = relationships.get(data);
		if(oneToOneSimpleRelationship == null)
		{
			synchronized(relationships){
				oneToOneSimpleRelationship = createRelationship(data);
			}
		}
		return oneToOneSimpleRelationship;
	}

	private OneToOneSimpleRelationship<T, IntervalsObject> createRelationship(O data) {
		OneToOneSimpleRelationship<T, IntervalsObject> oneToOneSimpleRelationship = relationships.get(data);
		if(oneToOneSimpleRelationship == null)
		{
			oneToOneSimpleRelationship = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(getRelationshipObjectClass(), IntervalsObject.class, "" +data.name() +"IntervaledData");
			relationships.index(data, oneToOneSimpleRelationship);
		}
		return oneToOneSimpleRelationship;
	}

	public void addInterval(T relationshipObject, O data, Interval interval) {
		this.getIntervalsAssistant().addInterval(relationshipObject, interval);
		super.addInterval(relationshipObject, interval, getIntervaledDataRelationship(data));
	}

	public void addIntervals(T relationshipObject, O data, List<Interval> intervals) {
		this.getIntervalsAssistant().addIntervals(relationshipObject, intervals);
		super.addIntervals(relationshipObject, intervals, getIntervaledDataRelationship(data));
	}

	public ImmutableList<Interval> getIntervals(T relationshipObject, O data) {
		return super.getIntervals(relationshipObject, getIntervaledDataRelationship(data));
	}
	
	public void compressIntervals(T relationshipObject, O data)
	{
		compressIntervals(relationshipObject, getIntervaledDataRelationship(data));
	}
	public void orderlyCompressIntervals(T relationshipObject, O data)
	{
		orderlyCompressIntervals(relationshipObject, getIntervaledDataRelationship(data));
	}
	public void deleteIntervals(T relationshipObject, List<Interval> intervals, O data)
	{
		this.getIntervalsAssistant().deleteIntervals(relationshipObject, intervals);
		deleteIntervals(relationshipObject, intervals, getIntervaledDataRelationship(data));
	}

	public void deleteInterval(T relationshipObject, Interval interval, O data)
	{
		this.getIntervalsAssistant().deleteInterval(relationshipObject, interval);
		deleteInterval(relationshipObject, interval, getIntervaledDataRelationship(data));
	}
	protected abstract IntervalsAssistant<T> getIntervalsAssistant();
}
