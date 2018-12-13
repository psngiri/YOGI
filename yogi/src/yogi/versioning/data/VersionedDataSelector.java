package yogi.versioning.data;

import yogi.base.Selector;
import yogi.versioning.version.Version;

public class VersionedDataSelector implements Selector<VersionedData> {
	private Version version;

	protected VersionedDataSelector(Version version) {
		super();
		this.version = version;
	}

	public boolean select(VersionedData item) {
		Version createdAtVersion = VersionedDataAssistant.get().getCreatedAtVersion(item);
		if(!version.isInPath(createdAtVersion)) return false;
		Version deletedAtVersion = VersionedDataAssistant.get().getDeletedAtVersion(item);
		if(!version.isInPath(deletedAtVersion)) return true;
		return false;
	}

}
