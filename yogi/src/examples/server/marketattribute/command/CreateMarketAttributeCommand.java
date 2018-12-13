package examples.server.marketattribute.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.action.ActionAssistant;
import yogi.server.gui.user.UserManager;

import examples.server.market.MarketManager;
import examples.server.marketattribute.MarketAttribute;
import examples.server.marketattribute.MarketAttributeCreator;
import examples.server.marketattribute.MarketAttributeFactory;
import examples.server.marketattribute.MarketAttributeManager;
import examples.server.marketattribute.MarketAttributeRecord;
import examples.server.marketattribute.MarketAttributeValidator;

public class CreateMarketAttributeCommand extends CommandAdapter<MarketAttributeRecord> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6118857730147670914L;
	private int action;
	private String market;
	private String attribute;
	
	public CreateMarketAttributeCommand(String userId, int action, String market, String attribute) {
		super(userId);
		this.action = action;
		this.market = market;
		this.attribute = attribute;
	}

	@Override
	public MarketAttributeRecord execute() {
		if(action == 0) action = 2;
		MarketAttribute marketAttribute = MarketAttributeManager.get().getMarketAttribute(MarketManager.get().getMarket(market));
		if(marketAttribute != null && action == 2) action = 3;
		long timeStamp = System.currentTimeMillis();
		String modifiedByUserId = this.getUserId();
		MarketAttributeCreator creator = new MarketAttributeCreator();
		creator.setAction(ActionAssistant.get().getAction(action));
		creator.setTimeStamp(timeStamp);
		creator.setModifiedByUser(UserManager.get().getUser(modifiedByUserId));
		creator.setMarket(market);
		creator.setAttribute(attribute);
		MarketAttribute create = MarketAttributeFactory.get().create(creator, new MarketAttributeValidator());
		if(create != null){
			return new MarketAttributeRecord(create);
		}
		return null;
	}

}
