package examples.server.marketattribute.command;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.remote.client.app.CommandAdapter;

import examples.server.market.Market;
import examples.server.market.MarketManager;
import examples.server.marketattribute.MarketAttribute;
import examples.server.marketattribute.MarketAttributeManager;
import examples.server.marketattribute.MarketAttributeRecord;

public class GetMarketAttributesHistoryCommand extends CommandAdapter<List<MarketAttributeRecord>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6118857730147670914L;
	private String market;
	public GetMarketAttributesHistoryCommand(String userId, String market) {
		super(userId);
		this.market = market;
	}

	@Override
	public List<MarketAttributeRecord> execute() {
		List<MarketAttributeRecord> rtnValue = new ArrayList<MarketAttributeRecord>();
		Market marketObject = MarketManager.get().getMarket(market);
		ImmutableList<MarketAttribute> marketAttributes = MarketAttributeManager.get().getRecords(marketObject);
		for(MarketAttribute marketAttribute: marketAttributes){
			rtnValue.add(new MarketAttributeRecord(marketAttribute));
		}
		return rtnValue;
	}

}
