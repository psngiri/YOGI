package yogi.server.gui.report.key.io.db.dump;


import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.server.gui.report.key.ReportKey;


public class ReportKeyDumpDbFormatter  implements DbFormatter<ReportKey> {
	protected ObjectDbRecord dbRecord = new ObjectDbRecord(4);
	
	@Override
	public DbRecord format(ReportKey record) {
		dbRecord.setObject(0, record.getUser().getId());
		dbRecord.setObject(1, record.getIdName());
		dbRecord.setObject(2, record.getCreateDate());
		dbRecord.setObject(3, record.getPartition().getPartitionCode());
		return 	dbRecord;	
	}
	public String query() {		
		QueryReader queryReader = new QueryReader(new ClassPathResource("insertQuery.txt", this.getClass()));
		return queryReader.read();
	}

	public String cleanUpQuery() {
       	return null;		
	}
}