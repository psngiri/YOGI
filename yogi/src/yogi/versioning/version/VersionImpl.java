package yogi.versioning.version;

import java.util.HashSet;
import java.util.Set;

import yogi.base.relationship.RelationshipObjectImpl;


public class VersionImpl extends RelationshipObjectImpl<Version> implements Version {
	private String id;
	private Version parent;
	private Set<Version> path;
	protected VersionImpl(String id, Version parent) {
		super();
		this.id = id;
		this.parent = parent;
		setPath();
	}

	private void setPath() {
		if(parent != null) path = new HashSet<Version>(parent.getPath());
		else path = new HashSet<Version>();
		path.add(this);
	}

	public Set<Version> getPath() {
		return path;
	}

	public String getID() {
		return id;
	}

	public Version getParent() {
		return parent;
	}

	public boolean isInPath(Version version) {
		return path.contains(version);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
