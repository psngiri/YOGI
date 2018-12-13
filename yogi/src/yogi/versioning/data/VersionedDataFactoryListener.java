package yogi.versioning.data;

import yogi.base.FactoryListener;

public class VersionedDataFactoryListener implements FactoryListener<VersionedData> {

	public void add(VersionedData versionedData) {
		VersionedDataAssistant.get().setCreatedAtVersion(versionedData);
	}

	public boolean delete(VersionedData versionedData) {
		VersionedDataAssistant.get().setDeletedAtVersion(versionedData);
		return false;
	}

	public boolean clearAll() {
		return false;		
	}

}
