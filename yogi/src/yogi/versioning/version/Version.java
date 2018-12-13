package yogi.versioning.version;

import java.util.Set;

import yogi.base.relationship.RelationshipObject;

public interface Version extends RelationshipObject {
	Version getParent();
	String getID();
	Set<Version> getPath();
	boolean isInPath(Version version);
}
