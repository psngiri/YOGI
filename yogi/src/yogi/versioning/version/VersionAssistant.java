package yogi.versioning.version;

import yogi.base.relationship.RelationshipAssistant;

public class VersionAssistant extends RelationshipAssistant<Version> {
	private static VersionAssistant versionAssistant = new VersionAssistant();

	public static VersionAssistant get() {
		return versionAssistant;
	}
}
