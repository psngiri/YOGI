package yogi.tools.builder.template.bto.io.db;

import yogi.base.Selector;
import yogi.base.io.db.DbRecord;


public class BtoTemplateDbRecordSelector implements Selector<DbRecord> {

	public boolean select(DbRecord dbRecord) {
		return true;
	}

}
