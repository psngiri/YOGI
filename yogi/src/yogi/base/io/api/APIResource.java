package yogi.base.io.api;

import java.util.List;

import yogi.base.io.db.DbRecord;
import yogi.base.io.resource.Resource;

public interface APIResource extends Resource{
	List<DbRecord> getRecords() throws APIException;
	boolean writeRecords(List<DbRecord> records) throws APIException;
	int cleanUp() throws APIException;
}
