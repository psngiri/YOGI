package yogi.server.gui.report.io.db.dump;


import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.base.util.JsonAssistant;
import yogi.server.action.ActionAssistant;
import yogi.server.gui.report.Report;


public class ReportDumpDbFormatter implements DbFormatter<Report> {
		private ObjectDbRecord dbRecord = new ObjectDbRecord(9);
		
		@Override
		public DbRecord format(Report record) {
			dbRecord.setObject(0, record.getKey().getUser().getId());
			dbRecord.setObject(1, record.getKey().getIdName());
			dbRecord.setObject(2, record.getKey().getPartition().getPartitionCode());
			dbRecord.setObject(3, record.getTimeStamp());
			dbRecord.setObject(4, record.getDescription());
			dbRecord.setObject(5, record.getComments());
			dbRecord.setObject(6, ActionAssistant.get().getActionCode(record.getAction()));
			dbRecord.setObject(7, JsonAssistant.get().toJson(record.getData()));
			dbRecord.setObject(8, record.getModifiedByUser().getId());
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