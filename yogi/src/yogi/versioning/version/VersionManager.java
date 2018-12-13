package yogi.versioning.version;

import yogi.base.relationship.RelationshipManager;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;

public class VersionManager extends RelationshipManager<Version> {
	private static VersionManager versionManager = new VersionManager();
	private static OneToManyInverseRelationship<Version, Version> children = RelationshipTypeFactory.get().createOneToManyInverseRelationship(Version.class, Version.class, "Children");

	protected VersionManager() {
		super();
	}

	public static VersionManager get() {
		return versionManager;
	}

	@Override
	protected void buildRelationships(Version version) {
		this.buildRelationship(version.getParent(), version, children);
	}

	@Override
	protected void deleteRelationships(Version version) {
		this.deleteRelationship(version.getParent(), version, children);
	}
	
	public ImmutableList<Version> getChildren(Version version)
	{
		return this.getRelationship(version, children);
	}
}
