package yogi.versioning.data;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.versioning.transaction.Transaction;
import yogi.versioning.version.Version;

public class VersionedDataAssistant extends RelationshipAssistant<VersionedData> {
	private static VersionedDataAssistant versionedDataAssistant = new VersionedDataAssistant();
	private static OneToOneSimpleRelationship<VersionedData, Version> deletedAt = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(VersionedData.class, Version.class, "CreatedAt");
	private static OneToOneSimpleRelationship<VersionedData, Version> createdAt = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(VersionedData.class, Version.class, "DeletedAt");

	public static VersionedDataAssistant get() {
		return versionedDataAssistant;
	}
	
	public Version getDeletedAtVersion(VersionedData versionedData)
	{
		return this.getRelationship(versionedData, deletedAt);
	}
	
	Version setDeletedAtVersion(VersionedData versionedData)
	{
		Version version = Transaction.getTransaction().getVersion();
		this.setRelationship(versionedData, deletedAt, version);
		VersionedDataManager.get().buildDeletedAtRelationship(versionedData, version);
		return version;
	}
	
	public Version getCreatedAtVersion(VersionedData versionedData)
	{
		return this.getRelationship(versionedData, createdAt);
	}
	
	Version setCreatedAtVersion(VersionedData versionedData)
	{
		Version version = Transaction.getTransaction().getVersion();
		this.setRelationship(versionedData, createdAt, version);
		VersionedDataManager.get().buildCreatedAtRelationship(versionedData, version);
		return version;
	}
}
