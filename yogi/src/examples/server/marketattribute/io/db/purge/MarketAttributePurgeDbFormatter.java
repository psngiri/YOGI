package examples.server.marketattribute.io.db.purge;

import yogi.base.io.db.DbRecord;
import yogi.server.gui.record.base.purge.db.BaseRecordPurgeDbFormatter;

import examples.server.marketattribute.MarketAttribute;

public class MarketAttributePurgeDbFormatter extends BaseRecordPurgeDbFormatter<MarketAttribute> {
	
	public MarketAttributePurgeDbFormatter() {
		super(5);
	}
	
	@Override
	public DbRecord format(MarketAttribute record) {
		super.format(record);
		dbRecord.setObject(1, record.getKey().getMarket());
		return dbRecord;
	}

	@Override
	protected String getTableType() {
		return "TableType";
	}

}
