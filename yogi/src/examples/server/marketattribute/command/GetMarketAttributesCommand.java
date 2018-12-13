package examples.server.marketattribute.command;

import java.util.ArrayList;
import java.util.List;

import yogi.remote.client.app.CommandAdapter;

import examples.server.market.MarketManager;
import examples.server.marketattribute.MarketAttribute;
import examples.server.marketattribute.MarketAttributeManager;
import examples.server.marketattribute.MarketAttributeRecord;

public class GetMarketAttributesCommand extends CommandAdapter<List<MarketAttributeRecord>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6118857730147670914L;
	private String[] markets;
	public GetMarketAttributesCommand(String userId, String... markets) {
		super(userId);
		this.markets = markets;
	}

	@Override
	public List<MarketAttributeRecord> execute() {
		List<MarketAttributeRecord> rtnValue = new ArrayList<MarketAttributeRecord>();
		for(String market: markets){
			MarketAttribute marketAttribute = MarketAttributeManager.get().getMarketAttribute(MarketManager.get().getMarket(market));
			if(marketAttribute != null){
				rtnValue.add(new MarketAttributeRecord(marketAttribute));
			}
		}
		return rtnValue;
	}

}
