package yogi.tools.builder.template.bto.io.db;

import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.tools.builder.template.bto.BtoTemplate;

public class BtoTemplateDbFormatter implements DbFormatter<BtoTemplate> {
	private ObjectDbRecord dbRecord = new ObjectDbRecord(0);

	public String query() {		
		return new QueryReader(new ClassPathResource("insertQuery.txt", this.getClass())).read();
	}

	public String cleanUpQuery() {
		return new QueryReader(new ClassPathResource("cleanUpQuery.txt", this.getClass())).read();
	}

	public DbRecord format(BtoTemplate BtoTemplate) {
		dbRecord.clear();
		return dbRecord;
	}

}
