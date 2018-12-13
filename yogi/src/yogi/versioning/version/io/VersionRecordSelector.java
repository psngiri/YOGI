package yogi.versioning.version.io;

import yogi.base.Selector;


public class VersionRecordSelector implements Selector<String> {

	public boolean select(String record) {
		return true;
	}

}
