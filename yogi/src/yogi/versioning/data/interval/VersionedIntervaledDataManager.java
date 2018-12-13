package yogi.versioning.data.interval;

import java.util.List;

import yogi.base.Implementation;
import yogi.base.indexing.Indexer;
import yogi.base.relationship.SpecialRelationshipManager;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.period.interval.data.IntervaledData;
import yogi.versioning.transaction.Transaction;
import yogi.versioning.version.Version;

public class VersionedIntervaledDataManager extends SpecialRelationshipManager<VersionedInterval> {
	private static VersionedIntervaledDataManager versionedIntervaledDataManager =  new VersionedIntervaledDataManager();
	private Indexer<Class, OneToManyInverseRelationship> addedIntervalsRelationshipMap = new Indexer<Class, OneToManyInverseRelationship>();
	private Indexer<Class, OneToManyInverseRelationship> deletedIntervalsRelationshipMap = new Indexer<Class, OneToManyInverseRelationship>();
	private Indexer<Class, OneToManyInverseRelationship> createdAtRelationshipMap = new Indexer<Class, OneToManyInverseRelationship>();
	private Indexer<Class, OneToManyInverseRelationship> deletedAtRelationshipMap = new Indexer<Class, OneToManyInverseRelationship>();

	protected VersionedIntervaledDataManager() {
		super();
	}

	public static VersionedIntervaledDataManager get() {
		return versionedIntervaledDataManager;
	}
	
	private OneToManyInverseRelationship<Version, VersionedInterval> getCreatedAtRelationship(RelationshipObject data)
	{
		return getVersionRelationship(data, createdAtRelationshipMap, "CreatedAt");
	}

	@SuppressWarnings("unchecked")
	private OneToManyInverseRelationship<Version, VersionedInterval> getVersionRelationship(RelationshipObject data, Indexer<Class, OneToManyInverseRelationship> indexer, String type) {
		Class dataClass = Implementation.getInterface(data);
		OneToManyInverseRelationship<Version, VersionedInterval> oneToManyInverseRelationship = indexer.get(dataClass);
		if(oneToManyInverseRelationship == null)
		{
			oneToManyInverseRelationship = RelationshipTypeFactory.get().createOneToManyInverseRelationship(Version.class, VersionedInterval.class, dataClass.getName() + type);
			indexer.index(dataClass, oneToManyInverseRelationship);
		}
		return oneToManyInverseRelationship;
	}
	
	private OneToManyInverseRelationship<Version, VersionedInterval> getDeletedAtRelationship(RelationshipObject versionedInterval)
	{
		return getVersionRelationship(versionedInterval, deletedAtRelationshipMap, "DeletedAt");
	}
	
	private <T extends RelationshipObject>OneToManyInverseRelationship<T, VersionedInterval> getAddedIntervalsRelationship(T dataObject)
	{
		return getRelationship(dataObject, addedIntervalsRelationshipMap, "AddedIntervals");
	}

	private <T extends RelationshipObject>OneToManyInverseRelationship<T, VersionedInterval> getDeletedIntervalsRelationship(T dataObject)
	{
		return getRelationship(dataObject, deletedIntervalsRelationshipMap, "DeletedIntervals");
	}

	@SuppressWarnings("unchecked")
	private <T extends RelationshipObject> OneToManyInverseRelationship<T, VersionedInterval> getRelationship(T dataObject, Indexer<Class, OneToManyInverseRelationship> indexer, String type) {
		Class<T> dataObjectClass = Implementation.getInterface(dataObject);
		OneToManyInverseRelationship<T, VersionedInterval> oneToManyInverseRelationship = indexer.get(dataObjectClass);
		if(oneToManyInverseRelationship == null)
		{
			oneToManyInverseRelationship = RelationshipTypeFactory.get().createOneToManyInverseRelationship(dataObjectClass, VersionedInterval.class, type);
			indexer.index(dataObjectClass, oneToManyInverseRelationship);
		}
		return oneToManyInverseRelationship;
	}
	
