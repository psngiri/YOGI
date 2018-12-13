package yogi.versioning.version;

import yogi.base.Creator;

public class VersionCreator implements Creator<Version> {
	private String id;
	private Version parent;
	public Version create() {
		return new VersionImpl(id, parent);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Version getParent() {
		return parent;
	}
	public void setParent(Version parent) {
		this.parent = parent;
	}
}
