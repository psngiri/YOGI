package examples.server.marketattribute.io.db.incremental;

import java.util.Collection;

import yogi.base.app.TimeWindowPauseLoopChecker;
import yogi.base.io.db.DbRecord;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.base.io.db.BaseRecordIncrementalDbReader;

import examples.server.market.Market;
import examples.server.marketattribute.MarketAttribute;
import examples.server.marketattribute.MarketAttributeCreator;
import examples.server.marketattribute.MarketAttributeFactory;
import examples.server.marketattribute.MarketAttributeManager;
import examples.server.marketattribute.MarketAttributeValidator;
import examples.server.marketattribute.io.db.MarketAttributeDbRecordSelector;
import examples.server.marketattribute.io.db.MarketAttributeDbScanner;
import examples.server.marketattribute.io.db.MarketAttributeLinkGenerator;


public class MarketAttributeIncrementalDbReader extends BaseRecordIncrementalDbReader<Market, MarketAttribute, MarketAttributeCreator> {
	
	public static boolean RUN = true;
	
	public MarketAttributeIncrementalDbReader(Collection<DbRecord> dbRecords, TimeWindowPauseLoopChecker loopChecker) {
		super(dbRecords, new MarketAttributeValidator(), new MarketAttributeDbScanner(), MarketAttributeFactory.get(), MarketAttributeManager.get(), new MarketAttributeDbRecordSelector(), null, new MarketAttributeLinkGenerator(), loopChecker);
	}

	public MarketAttributeIncrementalDbReader(DbResource resource, TimeWindowPauseLoopChecker loopChecker) {
		super(resource, new MarketAttributeValidator(), new MarketAttributeDbScanner(), MarketAttributeFactory.get(), MarketAttributeManager.get(), new MarketAttributeDbRecordSelector(), null, new MarketAttributeLinkGenerator(), loopChecker);
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