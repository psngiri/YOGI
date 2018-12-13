package yogi.base.io.db.mock;

import java.util.List;

import yogi.base.io.db.ObjectDbRecord;

public class MockDbRecord extends ObjectDbRecord{

	public MockDbRecord(List<?> objects) {
		super(objects);
	}

	public MockDbRecord(Object... objects) {
		super(objects);
	}
}
