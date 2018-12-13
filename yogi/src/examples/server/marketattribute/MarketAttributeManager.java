package examples.server.marketattribute;

import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.record.base.BaseRecordManager;

import examples.server.market.Market;
import examples.server.market.MarketManager;

public class MarketAttributeManager extends BaseRecordManager<Market, MarketAttribute> {
	private static MarketAttributeManager itsInstance = new MarketAttributeManager();
	private static OneToManyInverseRelationship<Market, MarketAttribute> marketAttributeRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(Market.class,MarketAttribute.class, "MarketAttributes");
	
	protected MarketAttributeManager() {
		super();
	}

	public static MarketAttributeManager get() {
		return itsInstance;
	}

	public MarketAttribute getMarketAttribute(Market market) {
		return this.getLatestRecord(market);
	}

	@Override
	protected OneToManyInverseRelationship<Market, MarketAttribute> getKeyToRecordRelationShip() {
		return marketAttributeRel;
	}

	public void purge() {
		purge(MarketManager.get(), MarketAttributeFactory.get());
	}
}
