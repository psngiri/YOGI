package yogi.base.io.api;

import java.util.List;

import yogi.base.io.db.DbRecord;

public abstract class APIResourceAdapter implements APIResource {

	public List<DbRecord> getRecords() throws APIException {
		return null;
	}

	public boolean writeRecords(List<DbRecord> records)
			throws APIException {
		return false;
	}

	public int cleanUp() throws APIException {
		return 0;
	}

}
