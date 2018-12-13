package yogi.versioning.data;

import yogi.base.Implementation;
import yogi.base.indexing.Indexer;
import yogi.base.relationship.SpecialRelationshipManager;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;
import yogi.versioning.version.Version;

public class VersionedDataManager extends SpecialRelationshipManager<VersionedData> {
	private static VersionedDataManager versionedDataManager =  new VersionedDataManager();
	private Indexer<Class, OneToManyInverseRelationship> createdAtRelationshipMap = new Indexer<Class, OneToManyInverseRelationship>();
	private Indexer<Class, OneToManyInverseRelationship> deletedAtRelationshipMap = new Indexer<Class, OneToManyInverseRelationship>();
	protected VersionedDataManager() {
		super();
	}

	public static VersionedDataManager get() {
		return versionedDataManager;
	}
	
	private <T extends VersionedData>OneToManyInverseRelationship<Version, T> getCreatedAtRelationship(T versionedData)
	{
		return getRelationship(versionedData, createdAtRelationshipMap, "CreatedAt");
	}

	@SuppressWarnings("unchecked")
	private <T> OneToManyInverseRelationship<Version, T> getRelationship(T versionedData, Indexer<Class, OneToManyInverseRelationship> indexer, String type) {
		Class<T> versionedDataClass = Implementation.getInterface(versionedData);
		OneToManyInverseRelationship<Version, T> oneToManyInverseRelationship = indexer.get(versionedDataClass);
		if(oneToManyInverseRelationship == null)
		{
			oneToManyInverseRelationship = RelationshipTypeFactory.get().createOneToManyInverseRelationship(Version.class, versionedDataClass, type);
			indexer.index(versionedDataClass, oneToManyInverseRelationship);
		}
		return oneToManyInverseRelationship;
	}
	
	private <T extends VersionedData>OneToManyInverseRelationship<Version, T> getDeletedAtRelationship(T versionedData)
	{
		return getRelationship(versionedData, deletedAtRelationshipMap, "DeletedAt");
	}
	

	void buildCreatedAtRelationship(VersionedData versionedData, Version version)
	{
		buildRelationship(version, versionedData, getCreatedAtRelationship(versionedData));
	}
	
	void buildDeletedAtRelationship(VersionedData versionedData, Version version)
	{
		buildRelationship(version, versionedData, getDeletedAtRelationship(versionedData));
	}
	
	@SuppressWarnings("unchecked")
	public <T extends VersionedData> ImmutableList<T> getVersionedDataCreatedAt(Version version, Class<T> versionedDataClass)
	{
		OneToManyInverseRelationship<Version, VersionedData> oneToManyInverseRelationship = createdAtRelationshipMap.get(versionedDataClass);
		ImmutableList<VersionedData> returnValue = this.getRelationship(version, oneToManyInverseRelationship);
		return (ImmutableList<T>) returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends VersionedData> ImmutableList<T> getVersionedDataDeletedAt(Version version, Class<T> versionedDataClass)
	{
		OneToManyInverseRelationship<Version, VersionedData> oneToManyInverseRelationship = deletedAtRelationshipMap.get(versionedDataClass);
		ImmutableList<VersionedData> returnValue = this.getRelationship(version, oneToManyInverseRelationship);
		return (ImmutableList<T>) returnValue;
	}
}