	public <T extends RelationshipObject> ImmutableList<Interval> getIntervals(T dataObject, Version version)
	{
		ImmutableList<VersionedInterval> addedVersionedIntervals = this.getRelationship(dataObject, getAddedIntervalsRelationship(dataObject));
		ImmutableList<VersionedInterval> deletedVersionedIntervals = this.getRelationship(dataObject, getDeletedIntervalsRelationship(dataObject));
		addedVersionedIntervals.isEmpty();
		deletedVersionedIntervals.isEmpty();
		List<Interval> addedIntervals = null;
		List<Interval> deletedIntervals = null;
		return new ImmutableList<Interval>(Intervals.subtract(addedIntervals, deletedIntervals));
	}
	
	public <T extends RelationshipObject> void addInterval(T dataObject, Interval interval)
	{
		VersionedInterval<T> versionedInterval = createVersionedData(dataObject, interval, IntervalType.ADDED);
		buildAddedRelationships(dataObject, versionedInterval);
	}

	private <T extends RelationshipObject> void buildAddedRelationships(T dataObject, VersionedInterval versionedInterval) {
		buildRelationship(dataObject, versionedInterval, getAddedIntervalsRelationship(dataObject));
		buildRelationship(versionedInterval.getVersion(), versionedInterval, getCreatedAtRelationship(versionedInterval.getData()));
	}

	private <T extends RelationshipObject> VersionedInterval<T> createVersionedData(T dataObject, Interval interval, IntervalType intervalType) {
		return new VersionedInterval<T>(dataObject, interval, Transaction.getTransaction().getVersion(), intervalType);
	}
	
	private <T extends RelationshipObject> VersionedInterval<T> createVersionedData(T dataObject, List<Interval> intervals, IntervalType intervalType) {
		return new VersionedInterval<T>(dataObject, intervals, Transaction.getTransaction().getVersion(), intervalType);
	}
	
	public <T extends RelationshipObject> void deleteInterval(T dataObject, Interval interval)
	{
		VersionedInterval<T> versionedInterval = createVersionedData(dataObject, interval, IntervalType.DELETED);
		buildDeletedRelationships(dataObject, versionedInterval);
	}

	private <T extends RelationshipObject> void buildDeletedRelationships(T dataObject, VersionedInterval versionedInterval) {
		deleteRelationship(dataObject, versionedInterval, getAddedIntervalsRelationship(dataObject));
		buildRelationship(versionedInterval.getVersion(), versionedInterval, getDeletedAtRelationship(versionedInterval.getData()));
	}
	
	public <T extends RelationshipObject> void addIntervals(T dataObject, List<Interval> intervals)
	{
		VersionedInterval<T> versionedInterval = createVersionedData(dataObject, intervals, IntervalType.ADDED);
		buildAddedRelationships(dataObject, versionedInterval);
	}
	
	public <T extends RelationshipObject> void deleteIntervals(T dataObject, List<Interval> intervals)
	{
		VersionedInterval<T> versionedInterval = createVersionedData(dataObject, intervals, IntervalType.DELETED);
		buildDeletedRelationships(dataObject, versionedInterval);
	}
	
	public <T extends RelationshipObject> ImmutableList<IntervaledData<T>> getIntervaledData(Version version, Class<T> dataClass)
	{
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T extends RelationshipObject> ImmutableList<VersionedInterval> getIntervaledDataCreatedAt(Version version, Class<T> dataClass)
	{
		OneToManyInverseRelationship<Version, VersionedInterval> oneToManyInverseRelationship = createdAtRelationshipMap.get(dataClass);
		return this.getRelationship(version, oneToManyInverseRelationship);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends RelationshipObject> ImmutableList<VersionedInterval> getIntervaledDataDeletedAt(Version version, Class<T> dataClass)
	{
		OneToManyInverseRelationship<Version, VersionedInterval> oneToManyInverseRelationship = deletedAtRelationshipMap.get(dataClass);
		return this.getRelationship(version, oneToManyInverseRelationship);
	}
}
