package examples.server.marketattribute.io.db;

import java.util.Collection;

import yogi.base.io.db.DbRecord;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.base.io.db.BaseRecordDbReader;

import examples.server.market.Market;
import examples.server.marketattribute.MarketAttribute;
import examples.server.marketattribute.MarketAttributeCreator;
import examples.server.marketattribute.MarketAttributeFactory;
import examples.server.marketattribute.MarketAttributeValidator;


public class MarketAttributeDbReader extends BaseRecordDbReader<Market, MarketAttribute, MarketAttributeCreator> {
	
	public static boolean RUN = true;
	
	public MarketAttributeDbReader(Collection<DbRecord> dbRecords) {
		super(dbRecords, new MarketAttributeValidator(), new MarketAttributeDbScanner(), MarketAttributeFactory.get(), new MarketAttributeDbRecordSelector(), null, new MarketAttributeLinkGenerator());
	}

	public MarketAttributeDbReader(DbResource resource) {
		super(resource, new MarketAttributeValidator(), new MarketAttributeDbScanner(), MarketAttributeFactory.get(), new MarketAttributeDbRecordSelector(), null, new MarketAttributeLinkGenerator());
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	protected String getTableType() {
		return "TableType";
	}

}