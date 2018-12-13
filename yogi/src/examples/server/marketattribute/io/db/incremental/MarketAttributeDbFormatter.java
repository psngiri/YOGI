package examples.server.marketattribute.io.db.incremental;


import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.ObjectDbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.server.action.ActionAssistant;

import examples.server.marketattribute.MarketAttribute;


public class MarketAttributeDbFormatter implements DbFormatter<MarketAttribute> {
//	public boolean flag = true;
	private ObjectDbRecord dbRecord = new ObjectDbRecord(5);
	
	public MarketAttributeDbFormatter() {
	}

	@Override
	public DbRecord format(MarketAttribute record) {
//		if(flag){
//			flag = false;
//			throw new RuntimeException("error");
//		}
		dbRecord.setObject(0, ActionAssistant.get().getActionCode(record.getAction()));
		dbRecord.setObject(1, record.getTimeStamp());
		dbRecord.setObject(2, record.getModifiedByUser().getId());
		dbRecord.setObject(3, record.getMarket().getMarket());
		dbRecord.setObject(4, record.getAttribute());
		return dbRecord;
	}

	public String query() {		
		QueryReader queryReader = new QueryReader(new ClassPathResource("insertQuery.txt", this.getClass()));
		queryReader.addVariable("TableName", "TableType");
		return queryReader.read();
	}

	public String cleanUpQuery() {
       	return null;		
	}
}